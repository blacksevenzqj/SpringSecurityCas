package ass.management.admin.common.word.policy;

import ass.management.admin.common.word.NiceXWPFDocument;
import ass.management.admin.common.word.XWPFTemplate;
import ass.management.admin.common.word.data.MiniTableRenderData;
import ass.management.admin.common.word.data.RowRenderData;
import ass.management.admin.common.word.data.TextRenderData;
import ass.management.admin.common.word.data.style.TableStyle;
import ass.management.admin.common.word.template.run.RunTemplate;
import ass.management.admin.common.word.util.StyleUtils;
import ass.management.admin.common.word.util.TableTools;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;

import java.util.List;
import java.util.Objects;

/**
 * 表格处理
 * 
 * @author Sayi 卅一
 * @since v1.3.0
 */
public class MiniTableRenderPolicy extends AbstractRenderPolicy {

    @Override
    protected boolean validate(Object data) {
        if (null == data) return false;
        if (!(data instanceof MiniTableRenderData)) {
            logger.error("Error datamodel: correct type is MiniTableRenderData, but is "
                    + data.getClass());
            return false;
        }
        if (!((MiniTableRenderData) data).isSetBody()
                && !((MiniTableRenderData) data).isSetHeader()) {
            logger.error("Empty MiniTableRenderData datamodel: {}", data);
            return false;
        }
        return true;
    }

    @Override
    public void doRender(RunTemplate runTemplate, Object data, XWPFTemplate template)
            throws Exception {
        NiceXWPFDocument doc = template.getXWPFDocument();
        XWPFRun run = runTemplate.getRun();

        if (!((MiniTableRenderData) data).isSetBody()) {
            renderNoDataTable(doc, run, (MiniTableRenderData) data);
        } else {
            renderTable(doc, run, (MiniTableRenderData) data);
        }

        // 成功后，才会清除标签，发生异常则保留标签，可以重写doRenderException方法在发生异常后也会清除标签
        clearPlaceholder(run);
    }

    private void renderTable(NiceXWPFDocument doc, XWPFRun run, MiniTableRenderData tableData) {
        // 1.计算行和列
        int row = tableData.getDatas().size(), col = 0;
        if (!tableData.isSetHeader()) {
            col = getMaxColumFromData(tableData.getDatas());
        } else {
            row++;
            col = tableData.getHeaders().size();
        }

        // 2.创建表格
        XWPFTable table = doc.insertNewTable(run, row, col);
        initBasicTable(table, col, tableData.getWidth(), tableData.getStyle());

        // 3.渲染数据
        int startRow = 0;
        if (tableData.isSetHeader()) renderRow(table, startRow++, tableData.getHeaders());
        for (RowRenderData data : tableData.getDatas()) {
            renderRow(table, startRow++, data);
        }

    }

    private void renderNoDataTable(NiceXWPFDocument doc, XWPFRun run,
            MiniTableRenderData tableData) {
        int row = 2, col = tableData.getHeaders().size();

        XWPFTable table = doc.insertNewTable(run, row, col);
        initBasicTable(table, col, tableData.getWidth(), tableData.getStyle());

        renderRow(table, 0, tableData.getHeaders());

        TableTools.mergeCellsHorizonal(table, 1, 0, tableData.getHeaders().size() - 1);
        XWPFTableCell cell = table.getRow(1).getCell(0);
        cell.setText(tableData.getNoDatadesc());

    }

    private void initBasicTable(XWPFTable table, int col, float width, TableStyle style) {
        TableTools.widthTable(table, width, col);
        TableTools.borderTable(table, 4);
        StyleUtils.styleTable(table, style);
    }

    /**
     * 填充表格一行的数据
     * 
     * @param table
     * @param row
     *            第几行
     * @param rowData
     *            行数据：确保行数据的大小不超过表格该行的单元格数量
     */
    public static void renderRow(XWPFTable table, int row, RowRenderData rowData) {
        if (null == rowData || rowData.size() <= 0) return;
        XWPFTableRow tableRow = table.getRow(row);
        Objects.requireNonNull(tableRow, "Row " + row + " do not exist in the table");

        TableStyle style = rowData.getStyle();
        List<TextRenderData> cellList = rowData.getRowData();
        XWPFTableCell cell = null;

        for (int i = 0; i < cellList.size(); i++) {
            cell = tableRow.getCell(i);
            if (null == cell) {
                logger.warn("Extra cell data at row {}, but no extra cell: col {}", row, cell);
                break;
            }

            // 处理单元格样式
            if (null != style && null != style.getBackgroundColor()) {
                cell.setColor(style.getBackgroundColor());
            }

            TextRenderData cellData = cellList.get(i);
            String cellText = cellData.getText();
            if (StringUtils.isBlank(cellText)) break;

            String[] fragment = cellText.split(TextRenderPolicy.REGEX_LINE_CHARACTOR);
            if (null == fragment) break;

            // 处理单元格数据
            XWPFParagraph par;
            XWPFRun run;
            for (int j = 0; j < fragment.length; j++) {
                if (0 == j) {
                    CTTc ctTc = cell.getCTTc();
                    CTP ctP = (ctTc.sizeOfPArray() == 0) ? ctTc.addNewP() : ctTc.getPArray(0);
                    par = new XWPFParagraph(ctP, cell);
                } else {
                    par = cell.addParagraph();
                }
                StyleUtils.styleTableParagraph(par, style);
                run = par.createRun();
                StyleUtils.styleRun(run, cellData.getStyle());
                run.setText(fragment[j]);
            }
        }
    }

    private int getMaxColumFromData(List<RowRenderData> datas) {
        int maxColom = 0;
        for (RowRenderData obj : datas) {
            if (null == obj) continue;
            if (obj.size() > maxColom) maxColom = obj.size();
        }
        return maxColom;
    }

}
