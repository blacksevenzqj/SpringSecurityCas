package ass.management.business.visa.service;

import ass.management.db.service.CrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ass.management.business.visa.dao.mybatis.ProceduresDao;
import ass.management.business.visa.entity.Procedures;


@Slf4j
@Service(value = "proceduresServiceImpl")
public class ProceduresServiceImpl extends CrudService<ProceduresDao, Procedures, Integer> {

}
