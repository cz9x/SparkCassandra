import org.apache.spark.sql.{SQLContext, SparkSession}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by li on 2017/1/13.
  */
object TestConnection {

  val cassandraHost = "127.0.0.1"

  def useRDD() = {

    val conf = new SparkConf(true)
      .set("spark.cassandra.connection.host", cassandraHost)
      .setMaster("local[2]")
      .setAppName("TestConnection")

    val sc = new SparkContext(conf)

    import com.datastax.spark.connector._

    val rdd = sc.cassandraTable("test", "key_value").select("key", "value")
    rdd.foreach(row => println(row.toString()))

    sc.stop()
  }

  def useDF() = {

    val conf = new SparkConf(true)
      .set("spark.cassandra.connection.host", cassandraHost)
      .setMaster("local[2]")
      .setAppName("TestConnection")

    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    val df = sqlContext
      .read
      .format("org.apache.spark.sql.cassandra")
      .options(Map("keyspace" -> "test", "table" -> "key_value"))
      .load()

    println(df.show())

    sc.stop()
  }

  def useDS ={

    val conf = new SparkConf().set("spark.cassandra.connection.host", cassandraHost)

    val sparkSession = SparkSession.builder().config(conf)
      .master("local[2]")
      .appName("TestConnection")
      .getOrCreate()

    val ds = sparkSession
      .read
      .format("org.apache.spark.sql.cassandra")
      .options(Map("keyspace" -> "test", "table" -> "key_value"))
      .load()

    println(ds.show())

    sparkSession.stop()
  }


  def main(args: Array[String]): Unit = {

    useDS

  }

}
