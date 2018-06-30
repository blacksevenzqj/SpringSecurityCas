package ass.management.business.visa.entity.order;

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
@Table(name="dd_visa_order_detail")
public class VisaOrderDetail extends IncrementDataEntity {

    @Override
    public boolean isNewRecord() {
        return id == null;
    }

	@Id
	@GeneratedValue
    private Integer id;
    private String orderNo;
    private String handleVisaPeopleName; // 签证办理人名字
    private String handleVisaPeoplePhone; // 签证办理人电话
    private BigDecimal price; // 签证办理人单份价格
    private Date createTime = new Date();
//    private Integer delFlag;

}