package ass.management.admin.test.word.source;

import ass.management.admin.common.word.XWPFTemplate;
import ass.management.admin.common.word.data.PictureRenderData;
import ass.management.admin.common.word.policy.PictureRenderPolicy;
import ass.management.admin.common.word.policy.RenderPolicy;
import ass.management.admin.common.word.policy.TextRenderPolicy;
import ass.management.admin.common.word.resolver.TemplateFactory;
import ass.management.admin.common.word.template.ElementTemplate;
import ass.management.admin.common.word.template.run.RunTemplate;
import ass.management.admin.common.word.util.StyleUtils;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.util.Iterator;

public class ListDataRenderPolicy implements RenderPolicy {
    
    final PictureRenderPolicy pictureRenderPolicy = new PictureRenderPolicy();
    final TextRenderPolicy textRenderPolicy = new TextRenderPolicy();

    @SuppressWarnings({ "unchecked" })
    @Override
    public void render(ElementTemplate eleTemplate, Object dataList, XWPFTemplate template) {
        RunTemplate runTemplate = (RunTemplate) eleTemplate;
        XWPFRun run = runTemplate.getRun();

        if (dataList == null || !(dataList instanceof Iterable)) {
            logger.warn("Error render {}, should be Iterable:{}", runTemplate.getTagName(),
                    dataList.getClass());
            return;
        }

        Iterable<Object> list = (Iterable<Object>) dataList;
        Iterator<Object> iterator = list.iterator();
        RenderPolicy policy = null;
        while (iterator.hasNext()) {
            Object next = iterator.next();
            policy = getPolicy(next);
            if (null == policy) {
                logger.warn("cannot find render policy: [" + runTemplate.getTagName() + "]");
                return;
            }
            XWPFRun insertNewRun = ((XWPFParagraph) run.getParent()).insertNewRun(runTemplate.getRunPos());
            StyleUtils.styleRun(insertNewRun, run);
            RunTemplate createRunTemplate = TemplateFactory.createRunTemplate(
                    runTemplate.getSign() + runTemplate.getTagName(),
                    template.getConfig(), insertNewRun);
            
            policy.render(createRunTemplate, next, template);

            // 使用poi api,需要自己处理数据之间的格式
            // NiceXWPFDocument xwpfDocument = template.getXWPFDocument();
             insertNewRun.addBreak();//数据之间换行
        }
        run.setText("", 0);
    }

    private RenderPolicy getPolicy(Object data) {
        if (data instanceof PictureRenderData) {
            return pictureRenderPolicy;
        } else if (data instanceof Iterable){
            return new ListDataRenderPolicy();
        }else{
            return textRenderPolicy;
        }
    }

}
