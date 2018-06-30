package ass.management.admin.modules.sys.service;

import ass.management.admin.modules.sys.dao.SysRoleDao;
import ass.management.admin.modules.sys.enums.SysContents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ass.management.admin.modules.sys.entity.SysRoleEntity;
import ass.management.db.service.CrudService;


/**
 * 角色
 */
@Service("sysRoleServiceImpl")
public class SysRoleServiceImpl extends CrudService<SysRoleDao, SysRoleEntity, Long> {

	@Autowired
	private SysRoleMenuServiceImpl sysRoleMenuServiceImpl;

	@Autowired
	private SysUserRoleServiceImpl sysUserRoleServiceImpl;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveOrUpdateRole(SysRoleEntity role) {
		this.save(role);
		//保存角色与菜单关系
		sysRoleMenuServiceImpl.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteBatch(Long[] roleIds) {
		//删除角色
		this.deleteBatchByIds(roleIds);

		//删除角色与菜单关联
		sysRoleMenuServiceImpl.deleteBatch(roleIds);

		//删除角色与用户关联
		sysUserRoleServiceImpl.deleteBatch(roleIds, SysContents.ROLE);
	}


}
