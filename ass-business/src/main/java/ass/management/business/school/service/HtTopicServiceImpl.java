package ass.management.business.school.service;

import ass.management.db.service.CrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import ass.management.business.school.dao.HtTopicDao;
import ass.management.business.school.entity.HtTopic;

@Slf4j
@Service(value = "topicServiceImpl")
public class HtTopicServiceImpl extends CrudService<HtTopicDao, HtTopic, Integer> {
}
