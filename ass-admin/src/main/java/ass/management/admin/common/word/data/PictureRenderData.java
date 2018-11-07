package ass.management.admin.common.word.data;


/**
 * 图片渲染数据
 * 
 * @author Sayi
 * @version 0.0.3
 *
 */
public class PictureRenderData implements RenderData {

	/**
	 * 图片宽度
	 */
	private int width;
	/**
	 * 图片高度
	 */
	private int height;
	/**
	 * 图片路径
	 */
	private String path;

	/**
	 * 图片二进制数据
	 */
	private transient byte[] data;

	/**
	 * @param width 宽度
	 * @param height 高度
	 * @param path  本地图片路径
	 */
	public PictureRenderData(int width, int height, String path) {
		this.width = width;
		this.height = height;
		this.path = path;
	}

	/**
	 * @param width 宽度
	 * @param height 高度
	 * @param path 标识图片后缀，如.png、.jpg等
	 * @param data 图片byte[]数据，可以通过工具类{@link BytePictureUtils}生成
	 */
	public PictureRenderData(int width, int height, String path, byte[] data) {
		this.width = width;
		this.height = height;
		this.path = path;
		this.data = data;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

}
