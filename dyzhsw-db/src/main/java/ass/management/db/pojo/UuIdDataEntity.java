package ass.management.db.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ass.management.utils.RandomHelper;
import ass.management.utils.StringHelper;

import java.util.Date;

/**
 * 数据Entity类
 */
public abstract class UuIdDataEntity extends BaseEntity {

    /**
     * String 类型实体编号（唯一标识）
     */
    private String id;

    private boolean defaultUuId = true;

    public UuIdDataEntity() {
    }

    public UuIdDataEntity(String id) {
        this.id = id;
    }

    /**
     * 插入之前执行方法，需要手动调用
     */
    @Override
    public void preInsert() {
        if (this.isDefaultUuId()) {
            setId(RandomHelper.uuid());
        }
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

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @JsonIgnore
    public boolean isDefaultUuId() {
        return defaultUuId;
    }
    public void setDefaultUuId(boolean defaultUuId) {
        this.defaultUuId = defaultUuId;
    }

    public boolean isNewRecord() {
        return StringHelper.isBlank(getId());
    }

}
