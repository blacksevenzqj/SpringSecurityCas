package ass.management.business.visa.service;

import ass.management.db.service.CrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ass.management.business.visa.dao.mybatis.HandleProceduresDao;
import ass.management.business.visa.entity.HandleProcedures;


@Slf4j
@Service(value = "handleProceduresServiceImpl")
public class HandleProceduresServiceImpl extends CrudService<HandleProceduresDao, HandleProcedures, Integer> {

}
