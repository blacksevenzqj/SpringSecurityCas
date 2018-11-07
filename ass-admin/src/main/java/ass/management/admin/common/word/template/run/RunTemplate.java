package ass.management.admin.common.word.template.run;

import ass.management.admin.common.word.template.ElementTemplate;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.util.List;

/**
 * basic docx template element：run
 * 
 * @author Sayi
 * @version 0.0.1
 */
public class RunTemplate extends ElementTemplate {

	protected XWPFRun run;

	public RunTemplate() {
	}

	public RunTemplate(String tagName, XWPFRun run) {
		this.tagName = tagName;
		this.run = run;
	}
	
	public Integer getRunPos() {
		XWPFParagraph paragraph = (XWPFParagraph) run.getParent();
		List<XWPFRun> runs = paragraph.getRuns();
		for (int i = 0; i < runs.size(); i++) {
			if (run == runs.get(i)) {
				return i;
			}
		}
		return null;
	}

	public XWPFRun getBeforeRun() {
		Integer runPos = getRunPos();
		if (null == runPos)
			return null;
		XWPFParagraph paragraph = (XWPFParagraph) run.getParent();
		return runPos == 0 ? null : paragraph.getRuns().get(runPos - 1);
	}

	public XWPFRun getAfterRun() {
		Integer runPos = getRunPos();
		if (null == runPos)
			return null;
		XWPFParagraph paragraph = (XWPFParagraph) run.getParent();
		return runPos == (paragraph.getRuns().size() - 1) ? null : paragraph
				.getRuns().get(runPos + 1);
	}

	/**
	 * @return the run
	 */
	public XWPFRun getRun() {
		return run;
	}

	/**
	 * @param run
	 *            the run to set
	 */
	public void setRun(XWPFRun run) {
		this.run = run;
	}

	

}
