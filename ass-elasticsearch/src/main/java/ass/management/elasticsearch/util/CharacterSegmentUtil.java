package ass.management.elasticsearch.util;

import org.apache.commons.lang3.StringUtils;

public class CharacterSegmentUtil {

    public static String[] SlashSegmentation(String strs, String separator){
        String[] result = null;
        if(StringUtils.isNotBlank(strs)){
            int startNum = 0;
            int endNum = strs.length();
            if(strs.contains("\\")){
                if(strs.startsWith("\\")){
                    startNum = 1;
                }
                if(strs.endsWith("\\")){   // 可以不进行结尾判断，split分割时忽略结尾符号。
                    endNum = strs.length() - 1;
                }
                strs = strs.substring(startNum, endNum);
                result = strs.split("\\\\");
            }else if(strs.contains("/")){
                if(strs.startsWith("/")){
                    startNum = 1;
                }
                if(strs.endsWith("/")){   // 可以不进行结尾判断，split分割时忽略结尾符号。
                    endNum = strs.length() - 1;
                }
                strs = strs.substring(startNum, endNum);
                result = strs.split("/");
            }
        }
        return result;
    }

}
