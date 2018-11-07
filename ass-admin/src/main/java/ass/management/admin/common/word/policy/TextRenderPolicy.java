package ass.management.admin.common.word.policy;

import ass.management.admin.common.word.XWPFParagraphWrapper;
import ass.management.admin.common.word.XWPFTemplate;
import ass.management.admin.common.word.data.HyperLinkTextRenderData;
import ass.management.admin.common.word.data.TextRenderData;
import ass.management.admin.common.word.template.run.RunTemplate;
import ass.management.admin.common.word.util.StyleUtils;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

/**
 * 
 * @author Sayi
 * @version
 */
public class TextRenderPolicy extends AbstractRenderPolicy {

    static final String REGEX_LINE_CHARACTOR = "\\n";

    @Override
    protected boolean validate(Object data) {
        return null != data;
    }

    @Override
    public void doRender(RunTemplate runTemplate, Object renderData, XWPFTemplate template)
            throws Exception {
        XWPFRun run = runTemplate.getRun();

        // create hyper link run
        if (renderData instanceof HyperLinkTextRenderData) {
            run = createHyperLinkRun(runTemplate, renderData);
        }

        // text
        TextRenderData textRenderData = renderData instanceof TextRenderData
                ? (TextRenderData) renderData : new TextRenderData(renderData.toString());

        String data = null == textRenderData.getText() ? "" : textRenderData.getText();

        StyleUtils.styleRun(run, textRenderData.getStyle());

        String[] split = data.split(REGEX_LINE_CHARACTOR);
        if (null != split) {
            run.setText(split[0], 0);
            for (int i = 1; i < split.length; i++) {
                run.addBreak();
                run.setText(split[i]);
            }
        }
    }

    private XWPFRun createHyperLinkRun(RunTemplate runTemplate, Object renderData) {
        XWPFRun run = runTemplate.getRun();
        XWPFParagraphWrapper paragraph = new XWPFParagraphWrapper((XWPFParagraph) run.getParent());
        XWPFRun hyperLinkRun = paragraph.insertNewHyperLinkRun(runTemplate.getRunPos(),
                ((HyperLinkTextRenderData) renderData).getUrl());
        StyleUtils.styleRun(hyperLinkRun, run);
        clearPlaceholder(run);
        return run = hyperLinkRun;
    }

}
