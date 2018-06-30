package ass.management.business.businesshelp.service;

import ass.management.db.service.CrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ass.management.business.businesshelp.dao.BusinessHeadlinesDao;
import ass.management.business.businesshelp.entity.BusinessHeadlines;

@Slf4j
@Service(value = "businessHeadlinesServiceImpl")
public class BusinessHeadlinesServiceImpl extends CrudService<BusinessHeadlinesDao, BusinessHeadlines, Integer> {
}
