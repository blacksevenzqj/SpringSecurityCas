package ass.management.db.service;

import ass.management.db.dao.CrudDao;
import ass.management.db.pojo.Paging;
import ass.management.db.utils.PageUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ass.management.db.pojo.BaseEntity;

import java.util.List;
import java.util.Map;

/**
 * Service基类
 */
public abstract class CrudService<D extends CrudDao<T, E>, T extends BaseEntity, E> {

    /**
     * 持久层对象
     */
    @Autowired
    private D dao;

    public D getDao() {
        return dao;
    }

    /**
     * 获取单条数据
     * @param id 主键
     * @return 数据实体
     */
    public T get(E id) {
        return dao.getById(id);
    }

    /**
     * 获取单条数据
     * @param entity 实体对象
     * @return 实体对象
     */
    public T get(T entity) {
        return dao.get(entity);
    }

    /**
     * 查询列表数据
     * @param entity 实体对象
     * @return 实体对象列表
     */
    public List<T> findList(T entity) {
        return dao.findList(entity);
    }

    /**
     * 查询列表数据
     * @param queryMap 查询条件
     * @return 实体对象列表
     */
    public List<T> queryList(Map<String, Object> queryMap) {
        return dao.queryList(queryMap);
    }

    /**
     * 查询分页数据
     * @param page   分页对象
     * @param entity 实体对象
     * @return 分页数据
     */
    public PageInfo<T> findPage(Paging page, T entity) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderBy());
        List<T> list = dao.findList(entity);
        return new PageInfo<>(list);
    }

    /**
     * 查询分页数据
     * @param page     分页对象
     * @param queryMap 查询条件
     * @return 分页数据
     */
    public PageInfo<T> queryPage(Paging page, Map<String, Object> queryMap) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderBy());
        List<T> list = dao.queryList(queryMap);
        return new PageInfo<>(list);
    }

    public PageUtils<T> queryPageMap(Map<String, Object> params) {
        if(params.get("pageNum") == null){
            params.put("pageNum", 1);
            params.put("pageSize", 10);
        }
        Paging page = new Paging(Integer.valueOf(params.get("pageNum").toString()), Integer.valueOf(params.get("pageSize").toString()));
        PageInfo<T> pageInfo = queryPage(page, params);
        return new PageUtils<>(pageInfo.getList(), pageInfo.getTotal(), pageInfo.getPageSize(), pageInfo.getPageNum());
    }

    /**
     * 保存数据（插入或更新）
     * @param entity 实体对象
     * @return 实体对象
     */
    @Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public T save(T entity) {
        if (entity.isNewRecord()) {
            entity.preInsert();
            dao.insert(entity);
        } else {
            entity.preUpdate();
            dao.update(entity);
        }
        return entity;
    }

    @Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int insertBatch(List<T> list){
        return getDao().insertBatch(list);
    }


    /**
     * 删除数据
     * @param entity 实体对象
     */
    @Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(T entity) {
        dao.delete(entity);
    }

    /**
     * 删除数据
     * @param id 主键
     */
    @Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteById(E id) {
        dao.deleteById(id);
    }

    @Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteBatchByIds(E[] ids) {
        dao.deleteBatchByIds(ids);
    }

}
