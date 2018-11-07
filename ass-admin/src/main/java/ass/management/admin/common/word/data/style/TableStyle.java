package ass.management.admin.common.word.data.style;

import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;

/**
 * 表格样式
 * @author Sayi
 *
 */
public class TableStyle {

	/**
	 * 背景色
	 */
	private String backgroundColor;

	/**
	 * 对齐方式
	 * STJc.LEFT 左对齐
	 * STJc.CENTER 居中对齐
	 * STJc.RIGHT 右对齐
	 */
	private STJc.Enum align;

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public STJc.Enum getAlign() {
		return align;
	}

	public void setAlign(STJc.Enum align) {
		this.align = align;
	}

}
