import org.apache.spark.sql.{SQLContext, SparkSession}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by li on 2017/1/13.
  */
object SparkCSV {

  def main(args: Array[String]): Unit = {
    val cassandraHost = "127.0.0.1"

    val conf = new SparkConf().set("spark.cassandra.connection.host", cassandraHost)

    val session = SparkSession.builder().config(conf)
      .master("local")
      .appName("SparkCSV")
      .getOrCreate()

//    import com.datastax.spark.connector._

    val df = session.read
      .format("com.databricks.spark.csv")
      .option("header", "true")
      .load("E:\\bus_data.csv")
//    println(df.schema)

    println(df.show())

    df.write
      .format("org.apache.spark.sql.cassandra")
      .options(Map("keyspace" -> "analysis", "table" -> "bus_data"))
      .save()

    session.stop()
  }

}
