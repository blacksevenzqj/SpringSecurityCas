package ass.management.admin.common.word.render;

import ass.management.admin.common.word.XWPFTemplate;
import ass.management.admin.common.word.config.Configure;
import ass.management.admin.common.word.data.TextRenderData;
import ass.management.admin.common.word.el.ELObject;
import ass.management.admin.common.word.exception.RenderException;
import ass.management.admin.common.word.policy.DocxRenderPolicy;
import ass.management.admin.common.word.policy.RenderPolicy;
import ass.management.admin.common.word.template.ElementTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @author Sayi
 * @version
 * @since 0.0.3
 */
public class RenderAPI {

	private static final Logger logger = LoggerFactory.getLogger(RenderAPI.class);

	public static void render(XWPFTemplate template, Object dataModel) {

		Objects.requireNonNull(template, "Template is null, should be setted first.");
		Objects.requireNonNull(dataModel, "Data-Model is null, should be setted first.");

		int docxCount = 0;
		Configure config = template.getConfig();

		// 模板
		List<ElementTemplate> elementTemplates = template.getElementTemplates();
		if (null == elementTemplates || elementTemplates.isEmpty())
			return;
		// 策略
		RenderPolicy policy = null;
		// 数据模型
		ELObject elObject = ELObject.create(dataModel);

		for (ElementTemplate runTemplate : elementTemplates) {
			policy = config.getPolicy(runTemplate.getTagName(), runTemplate.getSign());
			if (null == policy) {
				throw new RenderException("Cannot find render policy: [" + runTemplate.getTagName() + "]");
			}

			if (policy instanceof DocxRenderPolicy) {
				docxCount++;
			} else {
				doRender(runTemplate, elObject, policy, template);
			}
		}

		try {
			if (docxCount >= 1)
				template.reload(template.getXWPFDocument().generate());

			for (int i = 0; i < docxCount; i++) {
				elementTemplates = template.getElementTemplates();
				if (null == elementTemplates || elementTemplates.isEmpty())
					break;

				for (ElementTemplate runTemplate : elementTemplates) {
					policy = config.getPolicy(runTemplate.getTagName(), runTemplate.getSign());
					if (null == policy || !(policy instanceof DocxRenderPolicy))
						continue;

					doRender(runTemplate, elObject, policy, template);
					break;
				}
			}
		} catch (Exception e) {
			logger.error("Render docx error", e);
		}

	}

	private static void doRender(ElementTemplate ele, ELObject model, RenderPolicy policy, XWPFTemplate template) {
		logger.debug("Start render TemplateName:{}, Sign:{}, policy:{}", ele.getTagName(), ele.getSign(),
				policy.getClass().getSimpleName());
		policy.render(ele, model.eval(ele.getTagName()), template);
	}

	/**
	 * 自我渲染
	 * 
	 * @param template
	 */
	public static void selfRender(XWPFTemplate template) {
		Objects.requireNonNull(template, "Template is null, should be setted first.");
		List<ElementTemplate> elementTemplates = template.getElementTemplates();
		if (null == elementTemplates || elementTemplates.isEmpty())
			return;
		RenderPolicy policy = null;
		for (ElementTemplate runTemplate : elementTemplates) {
			logger.debug("Start self-render TemplateName:{}, Sign:{}", runTemplate.getTagName(), runTemplate.getSign());
			policy = template.getConfig().getDefaultPolicys().get(Character.valueOf('\0'));
			policy.render(runTemplate, new TextRenderData(runTemplate.getSource()), template);
		}
	}

	/**
	 * 协助调试：判断是否有缺失模板
	 * 
	 * @param template
	 * @param datas
	 */
	// TODO 数据模型为对象而不仅仅是Map
	@Deprecated
	public static void debug(XWPFTemplate template, Map<String, Object> datas) {
		List<ElementTemplate> all = template.getElementTemplates();
		logger.debug("Template tag number is:{}", (null == all ? 0 : all.size()));
		if ((all == null || all.isEmpty()) && (null == datas || datas.isEmpty())) {
			logger.debug("No template gramer find and no render data find");
			return;
		}
		Set<String> tagtKeys = new HashSet<String>();
		for (ElementTemplate ele : all) {
			logger.debug("Parse the tag：{}", ele.getTagName());
			tagtKeys.add(ele.getTagName());
		}

		Set<String> keySet = datas.keySet();
		HashSet<String> copySet = new HashSet<String>(keySet);

		copySet.removeAll(tagtKeys);
		Iterator<String> iterator = copySet.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			logger.warn("Cannot find the gramer tag in template:" + key);
		}
		tagtKeys.removeAll(keySet);
		iterator = tagtKeys.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			logger.warn("Cannot find the feild in java Map or Object:" + key);
		}

	}

}
