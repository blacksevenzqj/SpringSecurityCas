package ass.management.admin.common.word.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 正则工具类
 * 
 * @author Sayi
 * @version
 */
public final class RegexUtils {

	public static String escapeExprSpecialWord(String keyword) {
		if (StringUtils.isNotBlank(keyword)) {
			String[] fbsArr = { "\\", "$", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}",
					"|" };
			for (String key : fbsArr) {
				if (keyword.contains(key)) {
					keyword = keyword.replace(key, "\\" + key);
				}
			}
		}
		return keyword;
	}
}
