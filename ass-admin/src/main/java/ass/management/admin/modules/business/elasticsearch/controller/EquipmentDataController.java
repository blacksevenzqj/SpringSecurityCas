package ass.management.admin.modules.business.elasticsearch.controller;

import ass.management.admin.modules.business.elasticsearch.service.EquipmentDataService;
import ass.management.db.utils.PageUtils;
import ass.management.elasticsearch.common.QueryDescEnum;
import ass.management.elasticsearch.common.RestResult;
import ass.management.elasticsearch.entity.base.EsPageInfo;
import ass.management.elasticsearch.entity.group.EquipmentData;
import ass.management.elasticsearch.entity.search.AggQueryEntry;
import ass.management.elasticsearch.entity.search.AggResultEntry;
import ass.management.elasticsearch.entity.search.QueryEntry;
import ass.management.elasticsearch.util.CustomParamUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/equipmentData")
@Slf4j
public class EquipmentDataController {

    @Autowired
    @Qualifier("equipmentDataServiceImpl")
    EquipmentDataService equipmentDataService;

    @RequestMapping(value="/getListByValue", method= RequestMethod.GET)
    public RestResult<PageUtils<EquipmentData>> getListByValue(String equipmentId){
        try{
            Map<String, Object> ids = new HashMap();
            ids.put(QueryDescEnum.QUERY_EQUIPMENT_ID.getQueryFieldName(), equipmentId);
            ids.put(QueryDescEnum.QUERY_EQUIPMENT_CODE.getQueryFieldName(), equipmentId);
            QueryEntry<EquipmentData> queryEntry = CustomParamUtils.<EquipmentData>getQueryEntry(EquipmentData.class, ids, null, null, null);
            queryEntry.setEsPageInfo(new EsPageInfo());
            return equipmentDataService.pageQueryRequest(queryEntry);
        }catch (Exception e){
            return RestResult.getFailResult(500, "参数错误");
        }
    }

    @RequestMapping(value="/getListByKv", method= RequestMethod.POST)
    public RestResult<PageUtils<EquipmentData>> getListByKv(QueryEntry<EquipmentData> queryEntry){
        return equipmentDataService.pageQueryRequest(queryEntry);
    }

    @RequestMapping(value="/getListByJson", method= RequestMethod.POST)
    public RestResult<PageUtils<EquipmentData>> getListByJson(@RequestBody  QueryEntry<EquipmentData> queryEntry){
        return equipmentDataService.pageQueryRequest(queryEntry);
    }

    @RequestMapping(value="/save", method= RequestMethod.POST)
    public RestResult save(EquipmentData equipmentData){
        return equipmentDataService.save(equipmentData);
    }

    @RequestMapping(value="/saveBulk", method= RequestMethod.POST)
    public RestResult saveBulk(List<EquipmentData> list){
        return equipmentDataService.saveBulk(list);
    }


    @RequestMapping(value="/getMaxCount", method= RequestMethod.GET)
    public RestResult<List<AggResultEntry>> getMaxCount(String equipmentCode){
        try{
            Map<String, Object> codeMap = new HashMap();
            codeMap.put(QueryDescEnum.QUERY_EQUIPMENT_CODE.getQueryFieldName(), equipmentCode);
            QueryEntry<EquipmentData> queryEntry = CustomParamUtils.getQueryEntry(EquipmentData.class, codeMap, null, null, null);
            AggQueryEntry aggQueryEntry = CustomParamUtils.getAggQueryEntry(EquipmentData.class);
            return equipmentDataService.aggQueryRequest(queryEntry, aggQueryEntry);
        }catch (Exception e){
            return RestResult.getFailResult(500, "参数错误");
        }
    }

}
