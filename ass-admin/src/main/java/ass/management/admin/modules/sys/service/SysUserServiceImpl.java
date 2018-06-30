package ass.management.admin.modules.sys.service;

import ass.management.admin.modules.sys.entity.SysUserEntity;
import ass.management.admin.modules.sys.enums.SysContents;
import ass.management.admin.modules.sys.shiro.ShiroUtils;
import ass.management.db.service.CrudService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ass.management.admin.modules.sys.dao.SysUserDao;

import java.util.List;

@Slf4j
@Service(value = "sysUserServiceImpl")
public class SysUserServiceImpl extends CrudService<SysUserDao, SysUserEntity, Long> {

    @Autowired
    SysUserRoleServiceImpl sysUserRoleServiceImpl;

    /**
     * 查询用户的所有权限
     * @param userId  用户ID
     */
    public List<String> queryAllPerms(Long userId){
        return getDao().queryAllPerms(userId);
    }

    /**
     * 查询用户的所有菜单ID
     */
    public List<Long> queryAllMenuId(Long userId){
        return getDao().queryAllMenuId(userId);
    }


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveUser(SysUserEntity user) {
        //sha256加密
        String salt = RandomStringUtils.randomAlphanumeric(20);
        user.setSalt(salt);
        user.setPassword(ShiroUtils.sha256(user.getPassword(), user.getSalt()));
        save(user);
        //保存用户与角色关系
        sysUserRoleServiceImpl.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void upDateUser(SysUserEntity user) {
        if(StringUtils.isNotBlank(user.getPassword())){
            //sha256加密
            String salt = RandomStringUtils.randomAlphanumeric(20);
            user.setSalt(salt);
            user.setPassword(ShiroUtils.sha256(user.getPassword(), user.getSalt()));
        }
        save(user);
        //保存用户与角色关系
        sysUserRoleServiceImpl.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }


    /**
     * 修改密码
     * @param password     原密码
     * @param newPassword  新密码
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean updatePassword(SysUserEntity user, String password, String newPassword){
        //原密码
        password = ShiroUtils.sha256(password, user.getSalt());
        //新密码
        newPassword = ShiroUtils.sha256(newPassword, user.getSalt());
        if(!user.getPassword().equalsIgnoreCase(password)){
            return false;
        }
        return getDao().updatePassword(user.getUserId(), newPassword);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteBatch(Long[] userIds) {
        this.deleteBatchByIds(userIds);
        sysUserRoleServiceImpl.deleteBatch(userIds, SysContents.USER);
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void testSave(){
        SysUserEntity sysUserEntity = new SysUserEntity();
        sysUserEntity.setUsername("123");
        sysUserEntity = save(sysUserEntity);
        log.info(sysUserEntity.toString());
        sysUserEntity.setUsername("456");
        sysUserEntity = save(sysUserEntity);
        log.info(sysUserEntity.toString());
        testSave2();
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void testSave2(){
        SysUserEntity sysUserEntity = new SysUserEntity();
        sysUserEntity.setUsername("789");
        sysUserEntity = save(sysUserEntity);
        log.info(sysUserEntity.toString());
        int a = 1/0;
    }


}
