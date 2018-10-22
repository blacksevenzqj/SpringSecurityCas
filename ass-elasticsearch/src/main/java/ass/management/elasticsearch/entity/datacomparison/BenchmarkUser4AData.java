package ass.management.elasticsearch.entity.datacomparison;

import ass.management.elasticsearch.annotation.Es6Index;
import ass.management.elasticsearch.annotation.EsFieldData;
import ass.management.elasticsearch.common.EsConfig;
import ass.management.elasticsearch.common.QueryDescEnum;
import ass.management.elasticsearch.entity.base.EsBaseEntity;
import lombok.Data;

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



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserOrgId() {
        return userOrgId;
    }

    public void setUserOrgId(String userOrgId) {
        this.userOrgId = userOrgId;
    }

    public String getNameCode() {
        return nameCode;
    }

    public void setNameCode(String nameCode) {
        this.nameCode = nameCode;
    }

    public String getIdSaphrUserId() {
        return idSaphrUserId;
    }

    public void setIdSaphrUserId(String idSaphrUserId) {
        this.idSaphrUserId = idSaphrUserId;
    }

    public String getIdBaseOrgId() {
        return idBaseOrgId;
    }

    public void setIdBaseOrgId(String idBaseOrgId) {
        this.idBaseOrgId = idBaseOrgId;
    }

    public String getNameBaseOrgName() {
        return nameBaseOrgName;
    }

    public void setNameBaseOrgName(String nameBaseOrgName) {
        this.nameBaseOrgName = nameBaseOrgName;
    }

    public String getHumanOrgId() {
        return humanOrgId;
    }

    public void setHumanOrgId(String humanOrgId) {
        this.humanOrgId = humanOrgId;
    }

    public String getPki() {
        return pki;
    }

    public void setPki(String pki) {
        this.pki = pki;
    }

    public String getOrgPath() {
        return orgPath;
    }

    public void setOrgPath(String orgPath) {
        this.orgPath = orgPath;
    }

    public String getOrgPath1() {
        return orgPath1;
    }

    public void setOrgPath1(String orgPath1) {
        this.orgPath1 = orgPath1;
    }

    public String getOrgPath2() {
        return orgPath2;
    }

    public void setOrgPath2(String orgPath2) {
        this.orgPath2 = orgPath2;
    }

    public String getOrgPath3() {
        return orgPath3;
    }

    public void setOrgPath3(String orgPath3) {
        this.orgPath3 = orgPath3;
    }

    public String getOrgPath4() {
        return orgPath4;
    }

    public void setOrgPath4(String orgPath4) {
        this.orgPath4 = orgPath4;
    }

    public String getOrgPath5() {
        return orgPath5;
    }

    public void setOrgPath5(String orgPath5) {
        this.orgPath5 = orgPath5;
    }

    public String getOrgPath6() {
        return orgPath6;
    }

    public void setOrgPath6(String orgPath6) {
        this.orgPath6 = orgPath6;
    }

    public String getOrgPath7() {
        return orgPath7;
    }

    public void setOrgPath7(String orgPath7) {
        this.orgPath7 = orgPath7;
    }

    public String getOrgPath8() {
        return orgPath8;
    }

    public void setOrgPath8(String orgPath8) {
        this.orgPath8 = orgPath8;
    }

    public String getOrgPath9() {
        return orgPath9;
    }

    public void setOrgPath9(String orgPath9) {
        this.orgPath9 = orgPath9;
    }

    public String getOrgPath10() {
        return orgPath10;
    }

    public void setOrgPath10(String orgPath10) {
        this.orgPath10 = orgPath10;
    }
}
