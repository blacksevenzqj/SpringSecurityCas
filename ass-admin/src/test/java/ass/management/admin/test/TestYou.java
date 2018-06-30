package ass.management.admin.test;

import ass.management.admin.modules.business.visa.service.VisaAdminServiceImpl;
import ass.management.business.businesshelp.entity.BusinessHeadlines;
import ass.management.business.visa.entity.VisaCombo;
import ass.management.business.visa.service.VisaComboServiceImpl;
import ass.management.db.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ass.management.admin.modules.business.airticket.service.AirTicketOrderAdminServiceImpl;
import ass.management.admin.modules.business.businesshelp.service.BusinessHelpAdminServiceImpl;
import ass.management.business.airticket.entity.vo.AirTicketOrderVo;
import ass.management.business.visa.entity.order.VisaOrderMasterVo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestYou {

    @Autowired
    BusinessHelpAdminServiceImpl businessHelpAdminServiceImpl;

    @Autowired
    VisaComboServiceImpl visaComboServiceImpl;

    @Autowired
    VisaAdminServiceImpl visaAdminServiceImpl;

    @Autowired
    AirTicketOrderAdminServiceImpl airTicketOrderAdminServiceImpl;

    @Test
    public void airOrder() {
        Map map = new HashMap();
        map.put("orderNum", "at20180118103738XBPAERNWiTQLzzR9");
        PageUtils<AirTicketOrderVo> page = airTicketOrderAdminServiceImpl.airTicketOrderQueryPageMap(map);
        System.out.println(page);
    }


    @Test
    public void visaOrderMasterVo() {
        VisaOrderMasterVo visaOrderMasterVo = visaAdminServiceImpl.getVisaOrderMasterVo(175);
        System.out.println(visaOrderMasterVo);
        PageUtils<VisaOrderMasterVo> pageUtils = visaAdminServiceImpl.visaOrderQueryPageMap(new HashMap<>());
    }

    @Test
    public void businessHelp() {
        Map map = new HashMap();
        PageUtils<BusinessHeadlines> pageUtils = businessHelpAdminServiceImpl.headlinesQueryPageMap(map);
        System.out.println(pageUtils);
        for(BusinessHeadlines bl : pageUtils.getList()){
            System.out.println(bl);
        }
    }

    // 注意 测试时需要再 CrudService 上注解 @DataSource注解，切换数据源
    @Test
    public void visaCombo() {
        VisaCombo visaCombo = new VisaCombo();
        visaCombo.setVisaId(123);
        visaCombo.setComboNum(123);
        visaCombo.setName("333");
        visaCombo.setDescription("666");
        visaCombo.setMarketPrice(new BigDecimal(100));
        visaCombo.setPrice(new BigDecimal(100));
        System.out.println(visaComboServiceImpl.save(visaCombo));
    }



}
