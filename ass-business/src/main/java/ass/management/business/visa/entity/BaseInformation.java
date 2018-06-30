package ass.management.business.visa.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import ass.management.db.pojo.IncrementDataEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@DynamicUpdate
@Table(name="qz_base_information")
public class BaseInformation extends IncrementDataEntity {

    @Override
    public boolean isNewRecord() {
        return baseInformationId == null;
    }

    @Id
    @GeneratedValue
    @Column(name="base_information_id",nullable=true)
    private Integer baseInformationId;

    @Column(name="name",nullable=true)
    private String name; // 名字

    @Column(name="description",nullable=true)
    private String description; // 描述

    @Column(name="enter_country_num",nullable=true)
    private Integer enterCountryNum; // 入境次数

    @Column(name="stay_day",nullable=true)
    private Integer stayDay; // 停留天数

    @Column(name="validity_time",nullable=true)
    private String validityTime; // 有效期:年数

    @Column(name="combo_content",nullable=true)
    private String comboContent; // 套餐内容

    @Column(name="handle_time",nullable=true)
    private String handleTime;  // 办理时间

    @Column(name="visa_interview",nullable=true)
    private String visaInterview; // 是否需要面签

    @Column(name="cash_deposit",nullable=true)
    private BigDecimal cashDeposit;  // 保证金

    @Column(name="handle_range",nullable=true)
    private String handleRange; // 处理范围

    @Column(name="combo_description",nullable=true)
    private String comboDescription; // 套餐描述

    @Column(name="create_time",nullable=true)
    private Date createTime = new Date(); // 创建时间

//    @Column(name="del_flag",nullable=true)
//    private Integer delFlag; // 删除标志

}