package ass.management.business.hot.entity;

import ass.management.db.pojo.IncrementDataEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@DynamicUpdate
@Table(name="hot_todaynews")
public class TodayNews extends IncrementDataEntity {

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

    @Column(name="title",nullable=true)
    @NotNull(message="今日热点标题必填")
    private String title;

    @Column(name="case_cover_url",nullable=true)
    @NotNull(message="今日热点封面必填")
    private String caseCoverUrl;

    @Column(name="news_level",nullable=true)
    private Integer newsLevel = 1;

    @Column(name="news_text_url",nullable=true)
    @NotNull(message="今日热点链接必填")
    private String newsTextUrl;

    @Column(name="total_number_like",nullable=true)
    private Integer totalNumberLike = 0;

    @Column(name="total_number_comments",nullable=true)
    private Integer totalNumberComments = 0;

    @Column(name="hot_time",nullable=true)
    private String hotTime;

    @Column(name="create_time",nullable=true)
    private Date createTime;
	
//	@Column(name="del_flag",nullable=true)
//    private Integer delFlag = 0;
	
}
