package ass.management.business.visa.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ass.management.business.visa.dao.mybatis.VisaComboDao;
import ass.management.business.visa.entity.VisaCombo;
import ass.management.db.service.CrudService;


@Slf4j
@Service(value = "visaComboServiceImpl")
public class VisaComboServiceImpl extends CrudService<VisaComboDao, VisaCombo, Integer> {

}
