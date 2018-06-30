package ass.management.elasticsearch.entity;

import ass.management.elasticsearch.annotation.EsType;
import ass.management.elasticsearch.entity.group.EsIndexGroup;
import lombok.Data;


@Data
@EsType(typeName="hotnew", routingName="hotnew")
public class EsHotNew extends EsIndexGroup {

    @Override
    public String toString() {
        return "EsHotNew{" +
                "title='" + title + '\'' +
                ", serviceUrl='" + serviceUrl + '\'' +
                ", dbId='" + dbId + '\'' +
                ", createDate='" + createDate + '\'' +
                '}';
    }
}
