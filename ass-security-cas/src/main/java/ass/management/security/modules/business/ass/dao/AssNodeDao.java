package ass.management.security.modules.business.ass.dao;

import ass.management.db.dao.CrudDao;
import ass.management.security.modules.business.ass.entity.Node;
import org.apache.ibatis.annotations.Param;

public interface AssNodeDao extends CrudDao<Node, Long> {

    String getNodeName(@Param("id") Long id);

}
