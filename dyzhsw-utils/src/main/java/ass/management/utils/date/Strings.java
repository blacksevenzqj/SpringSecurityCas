package ass.management.utils.date;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;

public final class Strings extends StringUtils {
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private Strings() {
    }

    /** @deprecated */
    @Deprecated
    public static String ISO_8859_1ToGBK(String string) {
        if (isNotEmpty(string)) {
            try {
                return new String(string.getBytes("ISO-8859-1"), "GBK");
            } catch (UnsupportedEncodingException var2) {
                var2.printStackTrace();
            }
        }

        return null;
    }

    /** @deprecated */
    @Deprecated
    public static String decodeEscapeX(String src) {
        if (isEmpty(src)) {
            return src;
        } else {
            StringBuffer tmp = new StringBuffer();
            tmp.ensureCapacity(src.length());
            int lastPos = 0;
            boolean var3 = false;

            while(lastPos < src.length()) {
                int pos = src.indexOf("%", lastPos);
                if (pos == lastPos) {
                    char ch;
                    if (src.charAt(pos + 1) == 'u') {
                        ch = (char)Integer.parseInt(src.substring(pos + 2, pos + 6), 16);
                        tmp.append(ch);
                        lastPos = pos + 6;
                    } else {
                        ch = (char)Integer.parseInt(src.substring(pos + 1, pos + 3), 16);
                        tmp.append(ch);
                        lastPos = pos + 3;
                    }
                } else if (pos == -1) {
                    tmp.append(src.substring(lastPos));
                    lastPos = src.length();
                } else {
                    tmp.append(src.substring(lastPos, pos));
                    lastPos = pos;
                }
            }

            return tmp.toString();
        }
    }

    /** @deprecated */
    @Deprecated
    public static boolean isEmptyOrNull(String string) {
        return isEmpty(string);
    }
}

