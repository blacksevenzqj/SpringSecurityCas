package ass.management.db.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Date;

/**
 * Entity 基类
 */
public abstract class BaseEntity implements Serializable {

    /**
     * 删除标记0：正常
     */
    public static final String DEL_FLAG_NORMAL = "0";
    /**
     * 删除标记1：删除
     */
    public static final String DEL_FLAG_DELETE = "-1";

    /**
     * 创建日期
     */
    protected Date createDate;
    /**
     * 更新日期
     */
    @JsonIgnore
    protected Date updateDate;
    /**
     * 删除标记(0:正常; -1:删除;)
     */
    protected String delFlag;


    public BaseEntity() {
        setDelFlag(DEL_FLAG_NORMAL);
    }

    /**
     * 插入之前执行方法，子类实现
     */
    public abstract void preInsert();

    /**
     * 更新之前执行方法，子类实现
     */
    public abstract void preUpdate();

    @JsonIgnore
    public abstract boolean isNewRecord();


    public Date getCreateDate() {
        return createDate == null ? null : (Date) createDate.clone();
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate == null ? null : (Date) createDate.clone();
    }

    public Date getUpdateDate() {
        return updateDate == null ? null : (Date) updateDate.clone();
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate == null ? null : (Date) updateDate.clone();
    }

    @Length(min = 1, max = 2)
    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
