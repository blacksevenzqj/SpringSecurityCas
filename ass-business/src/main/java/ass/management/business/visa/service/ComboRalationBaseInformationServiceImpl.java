package ass.management.business.visa.service;

import ass.management.db.service.CrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ass.management.business.visa.dao.mybatis.ComboRalationBaseInformationDao;
import ass.management.business.visa.entity.ComboRalationBaseInformation;


@Slf4j
@Service(value = "comboRalationBaseInformationServiceImpl")
public class ComboRalationBaseInformationServiceImpl extends CrudService<ComboRalationBaseInformationDao, ComboRalationBaseInformation, Integer> {

}
