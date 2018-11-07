package ass.management.admin.common.word.config;

import ass.management.admin.common.word.policy.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 插件化配置
 * 
 * @author Sayi
 * @version 1.0.0
 */
public class Configure {

	// Highest priority
	private Map<String, RenderPolicy> customPolicys = new HashMap<String, RenderPolicy>(8);
	// Low priority
	private Map<Character, RenderPolicy> defaultPolicys = new HashMap<Character, RenderPolicy>();

	private String gramerPrefix = "{{";
	private String gramerSuffix = "}}";

	private Configure() {
		plugin(GramerSymbol.TEXT, new TextRenderPolicy());
		plugin(GramerSymbol.IMAGE, new PictureRenderPolicy());
		plugin(GramerSymbol.TABLE, new SimpleTableRenderPolicy());
		plugin(GramerSymbol.NUMBERIC, new NumbericRenderPolicy());
		plugin(GramerSymbol.DOCX_TEMPLATE, new DocxRenderPolicy());
	}

	/**
	 * 获取默认配置
	 */
	public static Configure createDefault() {
		return newBuilder().build();
	}

	/**
	 * 获取构建器
	 */
	public static ConfigureBuilder newBuilder() {
		return new ConfigureBuilder();
	}

	/**
	 * 新增语法插件
	 * 
	 * @param c
	 *            模板语法
	 * @param policy
	 *            策略
	 */
	public Configure plugin(char c, RenderPolicy policy) {
		defaultPolicys.put(Character.valueOf(c), policy);
		return this;
	}

    Configure plugin(GramerSymbol symbol, RenderPolicy policy) {
        defaultPolicys.put(symbol.getSymbol(), policy);
        return this;
    }

	/**
	 * 自定义模板
	 * 
	 * @param tagName
	 *            模板名称
	 * @param policy
	 *            策略
	 */
	public void customPolicy(String tagName, RenderPolicy policy) {
		customPolicys.put(tagName, policy);
	}

	/**
	 * 获取标签策略
	 * 
	 * @param tagName
	 *            模板名称
	 * @param sign
	 *            语法
	 */
	public RenderPolicy getPolicy(String tagName, Character sign) {
		RenderPolicy policy = getCustomPolicy(tagName);
		return null == policy ? getDefaultPolicy(sign) : policy;
	}

	public Map<Character, RenderPolicy> getDefaultPolicys() {
		return defaultPolicys;
	}

	public Map<String, RenderPolicy> getCustomPolicys() {
		return customPolicys;
	}

	public Set<Character> getGramerChars() {
		return defaultPolicys.keySet();
	}

	public String getGramerPrefix() {
		return gramerPrefix;
	}

	public String getGramerSuffix() {
		return gramerSuffix;
	}

	private RenderPolicy getCustomPolicy(String tagName) {
		return customPolicys.get(tagName);
	}

	private RenderPolicy getDefaultPolicy(Character sign) {
		return defaultPolicys.get(sign);
	}

	public static class ConfigureBuilder {
		private Configure config = new Configure();

		public ConfigureBuilder() {
		}

		public ConfigureBuilder buildGramer(String prefix, String suffix) {
			config.gramerPrefix = prefix;
			config.gramerSuffix = suffix;
			return this;
		}

		public ConfigureBuilder addPlugin(char c, RenderPolicy policy) {
			config.plugin(c, policy);
			return this;
		}

		public ConfigureBuilder customPolicy(String tagName, RenderPolicy policy) {
			config.customPolicy(tagName, policy);
			return this;
		}

		public Configure build() {
			return config;
		}

	}

}
