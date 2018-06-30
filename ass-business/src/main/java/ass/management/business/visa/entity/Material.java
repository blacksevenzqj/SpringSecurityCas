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
@Table(name="qz_material")
public class Material extends IncrementDataEntity { // 材料

    @Override
    public boolean isNewRecord() {
        return id == null;
    }

	@Id
	@GeneratedValue
    private Integer id;
    private String title; // 资料标题
    private String content; // 内容
    private Date createTime = new Date();
//    private Integer delFlag;

}