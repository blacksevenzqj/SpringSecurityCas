package ass.management.admin.common.excel;

import ass.management.admin.common.excel.config.ExcelDefinition;

import java.util.Map;

/**
 * Excel定义接口
 * @author lisuo
 *
 */
public interface ExcelDefinitionReader {
	/**
	 * 获取 ExcelDefinition注册信息
	 * @return
	 */
	Map<String, ExcelDefinition> getRegistry();
}
