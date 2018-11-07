package ass.management.admin.test.word.mytest;

import ass.management.admin.common.word.XWPFTemplate;
import ass.management.admin.common.word.config.Configure;
import ass.management.admin.common.word.data.MiniTableRenderData;
import ass.management.admin.common.word.data.RowRenderData;
import ass.management.admin.common.word.data.TextRenderData;
import ass.management.admin.common.word.data.style.Style;
import ass.management.admin.common.word.data.style.TableStyle;
import ass.management.admin.test.word.example.DetailTablePolicy;
import ass.management.admin.test.word.example.PaymentData;
import org.junit.Before;
import org.junit.Test;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;

import java.io.FileOutputStream;
import java.util.Arrays;

/**
 * 我测试付款通知书：表格操作示例
 * @author Sayi
 * @version 
 */
public class MyTestPaymentExample {
    
    PaymentData datas = new PaymentData();
    
    Style headTextStyle = new Style();
    TableStyle headStyle = new TableStyle();
    TableStyle rowStyle = new TableStyle();
    
    @Before
    public void init(){
        headTextStyle.setFontFamily("Hei");
        headTextStyle.setFontSize(9);
        headTextStyle.setColor("7F7F7F");
        
        headStyle.setBackgroundColor("F2F2F2");
        headStyle.setAlign(STJc.CENTER);
        
        rowStyle = new TableStyle();
        rowStyle.setAlign(STJc.CENTER);
        
        
        
//        datas.setNO("KB.6890451");
//        datas.setID("ZHANG_SAN_091");
//        datas.setTaitou("深圳XX家装有限公司");
//        datas.setConsignee("丙丁");
//
//        datas.setSubtotal("8000");
//        datas.setTax("600");
//        datas.setTransform("120");
//        datas.setOther("250");
//        datas.setUnpay("6600");
//        datas.setTotal("总共：7200");

        datas.setNO("2018年10月开始，政务信息共10个部门上报，其中采纳110份，未采纳78份。如下为明细：飞机飞机飞机飞机飞机");

        
        RowRenderData header = RowRenderData.build(new TextRenderData("标题", headTextStyle),
                new TextRenderData("拟稿单位", headTextStyle), new TextRenderData("拟稿人", headTextStyle),
                new TextRenderData("拟稿部门", headTextStyle), new TextRenderData("种类", headTextStyle),
                new TextRenderData("拟稿日期", headTextStyle), new TextRenderData("信息内容", headTextStyle));
        header.setStyle(headStyle);
        
        RowRenderData row = RowRenderData.build("2018-06-1211111111111", "SN18090", "李四", "5000元", "快递", "附录A", "T11090");
        row.setStyle(rowStyle);
        RowRenderData row2 = RowRenderData.build("2018-06-13", "SN18090", "王五", "5000元", "快递", "附录A", "T11090");
        row2.setStyle(rowStyle);

        MiniTableRenderData miniTableRenderData = new MiniTableRenderData(header, Arrays.asList(row, row2), MiniTableRenderData.WIDTH_A4_MEDIUM_FULL);
        miniTableRenderData.setStyle(headStyle);
        datas.setOrder(miniTableRenderData);
        
//        DetailData detailTable = new DetailData();
//        RowRenderData good = RowRenderData.build("4", "墙纸", "书房+卧室", "1500", "/", "400", "1600");
//        good.setStyle(rowStyle);
//        List<RowRenderData> goods = Arrays.asList(good, good, good);
//        RowRenderData  labor = RowRenderData.build("油漆工", "2", "200", "400");
//        labor.setStyle(rowStyle);
//        List<RowRenderData> labors = Arrays.asList(labor, labor, labor, labor);
//        detailTable.setGoods(goods);
//        detailTable.setLabors(labors);
//        datas.setDetailTable(detailTable);
    }

    @Test
    public void testResumeExample() throws Exception {
        Configure config = Configure.newBuilder().customPolicy("detail_table", new DetailTablePolicy()).build();
        XWPFTemplate template = XWPFTemplate.compile("src/test/resources/word/my/不带编辑的政务信息简报.docx", config).render(datas);
        FileOutputStream out = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\新建文件夹\\out_政务信息简报.docx");
        template.write(out);
        out.flush();
        out.close();
        template.close();
    }

}
