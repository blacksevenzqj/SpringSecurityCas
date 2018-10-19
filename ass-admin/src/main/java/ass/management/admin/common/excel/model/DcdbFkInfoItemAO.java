package ass.management.admin.common.excel.model;

import ass.management.utils.date.DateTimeUtils;
import ass.management.utils.date.Strings;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public final class DcdbFkInfoItemAO extends DcdbFkInfoItem implements Serializable {

    /**
     * 默认的序列化 id.
     */
    private static final long serialVersionUID = 1L;

    private String fkStatusStr;

    private String shStatuStr;

    private String finishTimeStr;

    private Date bjqx;//办结期限

    private String scheDul;//主要进度安排

    /** 办复期限string*/
    private String bjqxStr;

    private String tbdateStr;

    private Date fkEndTime;//当期反馈截至日期

    private String fkEndTimeStr;//当期反馈截至日期

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    public String getTbdateStr() {
        if (this.getTbdate() != null) {
            return DateTimeUtils.formateDateToStr(getTbdate(), DateTimeUtils.FORMAT_YMD_HMS);
        }
        return "";
    }

    public void setTbdateStr(String tbdateStr) {
        if (StringUtils.isNotEmpty(tbdateStr)) {
            this.setTbdate(DateTimeUtils.parseStrToDate(tbdateStr, DateTimeUtils.FORMAT_YMD_HMS));
        }
    }

    public String getFinishTimeStr() {
        if (this.getFinishTime() != null) {
            return DateTimeUtils.formateDateToStr(getFinishTime(), DateTimeUtils.FORMAT_YMD_HMS);
        }
        return "";
    }

    public void setFinishTimeStr(String finishTimeStr) {
        if (StringUtils.isNotEmpty(finishTimeStr)) {
            this.setFinishTime(DateTimeUtils.parseStrToDate(finishTimeStr, DateTimeUtils.FORMAT_YMD_HMS));
        }
    }

    public String getFkStatusStr() {
        if (Strings.isEmptyOrNull(this.getFkStatus())) {
            return "";
        } else {
            if ("0".equals(this.getFkStatus())) {
                return "进行中";
            } else if ("1".equals(this.getFkStatus())) {
                return "按时完成";
            } else if ("2".equals(this.getFkStatus())) {
                return "超期完成";
            } else if ("3".equals(this.getFkStatus())) {
                return "超期未完成";
            } else {
                return "";
            }
        }
    }

    public void setFkStatusStr(String fkStatusStr) {
        this.fkStatusStr = fkStatusStr;
    }

    public String getShStatuStr() {
        if (Strings.isEmptyOrNull(this.getShStatu())) {
            return "";
        } else {
            if ("0".equals(this.getShStatu())) {
                return "未通过";
            } else if ("1".equals(this.getShStatu())) {
                return "通过";
            } else {
                return "";
            }
        }
    }

    public void setShStatuStr(String shStatuStr) {
        this.shStatuStr = shStatuStr;
    }

    public Date getBjqx() {
        return bjqx;
    }

    public void setBjqx(Date bjqx) {
        this.bjqx = bjqx;
    }

    public String getBjqxStr() {
        if (getBjqx() != null) {
            return DateTimeUtils.formateDateToStr(getBjqx(), DateTimeUtils.FORMAT_YMD);
        }
        return "";
    }

    public void setBjqxStr(String bjqxStr) {
        if (StringUtils.isNotEmpty(bjqxStr)) {
            setBjqx(DateTimeUtils.parseStrToDate(bjqxStr, DateTimeUtils.FORMAT_YMD));
        }
    }

    public String getScheDul() {
        return scheDul;
    }

    public void setScheDul(String scheDul) {
        this.scheDul = scheDul;
    }

    public Date getFkEndTime() {
        return fkEndTime;
    }

    public void setFkEndTime(Date fkEndTime) {
        this.fkEndTime = fkEndTime;
    }

    public String getFkEndTimeStr() {
        return fkEndTimeStr;
    }

    public void setFkEndTimeStr(String fkEndTimeStr) {
        this.fkEndTimeStr = fkEndTimeStr;
    }

}
