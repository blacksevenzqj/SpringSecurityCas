package ass.management.db.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * DAO支持类实现
 */
public interface CrudDao<T, E> extends BaseDao {

    /**
     * 获取单条数据
     *
     * @param id 主键
     * @return T t
     */
    T getById(@Param("id") E id);

    /**
     * 获取单条数据
     *
     * @param entity T
     * @return T t
     */
    T get(T entity);

    /**
     * 查询数据列表
     *
     * @param entity T
     * @return List<T> list
     */
    List<T> findList(T entity);

    /**
     * 查询数据列表
     *
     * @param queryMap 查询条件
     * @return List<T> list
     */
    List<T> queryList(Map<String, Object> queryMap);

    /**
     * 查询所有数据列表
     *
     * @return List<T> list
     */
    List<T> findAllList();

    /**
     * 插入数据
     *
     * @param entity T
     * @return int int
     */
    int insert(T entity);

    int insertBatch(@Param("list") List<T> list);

    /**
     * 更新数据
     *
     * @param entity T
     * @return int int
     */
    int update(T entity);

    /**
     * 删除数据
     * @param entity T
     * @return int int
     */
    int delete(T entity);

    /**
     * 删除数据
     * @param id entity id
     * @return int int
     */
    int deleteById(@Param("id") E id);

    void deleteBatchByIds(@Param("ids") E[] ids);

}