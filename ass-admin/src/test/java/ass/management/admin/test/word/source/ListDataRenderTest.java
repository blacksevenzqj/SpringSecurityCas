package ass.management.admin.test.word.source;


import ass.management.admin.common.word.XWPFTemplate;
import ass.management.admin.common.word.data.PictureRenderData;
import ass.management.admin.common.word.data.TextRenderData;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Deprecated
public class ListDataRenderTest {
    
    @SuppressWarnings("serial")
    public void testListData() throws Exception {
        
        Map<String, Object> datas = new HashMap<String, Object>(){{
            
            final List<Object> list = new ArrayList<Object>(){{
                add("ver 0.0.3");
                add(new PictureRenderData(100, 120, "src/test/resources/logo.png"));
                add(new TextRenderData("9d55b8", "Deeply in love with the things you love, just deepoove."));
                add("ver 0.0.4");
                add(new PictureRenderData(100, 120, "src/test/resources/logo.png"));
            }};
            List<Object> list2 = new ArrayList<Object>(){{
                add(list);
                add(list);
            }};
            
            put("website", list2);
        }};
        
        
        XWPFTemplate template = XWPFTemplate.compile("src/test/resources/template.docx");
        template.registerPolicy("website", new ListDataRenderPolicy());
        
        template.render(datas);

        FileOutputStream out = new FileOutputStream("out_template.docx");
        template.write(out);
        out.flush();
        out.close();
        template.close();
    }

}
