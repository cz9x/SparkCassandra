package utils

import java.text.{DecimalFormat, Format, SimpleDateFormat}
import java.util
import java.util.{Calendar, Date}

/**
  * Created by li on 2017/1/22.
  */
object DateUtils2 {
  //返回一小时前日期
  def getLastHour(date: Date, num: Int): String ={
    val dateFormat: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val cal: Calendar = Calendar.getInstance()
    cal.setTime(date)
    cal.add(Calendar.HOUR, num)
    val result = dateFormat.format(cal.getTime)
    result
  }

  //返回一天前日期
  def getLastDay(date: Date): String ={
    val dateFormat: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val cal: Calendar = Calendar.getInstance()
    cal.setTime(date)
    cal.add(Calendar.DATE, -1)
    val result = dateFormat.format(cal.getTime)
    result
  }

  //返回一周前日期
  def getLastWeek(date: Date): String ={
    val dateFormat: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val cal: Calendar = Calendar.getInstance()
    cal.setTime(date)
    cal.add(Calendar.WEEK_OF_YEAR, -1)
    val result = dateFormat.format(cal.getTime)
    result
  }

  //返回一个月前日期
  def getLastMonth(date: Date): String ={
    val dateFormat: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val cal: Calendar = Calendar.getInstance()
    cal.setTime(date)
    cal.add(Calendar.MONTH, -1)
    val result = dateFormat.format(cal.getTime)
    result
  }

  def splitDate(dateStr: String): Map[String,Any] ={
    val year = dateStr.substring(0, 4)
    val month = dateStr.substring(5, 7)
    val day = dateStr.substring(8, 10)
    val hour = dateStr.substring(11, 13)

    val dateFormat: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val date = dateFormat.parse(dateStr)
    val cal: Calendar = Calendar.getInstance()
    cal.setTime(date)
    cal.setFirstDayOfWeek(Calendar.MONDAY)
    val week = cal.get(Calendar.WEEK_OF_YEAR)
    val f: Format = new DecimalFormat("00")

    val result = Map("year"->year, "month"->month, "day"->day, "hour"->hour, "week"->f.format(week))
    result
  }

  def main(args: Array[String]): Unit = {
    val date1 = "2016-12-31 23:00:00"
    val date = new Date()
    val result = getLastHour(date, -2)
    println(result)
  }
}
