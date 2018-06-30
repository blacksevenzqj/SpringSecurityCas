package ass.management.business.visa.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ass.management.business.visa.dao.mybatis.BaseInformationDao;
import ass.management.business.visa.entity.BaseInformation;
import ass.management.db.service.CrudService;


@Slf4j
@Service(value = "baseInformationServiceImpl")
public class BaseInformationServiceImpl extends CrudService<BaseInformationDao, BaseInformation, Integer> {

}
