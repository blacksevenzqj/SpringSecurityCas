package ass.management.admin.common.excel.model;

import ass.management.utils.date.DateTimeUtils;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import java.io.Serializable;
import java.util.List;


@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public final class DcdbgzmbfjAO extends Dcdbgzmbfj implements Serializable {

    /**
     * 默认的序列化 id.
     */
    private static final long serialVersionUID = 1L;

    private List<DbxitemAO> dbxitemAOList;

    private List<DcdbFkInfoItemAO> dcdbFkInfoItemAOList;

    private String taskSource;

    private String qxFlag;//反馈权限控制

    /** 办复期限string*/
    private String bfqxStr;

    public String getTaskSource() {
        return taskSource;
    }

    public void setTaskSource(String taskSource) {
        this.taskSource = taskSource;
    }

    public List<DbxitemAO> getDbxitemAOList() {
        return dbxitemAOList;
    }

    public void setDbxitemAOList(List<DbxitemAO> dbxitemAOList) {
        this.dbxitemAOList = dbxitemAOList;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    public List<DcdbFkInfoItemAO> getDcdbFkInfoItemAOList() {
        return dcdbFkInfoItemAOList;
    }

    public void setDcdbFkInfoItemAOList(List<DcdbFkInfoItemAO> dcdbFkInfoItemAOList) {
        this.dcdbFkInfoItemAOList = dcdbFkInfoItemAOList;
    }

    public String getBfqxStr() {
        if (getBfqx() != null) {
            return DateTimeUtils.formateDateToStr(getBfqx(), DateTimeUtils.FORMAT_YMD);
        }
        return "";
    }

    public void setBfqxStr(String bfqxStr) {
        if (StringUtils.isNotEmpty(bfqxStr)) {
            setBfqx(DateTimeUtils.parseStrToDate(bfqxStr, DateTimeUtils.FORMAT_YMD));
        }
    }

    public String getQxFlag() {
        return qxFlag;
    }

    public void setQxFlag(String qxFlag) {
        this.qxFlag = qxFlag;
    }
}
