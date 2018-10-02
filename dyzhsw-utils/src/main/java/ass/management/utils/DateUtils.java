package ass.management.utils;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 日期处理
 */
public class DateUtils {
	/** 时间格式(yyyy-MM-dd) */
	public final static String DATE_PATTERN = "yyyy-MM-dd";
	/** 时间格式(yyyy-MM-dd HH:mm:ss) */
	public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    public static String format(Date date, String pattern) {
        if(date != null){
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    public static Date getDate(){
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        Date date = Date.from(zdt.toInstant());
        return date;
    }

    public static String getDateStr() {
        String separator = File.separator;
        String forMat = "yyyy" + separator + "MM" + separator + "dd";
        DateTimeFormatter dfor = DateTimeFormatter.ofPattern(forMat);
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return zdt.format(dfor);
    }

    public static Date getDateByDouble(double value) {
        BigDecimal big = BigDecimal.valueOf(value);
        Date date = new Date(big.longValue());
        return date;
    }

    public static String getDateStrByUtcDouble(double value) {
        BigDecimal big = BigDecimal.valueOf(value);
        Date date = new Date(big.longValue());
        DateTimeFormatter dfor = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.of("+0");

        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), zoneId);  // 设置UTC时区
        String dateStr = localDateTime.format(dfor);
        // 或
//        ZonedDateTime zdt = instant.atZone(zoneId);
//        String dateStr = zdt.format(dfor);

        return dateStr;
    }

    public static Long getTimeDifference(Date startTime, Date endTime){
        if(startTime != null && endTime != null) {

            ZoneId zoneId = ZoneId.systemDefault();

            Instant instantStart = startTime.toInstant();
            LocalDateTime localDateTimeStart = instantStart.atZone(zoneId).toLocalDateTime();

            Instant instantEnd = endTime.toInstant();
            LocalDateTime localDateTimeEnd = instantEnd.atZone(zoneId).toLocalDateTime();

            Duration duration = Duration.between(localDateTimeStart, localDateTimeEnd);
            return duration.toMillis();
        }else{
            return null;
        }
    }








}
