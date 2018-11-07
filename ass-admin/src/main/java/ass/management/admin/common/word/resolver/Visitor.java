package ass.management.admin.common.word.resolver;

import ass.management.admin.common.word.template.ElementTemplate;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.util.List;

public interface Visitor {

    List<ElementTemplate> visitDocument(XWPFDocument doc);

}
