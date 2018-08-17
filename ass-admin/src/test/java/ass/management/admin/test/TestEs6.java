package ass.management.admin.test;

import ass.management.elasticsearch.client.EsClient;
import ass.management.elasticsearch.common.RestResult;
import ass.management.elasticsearch.entity.EsHotNew;
import ass.management.elasticsearch.entity.base.EsBaseEntity;
import ass.management.elasticsearch.entity.base.EsPageInfo;
import ass.management.elasticsearch.entity.group.EquipmentData;
import ass.management.elasticsearch.service.Es6ServiceImpl;
import ass.management.elasticsearch.service.EsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestEs6 {

    @Autowired
    EsClient esClient;

    @Autowired
    Es6ServiceImpl es6ServiceImpl;


    @Test
    public void getById() throws Exception {
        RestResult<EquipmentData> restResult = es6ServiceImpl.getById(EquipmentData.class, "61d533a353624e03bcff1aae1a748d5e");
        EquipmentData equipmentData = restResult.getData();
        System.out.println(equipmentData);
        if(equipmentData.getColumn10() == null){
            System.out.println("Column10 is null");
        }
    }

    @Test
    public void getByField() throws Exception {
        RestResult<List<EquipmentData>> restResult = es6ServiceImpl.searchTermByFiled(EquipmentData.class,
                "equipment_id", "8588ceaf5d70499e93fb1f824bc85ba1",
                new EsPageInfo(), null, null);
        System.out.println(restResult.getData());
    }

    @Test
    public void getMatchByField() throws Exception {
        RestResult<List<EquipmentData>> restResult = es6ServiceImpl.searchMatchByField(EquipmentData.class,
                "remarks", "seven go",
                new EsPageInfo(), null, null);
        System.out.println(restResult.getData());
    }

}
