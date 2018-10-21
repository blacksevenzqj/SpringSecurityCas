package ass.management.admin.modules.business.exceldatacomparison.service;

import ass.management.admin.common.excel.model.datacomparison.OaMatchPerson;
import ass.management.admin.modules.business.exceldatacomparison.dao.OaMatchPersonDao;
import ass.management.db.service.CrudService;
import ass.management.db.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Slf4j
@Service(value = "oaMatchPersonServiceImpl")
public class OaMatchPersonServiceImpl extends CrudService<OaMatchPersonDao, OaMatchPerson, Integer> {

    @Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public OaMatchPerson saveOrUpDateOaMatchPerson(OaMatchPerson oaMatchPerson){
        return super.save(oaMatchPerson);
    }

    public PageUtils<OaMatchPerson> oaMatchPersonQueryPageMap(Map<String, Object> params){
        return super.queryPageMap(params);
    }

}
