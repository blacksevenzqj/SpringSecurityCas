package ass.management.admin.common.word.config;

/**
 * 默认模板语法
 * 
 * @author Sayi
 */
public enum GramerSymbol {

    /**
     * 图片
     */
    IMAGE('@'),

    /**
     * 文本
     */
    TEXT('\0'),

    /**
     * 表格
     */
    TABLE('#'),

    /**
     * 列表
     */
    NUMBERIC('*'),

    /**
     * word文档模板
     */
    DOCX_TEMPLATE('+');

    private char symbol;

    private GramerSymbol(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return this.symbol;
    }

    public String toString() {
        return String.valueOf(this.symbol);
    }

}
