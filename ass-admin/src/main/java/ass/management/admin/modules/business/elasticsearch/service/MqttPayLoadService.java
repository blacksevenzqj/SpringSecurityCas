package ass.management.admin.modules.business.elasticsearch.service;


import ass.management.elasticsearch.common.RestResult;
import ass.management.elasticsearch.entity.group.MqttPayLoad;
import ass.management.elasticsearch.entity.search.AggQueryEntry;
import ass.management.elasticsearch.entity.search.AggResultEntry;
import ass.management.elasticsearch.entity.search.QueryEntry;

import java.util.List;

public interface MqttPayLoadService {

    RestResult<List<AggResultEntry>> aggQueryRequest(QueryEntry<MqttPayLoad> queryEntry, AggQueryEntry aggQueryEntry);

    RestResult save(MqttPayLoad mqttPayLoad);

    RestResult saveBulk(List<MqttPayLoad> addList);

}
