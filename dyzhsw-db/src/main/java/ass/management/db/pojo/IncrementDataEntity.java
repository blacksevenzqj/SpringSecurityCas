package ass.management.db.pojo;

import java.util.Date;

/**
 * 数据Entity类
 */
public abstract class IncrementDataEntity extends BaseEntity {

    /**
     * 插入之前执行方法，需要手动调用
     */
    @Override
    public void preInsert() {
        setUpdateDate(new Date());
        setCreateDate(getUpdateDate());
    }

    /**
     * 更新之前执行方法，需要手动调用
     */
    @Override
    public void preUpdate() {
        setUpdateDate(new Date());
    }

}
