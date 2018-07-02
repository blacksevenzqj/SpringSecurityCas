package ass.management.security.modules.business.ass.service;

import ass.management.db.service.CrudService;
import ass.management.security.modules.business.ass.dao.AssDeviceDataDao;
import ass.management.security.modules.business.ass.entity.DeviceData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service(value = "assDeviceDataServiceImpl")
public class AssDeviceDataServiceImpl extends CrudService<AssDeviceDataDao, DeviceData, Long> {

}
