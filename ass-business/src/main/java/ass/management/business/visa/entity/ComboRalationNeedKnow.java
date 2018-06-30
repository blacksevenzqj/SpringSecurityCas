package ass.management.business.visa.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import ass.management.db.pojo.IncrementDataEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@DynamicUpdate
@Table(name="qz_combo_ralation_need_know")
public class ComboRalationNeedKnow extends IncrementDataEntity {

    @Override
    public boolean isNewRecord() {
        return id == null;
    }

	@Id
	@GeneratedValue
    private Integer id;
	private Integer visaId; // 办理国家签证ID
	private Integer comboId; // 套餐ID
    private Integer needKnowId; // 预定须知ID
    private Date createTime = new Date();
//    private Integer delFlag;

}