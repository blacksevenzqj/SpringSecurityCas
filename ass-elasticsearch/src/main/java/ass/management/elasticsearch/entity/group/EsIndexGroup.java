package ass.management.elasticsearch.entity.group;

import ass.management.elasticsearch.annotation.EsFieldData;
import ass.management.elasticsearch.annotation.EsIndex;
import ass.management.elasticsearch.common.AnalyzerConfigEnum;
import ass.management.elasticsearch.common.EsConfig;
import ass.management.elasticsearch.entity.base.EsBaseEntity;
import lombok.Data;

@Data
@EsIndex(indexName="school", numberOfShards=5, numberOfReplicas=1)
public class EsIndexGroup extends EsBaseEntity {

    @EsFieldData(dataName= EsConfig.El_STRING, analyzerType = AnalyzerConfigEnum.IK, analyzerSearchType = AnalyzerConfigEnum.IK_SEARCH)
    public String title;

    @EsFieldData(dataName= EsConfig.El_KEYWORD)
    public String serviceUrl;

}
