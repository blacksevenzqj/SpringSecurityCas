package ass.management.business.visa.service;

import ass.management.db.service.CrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ass.management.business.visa.dao.mybatis.MaterialDao;
import ass.management.business.visa.entity.Material;


@Slf4j
@Service(value = "materialServiceImpl")
public class MaterialServiceImpl extends CrudService<MaterialDao, Material, Integer> {

}
