package ass.management.admin.common.word.policy;

import ass.management.admin.common.word.NiceXWPFDocument;
import ass.management.admin.common.word.XWPFTemplate;
import ass.management.admin.common.word.data.PictureRenderData;
import ass.management.admin.common.word.template.run.RunTemplate;
import ass.management.admin.common.word.util.BytePictureUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileInputStream;

public class PictureRenderPolicy extends AbstractRenderPolicy {

    @Override
    protected boolean validate(Object data) {
        if (null == data) return false;

        if (!(data instanceof PictureRenderData)) {
            logger.error("Error datamodel: correct type is PictureRenderData, but is "
                    + data.getClass());
            return false;
        }

        return (null != ((PictureRenderData) data).getData()
                || null != ((PictureRenderData) data).getPath());
    }

    @Override
    public void doRender(RunTemplate runTemplate, Object model, XWPFTemplate template)
            throws Exception {
        // 如果出现异常，图片不存在，优先清空标签
        clearPlaceholder(runTemplate.getRun());

        PictureRenderData picture = (PictureRenderData) model;
        NiceXWPFDocument doc = template.getXWPFDocument();
        int suggestFileType = suggestFileType(picture.getPath());

        byte[] data = null == picture.getData()
                ? BytePictureUtils.toByteArray(new FileInputStream(picture.getPath()))
                : picture.getData();
        String blipId = doc.addPictureData(data, suggestFileType);
        doc.addPicture(blipId, doc.getNextPicNameNumber(suggestFileType), picture.getWidth(),
                picture.getHeight(), runTemplate.getRun());
    }

    public static int suggestFileType(String imgFile) {
        int format = 0;

        if (imgFile.endsWith(".emf")) format = XWPFDocument.PICTURE_TYPE_EMF;
        else if (imgFile.endsWith(".wmf")) format = XWPFDocument.PICTURE_TYPE_WMF;
        else if (imgFile.endsWith(".pict")) format = XWPFDocument.PICTURE_TYPE_PICT;
        else if (imgFile.endsWith(".jpeg") || imgFile.endsWith(".jpg"))
            format = XWPFDocument.PICTURE_TYPE_JPEG;
        else if (imgFile.endsWith(".png")) format = XWPFDocument.PICTURE_TYPE_PNG;
        else if (imgFile.endsWith(".dib")) format = XWPFDocument.PICTURE_TYPE_DIB;
        else if (imgFile.endsWith(".gif")) format = XWPFDocument.PICTURE_TYPE_GIF;
        else if (imgFile.endsWith(".tiff")) format = XWPFDocument.PICTURE_TYPE_TIFF;
        else if (imgFile.endsWith(".eps")) format = XWPFDocument.PICTURE_TYPE_EPS;
        else if (imgFile.endsWith(".bmp")) format = XWPFDocument.PICTURE_TYPE_BMP;
        else if (imgFile.endsWith(".wpg")) format = XWPFDocument.PICTURE_TYPE_WPG;
        else {
            logger.error("Unsupported picture: " + imgFile
                    + ". Expected emf|wmf|pict|jpeg|png|dib|gif|tiff|eps|bmp|wpg");
        }
        return format;
    }

}
