package ass.management.admin.test.word.el;


import ass.management.admin.common.word.data.PictureRenderData;
import ass.management.admin.common.word.data.TextRenderData;

public class Author {

    private String name;
    private TextRenderData alias;
    private PictureRenderData avatar;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TextRenderData getAlias() {
        return alias;
    }

    public void setAlias(TextRenderData alias) {
        this.alias = alias;
    }

    public PictureRenderData getAvatar() {
        return avatar;
    }

    public void setAvatar(PictureRenderData avatar) {
        this.avatar = avatar;
    }

}
