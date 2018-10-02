package ass.management.elasticsearch.entity;//package com.thinkgem.elclient.elasticsearch.entity;

import ass.management.elasticsearch.annotation.EsFieldData;
import ass.management.elasticsearch.annotation.EsType;
import ass.management.elasticsearch.common.AggDescEnum;
import ass.management.elasticsearch.common.EsConfig;
import ass.management.elasticsearch.common.SortDescEnum;
import ass.management.elasticsearch.entity.group.EsIndexGroup;
import lombok.Data;


@Data
@EsType(typeName="hotnew", routingName="hotnew")
public class EsHotNew extends EsIndexGroup {

    @EsFieldData(dataName= EsConfig.El_KEYWORD, elName = "id")
    public String dbId;

    @EsFieldData(dataName=EsConfig.EL_DATE, elName = "createDate")
    public String createDate;

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
