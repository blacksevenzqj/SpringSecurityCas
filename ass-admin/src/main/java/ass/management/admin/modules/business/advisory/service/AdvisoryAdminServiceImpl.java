package ass.management.admin.modules.business.advisory.service;

import ass.management.business.advisory.service.AdvisoryConsultantServiceImpl;
import ass.management.db.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ass.management.business.advisory.entity.AdvisoryConsultant;
import ass.management.business.advisory.entity.AdvisoryFreeQuestion;
import ass.management.business.advisory.service.AdvisoryFreeQuestionServiceImpl;

import java.util.Date;
import java.util.Map;

@Slf4j
@Service(value = "advisoryAdminServiceImpl")
public class AdvisoryAdminServiceImpl {

    @Autowired
    AdvisoryConsultantServiceImpl advisoryConsultantServiceImpl;
    
    @Autowired
    AdvisoryFreeQuestionServiceImpl advisoryFreeQuestionServiceImpl;

    public PageUtils<AdvisoryConsultant> consultantQueryPageMap(Map<String, Object> params){
        return advisoryConsultantServiceImpl.queryPageMap(params);
    }
    
    public AdvisoryConsultant consultantInfo(int id){
        return advisoryConsultantServiceImpl.get(id);
    }

    public AdvisoryConsultant saveOrUpDateConsultant(AdvisoryConsultant advisoryConsultant){
    	advisoryConsultant.setCreateTime(new Date());
        return advisoryConsultantServiceImpl.save(advisoryConsultant);
    }

    public void delConsultantByIds(Integer[] ids){
        advisoryConsultantServiceImpl.deleteBatchByIds(ids);
    }

    public PageUtils<AdvisoryFreeQuestion> freeQuestionQueryPageMap(Map<String, Object> params){
        return advisoryFreeQuestionServiceImpl.queryPageMap(params);
    }
    
    public AdvisoryFreeQuestion freeQuestionInfo(int id){
        return advisoryFreeQuestionServiceImpl.get(id);
    }

    public AdvisoryFreeQuestion saveOrUpDateFreeQuestion(AdvisoryFreeQuestion advisoryFreeQuestion){
    	advisoryFreeQuestion.setCreateTime(new Date());
        return advisoryFreeQuestionServiceImpl.save(advisoryFreeQuestion);
    }

    public void delFreeQuestionByIds(Integer[] ids){
    	advisoryFreeQuestionServiceImpl.deleteBatchByIds(ids);
    }
}
