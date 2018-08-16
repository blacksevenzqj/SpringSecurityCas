package ass.management.elasticsearch.entity.base;

import ass.management.elasticsearch.common.EsConfig;
import lombok.Data;
import ass.management.elasticsearch.annotation.EsFieldData;

@Data
public class EsBaseEntity {

    @EsFieldData(dataName= EsConfig.El_KEYWORD)
    public String dbId;

    @EsFieldData(dataName=EsConfig.EL_DATE)
    public String createDate;

    @EsFieldData(dataName=EsConfig.EL_DATE)
    public String updateDate;

}
