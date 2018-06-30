package ass.management.business.visa.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ass.management.business.visa.dao.mybatis.RoleMaterialDao;
import ass.management.business.visa.entity.RoleMaterial;
import ass.management.db.service.CrudService;


@Slf4j
@Service(value = "roleMaterialServiceImpl")
public class RoleMaterialServiceImpl extends CrudService<RoleMaterialDao, RoleMaterial, Integer> {

}
