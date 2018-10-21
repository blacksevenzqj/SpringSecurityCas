package ass.management.admin.common.excel.model.datacomparison;

import ass.management.db.pojo.IncrementDataEntity;

public class OaMatchPerson extends IncrementDataEntity {

    @Override
    public boolean isNewRecord() {
        return id == null;
    }

    private Integer id;

    private String idUser;

    private String name;

    private String fullName;

    private String orgName;

    private String parentUnitName;

    private String associationId;

    private String associationReason;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getParentUnitName() {
        return parentUnitName;
    }

    public void setParentUnitName(String parentUnitName) {
        this.parentUnitName = parentUnitName;
    }

    public String getAssociationId() {
        return associationId;
    }

    public void setAssociationId(String associationId) {
        this.associationId = associationId;
    }

    public String getAssociationReason() {
        return associationReason;
    }

    public void setAssociationReason(String associationReason) {
        this.associationReason = associationReason;
    }
}
