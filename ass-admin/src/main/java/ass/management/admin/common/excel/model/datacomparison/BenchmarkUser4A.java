package ass.management.admin.common.excel.model.datacomparison;

import ass.management.db.pojo.IncrementDataEntity;

public class BenchmarkUser4A extends IncrementDataEntity {

    @Override
    public boolean isNewRecord() {
        return isNewData();
    }

    private Integer id;

    private String userId;

    private String userName;

    private String userOrgId;

    private String nameCode;

    // 用户人资
    private String idSaphrUserId;

    private String idBaseOrgId;

    private String nameBaseOrgName;

    // 人资组织Id
    private String humanOrgId;

    private String orgPath;

    private String pki;

    // 专门设置的new or update开关，不进存储。
    private boolean newData;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getOrgPath() {
        return orgPath;
    }

    public void setOrgPath(String orgPath) {
        this.orgPath = orgPath;
    }

    public String getPki() {
        return pki;
    }

    public void setPki(String pki) {
        this.pki = pki;
    }

    public boolean isNewData() {
        return newData;
    }
    public void setNewData(boolean newData) {
        this.newData = newData;
    }
}
