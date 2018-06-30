package ass.management.security.modules.sys.dao;

import ass.management.db.dao.CrudDao;
import ass.management.security.modules.sys.entity.UserInfo;
import org.apache.ibatis.annotations.Param;


public interface UserInfoDao extends CrudDao<UserInfo, Long> {

    /**
     * 查询用户的所有权限
     */
    UserInfo queryAllPerms(@Param("username") String username);

}
