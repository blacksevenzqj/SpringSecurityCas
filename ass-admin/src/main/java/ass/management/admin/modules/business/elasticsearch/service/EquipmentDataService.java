package ass.management.admin.modules.business.elasticsearch.service;


import ass.management.db.utils.PageUtils;
import ass.management.elasticsearch.common.RestResult;
import ass.management.elasticsearch.entity.group.EquipmentData;
import ass.management.elasticsearch.entity.search.AggQueryEntry;
import ass.management.elasticsearch.entity.search.AggResultEntry;
import ass.management.elasticsearch.entity.search.QueryEntry;

import java.util.List;

public interface EquipmentDataService {

   RestResult<PageUtils<EquipmentData>> pageQueryRequest(QueryEntry<EquipmentData> queryEntry);

    RestResult save(EquipmentData equipmentData);

    RestResult saveBulk(List<EquipmentData> addList);

    RestResult<List<AggResultEntry>> aggQueryRequest(QueryEntry<EquipmentData> queryEntry, AggQueryEntry aggQueryEntry);

}
