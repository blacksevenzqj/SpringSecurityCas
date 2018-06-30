package ass.management.business.businesshelp.entity;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;
import lombok.Data;
import ass.management.db.pojo.IncrementDataEntity;

@Data
@Entity
@DynamicUpdate
@Table(name="cy_success_case")
public class SuccessCase extends IncrementDataEntity{
	@Override
	public boolean isNewRecord() {
		return id == null;
	}
	@Id
	@GeneratedValue
	@Column(name="id",nullable=true)
    private Integer id;
	
	@Column(name="sys_user_id",nullable=true)
    private Integer sysUserId = 1;
    
	@Column(name="startbusiness_id",nullable=true)
    private Integer startbusinessId = 1;

    @Column(name="project_type_id",nullable=true)
    private Integer projectTypeId = 1;
    
    @Column(name="qr_code_url",nullable=true)
    @NotNull(message="二维码链接必填") 
    private String qrCodeUrl;
    
    @Column(name="title",nullable=true)
    @NotNull(message="成功案例标题必填") 
    private String title;

    @Column(name="case_cover_url",nullable=true)
    private String caseCoverUrl;

    @Column(name="case_text_url",nullable=true)
    @NotNull(message="成功案例链接必填")
    private String caseTextUrl;
    
    @Column(name="create_time",nullable=true)
    private Date createTime;
    
/*    @Column(name="del_flag",nullable=true)
    private Integer delFlag = 0;*/
	
}
