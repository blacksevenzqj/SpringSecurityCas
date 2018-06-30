package ass.management.business.visa.dao.mybatis;

import ass.management.business.visa.entity.order.VisaOrderDetail;
import ass.management.business.visa.entity.order.VisaOrderMaster;
import ass.management.db.dao.CrudDao;
import ass.management.business.visa.entity.order.VisaOrderMasterVo;


public interface VisaOrderDao extends CrudDao<VisaOrderMasterVo, Integer> {

    void updateVisaOrderMaster(VisaOrderMaster visaOrderMaster);

    void updateVisaOrderDetail(VisaOrderDetail visaOrderDetail);

}
