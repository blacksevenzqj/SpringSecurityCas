package ass.management.admin.modules.sys.dao;

import ass.management.db.dao.CrudDao;
import org.apache.ibatis.annotations.Param;
import ass.management.admin.modules.sys.entity.SysUserEntity;

import java.util.List;

/**
 * 系统用户
 */
public interface SysUserDao extends CrudDao<SysUserEntity, Long> {
	
	/**
	 * 查询用户的所有权限
	 * @param userId  用户ID
	 */
	List<String> queryAllPerms(Long userId);
	
	/**
	 * 查询用户的所有菜单ID
	 */
	List<Long> queryAllMenuId(Long userId);

	boolean updatePassword(@Param("userId")Long userId, @Param("newPassword")String newPassword);

}
