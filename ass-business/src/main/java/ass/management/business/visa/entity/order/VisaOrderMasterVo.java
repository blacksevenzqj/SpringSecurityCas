package ass.management.business.visa.entity.order;

import lombok.Data;

import java.util.List;

@Data
public class VisaOrderMasterVo extends VisaOrderMaster{

    String userName;

    String countryName;

    String comboName;

    List<VisaOrderDetail> orderDetailList;

}
