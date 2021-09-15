package minsait.ttaa.datio;

import minsait.ttaa.datio.engine.Transformer;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.StructType;

import static minsait.ttaa.datio.common.Common.SPARK_MODE;

public class Runner {
    static SparkSession spark = SparkSession
            .builder()
            .master(SPARK_MODE)
            .getOrCreate();
    .;

    public static <String> void main(String... args) {
        Transformer engine = new Transformer(spark);
        val arrayStructureSchema = new StructType()
                .add("short_name", stringtype())
                .add("long_name",  stringtype())
                .add("age",stringtype())
                .add("height_cm", stringtype())
                .add("weight_kg", stringtype())
                .add("nationality", stringtype())
                .add("club_name", stringtype())
                .add("overall", stringtype())
                .add("potential",stringtype())
                .add("team_position", stringType());

        val df = spark.read().csv("src/test/resources/data/players_21.csv")
                .printSchema();

        val df1 = spark.read().csv("src/test/resources/data/players_21.csv").toDF().filter("");
        val df2 = SparkSession.read().option("header",true).csv("src/test/resources/data/players_21.csv")
                .select("short_name, long_name, age, height_cm, weight_kg, nationality, club_name, overall, potential, team_position")
                .withColumn("age_range",when(col("age") < 23, "***A***")
                .when(col("age") >= 23 && col("age") < 27), "***B***")
                .when(col("age") >= 27 && col("age") < 32, "***C***")
                .when(col("age") >= 32 , "***D***")
                .show();

        val df4 = SparkSession.read().option("header",true).csv("src/test/resources/data/players_21.csv")
                .select("short_name, long_name, age, height_cm, weight_kg, nationality, club_name, overall, potential, team_position")
                .withColumn("potential_vs_overall", col("potential") / col("overall"))
                .show();
    }

}
