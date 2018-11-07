package ass.management.admin.common.word.resolver;

import ass.management.admin.common.word.config.Configure;
import ass.management.admin.common.word.template.run.RunTemplate;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.util.Set;

/**
 * 
 * @author Sayi
 * @version 1.0.0
 */
public class TemplateFactory{
	
	public static final char EMPTY_CHAR = '\0'; 

	public static RunTemplate createRunTemplate(String tag, Configure config, XWPFRun run) {
		RunTemplate template = new RunTemplate();
		Set<Character> gramerChars = config.getGramerChars();
		char fisrtChar = tag.charAt(0);
		Character symbol = Character.valueOf(EMPTY_CHAR);
		for (Character chara : gramerChars){
			if (chara.equals(fisrtChar)){
				symbol = Character.valueOf(fisrtChar);
				break;
			}
		}
		template.setSource(config.getGramerPrefix() + tag + config.getGramerSuffix());
		template.setTagName(symbol.equals(Character.valueOf(EMPTY_CHAR)) ? tag : tag.substring(1));
		template.setSign(symbol);
		template.setRun(run);
		return template;
	}
	
	

}
