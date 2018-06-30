package ass.management.security.modules.sys.service;

import ass.management.db.service.CrudService;
import ass.management.security.modules.sys.dao.UserInfoDao;
import ass.management.security.modules.sys.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service(value = "userInfoServiceImpl")
public class UserInfoServiceImpl extends CrudService<UserInfoDao, UserInfo, Long> {

    /**
     * 查询用户的所有权限
     */
    public UserInfo queryAllPerms(String username){
        return getDao().queryAllPerms(username);
    }

}
