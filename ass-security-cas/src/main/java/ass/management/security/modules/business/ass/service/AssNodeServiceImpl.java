package ass.management.security.modules.business.ass.service;

import ass.management.db.service.CrudService;
import ass.management.security.modules.business.ass.dao.AssNodeDao;
import ass.management.security.modules.business.ass.entity.Node;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service(value = "assNodeServiceImpl")
public class AssNodeServiceImpl extends CrudService<AssNodeDao, Node, Long> {

    public String getNodeName(Long id){
        return getDao().getNodeName(id);
    }

}
