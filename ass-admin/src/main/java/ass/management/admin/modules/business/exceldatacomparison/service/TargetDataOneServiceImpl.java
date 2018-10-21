package ass.management.admin.modules.business.exceldatacomparison.service;

import ass.management.admin.common.excel.model.datacomparison.TargetDataOne;
import ass.management.admin.modules.business.exceldatacomparison.dao.TargetDataOneDao;
import ass.management.db.service.CrudService;
import ass.management.db.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Slf4j
@Service(value = "targetDataOneServiceImpl")
public class TargetDataOneServiceImpl extends CrudService<TargetDataOneDao, TargetDataOne, Integer> {

    @Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public TargetDataOne saveOrUpDateTargetDataOne(TargetDataOne targetDataOne){
        return super.save(targetDataOne);
    }

    public PageUtils<TargetDataOne> targetDataOneQueryPageMap(Map<String, Object> params){
        return super.queryPageMap(params);
    }

}
