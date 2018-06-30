package ass.management.business.advisory.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import ass.management.db.pojo.IncrementDataEntity;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@DynamicUpdate
@Table(name="advisory_free_question")
public class AdvisoryFreeQuestion extends IncrementDataEntity {

	@Override
	public boolean isNewRecord() {
		return id == null;
	}

	@Id
    @GeneratedValue
    @Column(name="id",nullable=true)
    private Integer id;

    @Column(name="consultant_id",nullable=true)
    private Integer consultantId;

    @Column(name="userp_id",nullable=true)
    private Integer userpId;

    @Column(name="duty_day",nullable=true)
    private String dutyDay;

    @Column(name="question",nullable=true)
    private String question;

    @Column(name="question_media_type",nullable=true)
    private Integer questionMediaType; // 提问媒体类型：1:文字，2:语音，3:视频

    @Column(name="question_time",nullable=true)
    private Date questionTime;

    @Column(name="answer",nullable=true)
    private String answer;

    @Column(name="answer_media_type",nullable=true)
    private Integer answerMediaType; // 回答媒体类型：1:文字，2:语音，3:视频

    @Column(name="answer_time",nullable=true)
    private Date answerTime;

    @Column(name="create_time",nullable=true)
    private Date createTime;
	
//	@Column(name="del_flag",nullable=true)
//    private Integer delFlag = 0;
	
}
