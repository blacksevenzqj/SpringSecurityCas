package ass.management.admin.modules.business.visa.service;

import ass.management.admin.modules.business.visa.entity.MaterialConfig;
import ass.management.admin.modules.business.visa.entity.VisaComboVo;
import ass.management.business.visa.entity.*;
import ass.management.business.visa.entity.order.VisaOrderDetail;
import ass.management.business.visa.service.*;
import ass.management.common.utils.beancopier.CachedBeanCopier;
import ass.management.db.pojo.Paging;
import ass.management.db.utils.PageUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ass.management.admin.modules.business.visa.dao.VisaAdminDao;
import ass.management.admin.modules.business.visa.entity.VisaComboForm;
import ass.management.business.visa.entity.order.VisaOrderMasterVo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
@Service(value = "visaAdminServiceImpl")
public class VisaAdminServiceImpl {

    @Autowired
    VisaAdminDao visaAdminDao;

    /**
     * 1、签证_主类：
     */
    @Autowired
    VisaServiceImpl visaServiceImpl;

    public PageUtils<Visa> visaCountryQueryPageMap(Map<String, Object> params){
        return visaServiceImpl.queryPageMap(params);
    }
    public Visa visaCountryInfo(Integer id){
        return visaServiceImpl.get(id);
    }
    public Visa saveOrUpDateVisaCountry(Visa visa){
        return visaServiceImpl.save(visa);
    }
    public void delVisaCountryByIds(Integer[] ids){
        visaServiceImpl.deleteBatchByIds(ids);
    }

    /**
     * 2.2、套餐_基本信息：一对一
     */
    @Autowired
    BaseInformationServiceImpl baseInformationServiceImpl;

//    @DataSource(name = DataSourceNames.BUSINESS_SYSTEM)
    public PageUtils<BaseInformation> baseInformationQueryPageMap(Map<String, Object> params){
        return baseInformationServiceImpl.queryPageMap(params);
    }
    public BaseInformation baseInformationInfo(Integer id){
        return baseInformationServiceImpl.get(id);
    }
    public BaseInformation saveOrUpDateBaseInformation(BaseInformation baseInformation){
        return baseInformationServiceImpl.save(baseInformation);
    }
    public void delBaseInformationByIds(Integer[] ids){
        baseInformationServiceImpl.deleteBatchByIds(ids);
    }

    /**
     * 3、须知：
     * 套餐 选 须知：
     */
    @Autowired
    NeedKnowServiceImpl needKnowServiceImpl;

    public PageUtils<NeedKnow> needKnowQueryPageMap(Map<String, Object> params){
        return needKnowServiceImpl.queryPageMap(params);
    }
    public NeedKnow needKnowInfo(Integer id){
        return needKnowServiceImpl.get(id);
    }
    public NeedKnow saveOrUpDateNeedKnow(NeedKnow needKnow){
        return needKnowServiceImpl.save(needKnow);
    }
    public void delNeedKnowByIds(Integer[] ids){
        needKnowServiceImpl.deleteBatchByIds(ids);
    }

    /**
     * 4、办理流程：
     * 套餐 选 办理流程：
     */
    @Autowired
    ProceduresServiceImpl proceduresServiceImpl;

    public PageUtils<Procedures> proceduresQueryPageMap(Map<String, Object> params){
        return proceduresServiceImpl.queryPageMap(params);
    }
    public Procedures proceduresInfo(Integer id){
        return proceduresServiceImpl.get(id);
    }
    public Procedures saveOrUpDateProcedures(Procedures procedures){
        return proceduresServiceImpl.save(procedures);
    }
    public void delProceduresByIds(Integer[] ids){
        proceduresServiceImpl.deleteBatchByIds(ids);
    }

    /**
     * 5、所需材料：
     * 套餐 选 角色 再选 所需材料：
     */
    @Autowired
    MaterialServiceImpl materialServiceImpl;

    public PageUtils<Material> materialQueryPageMap(Map<String, Object> params){
        return materialServiceImpl.queryPageMap(params);
    }
    public Material materialInfo(Integer id){
        return materialServiceImpl.get(id);
    }
    public Material saveOrUpDateMaterial(Material material){
        return materialServiceImpl.save(material);
    }
    public void delMaterialByIds(Integer[] ids){
        materialServiceImpl.deleteBatchByIds(ids);
    }


// =====================================================================================================================


    /**
     * 2.3、签证_主类：套餐_主类：套餐_基本信息：关系表
     */
    @Autowired
    ComboRalationBaseInformationServiceImpl comboRalationBaseInformationServiceImpl;

    public ComboRalationBaseInformation saveOrUpDateComboRalationBaseInformation(ComboRalationBaseInformation comboRalationBaseInformation){
        return comboRalationBaseInformationServiceImpl.save(comboRalationBaseInformation);
    }
    public void delComboRalationBaseInformationByIds(Integer[] ids){
        comboRalationBaseInformationServiceImpl.deleteBatchByIds(ids);
    }

    /**
     * 3.1、签证_主类：套餐_主类：须知：关系表
     */
    @Autowired
    ComboRalationNeedKnowServiceImpl comboRalationNeedKnowServiceImpl;

    public ComboRalationNeedKnow saveOrUpDateComboRalationNeedKnow(ComboRalationNeedKnow comboRalationNeedKnow){
        return comboRalationNeedKnowServiceImpl.save(comboRalationNeedKnow);
    }
    public void delComboRalationNeedKnowByIds(Integer[] ids){
        comboRalationNeedKnowServiceImpl.deleteBatchByIds(ids);
    }

    /**
     * 4.1、签证_主类：套餐_主类：办理流程：关系表
     */
    @Autowired
    HandleProceduresServiceImpl handleProceduresServiceImpl;

    public HandleProcedures saveOrUpDateHandleProcedures(HandleProcedures handleProcedures){
        return handleProceduresServiceImpl.save(handleProcedures);
    }
    public void delHandleProceduresByIds(Integer[] ids){
        handleProceduresServiceImpl.deleteBatchByIds(ids);
    }


// =====================================================================================================================

    /**
     * 2.1、套餐_主类：一对一
     */
    @Autowired
    VisaComboServiceImpl visaComboServiceImpl;

    @Autowired
    RoleMaterialServiceImpl roleMaterialServiceImpl;

    public PageUtils<VisaComboVo> visaComboQueryPageMap(Map<String, Object> params){
        if(params.get("pageNum") == null){
            params.put("pageNum", 1);
            params.put("pageSize", 10);
        }
        Paging page = new Paging(Integer.valueOf(params.get("pageNum").toString()), Integer.valueOf(params.get("pageSize").toString()));
        PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderBy());
        List<VisaComboVo> list = visaAdminDao.visaComboVoQueryList(params);
        PageInfo<VisaComboVo> pageInfo = new PageInfo<>(list);
        return new PageUtils<>(pageInfo.getList(), pageInfo.getTotal(), pageInfo.getPageSize(), pageInfo.getPageNum());
    }

    public VisaCombo visaComboInfo(Integer id){
        return visaComboServiceImpl.get(id);
    }

    @Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public VisaCombo saveOrUpDateVisaCombo(VisaComboForm visaComboForm){
        VisaCombo visaCombo = new VisaCombo();
        CachedBeanCopier.defaultCopy(visaComboForm, visaCombo);
        visaCombo =  visaComboServiceImpl.save(visaCombo);

        // 基本信息
        ComboRalationBaseInformation crbInfo = new ComboRalationBaseInformation();
        crbInfo.setVisaId(visaComboForm.getVisaId());
        crbInfo.setComboId(visaCombo.getComboId());
        crbInfo.setBaseInformationId(visaComboForm.getBaseInformationId());
        comboRalationBaseInformationServiceImpl.save(crbInfo);
        // 须知
        Integer[] needKnowIds = visaComboForm.getNeedKnowIds();
        Arrays.sort(needKnowIds);
        List<ComboRalationNeedKnow> comboRalationNeedKnowList = new ArrayList<ComboRalationNeedKnow>();
        for(Integer id : needKnowIds){
            ComboRalationNeedKnow comboRalationNeedKnow = new ComboRalationNeedKnow();
            comboRalationNeedKnow.setVisaId(visaComboForm.getVisaId());
            comboRalationNeedKnow.setComboId(visaCombo.getComboId());
            comboRalationNeedKnow.setNeedKnowId(id);
            comboRalationNeedKnowList.add(comboRalationNeedKnow);
        }
        comboRalationNeedKnowServiceImpl.insertBatch(comboRalationNeedKnowList);
        // 办理流程
        Integer[] proceduresIds = visaComboForm.getProceduresIds();
        Arrays.sort(proceduresIds);
        List<HandleProcedures> handleProceduresList = new ArrayList<HandleProcedures>();
        for(int i=0; i<proceduresIds.length; i++){
            HandleProcedures handleProcedures = new HandleProcedures();
            handleProcedures.setVisaId(visaComboForm.getVisaId());
            handleProcedures.setComboId(visaCombo.getComboId());
            handleProcedures.setProceduresId(proceduresIds[i]);
            handleProcedures.setOrder(i);
            handleProceduresList.add(handleProcedures);
        }
        handleProceduresServiceImpl.insertBatch(handleProceduresList);
        // 所需材料
        //1、学生
        Integer[] studentMaterialIds = visaComboForm.getStudentMaterialIds();
        materialsSave(1, studentMaterialIds, visaComboForm, visaCombo);
        //2、在职
        Integer[] officersMaterialIds = visaComboForm.getOfficersMaterialIds();
        materialsSave(2, officersMaterialIds, visaComboForm, visaCombo);
        //3、退休
        Integer[] retireesMaterialIds = visaComboForm.getRetireesMaterialIds();
        materialsSave(3, retireesMaterialIds, visaComboForm, visaCombo);
        //4、自由职业
        Integer[] freelancerMaterialIds = visaComboForm.getFreelancerMaterialIds();
        materialsSave(4, freelancerMaterialIds, visaComboForm, visaCombo);
        return visaCombo;
    }
    private void materialsSave(int type, Integer[] ids, VisaComboForm visaComboForm, VisaCombo visaCombo){
        Arrays.sort(ids);
        RoleMaterial roleMaterial = new RoleMaterial();
        String idsStr = "";
        for(Integer id : ids){
            idsStr += id.toString() + ",";
        }
        roleMaterial.setMaterialIds(idsStr.substring(0, idsStr.length()-1));
        roleMaterial.setVisaId(visaComboForm.getVisaId());
        roleMaterial.setComboId(visaCombo.getComboId());
        roleMaterial.setMaterialNum(ids.length);
        roleMaterial.setAcceptEmail(visaComboForm.getAcceptEmail());
        roleMaterial.setAcceptAddress(visaComboForm.getAcceptAddress());
        roleMaterial.setRoleType(type);
        switch (type){
        case 1 :
            roleMaterial.setRoleName(MaterialConfig.STUDENT);
            roleMaterial.setDescrption(visaComboForm.getStudentMaterialDesc());
            break;
        case 2:
            roleMaterial.setRoleName(MaterialConfig.OFFICERS);
            roleMaterial.setDescrption(visaComboForm.getOfficersMaterialDesc());
            break;
        case 3:
            roleMaterial.setRoleName(MaterialConfig.RETIREES);
            roleMaterial.setDescrption(visaComboForm.getRetireesMaterialDesc());
            break;
        case 4:
            roleMaterial.setRoleName(MaterialConfig.FREELANCER);
            roleMaterial.setDescrption(visaComboForm.getFreelancerMaterialDesc());
            break;
        default:
            break;
        }
        roleMaterialServiceImpl.save(roleMaterial);
    }

    @Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delVisaComboByIds(Integer[] ids){
        visaComboServiceImpl.deleteBatchByIds(ids);
        visaAdminDao.cbaseInformationDelBatchBycomboIds(ids);
        visaAdminDao.cneedKnowDelBatchBycomboIds(ids);
        visaAdminDao.handleProceduresDelBatchBycomboIds(ids);
        visaAdminDao.roleMaterialDelBatchBycomboIds(ids);
    }



// =====================================================================================================================

    @Autowired
    VisaOrderServiceImpl visaOrderServiceImpl;

    public PageUtils<VisaOrderMasterVo> visaOrderQueryPageMap(Map<String, Object> params){
        return visaOrderServiceImpl.queryPageMap(params);
    }

    @Transactional(readOnly=true, rollbackFor = Exception.class)
    public VisaOrderMasterVo getVisaOrderMasterVo(int id){
        return visaOrderServiceImpl.get(id);
    }

    @Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveOrUpdateVisaOrder(VisaOrderMasterVo visaOrderMasterVo){
        visaOrderServiceImpl.updateVisaOrderMaster(visaOrderMasterVo);
        for(VisaOrderDetail visaOrderDetail : visaOrderMasterVo.getOrderDetailList()) {
            visaOrderDetail.setDelFlag(visaOrderMasterVo.getDelFlag());
            visaOrderServiceImpl.updateVisaOrderDetail(visaOrderDetail);
        }
    }


/**
 * JPA 测试
 */
//    @Autowired
//    BaseInformationRepository baseInformationRepository;
//
//    @Transactional(readOnly = true)
//    public BaseInformation getBaseInformationForJpa(Integer baseInformationId){
//        return baseInformationRepository.findOne(baseInformationId);
//    }
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
//    public BaseInformation saveBaseInformationForJpa(BaseInformation baseInformation){
//        return baseInformationRepository.saveAndFlush(baseInformation);
//    }

}
