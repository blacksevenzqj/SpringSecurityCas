package ass.management.admin.modules.business.advisory.controller;

import ass.management.admin.modules.business.advisory.service.AdvisoryAdminServiceImpl;
import ass.management.business.advisory.entity.AdvisoryFreeQuestion;
import ass.management.common.validator.ValidatorUtils;
import ass.management.db.utils.PageUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ass.management.admin.common.annotation.SysLog;
import ass.management.admin.common.aspect.SysLogConfig;
import ass.management.admin.modules.sys.controller.AbstractController;
import ass.management.business.advisory.entity.AdvisoryConsultant;
import ass.management.common.utils.R;
import ass.management.common.validator.group.UpdateGroup;

import java.util.Map;

@RestController
@RequestMapping("/advisory")
public class AdvisoryController extends AbstractController {

    @Autowired
    AdvisoryAdminServiceImpl advisoryAdminServiceImpl;

    /**
     * 咨询师
     */
    @RequestMapping(value = "/consultant/list", method = RequestMethod.GET)
    @RequiresPermissions("business:advisory:consultant:list")
    public R consultantList(@RequestParam Map<String, Object> params){
        PageUtils page = advisoryAdminServiceImpl.consultantQueryPageMap(params);
        return R.ok().put("page", page);
    }
    @RequestMapping(value = "/consultant/info/{id}", method = RequestMethod.GET)
    @RequiresPermissions("business:advisory:consultant:info")
    public R consultantInfo(@PathVariable("id") int id){
        return R.ok().put("consultant", advisoryAdminServiceImpl.consultantInfo(id));
    }
    @RequestMapping(value = "/consultant/save", method = RequestMethod.POST)
    @RequiresPermissions("business:advisory:consultant:save")
    @SysLog(SysLogConfig.ADD + SysLogConfig.COLON  + SysLogConfig.ADVISORY + SysLogConfig.COLON + SysLogConfig.CONNSULTANT)
    public R saveConsultant(@RequestBody AdvisoryConsultant advisoryConsultant){
        ValidatorUtils.validateEntity(advisoryConsultant);
        advisoryAdminServiceImpl.saveOrUpDateConsultant(advisoryConsultant);
        return R.ok();
    }
    @RequestMapping(value = "/consultant/update", method = RequestMethod.POST)
    @RequiresPermissions("business:advisory:consultant:update")
    @SysLog(SysLogConfig.UPDATE + SysLogConfig.COLON  + SysLogConfig.ADVISORY + SysLogConfig.COLON + SysLogConfig.CONNSULTANT)
    public R updateConsultant(@RequestBody AdvisoryConsultant advisoryConsultant){
        ValidatorUtils.validateEntity(advisoryConsultant, UpdateGroup.class);
        advisoryAdminServiceImpl.saveOrUpDateConsultant(advisoryConsultant);
        return R.ok();
    }
    @RequestMapping(value = "/consultant/del", method = RequestMethod.POST)
    @RequiresPermissions("business:advisory:consultant:del")
    @SysLog(SysLogConfig.DEL + SysLogConfig.COLON  + SysLogConfig.ADVISORY + SysLogConfig.COLON + SysLogConfig.CONNSULTANT)
    public R delConsultant(@RequestBody Integer[] ids){
        advisoryAdminServiceImpl.delConsultantByIds(ids);
        return R.ok();
    }
    
    /**
     * 往期免费问答
     */
    @RequestMapping(value = "/freequestion/list", method = RequestMethod.GET)
    @RequiresPermissions("business:advisory:freequestion:list")
    public R freeQuestionList(@RequestParam Map<String, Object> params){
        PageUtils page = advisoryAdminServiceImpl.freeQuestionQueryPageMap(params);
        return R.ok().put("page", page);
    }
    @RequestMapping(value = "/freequestion/info/{id}", method = RequestMethod.GET)
    @RequiresPermissions("business:advisory:freequestion:info")
    public R freeQuestionInfo(@PathVariable("id") int id){
        return R.ok().put("freequestion", advisoryAdminServiceImpl.freeQuestionInfo(id));
    }
    @RequestMapping(value = "/freequestion/save", method = RequestMethod.POST)
    @RequiresPermissions("business:advisory:freequestion:save")
    @SysLog(SysLogConfig.ADD + SysLogConfig.COLON  + SysLogConfig.ADVISORY + SysLogConfig.COLON + SysLogConfig.FREEQUESTION)
    public R saveFreeQuestion(@RequestBody AdvisoryFreeQuestion advisoryFreeQuestion){
        ValidatorUtils.validateEntity(advisoryFreeQuestion);
        advisoryAdminServiceImpl.saveOrUpDateFreeQuestion(advisoryFreeQuestion);
        return R.ok();
    }
    @RequestMapping(value = "/freequestion/update", method = RequestMethod.POST)
    @RequiresPermissions("business:advisory:freequestion:update")
    @SysLog(SysLogConfig.UPDATE + SysLogConfig.COLON  + SysLogConfig.ADVISORY + SysLogConfig.COLON + SysLogConfig.FREEQUESTION)
    public R updateFreeQuestion(@RequestBody AdvisoryFreeQuestion advisoryFreeQuestion){
        ValidatorUtils.validateEntity(advisoryFreeQuestion, UpdateGroup.class);
        advisoryAdminServiceImpl.saveOrUpDateFreeQuestion(advisoryFreeQuestion);
        return R.ok();
    }
    @RequestMapping(value = "/freequestion/del", method = RequestMethod.POST)
    @RequiresPermissions("business:advisory:freequestion:del")
    @SysLog(SysLogConfig.DEL + SysLogConfig.COLON  + SysLogConfig.ADVISORY + SysLogConfig.COLON + SysLogConfig.FREEQUESTION)
    public R delFreeQuestion(@RequestBody Integer[] ids){
        advisoryAdminServiceImpl.delFreeQuestionByIds(ids);
        return R.ok();
    }
}
