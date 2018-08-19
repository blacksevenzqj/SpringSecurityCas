package ass.management.elasticsearch.entity.base;

import ass.management.elasticsearch.common.EsConfig;
import lombok.Data;
import ass.management.elasticsearch.annotation.EsFieldData;

@Data
public class EsBaseEntity {

    private String esId;

    @EsFieldData(dataName= EsConfig.El_KEYWORD, elName = "db_id")
    public String dbId;

    @EsFieldData(dataName=EsConfig.EL_DATE, elName = "create_date")
    public String createDate;

    @EsFieldData(dataName=EsConfig.EL_DATE, elName = "update_date")
    public String updateDate;

}
