package ass.management.elasticsearch.entity.base;

import lombok.Data;

@Data
public class EsPageInfo {

    private int pageNum = 0;

    private int pageSize = 10;

}
