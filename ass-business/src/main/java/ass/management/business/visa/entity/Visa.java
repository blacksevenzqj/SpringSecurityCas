package ass.management.business.visa.entity;

import ass.management.db.pojo.IncrementDataEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@DynamicUpdate
@Table(name="qz_visa")
public class Visa extends IncrementDataEntity {

    @Override
    public boolean isNewRecord() {
        return id == null;
    }

    @Id
	@GeneratedValue
    private Integer id;
    private String nationalName; // 国家
    private String nationalEnglishName; // 国家英文名
    private String nationalCode; // 国家二字节码
    private String nationalFlag; // 国旗图片
    private String area; // 地区
    private Integer hotVisa; // 热门签证配置
    private String topDescription; // 置顶信息描述
    private Integer topFlag; // 顶部签证宣传标志：0:默认，1:顶部设置
    private Integer orderFlag; // 热门签证排序设置：0，默认
    private Integer onlineFlag; // 是否上线标志：0:默认，1:上线，2:下线
    private BigDecimal minPrice; // 办理签证最低价格
    private BigDecimal maxPrice; // 办理签证最高价格
    private Date createTime = new Date();
//    private Integer delFlag;

}