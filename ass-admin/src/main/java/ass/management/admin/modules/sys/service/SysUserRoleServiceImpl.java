package ass.management.admin.modules.sys.service;

import ass.management.admin.modules.sys.enums.SysContents;
import ass.management.db.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ass.management.admin.modules.sys.dao.SysUserRoleDao;
import ass.management.admin.modules.sys.entity.SysUserRoleEntity;

import java.util.ArrayList;
import java.util.List;


/**
 * 用户与角色对应关系
 */
@Service("sysUserRoleServiceImpl")
public class SysUserRoleServiceImpl extends CrudService<SysUserRoleDao, SysUserRoleEntity, Long> {

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveOrUpdate(Long userId, List<Long> roleIdList) {
		//先删除用户与角色关系
		getDao().deleteByUserId(userId);

		if(roleIdList.size() == 0){
			return ;
		}

		//保存用户与角色关系
		List<SysUserRoleEntity> list = new ArrayList<>(roleIdList.size());
		for(Long roleId : roleIdList){
			SysUserRoleEntity sysUserRoleEntity = new SysUserRoleEntity();
			sysUserRoleEntity.setUserId(userId);
			sysUserRoleEntity.setRoleId(roleId);

			list.add(sysUserRoleEntity);
		}
		this.insertBatch(list);
	}

	public List<Long> queryRoleIdList(Long userId) {
		return getDao().queryRoleIdList(userId);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteBatch(Long[] ids, String type){
		if(SysContents.ROLE.equalsIgnoreCase(type)){
			this.deleteBatchByIds(ids);
		}else{
			getDao().deleteBatchByUserId(ids);
		}
	}
}
