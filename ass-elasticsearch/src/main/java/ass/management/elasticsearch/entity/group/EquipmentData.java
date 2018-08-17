package ass.management.elasticsearch.entity.group;

import ass.management.elasticsearch.annotation.Es6Index;
import ass.management.elasticsearch.annotation.EsFieldData;
import ass.management.elasticsearch.common.EsConfig;
import ass.management.elasticsearch.entity.base.EsBaseEntity;
import lombok.Data;

@Data
@Es6Index(numberOfShards=5, numberOfReplicas=1, indexName="equipmentdata", routingName="equipment")
public class EquipmentData extends EsBaseEntity {

    @EsFieldData(dataName= EsConfig.El_KEYWORD, elName = "equipment_id")
    private String equipmentId;

    @EsFieldData(dataName= EsConfig.El_KEYWORD, elName = "equipment_code")
    private String equipmentCode;

    @EsFieldData(dataName= EsConfig.El_KEYWORD, elName = "create_by")
    private String createBy;

    @EsFieldData(dataName= EsConfig.El_KEYWORD, elName = "update_by")
    private String updateBy;

    @EsFieldData(dataName= EsConfig.El_KEYWORD)
    private String remarks;

    @EsFieldData(dataName= EsConfig.El_KEYWORD, elName = "del_flag")
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


    @Override
    public String toString() {
        return "EquipmentData{" +
                "equipmentId='" + equipmentId + '\'' +
                ", equipmentCode='" + equipmentCode + '\'' +
                ", createBy='" + createBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", remarks='" + remarks + '\'' +
                ", delFlag='" + delFlag + '\'' +
                ", column1='" + column1 + '\'' +
                ", column2='" + column2 + '\'' +
                ", column3='" + column3 + '\'' +
                ", column4='" + column4 + '\'' +
                ", column5='" + column5 + '\'' +
                ", column6='" + column6 + '\'' +
                ", column7='" + column7 + '\'' +
                ", column8='" + column8 + '\'' +
                ", column9='" + column9 + '\'' +
                ", column10='" + column10 + '\'' +
                ", dbId='" + dbId + '\'' +
                ", createDate='" + createDate + '\'' +
                ", updateDate='" + updateDate + '\'' +
                '}';
    }

}
