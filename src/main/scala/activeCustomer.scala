import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

/**
  * Created by li on 2017/1/20.
  */
object activeCustomer {
  def main(args: Array[String]): Unit = {

    val cassandraHost = "192.168.1.88"

    val conf = new SparkConf(true)
      .set("spark.cassandra.connection.host", cassandraHost)

    val ss = SparkSession.builder().config(conf)
      .appName("activeCustomer")
      .master("local")
      .getOrCreate()

    val df = ss
      .read
      .format("org.apache.spark.sql.cassandra")
  }

}
