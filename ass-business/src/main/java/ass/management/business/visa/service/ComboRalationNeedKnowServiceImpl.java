package ass.management.business.visa.service;

import ass.management.db.service.CrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ass.management.business.visa.dao.mybatis.ComboRalationNeedKnowDao;
import ass.management.business.visa.entity.ComboRalationNeedKnow;


@Slf4j
@Service(value = "comboRalationNeedKnowServiceImpl")
public class ComboRalationNeedKnowServiceImpl extends CrudService<ComboRalationNeedKnowDao, ComboRalationNeedKnow, Integer> {

}
