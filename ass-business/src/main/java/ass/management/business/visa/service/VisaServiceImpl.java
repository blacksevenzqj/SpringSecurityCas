package ass.management.business.visa.service;

import ass.management.db.service.CrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ass.management.business.visa.dao.mybatis.VisaDao;
import ass.management.business.visa.entity.Visa;


@Slf4j
@Service(value = "visaServiceImpl")
public class VisaServiceImpl extends CrudService<VisaDao, Visa, Integer> {

}
