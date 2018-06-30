package ass.management.admin.modules.business.visa.controller;

import ass.management.admin.common.aspect.SysLogConfig;
import ass.management.admin.modules.business.visa.entity.VisaComboForm;
import ass.management.admin.modules.business.visa.service.VisaAdminServiceImpl;
import ass.management.admin.modules.sys.controller.AbstractController;
import ass.management.business.visa.entity.*;
import ass.management.common.utils.R;
import ass.management.common.validator.ValidatorUtils;
import ass.management.db.utils.PageUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ass.management.admin.common.annotation.SysLog;
import ass.management.admin.modules.business.businesshelp.service.BusinessHelpAdminServiceImpl;
import ass.management.business.visa.entity.order.VisaOrderMasterVo;
import ass.management.common.validator.group.UpdateGroup;

import java.util.Map;

@RestController
@RequestMapping("/visa")
public class VisaController extends AbstractController {

    @Autowired
    BusinessHelpAdminServiceImpl businessHelpAdminServiceImpl;

    @Autowired
    VisaAdminServiceImpl visaAdminServiceImpl;

    /**
     * 国家
     */
    @RequestMapping(value = "/country/list", method = RequestMethod.GET)
    @RequiresPermissions("business:visa:country:list")
    public R countryList(@RequestParam Map<String, Object> params){
        PageUtils page = visaAdminServiceImpl.visaCountryQueryPageMap(params);
        return R.ok().put("page", page);
    }
    @RequestMapping(value = "/country/info/{id}", method = RequestMethod.GET)
    @RequiresPermissions("business:visa:country:info")
    public R countryInfo(@PathVariable("id") int id){
        return R.ok().put("country", visaAdminServiceImpl.visaCountryInfo(id));
    }
    @RequestMapping(value = "/country/save", method = RequestMethod.POST)
    @RequiresPermissions("business:visa:country:save")
    @SysLog(SysLogConfig.ADD + SysLogConfig.COLON  + SysLogConfig.VISA + SysLogConfig.COLON + SysLogConfig.COUNTRY)
    public R saveCountry(@RequestBody Visa visa){
        ValidatorUtils.validateEntity(visa);
        visaAdminServiceImpl.saveOrUpDateVisaCountry(visa);
        return R.ok();
    }
    @RequestMapping(value = "/country/update", method = RequestMethod.POST)
    @RequiresPermissions("business:visa:country:update")
    @SysLog(SysLogConfig.UPDATE + SysLogConfig.COLON  + SysLogConfig.VISA + SysLogConfig.COLON + SysLogConfig.COUNTRY)
    public R updateCountry(@RequestBody Visa visa){
        ValidatorUtils.validateEntity(visa, UpdateGroup.class);
        visaAdminServiceImpl.saveOrUpDateVisaCountry(visa);
        return R.ok();
    }
    @RequestMapping(value = "/country/del", method = RequestMethod.POST)
    @RequiresPermissions("business:visa:country:del")
    @SysLog(SysLogConfig.DEL + SysLogConfig.COLON  + SysLogConfig.VISA + SysLogConfig.COLON + SysLogConfig.COUNTRY)
    public R delCountry(@RequestBody Integer[] ids){
        visaAdminServiceImpl.delVisaCountryByIds(ids);
        return R.ok();
    }


    /**
     * 基本信息
     */
    @RequestMapping(value = "/baseInformation/list", method = RequestMethod.GET)
    @RequiresPermissions("business:visa:baseInformation:list")
    public R baseInformationList(@RequestParam Map<String, Object> params){
        PageUtils page = visaAdminServiceImpl.baseInformationQueryPageMap(params);
        return R.ok().put("page", page);
    }
    @RequestMapping(value = "/baseInformation/info/{id}", method = RequestMethod.GET)
    @RequiresPermissions("business:visa:baseInformation:info")
    public R baseInformationInfo(@PathVariable("id") int id){
        return R.ok().put("baseInformation", visaAdminServiceImpl.baseInformationInfo(id));
    }
    @RequestMapping(value = "/baseInformation/save", method = RequestMethod.POST)
    @RequiresPermissions("business:visa:baseInformation:save")
    @SysLog(SysLogConfig.ADD + SysLogConfig.COLON  + SysLogConfig.VISA + SysLogConfig.COLON + SysLogConfig.BASEINFORMATION)
    public R saveBaseInformation(@RequestBody BaseInformation baseInformation){
        ValidatorUtils.validateEntity(baseInformation);
        visaAdminServiceImpl.saveOrUpDateBaseInformation(baseInformation);
        return R.ok();
    }
    @RequestMapping(value = "/baseInformation/update", method = RequestMethod.POST)
    @RequiresPermissions("business:visa:baseInformation:update")
    @SysLog(SysLogConfig.UPDATE + SysLogConfig.COLON  + SysLogConfig.VISA + SysLogConfig.COLON + SysLogConfig.BASEINFORMATION)
    public R updateBaseInformation(@RequestBody BaseInformation baseInformation){
        ValidatorUtils.validateEntity(baseInformation, UpdateGroup.class);
        visaAdminServiceImpl.saveOrUpDateBaseInformation(baseInformation);
        return R.ok();
    }
    @RequestMapping(value = "/baseInformation/del", method = RequestMethod.POST)
    @RequiresPermissions("business:visa:baseInformation:del")
    @SysLog(SysLogConfig.DEL + SysLogConfig.COLON  + SysLogConfig.VISA + SysLogConfig.COLON + SysLogConfig.BASEINFORMATION)
    public R delBaseInformation(@RequestBody Integer[] ids){
        visaAdminServiceImpl.delBaseInformationByIds(ids);
        return R.ok();
    }


    /**
     * 须知
     */
    @RequestMapping(value = "/needKnow/list", method = RequestMethod.GET)
    @RequiresPermissions("business:visa:needKnow:list")
    public R needKnowList(@RequestParam Map<String, Object> params){
        PageUtils page = visaAdminServiceImpl.needKnowQueryPageMap(params);
        return R.ok().put("page", page);
    }
    @RequestMapping(value = "/needKnow/info/{id}", method = RequestMethod.GET)
    @RequiresPermissions("business:visa:needKnow:info")
    public R needKnowInfo(@PathVariable("id") int id){
        return R.ok().put("needKnow", visaAdminServiceImpl.needKnowInfo(id));
    }
    @RequestMapping(value = "/needKnow/save", method = RequestMethod.POST)
    @RequiresPermissions("business:visa:needKnow:save")
    @SysLog(SysLogConfig.ADD + SysLogConfig.COLON  + SysLogConfig.VISA + SysLogConfig.COLON + SysLogConfig.NEEDKNOW)
    public R saveNeedKnow(@RequestBody NeedKnow needKnow){
        ValidatorUtils.validateEntity(needKnow);
        visaAdminServiceImpl.saveOrUpDateNeedKnow(needKnow);
        return R.ok();
    }
    @RequestMapping(value = "/needKnow/update", method = RequestMethod.POST)
    @RequiresPermissions("business:visa:needKnow:update")
    @SysLog(SysLogConfig.UPDATE + SysLogConfig.COLON  + SysLogConfig.VISA + SysLogConfig.COLON + SysLogConfig.NEEDKNOW)
    public R updateNeedKnow(@RequestBody NeedKnow needKnown){
        ValidatorUtils.validateEntity(needKnown, UpdateGroup.class);
        visaAdminServiceImpl.saveOrUpDateNeedKnow(needKnown);
        return R.ok();
    }
    @RequestMapping(value = "/needKnow/del", method = RequestMethod.POST)
    @RequiresPermissions("business:visa:needKnow:del")
    @SysLog(SysLogConfig.DEL + SysLogConfig.COLON  + SysLogConfig.VISA + SysLogConfig.COLON + SysLogConfig.NEEDKNOW)
    public R delNeedKnow(@RequestBody Integer[] ids){
        visaAdminServiceImpl.delNeedKnowByIds(ids);
        return R.ok();
    }


    /**
     * 办理流程
     */
    @RequestMapping(value = "/procedures/list", method = RequestMethod.GET)
    @RequiresPermissions("business:visa:procedures:list")
    public R proceduresList(@RequestParam Map<String, Object> params){
        PageUtils page = visaAdminServiceImpl.proceduresQueryPageMap(params);
        return R.ok().put("page", page);
    }
    @RequestMapping(value = "/procedures/info/{id}", method = RequestMethod.GET)
    @RequiresPermissions("business:visa:procedures:info")
    public R proceduresInfo(@PathVariable("id") int id){
        return R.ok().put("procedures", visaAdminServiceImpl.proceduresInfo(id));
    }
    @RequestMapping(value = "/procedures/save", method = RequestMethod.POST)
    @RequiresPermissions("business:visa:procedures:save")
    @SysLog(SysLogConfig.ADD + SysLogConfig.COLON  + SysLogConfig.VISA + SysLogConfig.COLON + SysLogConfig.PROCEDURES)
    public R saveProcedures(@RequestBody Procedures procedures){
        ValidatorUtils.validateEntity(procedures);
        visaAdminServiceImpl.saveOrUpDateProcedures(procedures);
        return R.ok();
    }
    @RequestMapping(value = "/procedures/update", method = RequestMethod.POST)
    @RequiresPermissions("business:visa:procedures:update")
    @SysLog(SysLogConfig.UPDATE + SysLogConfig.COLON  + SysLogConfig.VISA + SysLogConfig.COLON + SysLogConfig.PROCEDURES)
    public R updateProcedures(@RequestBody Procedures procedures){
        ValidatorUtils.validateEntity(procedures, UpdateGroup.class);
        visaAdminServiceImpl.saveOrUpDateProcedures(procedures);
        return R.ok();
    }
    @RequestMapping(value = "/procedures/del", method = RequestMethod.POST)
    @RequiresPermissions("business:visa:procedures:del")
    @SysLog(SysLogConfig.DEL + SysLogConfig.COLON  + SysLogConfig.VISA + SysLogConfig.COLON + SysLogConfig.PROCEDURES)
    public R delProcedures(@RequestBody Integer[] ids){
        visaAdminServiceImpl.delProceduresByIds(ids);
        return R.ok();
    }


    /**
     * 所需材料
     */
    @RequestMapping(value = "/material/list", method = RequestMethod.GET)
    @RequiresPermissions("business:visa:material:list")
    public R materialList(@RequestParam Map<String, Object> params){
        PageUtils page = visaAdminServiceImpl.materialQueryPageMap(params);
        return R.ok().put("page", page);
    }
    @RequestMapping(value = "/material/info/{id}", method = RequestMethod.GET)
    @RequiresPermissions("business:visa:material:info")
    public R materialInfo(@PathVariable("id") int id){
        return R.ok().put("material", visaAdminServiceImpl.materialInfo(id));
    }
    @RequestMapping(value = "/material/save", method = RequestMethod.POST)
    @RequiresPermissions("business:visa:material:save")
    @SysLog(SysLogConfig.ADD + SysLogConfig.COLON  + SysLogConfig.VISA + SysLogConfig.COLON + SysLogConfig.MATERIAL)
    public R saveMaterial(@RequestBody Material material){
        ValidatorUtils.validateEntity(material);
        visaAdminServiceImpl.saveOrUpDateMaterial(material);
        return R.ok();
    }
    @RequestMapping(value = "/material/update", method = RequestMethod.POST)
    @RequiresPermissions("business:visa:material:update")
    @SysLog(SysLogConfig.UPDATE + SysLogConfig.COLON  + SysLogConfig.VISA + SysLogConfig.COLON + SysLogConfig.MATERIAL)
    public R updateMaterial(@RequestBody Material material){
        ValidatorUtils.validateEntity(material, UpdateGroup.class);
        visaAdminServiceImpl.saveOrUpDateMaterial(material);
        return R.ok();
    }
    @RequestMapping(value = "/material/del", method = RequestMethod.POST)
    @RequiresPermissions("business:visa:material:del")
    @SysLog(SysLogConfig.DEL + SysLogConfig.COLON  + SysLogConfig.VISA + SysLogConfig.COLON + SysLogConfig.MATERIAL)
    public R delMaterial(@RequestBody Integer[] ids){
        visaAdminServiceImpl.delMaterialByIds(ids);
        return R.ok();
    }



    /**
     * 签证套餐
     */
    @RequestMapping(value = "/visaCombo/list", method = RequestMethod.GET)
    @RequiresPermissions("business:visa:visaCombo:list")
    public R visaComboList(@RequestParam Map<String, Object> params){
        PageUtils page = visaAdminServiceImpl.visaComboQueryPageMap(params);
        return R.ok().put("page", page);
    }
    @RequestMapping(value = "/visaCombo/info/{id}", method = RequestMethod.GET)
    @RequiresPermissions("business:visa:visaCombo:info")
    public R visaComboInfo(@PathVariable("id") int id){
        return R.ok().put("visaCombo", visaAdminServiceImpl.visaComboInfo(id));
    }
    @RequestMapping(value = "/visaCombo/save", method = RequestMethod.POST)
    @RequiresPermissions("business:visa:visaCombo:save")
    @SysLog(SysLogConfig.ADD + SysLogConfig.COLON  + SysLogConfig.VISA + SysLogConfig.COLON + SysLogConfig.VISACOMBO)
    public R saveVisaCombo(@RequestBody VisaComboForm visaComboForm){
        ValidatorUtils.validateEntity(visaComboForm);
        visaAdminServiceImpl.saveOrUpDateVisaCombo(visaComboForm);
        return R.ok();
    }
    @RequestMapping(value = "/visaCombo/del", method = RequestMethod.POST)
    @RequiresPermissions("business:visa:visaCombo:del")
    @SysLog(SysLogConfig.DEL + SysLogConfig.COLON  + SysLogConfig.VISA + SysLogConfig.COLON + SysLogConfig.MATERIAL)
    public R delVisaCombo(@RequestBody Integer[] ids){
        visaAdminServiceImpl.delVisaComboByIds(ids);
        return R.ok();
    }


    /**
     * 签证订单
     */
    @RequestMapping(value = "/order/list", method = RequestMethod.GET)
    @RequiresPermissions("business:visa:order:list")
    public R visaOrderList(@RequestParam Map<String, Object> params){
        PageUtils page = visaAdminServiceImpl.visaOrderQueryPageMap(params);
        return R.ok().put("page", page);
    }
    @RequestMapping(value = "/order/info/{id}", method = RequestMethod.GET)
    @RequiresPermissions("business:visa:order:info")
    public R visaOrderInfo(@PathVariable("id") int id){
        return R.ok().put("visaOrder", visaAdminServiceImpl.getVisaOrderMasterVo(id));
    }
    @RequestMapping(value = "/order/update", method = RequestMethod.POST)
    @RequiresPermissions("business:visa:order:update")
    @SysLog(SysLogConfig.UPDATE + SysLogConfig.COLON  + SysLogConfig.VISA + SysLogConfig.COLON + SysLogConfig.VISAORDER)
    public R updateVisaOrder(@RequestBody VisaOrderMasterVo visaOrderMasterVo){
        ValidatorUtils.validateEntity(visaOrderMasterVo, UpdateGroup.class);
        visaAdminServiceImpl.saveOrUpdateVisaOrder(visaOrderMasterVo);
        return R.ok();
    }

}
