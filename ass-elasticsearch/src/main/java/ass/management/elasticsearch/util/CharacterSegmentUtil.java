package ass.management.elasticsearch.util;

import org.apache.commons.lang3.StringUtils;

public class CharacterSegmentUtil {

    public static final String REVERSE_SLANT = "\\";
    public static final String REVERSE_SLANT_REGEX = "\\\\";

    public static final String POSITIVE_SLANT = "/";
    public static final String POSITIVE_SLANT_REGEX = "/";

    public static String[] SlashSegmentation(String strs, String separator){
        String[] result = null;
        if(StringUtils.isNotBlank(strs)){
            int startNum = 0;
            int endNum = strs.length();
            if(strs.startsWith(separator)){
                startNum = 1;
            }
            if(strs.endsWith(separator)){   // 可以不进行结尾判断，split分割时忽略结尾符号。
                endNum = strs.length() - 1;
            }
            strs = strs.substring(startNum, endNum);
            if(strs.contains(REVERSE_SLANT)){
                result = strs.split(REVERSE_SLANT_REGEX);
            }else if(strs.contains(POSITIVE_SLANT)){
                result = strs.split(POSITIVE_SLANT_REGEX);
            }
        }
        return result;
    }

}
