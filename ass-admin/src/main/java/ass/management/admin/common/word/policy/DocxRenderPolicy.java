package ass.management.admin.common.word.policy;

import ass.management.admin.common.word.NiceXWPFDocument;
import ass.management.admin.common.word.XWPFTemplate;
import ass.management.admin.common.word.config.Configure;
import ass.management.admin.common.word.data.DocxRenderData;
import ass.management.admin.common.word.template.run.RunTemplate;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * word模板List循环渲染后合并
 * </p>
 * 
 * @author Sayi
 * @version 1.3.0
 */
public class DocxRenderPolicy extends AbstractRenderPolicy {
    @Override
    protected boolean validate(Object data) {
        return null != data;
    }

    @Override
    public void doRender(RunTemplate runTemplate, Object data, XWPFTemplate template)
            throws Exception {
        NiceXWPFDocument doc = template.getXWPFDocument();
        XWPFRun run = runTemplate.getRun();
        // 优先清空标签
        clearPlaceholder(run);

        List<NiceXWPFDocument> docMerges = getMergedDocxs((DocxRenderData) data,
                template.getConfig());
        try {
            doc = doc.merge(docMerges, run);
        } catch (Exception e) {
            logger.error("merge docx error", e);
        }

        template.reload(doc);
    }

    private List<NiceXWPFDocument> getMergedDocxs(DocxRenderData data, Configure configure) {
        List<NiceXWPFDocument> docs = new ArrayList<NiceXWPFDocument>();
        File docx = data.getDocx();
        List<?> dataList = data.getDataList();
        if (null == dataList || dataList.isEmpty()) {
            try {
                // 待合并的文档不是模板
                docs.add(new NiceXWPFDocument(new FileInputStream(docx)));
            } catch (Exception e) {
                logger.error("Cannot get the merged docx.", e);
            }
        } else {
            for (int i = 0; i < dataList.size(); i++) {
                XWPFTemplate temp = XWPFTemplate.compile(docx, configure);
                temp.render(dataList.get(i));
                docs.add(temp.getXWPFDocument());
            }
        }
        return docs;
    }

}
