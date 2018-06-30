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
@Table(name="qz_combo_ralation_base_information")
public class ComboRalationBaseInformation extends IncrementDataEntity {

    @Override
    public boolean isNewRecord() {
        return id == null;
    }

	/** 套餐ID **/
	@Id
	@GeneratedValue
	private Integer id;
	private Integer visaId; // 签证ID
    private Integer comboId; // 签证套餐ID
    /** 办理该套餐基本信息ID **/
    private Integer baseInformationId; // 签证基本信息ID
    private Date createTime = new Date();
//    private Integer delFlag;

}
