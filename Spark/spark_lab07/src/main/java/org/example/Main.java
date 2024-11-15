package org.example;
import org.apache.spark.ml.recommendation.ALS;
import org.apache.spark.ml.recommendation.ALSModel;
import org.apache.spark.mllib.recommendation.MatrixFactorizationModel;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.SparkSession;

import static org.apache.spark.sql.functions.*;

public class Main {
    public static void main(String[] args) {
        SparkSession spark = SparkSession
                .builder()
                .master("spark://hadoop1:7077")
                .appName("spark-lab-7")
                .getOrCreate();

        Dataset<Row> ratings = spark.read()
                .option("header", true)
                .option("inferSchema", true)
                .csv("hdfs:///students/st44317/ml-latest/ratings.csv");

        Dataset<Row> movies = spark.read()
                .option("header", true)
                .option("inferSchema", true)
                .csv("hdfs:///students/st44317/ml-latest/movies.csv");

        Dataset<Row> moviesRatings = ratings.join(movies, "movieId");

        Dataset<Row> moviesCount = moviesRatings
                .groupBy("movieId")
                .count()
                .sort("movieId");

        moviesCount.show();

        ALS als = new ALS()
                .setRank(10)
                .setMaxIter(10)
                .setUserCol("userId")
                .setItemCol("movieId");

        ALSModel model = als.fit(ratings);

        Dataset<Row> recommendsTable = model.recommendForAllItems(3);
        recommendsTable.show(false);

        Dataset<Row> recommendsWithCount = recommendsTable.join(moviesCount, "movieId");
        recommendsWithCount.show(false);

        Dataset<Row> preFinalRecommends = recommendsWithCount
                .filter("count > 10")
                .sort(asc("count"));

        Dataset<Row> finalRecommends = preFinalRecommends.join(movies, "movieId");

        Dataset<Row> finalRecommends2 = finalRecommends
                .select(
                        col("movieId"),
                        col("recommendations").getItem(0).getField("userId").as("userId"),
                        col("title"))
                .join(moviesCount, "movieId")  // Dodaj to, aby dołączyć kolumnę count
                .sort(desc("count"));

        finalRecommends2
                .write()
                .json("hdfs:///students/st44317/ml-latest/results/");
    }
}
