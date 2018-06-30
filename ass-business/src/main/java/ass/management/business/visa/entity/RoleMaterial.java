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
@Table(name="qz_role_material")
public class RoleMaterial extends IncrementDataEntity {

    @Override
    public boolean isNewRecord() {
        return id == null;
    }

	@Id
	@GeneratedValue
    private Integer id;
	private Integer visaId; // 签证国家ID
    private Integer comboId; // 套餐Id
    private String roleName; // 材料角色名
    private Integer roleType; // 材料角色类型：1:在校学生，2:在职人员，3:退休人员，4:自由职业者，5:学龄前儿童
    private Integer materialNum; // 材料总数
    private String descrption; // 资料描述
    private String materialIds; // 材料IDS：1,2,3
    private String acceptEmail; // 接收资料的邮箱
    private String acceptAddress; // 资料邮寄地址
    private Date createTime = new Date();
//    private Integer delFlag;

}