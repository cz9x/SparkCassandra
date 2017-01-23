package utils

import java.text.{DecimalFormat, Format, SimpleDateFormat}
import java.util.{Calendar, Date}

/**
  * Created by li on 2017/1/20.
  */
object DateUtils {

  //获取今天日期
  def getNowDate(): String ={
    val now: Date = new Date()
    val dateFormat: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd")
    val nowDate = dateFormat.format(now)
    nowDate
  }

  def getNowDate2(): String ={
    val now: Date = new Date()
    val dateFormat: SimpleDateFormat = new SimpleDateFormat("yyyyMMdd")
    val nowDate = dateFormat.format(now)
    nowDate
  }

  //获取昨天日期
  def getYesterday(): String ={
    val dateFormat: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd")
    val cal: Calendar = Calendar.getInstance()
    cal.add(Calendar.DATE, -1)
    val yesterday = dateFormat.format(cal.getTime)
    yesterday
  }

  def getYesterday2(): String ={
    val dateFormat: SimpleDateFormat = new SimpleDateFormat("yyyyMMdd")
    val cal: Calendar = Calendar.getInstance()
    cal.add(Calendar.DATE, -1)
    val yesterday = dateFormat.format(cal.getTime)
    yesterday
  }

  //获取当前小时
  def getHour(): String ={
    val now: Date = new Date()
    val dateFormat: SimpleDateFormat = new SimpleDateFormat("HH")
    val Hour = dateFormat.format(now)
    Hour
  }

  //获取上一个小时
  def getLastHour(): String={
    val dateFormat: SimpleDateFormat = new SimpleDateFormat("HH")
    val cal: Calendar = Calendar.getInstance()
    cal.add(Calendar.HOUR, -1)
    val lastHour = dateFormat.format(cal.getTime)
    lastHour
  }

  //获取当前周
  def getWeek(): String ={
    val cal: Calendar = Calendar.getInstance()
    cal.setTime(new Date())
    val week = cal.get(Calendar.WEEK_OF_YEAR)
    val f: Format = new DecimalFormat("00")
    f.format(week)
  }

  //获取上一周
  def getLastWeek(): String ={
    val cal: Calendar = Calendar.getInstance()
    cal.add(Calendar.WEEK_OF_YEAR, -1)
    val dateFormat: SimpleDateFormat = new SimpleDateFormat("WW")
    val week = dateFormat.format(cal.getTime)
    week
  }

  //获取当前月
  def getMonth(): String ={
    val cal: Calendar = Calendar.getInstance()
    cal.setTime(new Date())
    val month = cal.get(Calendar.MONTH)
    val f: Format = new DecimalFormat("00")
    f.format(month)
  }

  //获取上一月
  def getMonth2(): String ={
    val cal: Calendar = Calendar.getInstance()
    cal.add(Calendar.MONTH, -1)
    val dateFormat: SimpleDateFormat = new SimpleDateFormat("MM")
    val month = dateFormat.format(cal.getTime)
    val f: Format = new DecimalFormat("00")
    f.format(Integer.parseInt(month))
  }

  //获取当前年
  def getYead(): String ={
    val cal: Calendar = Calendar.getInstance()
    val dateFormat: SimpleDateFormat = new SimpleDateFormat("yyyy")
    val year = dateFormat.format(cal.getTime)
    year
  }


  def main(args: Array[String]): Unit = {
    println(getLastWeek())
    println(getWeek())
//    println(getWeek())
  }

}
