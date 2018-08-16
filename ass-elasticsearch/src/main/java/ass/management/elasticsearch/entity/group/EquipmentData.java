package ass.management.elasticsearch.entity.group;

import ass.management.elasticsearch.annotation.Es6Index;
import ass.management.elasticsearch.annotation.EsFieldData;
import ass.management.elasticsearch.common.EsConfig;
import ass.management.elasticsearch.entity.base.EsBaseEntity;
import lombok.Data;

@Data
@Es6Index(numberOfShards=5, numberOfReplicas=1, indexName="equipmentdata", routingName="equipment")
public class EquipmentData extends EsBaseEntity {

    @EsFieldData(dataName= EsConfig.El_KEYWORD)
    private String equipmentId;

    @EsFieldData(dataName= EsConfig.El_KEYWORD)
    private String createBy;

    @EsFieldData(dataName= EsConfig.El_KEYWORD)
    private String updateBy;

    @EsFieldData(dataName= EsConfig.El_KEYWORD)
    private String remarks;

    @EsFieldData(dataName= EsConfig.El_KEYWORD)
    private String delFlag;

    @EsFieldData(dataName= EsConfig.El_KEYWORD)
    private String column1;

    @EsFieldData(dataName= EsConfig.El_KEYWORD)
    private String column2;

    @EsFieldData(dataName= EsConfig.El_KEYWORD)
    private String column3;

    @EsFieldData(dataName= EsConfig.El_KEYWORD)
    private String column4;

    @EsFieldData(dataName= EsConfig.El_KEYWORD)
    private String column5;

    @EsFieldData(dataName= EsConfig.El_KEYWORD)
    private String column6;

    @EsFieldData(dataName= EsConfig.El_KEYWORD)
    private String column7;

    @EsFieldData(dataName= EsConfig.El_KEYWORD)
    private String column8;

    @EsFieldData(dataName= EsConfig.El_KEYWORD)
    private String column9;

    @EsFieldData(dataName= EsConfig.El_KEYWORD)
    private String column10;



}
