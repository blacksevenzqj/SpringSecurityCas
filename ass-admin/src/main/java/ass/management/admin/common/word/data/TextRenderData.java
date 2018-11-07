package ass.management.admin.common.word.data;


import ass.management.admin.common.word.data.builder.StyleBuilder;
import ass.management.admin.common.word.data.style.Style;

/**
 * 文本数据
 * 
 * @author Sayi
 * @version 0.0.3
 *
 */
public class TextRenderData implements RenderData {
	protected Style style;

	/**
	 * \n 表示换行
	 */
	protected String text;

	public TextRenderData() {
	}

	public TextRenderData(String text) {
		this.text = text;
	}

	public TextRenderData(String color, String text) {
		this.style = StyleBuilder.newBuilder().buildColor(color).build();
		this.text = text;
	}

	public TextRenderData(String text, Style style) {
		this.style = style;
		this.text = text;
	}

	public Style getStyle() {
		return style;
	}

	public void setStyle(Style style) {
		this.style = style;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
