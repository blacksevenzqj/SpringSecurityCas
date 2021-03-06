package ass.management.admin.common.word.policy;

import ass.management.admin.common.word.XWPFTemplate;
import ass.management.admin.common.word.template.ElementTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface RenderPolicy {

	Logger logger = LoggerFactory.getLogger(RenderPolicy.class);

	/**
	 * @param eleTemplate 模板元素
	 * @param data 数据
	 * @param template 持有模板对象
	 */
	void render(ElementTemplate eleTemplate, Object data, XWPFTemplate template);

}
