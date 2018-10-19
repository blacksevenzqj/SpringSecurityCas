package ass.management.utils.date;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public final class DateTimeUtils {
    public static final String FORMAT_YMD = "yyyy-MM-dd";
    public static final SimpleDateFormat SDF_YMD = new SimpleDateFormat("yyyy-MM-dd");
    public static final String FORMAT_HMS = "HH:mm:ss";
    public static final SimpleDateFormat SDF_HMS = new SimpleDateFormat("HH:mm:ss");
    public static final String FORMAT_YMD_HMS = "yyyy-MM-dd HH:mm:ss";
    public static final SimpleDateFormat SDF_YMD_HMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final String FORMAT_YMD_HMSS = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final SimpleDateFormat SDF_YMD_HMSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    public static final long MILLIONSECOND_OF_SECOND = 1000L;
    public static final long MILLIONSECOND_OF_MINUTE = 60000L;
    public static final long MILLIONSECOND_OF_HOUR = 3600000L;
    public static final long MILLIONSECOND_OF_DAY = 86400000L;
    public static final long TIMEZONE_OFFSET = (long)(Calendar.getInstance().get(15) + Calendar.getInstance().get(16));

    private DateTimeUtils() {
    }

    public static Date parseStrToDate(String dateStr, String formatStr) {
        Date retDate = null;
        if (StringUtils.isNotEmpty(dateStr)) {
            SimpleDateFormat simpleDateFormat = null;
            if (StringUtils.isNotEmpty(formatStr)) {
                if (formatStr.equals("yyyy-MM-dd")) {
                    simpleDateFormat = SDF_YMD;
                } else if (formatStr.equals("HH:mm:ss")) {
                    simpleDateFormat = SDF_HMS;
                } else if (formatStr.equals("yyyy-MM-dd HH:mm:ss")) {
                    simpleDateFormat = SDF_YMD_HMS;
                } else if (formatStr.equals("yyyy-MM-dd HH:mm:ss.SSS")) {
                    simpleDateFormat = SDF_YMD_HMSS;
                } else {
                    simpleDateFormat = new SimpleDateFormat(formatStr);
                }
            } else {
                simpleDateFormat = SDF_YMD_HMS;
            }

            try {
                retDate = simpleDateFormat.parse(dateStr);
            } catch (ParseException var5) {
                var5.printStackTrace();
            }
        }

        return retDate;
    }

    public static String formateDateToStr(Date srcDate, String formatStr) {
        String retStr = "";
        if (srcDate != null) {
            SimpleDateFormat simpleDateFormat = null;
            if (StringUtils.isNotEmpty(formatStr)) {
                if (formatStr.equals("yyyy-MM-dd")) {
                    simpleDateFormat = SDF_YMD;
                } else if (formatStr.equals("HH:mm:ss")) {
                    simpleDateFormat = SDF_HMS;
                } else if (formatStr.equals("yyyy-MM-dd HH:mm:ss")) {
                    simpleDateFormat = SDF_YMD_HMS;
                } else if (formatStr.equals("yyyy-MM-dd HH:mm:ss.SSS")) {
                    simpleDateFormat = SDF_YMD_HMSS;
                } else {
                    simpleDateFormat = new SimpleDateFormat(formatStr);
                }
            } else {
                simpleDateFormat = SDF_YMD_HMS;
            }

            retStr = simpleDateFormat.format(srcDate);
        }

        return retStr;
    }

    public static Date getNow() {
        Calendar now = Calendar.getInstance();
        return now.getTime();
    }

    public static String getNowDateString() {
        return formateDateToStr(getNow(), "yyyy-MM-dd");
    }

    public static String getNowDateTimeString() {
        return formateDateToStr(getNow(), "yyyy-MM-dd HH:mm:ss");
    }

    public static Date getAdjustTime(Date date, int day, int hour, int minute, int second, long microsecond) {
        Date d;
        if (date == null) {
            d = getNow();
        } else {
            d = date;
        }

        return new Date(d.getTime() + 86400000L * (long)day + 3600000L * (long)hour + 60000L * (long)minute + 1000L * (long)second + microsecond);
    }

    public static void setAdjustTime(Date date, int day, int hour, int minute, int second, long microsecond) {
        if (date != null) {
            date.setTime(date.getTime() + 86400000L * (long)day + 3600000L * (long)hour + 60000L * (long)minute + 1000L * (long)second + microsecond);
        }
    }

    public static Date setDayFirstTime(Date date) {
        if (date == null) {
            return null;
        } else {
            long l = date.getTime();
            l -= (l + TIMEZONE_OFFSET) % 86400000L;
            date.setTime(l);
            return date;
        }
    }

    public static Date setDayLastTime(Date date) {
        if (date == null) {
            return null;
        } else {
            long l = date.getTime();
            l = l - (l + TIMEZONE_OFFSET) % 86400000L + 86400000L - 1L;
            date.setTime(l);
            return date;
        }
    }

    public static Date getYesterday(Date date) {
        return getAdjustTime(date, -1, 0, 0, 0, 0L);
    }

    public static Date getYesterday() {
        return getYesterday((Date)null);
    }

    public static Date getTomorrow(Date date) {
        return getAdjustTime(date, 1, 0, 0, 0, 0L);
    }

    public static Date getTomorrow() {
        return getTomorrow((Date)null);
    }

    public static String getThisYearStr() {
        return Strings.substringBefore(getNowDateString(), "-");
    }

    public static int getThisYear() {
        return Integer.valueOf(getThisYearStr()).intValue();
    }

    public static Date getStartOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(5, 1);
        return setDayFirstTime(cal.getTime());
    }

    public static Date getEndOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(1);
        int month = cal.get(2) + 1;
        if (month > 11) {
            ++year;
            month = 0;
        }

        cal.set(year, month, 0);
        return setDayLastTime(cal.getTime());
    }

    public static Date getStartOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayOfWeek = cal.get(7);
        cal.add(5, -(dayOfWeek - 2));
        return setDayFirstTime(cal.getTime());
    }

    public static Date getEndOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayOfWeek = cal.get(7);
        cal.add(5, -(dayOfWeek - 8));
        return setDayLastTime(cal.getTime());
    }

    public static Date getDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(1, year);
        cal.set(2, month - 1);
        cal.set(5, day);
        return cal.getTime();
    }

    public static int getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(1);
    }

    public static int getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(2) + 1;
    }

    public static int getDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(5);
    }

    public static Date getRollDate(Date date, int rollType, int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.roll(rollType, amount);
        return c.getTime();
    }

    public static int compareDate(Date date1, Date date2) {
        if (date1 == date2) {
            return 0;
        } else if (date1 == null) {
            return -1;
        } else if (date2 == null) {
            return 1;
        } else {
            long l = date1.getTime() - date2.getTime();
            if (l == 0L) {
                return 0;
            } else {
                return l > 0L ? 1 : -1;
            }
        }
    }

    public static Date getStartOfLastTwoWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayOfWeek = cal.get(7);
        cal.add(5, -(dayOfWeek + 6));
        return cal.getTime();
    }

    public static Date getStartQuerterDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(2) + 1;
        byte var3;
        if (month >= 1 && month <= 3) {
            var3 = 1;
        } else if (month >= 4 && month <= 6) {
            var3 = 4;
        } else if (month >= 7 && month <= 9) {
            var3 = 7;
        } else {
            var3 = 10;
        }

        int startMonth = var3 - 1;
        cal.set(2, startMonth);
        cal.set(5, 1);
        Date startQuarterDay = cal.getTime();
        return startQuarterDay;
    }

    public static Date getEndQuerterDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(2) + 1;
        byte var3;
        if (month >= 1 && month <= 3) {
            var3 = 3;
        } else if (month >= 4 && month <= 6) {
            var3 = 6;
        } else if (month >= 7 && month <= 9) {
            var3 = 9;
        } else {
            var3 = 12;
        }

        int endMonth = var3 - 1;
        cal.set(5, 1);
        cal.set(2, endMonth);
        Date endQuarterDay = getEndOfMonth(cal.getTime());
        return endQuarterDay;
    }

    public static DateTimeUtils.ETimeSectionRelationship getTimeSectionRelationship(List<Date> l1, List<Date> l2) {
        if (l1 != null && l2 != null && l1.size() >= 2 && l2.size() >= 2) {
            if (l1.get(0) != null && l1.get(1) != null && l2.get(0) != null && l2.get(1) != null) {
                if (compareDate((Date)l1.get(0), (Date)l1.get(1)) == -1 && compareDate((Date)l2.get(0), (Date)l2.get(1)) == -1) {
                    if (compareDate((Date)l2.get(0), (Date)l1.get(0)) == -1 && compareDate((Date)l2.get(1), (Date)l1.get(1)) == 1) {
                        return DateTimeUtils.ETimeSectionRelationship.OUT;
                    } else if (compareDate((Date)l2.get(0), (Date)l1.get(0)) != 1 && compareDate((Date)l2.get(0), (Date)l1.get(0)) != 0 || compareDate((Date)l2.get(1), (Date)l1.get(1)) != -1 && compareDate((Date)l2.get(1), (Date)l1.get(1)) != 0) {
                        if (compareDate((Date)l2.get(1), (Date)l1.get(0)) == -1) {
                            return ((Date)l1.get(0)).getTime() - ((Date)l2.get(1)).getTime() == 1000L ? DateTimeUtils.ETimeSectionRelationship.HALF_LEFT : DateTimeUtils.ETimeSectionRelationship.LEFT;
                        } else if (compareDate((Date)l2.get(0), (Date)l1.get(1)) == 1) {
                            return ((Date)l2.get(0)).getTime() - ((Date)l1.get(1)).getTime() == 1000L ? DateTimeUtils.ETimeSectionRelationship.HALF_RIGHT : DateTimeUtils.ETimeSectionRelationship.RIGHT;
                        } else if (compareDate((Date)l2.get(0), (Date)l1.get(0)) == -1 && (compareDate((Date)l2.get(1), (Date)l1.get(0)) == 1 || compareDate((Date)l2.get(1), (Date)l1.get(0)) == 0) && (compareDate((Date)l2.get(1), (Date)l1.get(1)) == -1 || compareDate((Date)l2.get(1), (Date)l1.get(1)) == 0)) {
                            return DateTimeUtils.ETimeSectionRelationship.HALF_LEFT;
                        } else {
                            return compareDate((Date)l2.get(1), (Date)l1.get(1)) != 1 || compareDate((Date)l2.get(0), (Date)l1.get(0)) != 1 && compareDate((Date)l2.get(0), (Date)l1.get(0)) != 0 || compareDate((Date)l2.get(0), (Date)l1.get(1)) != -1 && compareDate((Date)l2.get(0), (Date)l1.get(1)) != 0 ? DateTimeUtils.ETimeSectionRelationship.ERROR : DateTimeUtils.ETimeSectionRelationship.HALF_RIGHT;
                        }
                    } else {
                        return DateTimeUtils.ETimeSectionRelationship.IN;
                    }
                } else {
                    return DateTimeUtils.ETimeSectionRelationship.ERROR;
                }
            } else {
                return DateTimeUtils.ETimeSectionRelationship.ERROR;
            }
        } else {
            return DateTimeUtils.ETimeSectionRelationship.ERROR;
        }
    }

    public static enum ETimeSectionRelationship {
        ERROR,
        OUT,
        IN,
        LEFT,
        RIGHT,
        HALF_LEFT,
        HALF_RIGHT;

        private ETimeSectionRelationship() {
        }
    }
}
