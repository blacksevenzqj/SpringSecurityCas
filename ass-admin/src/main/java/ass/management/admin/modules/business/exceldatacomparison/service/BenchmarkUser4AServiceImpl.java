package ass.management.admin.modules.business.exceldatacomparison.service;

import ass.management.admin.common.excel.model.datacomparison.BenchmarkUser4A;
import ass.management.admin.modules.business.exceldatacomparison.dao.BenchmarkUser4ADao;
import ass.management.db.service.CrudService;
import ass.management.db.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Slf4j
@Service(value = "benchmarkUser4AServiceImpl")
public class BenchmarkUser4AServiceImpl extends CrudService<BenchmarkUser4ADao, BenchmarkUser4A, Integer> {

    @Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public BenchmarkUser4A saveOrUpDateTargetDataOne(BenchmarkUser4A targetDataOne){
        return super.save(targetDataOne);
    }

    public PageUtils<BenchmarkUser4A> targetDataOneQueryPageMap(Map<String, Object> params){
        return super.queryPageMap(params);
    }

}
