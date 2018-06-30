package ass.management.business.hot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ass.management.business.hot.dao.TodayNewsDao;
import ass.management.business.hot.entity.TodayNews;
import ass.management.db.service.CrudService;

@Slf4j
@Service(value = "TodayNewsServiceImpl")
public class TodayNewsServiceImpl extends CrudService<TodayNewsDao, TodayNews, Integer> {
}
