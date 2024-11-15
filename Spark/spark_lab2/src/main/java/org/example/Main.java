package org.example;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

public class Main {
    public static void main(String[] args) {
        SparkConf sparkConf = new SparkConf()
                .setAppName("spark_zad3")
                .setMaster("local");

        JavaSparkContext sc = new JavaSparkContext(sparkConf);

        JavaRDD<String> ratingsData = sc.textFile("ratings.csv")
                .filter(r -> !r.contains("movieId") && !r.isEmpty());

        JavaRDD<String> moviesData = sc.textFile("movies.csv")
                .filter(r -> !r.contains("movieId") && !r.isEmpty());

        JavaPairRDD<Integer, Double> moviesRatings = ratingsData.mapToPair(r -> {
            String[] data = r.split(",");
            int movieId = Integer.parseInt(data[1]);
            double rating = Double.parseDouble(data[2]);
            return new Tuple2<>(movieId, rating);
        });

        JavaPairRDD<Integer, String> moviesTitles = moviesData.mapToPair(r -> {
            String[] data = r.split(",");
            int movieId = Integer.parseInt(data[0]);
            String title = data[1];
            return new Tuple2<>(movieId, title);
        });

        JavaPairRDD<Integer, Integer> ratingsCount = moviesRatings
                .mapToPair(tuple -> new Tuple2<>(tuple._1, 1))
                .reduceByKey(Integer::sum);

        JavaPairRDD<Integer, Double> ratingsSum = moviesRatings
                .reduceByKey(Double::sum);

        JavaPairRDD<Integer, Double> averageRatings = ratingsSum.join(ratingsCount)
                .mapValues(tuple -> tuple._1 / tuple._2);

        JavaPairRDD<Integer, Tuple2<Double, String>> result = averageRatings.join(moviesTitles)
                .mapToPair(tuple -> new Tuple2<>(tuple._1, tuple._2));

        JavaPairRDD<Double, String> sortedRatingsResult = result
                .mapToPair(tuple -> new Tuple2<>(tuple._2._1, tuple._2._2))
                .sortByKey(false);

        sortedRatingsResult.foreach(tuple -> System.out.println(tuple._1 + " " + tuple._2));
        sc.stop();
    }
}