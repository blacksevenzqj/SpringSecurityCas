package ass.management.admin.common.excel.model;

import ass.management.utils.date.DateTimeUtils;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import java.io.Serializable;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public final class DbxitemAO extends Dbxitem implements Serializable {

    /**
     * 默认的序列化 id.
     */
    private static final long serialVersionUID = 1L;

    /** 办复期限string*/
    private String dbxbfqxStr;

    public String getDbxbfqxStr() {
        if (getDbxbfqx() != null) {
            return DateTimeUtils.formateDateToStr(getDbxbfqx(), DateTimeUtils.FORMAT_YMD);
        }
        return "";
    }

    public void setDbxbfqxStr(String dbxbfqxStr) {
        if (StringUtils.isNotEmpty(dbxbfqxStr)) {
            setDbxbfqx(DateTimeUtils.parseStrToDate(dbxbfqxStr, DateTimeUtils.FORMAT_YMD));
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
