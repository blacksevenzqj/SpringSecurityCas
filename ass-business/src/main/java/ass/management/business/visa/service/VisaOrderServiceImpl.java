package ass.management.business.visa.service;

import ass.management.business.visa.entity.order.VisaOrderDetail;
import ass.management.business.visa.entity.order.VisaOrderMaster;
import ass.management.db.service.CrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ass.management.business.visa.dao.mybatis.VisaOrderDao;
import ass.management.business.visa.entity.order.VisaOrderMasterVo;


@Slf4j
@Service(value = "visaOrderServiceImpl")
public class VisaOrderServiceImpl extends CrudService<VisaOrderDao, VisaOrderMasterVo, Integer> {

    public void updateVisaOrderMaster(VisaOrderMaster visaOrderMaster){
        getDao().updateVisaOrderMaster(visaOrderMaster);
    }

    public void updateVisaOrderDetail(VisaOrderDetail visaOrderDetail){
        getDao().updateVisaOrderDetail(visaOrderDetail);
    }


}
