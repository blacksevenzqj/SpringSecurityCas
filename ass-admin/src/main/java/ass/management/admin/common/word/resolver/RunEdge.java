package ass.management.admin.common.word.resolver;

/**
 * 奔跑的边界
 * 
 * @author Sayi
 * @version 0.0.2
 */
public class RunEdge {

	/**
	 * 起始文本在段落内部的全局位置
	 */
	private int allEdge;

	/**
	 * 起始文本在run内部的位置
	 */
	private int runEdge;
	
	/**
     * 起始文本所在的run在段落内的位置
     */
    private int runPos;

	private String tag;
	
	private String text;

	public RunEdge(int allPos, String tag) {
		this.allEdge = allPos;
		this.tag = tag;
	}

	public int getAllEdge() {
		return allEdge;
	}

	public void setAllEdge(int allEdge) {
		this.allEdge = allEdge;
	}

	public int getRunPos() {
		return runPos;
	}

	public void setRunPos(int runPos) {
		this.runPos = runPos;
	}

	public int getRunEdge() {
		return runEdge;
	}

	public void setRunEdge(int runEdge) {
		this.runEdge = runEdge;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "段落内第" + runPos + "个run,标签为" + tag + ", 在这个run内的边界位置为：" + runEdge;
	}

}
