import org.apache.spark.sql.{SQLContext, SparkSession}
import org.apache.spark.{SparkConf, SparkContext}


/**
  * Created by li on 2017/1/13.
  */
object SparkCSV {

  val cassandraHost = "127.0.0.1"
  val file = "bus_data2.csv"

  def DStoCassandra = {

    val conf = new SparkConf().set("spark.cassandra.connection.host", cassandraHost)

    val session = SparkSession.builder().config(conf)
      .master("local")
      .appName("SparkCSV")
      .getOrCreate()

    import com.datastax.spark.connector._

    val df = session.read
      .format("com.databricks.spark.csv")
      .option("header", "true")
      .load(file)
//    println(df.schema)

    println(df.show())

    df.write
      .format("org.apache.spark.sql.cassandra")
      .options(Map("keyspace" -> "analysis", "table" -> "bus_data"))
      .save()

    session.stop()
  }

  def RDD2Cassandra = {

    val conf = new SparkConf(true)
      .setAppName("RDD2Cassandra")
      .setMaster("local")
      .set("spark.cassandra.connection.host", cassandraHost)

    val sc = new SparkContext(conf)

    import com.datastax.spark.connector._

    val data = sc.textFile(file).map(row => row.split(","))
//    val result = data.collect{case Array (a,b,c,d,e,f,g,h) => busData(Integer.parseInt(a), Integer.parseInt(b),
//                                                                    Integer.parseInt(c), d, Integer.parseInt(e),
//                                                                    Integer.parseInt(f), Integer.parseInt(g),
//                                                                    Integer.parseInt(h))}

    val result = data.collect {case Array(a,b,c,d,e) => busData(Integer.parseInt(a), Integer.parseInt(b), c,
      Integer.parseInt(d), Integer.parseInt(e))}

    result.saveToCassandra("analysis", "bus_data2")

    sc.stop()
  }

  def main(args: Array[String]): Unit = {

    DStoCassandra
  }

}

case class busData(id: Int, busid: Int, cardid: String, stationfrom: Int, stationto: Int)