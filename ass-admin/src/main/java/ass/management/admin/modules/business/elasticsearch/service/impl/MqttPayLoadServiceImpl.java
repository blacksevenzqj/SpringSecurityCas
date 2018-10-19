package ass.management.admin.modules.business.elasticsearch.service.impl;

import ass.management.admin.modules.business.elasticsearch.service.MqttPayLoadService;
import ass.management.elasticsearch.common.RestResult;
import ass.management.elasticsearch.entity.group.MqttPayLoad;
import ass.management.elasticsearch.entity.search.AggQueryEntry;
import ass.management.elasticsearch.entity.search.AggResultEntry;
import ass.management.elasticsearch.entity.search.QueryEntry;
import ass.management.elasticsearch.service.Es6ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "mqttPayLoadServiceImpl")
public class MqttPayLoadServiceImpl implements MqttPayLoadService {

    @Autowired
    Es6ServiceImpl es6ServiceImpl;

    @Override
    public RestResult<List<AggResultEntry>> aggQueryRequest(QueryEntry<MqttPayLoad> queryEntry, AggQueryEntry aggQueryEntry) {
        return es6ServiceImpl.aggQueryRequest(queryEntry, aggQueryEntry);
    }

    @Override
    public RestResult save(MqttPayLoad mqttPayLoad) {
        return es6ServiceImpl.createIndexDoc(MqttPayLoad.class, mqttPayLoad);
    }

    @Override
    public RestResult saveBulk(List<MqttPayLoad> addList) {
        return es6ServiceImpl.processDocBulk(MqttPayLoad.class, addList, null, null);
    }

}
