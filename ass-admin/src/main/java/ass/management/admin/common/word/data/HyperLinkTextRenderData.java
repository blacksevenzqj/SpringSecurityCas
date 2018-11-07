package ass.management.admin.common.word.data;


import ass.management.admin.common.word.data.builder.StyleBuilder;

/**
 * 超链接
 * 
 * @author Sayi
 * @version 1.4.0
 */
public class HyperLinkTextRenderData extends TextRenderData {

	/**
	 * 超链接或者发送邮件链接("mailto:adasai90@gmail.com?subject=poi-tl")
	 */
	private String url;

	public HyperLinkTextRenderData(String text, String url) {
		super(text);
		this.url = url;
		// 链接默认蓝色加下划线
		this.style = StyleBuilder.newBuilder().buildColor("0000FF").buildUnderLine().build();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
