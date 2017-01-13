import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by li on 2017/1/13.
  */
object RddCassandra {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf()
      .setMaster("local")
      .setAppName("RddCassandra")
      .set("spark.cassandra.connection.host", "127.0.0.1")

    val sc = new SparkContext(conf)

    val file = sc.textFile("E:\\bus_data.csv")
    val rdd = file.flatMap(row => row.split(","))

    import com.datastax.spark.connector._

    rdd.saveToCassandra("analysis", "bus_data")

  }

}

case class busData(CARDID: String, LINEID: Int, BEGINTIME: BigInt, ENDTIME: BigInt, STATIONFROM: Int, STATIONTO: Int, BUSID: Int)
