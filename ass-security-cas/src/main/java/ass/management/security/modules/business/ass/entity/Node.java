package ass.management.security.modules.business.ass.entity;

import ass.management.db.pojo.IncrementDataEntity;
import lombok.Data;

/**
 * 节点
 */
@Data
public class Node extends IncrementDataEntity {

    private static final long serialVersionUID = 1L;

    @Override
    public boolean isNewRecord() {
        return id == null;
    }

    private Long id;

    private String name;

    private Integer type;

    private Long parentId;

    private Integer sort;


}
