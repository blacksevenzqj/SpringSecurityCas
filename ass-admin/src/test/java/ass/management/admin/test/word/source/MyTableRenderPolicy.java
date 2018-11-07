package ass.management.admin.test.word.source;

import ass.management.admin.common.word.data.TableRenderData;
import ass.management.admin.common.word.policy.DynamicTableRenderPolicy;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;

public class MyTableRenderPolicy extends DynamicTableRenderPolicy {

	@Override
	public void render(XWPFTable table, Object data) {
		TableRenderData tableData = (TableRenderData) data;
		List<Object> datas = tableData.getDatas();
		if (null == datas || datas.isEmpty()) {
			table.getRow(1).getCell(0).removeParagraph(0);
			table.getRow(1).getCell(0).setText(tableData.getNoDatadesc());
		} else {
			// XWPFTableRow row = table.getRow(1);

			table.removeRow(1);
			for (Object obj : datas) {
				XWPFTableRow row = table.insertNewTableRow(1);
				String str = obj.toString();
				String[] split = str.split(";");
				XWPFTableCell cell0 = row.createCell();
				cell0.setText(split[0]);
				XWPFTableCell cell1 = row.createCell();
				cell1.setText(split[1]);
				XWPFTableCell cell2 = row.createCell();
				cell2.setText(split[2]);
			}
		}

	}

}
