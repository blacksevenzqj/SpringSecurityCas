package ass.management.admin.modules.sys.service;

import ass.management.admin.modules.sys.dao.SysRoleMenuDao;
import ass.management.admin.modules.sys.entity.SysRoleMenuEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ass.management.db.service.CrudService;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色与菜单对应关系
 */
@Service("sysRoleMenuServiceImpl")
public class SysRoleMenuServiceImpl extends CrudService<SysRoleMenuDao, SysRoleMenuEntity, Long> {

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveOrUpdate(Long roleId, List<Long> menuIdList) {
		//先删除角色与菜单关系
		deleteBatch(new Long[]{roleId});

		if(menuIdList.size() == 0){
			return ;
		}

		//保存角色与菜单关系
		List<SysRoleMenuEntity> list = new ArrayList<>(menuIdList.size());
		for(Long menuId : menuIdList){
			SysRoleMenuEntity sysRoleMenuEntity = new SysRoleMenuEntity();
			sysRoleMenuEntity.setMenuId(menuId);
			sysRoleMenuEntity.setRoleId(roleId);

			list.add(sysRoleMenuEntity);
		}
		this.insertBatch(list);
	}



	public List<Long> queryMenuIdList(Long roleId) {
		return getDao().queryMenuIdList(roleId);
	}


	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteByMenuId(Long menuId){
		getDao().deleteByMenuId(menuId);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteBatch(Long[] roleIds){
		this.deleteBatchByIds(roleIds);
	}

}
