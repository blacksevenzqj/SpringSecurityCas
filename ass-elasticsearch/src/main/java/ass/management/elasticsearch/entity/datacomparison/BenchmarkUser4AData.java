package ass.management.elasticsearch.entity.datacomparison;

import ass.management.elasticsearch.annotation.Es6Index;
import ass.management.elasticsearch.annotation.EsFieldData;
import ass.management.elasticsearch.common.EsConfig;
import ass.management.elasticsearch.common.QueryDescEnum;
import ass.management.elasticsearch.entity.base.EsBaseEntity;
import lombok.Data;

@Data
@Es6Index(numberOfShards=5, numberOfReplicas=1, indexName="benchmarkuser4adata", routingName="benchmarkuser4a")
public class BenchmarkUser4AData extends EsBaseEntity {

    @EsFieldData(dataName= EsConfig.El_KEYWORD, elName = "db_id")
    public String id;

    @EsFieldData(dataName= EsConfig.El_KEYWORD, elName = "user_id")
    public String userId;

    @EsFieldData(dataName= EsConfig.El_KEYWORD, elName = "user_name", elQueryType = QueryDescEnum.QUERY_USER_NAME)
    public String userName;

    @EsFieldData(dataName= EsConfig.El_KEYWORD, elName = "user_org_id")
    public String userOrgId;

    @EsFieldData(dataName= EsConfig.El_KEYWORD, elName = "name_code")
    public String nameCode;

    @EsFieldData(dataName= EsConfig.El_KEYWORD, elName = "id_saphr_user_id")
    public String idSaphrUserId;

    @EsFieldData(dataName= EsConfig.El_KEYWORD, elName = "id_base_org_id")
    public String idBaseOrgId;

    @EsFieldData(dataName= EsConfig.El_KEYWORD, elName = "name_base_org_name", elQueryType = QueryDescEnum.QUERY_NAME_BASE_ORG_NAME)
    public String nameBaseOrgName;

    @EsFieldData(dataName= EsConfig.El_KEYWORD, elName = "human_org_id")
    public String humanOrgId;

    @EsFieldData(dataName= EsConfig.El_KEYWORD, elName = "pki")
    public String pki;


    // 从 BenchmarkUser4A 复制过来的属性，需要分解为：orgPath1 ... orgPath10
    @EsFieldData(dataName= EsConfig.El_STRING, elName = "orgPath")
    private String orgPath;
    // 根据/号切分为多个字段做term精确查询
    @EsFieldData(dataName= EsConfig.El_KEYWORD, elName = "org_path1")
    public String orgPath1;

    @EsFieldData(dataName= EsConfig.El_KEYWORD, elName = "org_path2")
    public String orgPath2;

    @EsFieldData(dataName= EsConfig.El_KEYWORD, elName = "org_path3")
    public String orgPath3;

    @EsFieldData(dataName= EsConfig.El_KEYWORD, elName = "org_path4")
    public String orgPath4;

    @EsFieldData(dataName= EsConfig.El_KEYWORD, elName = "org_path5")
    public String orgPath5;

    @EsFieldData(dataName= EsConfig.El_KEYWORD, elName = "org_path6")
    public String orgPath6;

    @EsFieldData(dataName= EsConfig.El_KEYWORD, elName = "org_path7")
    public String orgPath7;

    @EsFieldData(dataName= EsConfig.El_KEYWORD, elName = "org_path8")
    public String orgPath8;

    @EsFieldData(dataName= EsConfig.El_KEYWORD, elName = "org_path9")
    public String orgPath9;

    @EsFieldData(dataName= EsConfig.El_KEYWORD, elName = "org_path10")
    public String orgPath10;


}
