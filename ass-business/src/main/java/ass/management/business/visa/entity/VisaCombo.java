package ass.management.business.visa.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import ass.management.db.pojo.IncrementDataEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@DynamicUpdate
@Table(name="qz_visa_combo")
public class VisaCombo extends IncrementDataEntity {

    @Override
    public boolean isNewRecord() {
        return comboId == null;
    }

	@Id
	@GeneratedValue
    private Integer comboId; // 签证套餐ID
    private Integer visaId; // 签证ID
    private Integer comboNum; // 套餐序号：1:表示套餐一
    private String name; // 套餐名字
    private String description; // 套餐描述
    private BigDecimal marketPrice; // 行情价
    private BigDecimal price; // 套餐价格
    private Integer onlineFlag; // 套餐上线标识：0:默认，1:上线，2:下线
    private Date createTime = new Date();
//    private Integer delFlag;

    
}