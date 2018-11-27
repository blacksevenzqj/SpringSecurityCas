package ass.management.admin.test.excel;

import ass.management.admin.common.excel.ExcelContext;
import ass.management.admin.common.excel.config.ExcelDefinition;
import ass.management.admin.common.excel.config.FieldValue;
import ass.management.admin.common.excel.model.DbxitemAO;
import ass.management.admin.common.excel.model.DcdbFkInfoItemAO;
import ass.management.admin.common.excel.model.DcdbgzmbfjAO;
import ass.management.admin.common.excel.parsing.ExcelHeader;
import ass.management.admin.common.excel.result.ExcelExportResult;
import ass.management.admin.test.excel.model.AuthorModel;
import ass.management.admin.test.excel.model.BookModel;
import ass.management.admin.test.excel.model.StudentModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Excel导出测试,运行后刷新工程的src/test/resources目录
 * @author lisuo
 *
 */
public class ExportDcdbTest {
	
	//测试时文件磁盘路径
//	private static String path = "src/test/resources/test-export-excel.xlsx";
	private static String path = "C:\\Users\\Administrator\\Desktop\\123123\\test-export-excel.xlsx";
//	private static String path = "C:\\Users\\dell\\Desktop\\123123\\test-export-excel.xlsx";

	//配置文件路径
	private static ExcelContext context = new ExcelContext("config/excel/excel-config.xml");
	//Excel配置文件中配置的id
	private static String excelId = "dcdbgzmbfjAO";
	
	/***
	 * 导出测试,分多次导出
	 * @throws Exception
	 */
	@Test
	public void testExporPart()throws Exception{
		//需求概述.数据量较大,可能大批量数据导出,会对DB造成压力,这里分批次检索数据,一部分一部分向Excel中写
		OutputStream ops = new FileOutputStream(path);
		ExcelExportResult exportResult = context.createExcelForPart(excelId, getData());

		Workbook workbook = exportResult.build();
		workbook.write(ops);
		ops.close();
		workbook.close();
	}

	//获取模拟数据,数据库数据...
	public static List<DcdbgzmbfjAO> getData(){
		int size = 3;
		List<DcdbgzmbfjAO> dcdbgzmbfjAOList = new ArrayList<DcdbgzmbfjAO>(size);
		for(int i=0;i<size;i++){
			DcdbgzmbfjAO yjrw = new DcdbgzmbfjAO();
			yjrw.setMbfjdseq("1");
			yjrw.setWorktarget("1");
			yjrw.setZrunitname("1");
			yjrw.setXbunitname("1");
			yjrw.setBfqxStr("2018-11-26");
			yjrw.setBz("1");

			if(i % 2 == 0) {
				List<DbxitemAO> dbxitemAOList = new ArrayList<DbxitemAO>();
				DbxitemAO dbxitemAO = new DbxitemAO();
				dbxitemAO.setDbxsequence("11");
				List<DcdbFkInfoItemAO> dcdbFkInfoItemAOList = new ArrayList<>();
				DcdbFkInfoItemAO dcdbFkInfoItemAO = new DcdbFkInfoItemAO();
				dcdbFkInfoItemAO.setCurProgress("111");
				dcdbFkInfoItemAOList.add(dcdbFkInfoItemAO);
				dbxitemAO.setDcdbFkInfoItemAOList(dcdbFkInfoItemAOList);

				DbxitemAO dbxitemAO2 = new DbxitemAO();
				dbxitemAO2.setDbxsequence("11");
				List<DcdbFkInfoItemAO> dcdbFkInfoItemAOList2 = new ArrayList<>();
				DcdbFkInfoItemAO dcdbFkInfoItemAO2 = new DcdbFkInfoItemAO();
				dcdbFkInfoItemAO2.setCurProgress("111");
				dcdbFkInfoItemAOList2.add(dcdbFkInfoItemAO2);
				dbxitemAO2.setDcdbFkInfoItemAOList(dcdbFkInfoItemAOList2);

				DbxitemAO dbxitemAO3 = new DbxitemAO();
				dbxitemAO3.setDbxsequence("11");
				List<DcdbFkInfoItemAO> dcdbFkInfoItemAOList3 = new ArrayList<>();
				DcdbFkInfoItemAO dcdbFkInfoItemAO3 = new DcdbFkInfoItemAO();
				dcdbFkInfoItemAO3.setCurProgress("111");
				dcdbFkInfoItemAOList3.add(dcdbFkInfoItemAO2);
				dbxitemAO3.setDcdbFkInfoItemAOList(dcdbFkInfoItemAOList3);

				dbxitemAOList.add(dbxitemAO);
				dbxitemAOList.add(dbxitemAO2);
				dbxitemAOList.add(dbxitemAO3);
				yjrw.setDbxitemAOList(dbxitemAOList);

			}else{
				List<DcdbFkInfoItemAO> dcdbFkInfoItemAOList = new ArrayList<>();
				DcdbFkInfoItemAO dcdbFkInfoItemAO = new DcdbFkInfoItemAO();
				dcdbFkInfoItemAO.setCurProgress("aaa");
				DcdbFkInfoItemAO dcdbFkInfoItemAO2 = new DcdbFkInfoItemAO();
				dcdbFkInfoItemAO2.setCurProgress("bbb");
				DcdbFkInfoItemAO dcdbFkInfoItemAO3 = new DcdbFkInfoItemAO();
				dcdbFkInfoItemAO3.setCurProgress("ccc");

				dcdbFkInfoItemAOList.add(dcdbFkInfoItemAO);
				dcdbFkInfoItemAOList.add(dcdbFkInfoItemAO2);
				dcdbFkInfoItemAOList.add(dcdbFkInfoItemAO3);
				yjrw.setDcdbFkInfoItemAOList(dcdbFkInfoItemAOList);
			}
			dcdbgzmbfjAOList.add(yjrw);
		}
		return dcdbgzmbfjAOList;
	}

}
