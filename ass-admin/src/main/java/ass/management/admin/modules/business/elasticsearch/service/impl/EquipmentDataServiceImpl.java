package ass.management.admin.modules.business.elasticsearch.service.impl;

import ass.management.admin.modules.business.elasticsearch.service.EquipmentDataService;
import ass.management.db.utils.PageUtils;
import ass.management.elasticsearch.common.RestResult;
import ass.management.elasticsearch.entity.group.EquipmentData;
import ass.management.elasticsearch.entity.search.AggQueryEntry;
import ass.management.elasticsearch.entity.search.AggResultEntry;
import ass.management.elasticsearch.entity.search.QueryEntry;
import ass.management.elasticsearch.service.Es6ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "equipmentDataService")
public class EquipmentDataServiceImpl implements EquipmentDataService {

    @Autowired
    Es6ServiceImpl es6ServiceImpl;

    @Override
    public RestResult<PageUtils<EquipmentData>> pageQueryRequest(QueryEntry<EquipmentData> queryEntry) {
        if(queryEntry.getTClass() == null){
            queryEntry.setTClass(EquipmentData.class);
        }
        return es6ServiceImpl.pageQueryRequest(queryEntry);
    }

    @Override
    public RestResult save(EquipmentData equipmentData) {
        return es6ServiceImpl.createIndexDoc(EquipmentData.class, equipmentData);
    }

    @Override
    public RestResult saveBulk(List<EquipmentData> addList) {
        return es6ServiceImpl.processDocBulk(EquipmentData.class, addList, addList, null);
    }

    @Override
    public RestResult<List<AggResultEntry>> aggQueryRequest(QueryEntry<EquipmentData> queryEntry, AggQueryEntry aggQueryEntry) {
        return es6ServiceImpl.aggQueryRequest(queryEntry, aggQueryEntry);
    }
}
