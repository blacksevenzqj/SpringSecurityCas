package ass.management.business.visa.entity;

import ass.management.db.pojo.IncrementDataEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@DynamicUpdate
@Table(name="qz_need_know")
public class NeedKnow extends IncrementDataEntity { // 预定须知

    @Override
    public boolean isNewRecord() {
        return id == null;
    }

	@Id
	@GeneratedValue
    private Integer id;

    private String title; // 预定须知 标题描述

    private String content; // 预定须知 内容

    private Date createTime = new Date();

//    private Integer delFlag;

}