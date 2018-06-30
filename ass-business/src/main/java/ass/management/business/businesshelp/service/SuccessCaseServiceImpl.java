package ass.management.business.businesshelp.service;

import ass.management.business.businesshelp.entity.SuccessCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ass.management.business.businesshelp.dao.SuccessCaseDao;
import ass.management.db.service.CrudService;

@Slf4j
@Service(value = "successCaseServiceImpl")
public class SuccessCaseServiceImpl extends CrudService<SuccessCaseDao, SuccessCase, Integer> {
}
