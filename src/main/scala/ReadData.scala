import org.apache.commons.httpclient.util.DateUtil
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import utils.DateUtils

/**
  * Created by li on 2017/1/19.
  */
object ReadData {

  def main(args: Array[String]): Unit = {

    val cassandraHost = "192.168.1.88"
//    val nowDate = new DateUtils()
    val conf = new SparkConf()
      .set("spark.cassandra.connection.host", cassandraHost)

    val ss = SparkSession
      .builder()
      .config(conf)
      .master("local")
      .appName("ReadData")
      .getOrCreate()
    val month = 10
    val df = ss
      .read
      .format("org.apache.spark.sql.cassandra")
      .options(Map("keyspace" -> "nirvana", "table" -> "app_profile_test"))
      .load()
      .where("weekofyear(time)= " + month)

    df.show()

//    df.createOrReplaceTempView("app_profile")
//
//    val result = ss
//      .sql("select app_channel,app_gps,app_key,app_name,time,count(device_id) " +
//        "from ( " +
//        "select app_channel,app_gps,app_key,app_name,device_id,time, " +
//        "row_number() over(partition by device_id order by time desc) rn " +
//        "from app_profile) a " +
//        "where rn = 1 " +
//        "group by app_channel,app_gps,app_key,app_name,time")
//    result.show()

    ss.stop()
  }
}
