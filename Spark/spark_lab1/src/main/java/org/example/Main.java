package org.example;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf()
                .setAppName("my-spark-word-counter")
                .setMaster("spark://hadoop1:7077");

        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> lines = sc.textFile("dziewczynka-z-zapalkami.txt");

        JavaRDD<String> words = lines.flatMap(line -> Arrays.asList(line.split("[ ,;\\.]")).iterator());
        JavaPairRDD<String, Integer> pairs = words.mapToPair(word -> new Tuple2<>(word, 1));
        JavaPairRDD<String, Integer> counts = pairs.reduceByKey((a,b) -> a + b);
        counts.foreach(el -> System.out.println(el));
    }
}