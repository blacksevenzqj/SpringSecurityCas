package ass.management.admin.modules.business.airticket.controller;

import ass.management.db.utils.PageUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ass.management.admin.modules.business.airticket.service.AirTicketOrderAdminServiceImpl;
import ass.management.admin.modules.sys.controller.AbstractController;
import ass.management.common.utils.R;

import java.util.Map;

@RestController
@RequestMapping("/airTicket")
public class AirTicketOrderController extends AbstractController {

    @Autowired
    AirTicketOrderAdminServiceImpl airTicketOrderAdminServiceImpl;

    /**
     * 机票订单
     */
    @RequestMapping(value = "/airTicketOrder/list", method = RequestMethod.GET)
    @RequiresPermissions("business:airTicket:airTicketOrder:list")
    public R countryList(@RequestParam Map<String, Object> params){
        PageUtils page = airTicketOrderAdminServiceImpl.airTicketOrderQueryPageMap(params);
        return R.ok().put("page", page);
    }
//    @RequestMapping(value = "/airTicketOrder/info/{id}", method = RequestMethod.GET)
//    @RequiresPermissions("business:airTicket:airTicketOrder:info")
//    public R countryInfo(@PathVariable("id") int id){
//        return R.ok().put("airTicketOrder", visaAdminServiceImpl.visaCountryInfo(id));
//    }
//    @RequestMapping(value = "/airTicketOrder/update", method = RequestMethod.POST)
//    @RequiresPermissions("business:airTicket:airTicketOrder:update")
//    @SysLog(SysLogConfig.UPDATE + SysLogConfig.COLON  + SysLogConfig.VISA + SysLogConfig.COLON + SysLogConfig.COUNTRY)
//    public R updateCountry(@RequestBody Visa visa){
//        ValidatorUtils.validateEntity(visa, UpdateGroup.class);
//        visaAdminServiceImpl.saveOrUpDateVisaCountry(visa);
//        return R.ok();
//    }


}
