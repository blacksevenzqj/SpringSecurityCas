package ass.management.business.visa.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ass.management.business.visa.dao.mybatis.NeedKnowDao;
import ass.management.business.visa.entity.NeedKnow;
import ass.management.db.service.CrudService;


@Slf4j
@Service(value = "needKnowServiceImpl")
public class NeedKnowServiceImpl extends CrudService<NeedKnowDao, NeedKnow, Integer> {

}
