package ass.management.security.modules.business.ass.entity;

import ass.management.db.pojo.IncrementDataEntity;

/**
 * 节点
 */
public class Node extends IncrementDataEntity {

    private static final long serialVersionUID = 1L;

    @Override
    public boolean isNewRecord() {
        return id == null;
    }

    private Long id;

    private String name;

    private int type;

    private long parentId;

    private int sort;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
