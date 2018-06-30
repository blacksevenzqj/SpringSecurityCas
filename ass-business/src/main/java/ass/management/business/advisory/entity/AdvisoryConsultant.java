package ass.management.business.advisory.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import ass.management.db.pojo.IncrementDataEntity;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@DynamicUpdate
@Table(name="advisory_consultant")
public class AdvisoryConsultant extends IncrementDataEntity {

	@Override
	public boolean isNewRecord() {
		return id == null;
	}

	@Id
    @GeneratedValue
    @Column(name="id",nullable=true)
    private Integer id;

    @Column(name="userp_id",nullable=true)
    private Integer userpId;

    @Column(name="photo_url",nullable=true)
    private String photoUrl;

    @Column(name="motto",nullable=true)
    private String motto;

    @Column(name="consultant_name",nullable=true)
    private String consultantName;

    @Column(name="job_title",nullable=true)
    private String jobTitle;

    @Column(name="consultant_type",nullable=true)
    private Integer consultantType = 1; // 咨询师类型：1:免费，2:付费

    @Column(name="consultant_level",nullable=true)
    private Integer consultantLevel = 1; // 咨询师等级（1级为免费咨询师，2级及以上为付费咨询师）

    @Column(name="create_time",nullable=true)
    private Date createTime;
	
//	@Column(name="del_flag",nullable=true)
//    private Integer delFlag = 0;
	
}
