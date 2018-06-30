package ass.management.business.advisory.service;

import ass.management.db.service.CrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import ass.management.business.advisory.dao.AdvisoryFreeQuestionDao;
import ass.management.business.advisory.entity.AdvisoryFreeQuestion;

@Slf4j
@Service(value = "advisoryFreeQuestionServiceImpl")
public class AdvisoryFreeQuestionServiceImpl extends CrudService<AdvisoryFreeQuestionDao, AdvisoryFreeQuestion, Integer> {
}
