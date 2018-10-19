package ass.management.admin.modules.business.elasticsearch.controller;

import ass.management.admin.modules.business.elasticsearch.service.MqttPayLoadService;
import ass.management.elasticsearch.common.QueryDescEnum;
import ass.management.elasticsearch.common.RestResult;
import ass.management.elasticsearch.entity.group.MqttPayLoad;
import ass.management.elasticsearch.entity.search.AggQueryEntry;
import ass.management.elasticsearch.entity.search.AggResultEntry;
import ass.management.elasticsearch.entity.search.QueryEntry;
import ass.management.elasticsearch.util.CustomParamUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/mqttPayLoad")
@Slf4j
public class MqttPayLoadController {

    @Resource(name = "mqttPayLoadServiceImpl")
    MqttPayLoadService mqttPayLoadService;

    @RequestMapping(value="/getListByClientIds", method= RequestMethod.GET)
    public RestResult<List<AggResultEntry>> getListByClientIds(String[] clientIds){
        if(clientIds == null || clientIds.length < 1){
            return RestResult.getFailResult(500, "参数为NULL");
        }
        try{
            Map<String, Object[]> ids = new HashMap();
            ids.put(QueryDescEnum.QUERY_CLIENTS_ID.getQueryFieldName(), clientIds);
            QueryEntry queryEntry = CustomParamUtils.getQueryEntry(MqttPayLoad.class, null, ids, null, null);
            AggQueryEntry aggQueryEntry = CustomParamUtils.getAggQueryEntry(MqttPayLoad.class);
            return mqttPayLoadService.aggQueryRequest(queryEntry, aggQueryEntry);
        }catch (Exception e){
            return RestResult.getFailResult(500, "参数错误");
        }
    }

    @RequestMapping(value="/save", method= RequestMethod.POST)
    public RestResult save(MqttPayLoad mqttPayLoad){
        return mqttPayLoadService.save(mqttPayLoad);
    }

    @RequestMapping(value="/saveBulk", method= RequestMethod.POST)
    public RestResult saveBulk(List<MqttPayLoad> list){
        return mqttPayLoadService.saveBulk(list);
    }


}
