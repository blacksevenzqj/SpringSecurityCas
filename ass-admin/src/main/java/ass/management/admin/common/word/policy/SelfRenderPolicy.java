package ass.management.admin.common.word.policy;

import ass.management.admin.common.word.XWPFTemplate;
import ass.management.admin.common.word.template.ElementTemplate;
import ass.management.admin.common.word.template.run.RunTemplate;
import org.apache.poi.xwpf.usermodel.XWPFRun;

/**
 * self render
 * @author Sayi
 * @version 0.0.3
 */
public class SelfRenderPolicy implements RenderPolicy {

	@Override
	public void render(ElementTemplate eleTemplate, Object renderData,
					   XWPFTemplate template) {
		RunTemplate runTemplate = (RunTemplate) eleTemplate;
		XWPFRun run = runTemplate.getRun();
		run.setText(runTemplate.getSource(), 0);
	}

}
