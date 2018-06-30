package ass.management.elasticsearch.entity.group;

import lombok.Data;
import ass.management.elasticsearch.annotation.EsFieldData;
import ass.management.elasticsearch.annotation.EsIndex;
import ass.management.elasticsearch.common.EsConfig;
import ass.management.elasticsearch.entity.base.EsBaseEntity;

@Data
@EsIndex(indexName="school", numberOfShards=5, numberOfReplicas=1)
public class EsIndexGroup extends EsBaseEntity {

    @EsFieldData(dataName= EsConfig.El_STRING, analyzerType= EsConfig.AnalyzerConfig.IK, analyzerSearchType= EsConfig.AnalyzerConfig.IK_SEARCH)
    public String title;

    @EsFieldData(dataName= EsConfig.El_KEYWORD)
    public String serviceUrl;

}
