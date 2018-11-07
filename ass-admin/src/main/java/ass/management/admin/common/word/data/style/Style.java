package ass.management.admin.common.word.data.style;

/**
 * 样式
 * 
 * @author Sayi
 * @version 0.0.3
 *
 */
public class Style {

    /**
     * 文字颜色
     */
    private String color;
    /**
     * 字体
     */
    private String fontFamily;
    /**
     * 字体大小
     */
    private int fontSize;
    /**
     * 粗体
     */
    private Boolean isBold;
    /**
     * 斜体
     */
    private Boolean isItalic;
    /**
     * 删除线
     */
    private Boolean isStrike;
    /**
     * 下划线
     */
    private Boolean isUnderLine;

    public Style() {}

    public Style(String color) {
        this.color = color;
    }

    public Style(String fontFamily, int fontSize) {
        this.fontFamily = fontFamily;
        this.fontSize = fontSize;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public Boolean isBold() {
        return isBold;
    }

    public void setBold(Boolean isBold) {
        this.isBold = isBold;
    }

    public Boolean isItalic() {
        return isItalic;
    }

    public void setItalic(Boolean isItalic) {
        this.isItalic = isItalic;
    }

    public Boolean isStrike() {
        return isStrike;
    }

    public void setStrike(Boolean isStrike) {
        this.isStrike = isStrike;
    }

    public Boolean isUnderLine() {
        return isUnderLine;
    }

    public void setUnderLine(Boolean isUnderLine) {
        this.isUnderLine = isUnderLine;
    }
}
