package ass.management.admin.test.excel;

import ass.management.admin.common.excel.ExcelContext;
import ass.management.admin.common.excel.result.ExcelImportResult;
import ass.management.admin.test.excel.model.BookModel;
import ass.management.admin.test.excel.model.StudentModel;
import org.junit.Test;

import java.io.FileInputStream;
import java.util.List;

/**
 * Excel导入测试
 * @author lisuo
 *
 */
public class ImportTest {
	
	// 测试时文件磁盘路径
	private static String path = "E:\\excel_temp\\test-excel.xlsx";
	// 配置文件路径
	private static ExcelContext context = new ExcelContext("excel-config.xml");
	// Excel配置文件中配置的id
	private static String excelId = "student";
	
	/**
	 * 导入Excel,使用了org.easy.excel.test.ExportTest.testExportCustomHeader()方法生成的Excel
	 * @throws Exception
	 */
	@Test
	public void testImport()throws Exception{
//		ClassPathResource resource = new ClassPathResource(path);
//		InputStream in = resource.getInputStream();
		FileInputStream in = new FileInputStream(path);

		//第二个参数需要注意,它是指标题索引的位置,可能你的前几行并不是标题,而是其他信息,
		//比如数据批次号之类的,关于如何转换成javaBean,具体参考配置信息描述
		ExcelImportResult result = context.readExcel(excelId, 2, in);
		System.out.println(result.getHeader());
		List<StudentModel> stus = result.getListBean();
		for(StudentModel stu:stus){
			System.out.println(stu);
			BookModel book = stu.getBook();
			System.out.println(book);
			if(book!=null){
				System.out.println(book.getAuthor());
			}
		}
//		resource.getInputStream().close();
		in.close();
		//这种方式和上面的没有任何区别,底层方法默认标题索引为0
		//context.readExcel(excelId, fis);
	}
	
	
		
}
