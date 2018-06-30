package ass.management.admin.modules.sys.dao;

import org.apache.ibatis.annotations.Param;
import ass.management.admin.modules.sys.entity.SysUserRoleEntity;
import ass.management.db.dao.CrudDao;

import java.util.List;

/**
 * 用户与角色对应关系
 */
public interface SysUserRoleDao extends CrudDao<SysUserRoleEntity, Long> {
	
	/**
	 * 根据用户ID，获取角色ID列表
	 */
	List<Long> queryRoleIdList(Long userId);

	void deleteByUserId(Long userId);

	void deleteBatchByUserId(@Param("userIds")Long[] userIds);

}
