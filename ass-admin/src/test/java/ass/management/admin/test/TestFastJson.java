package ass.management.admin.test;

import ass.management.admin.common.excel.config.FieldValue;
import ass.management.admin.common.utils.BsEntity2EsEntity;
import ass.management.common.concurrent.executor.DefaultExecutorFactory;
import ass.management.elasticsearch.entity.group.EquipmentData;
import ass.management.elasticsearch.util.EsUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

public class TestFastJson {

    public static void main(String[] args) throws Exception{

//        String str = "{\"db_id\":\"61d533a353624e03bcff1aae1a748d5e\",\"equipment_id\":\"8588ceaf5d70499e93fb1f824bc85ba1\",\"create_by\":\"1\",\"update_by\":\"1\",\"create_date\":\"2018-08-06 14:40:07\",\"update_date\":\"2018-08-06 14:40:07\",\"del_flag\":\"0\",\"column1\":\"-*++-.+\",\"column2\":\"-*++-.+\",\"column3\":\"854\",\"column4\":\"0\",\"column5\":\"--*'\",\"column6\":\".(\",\"column7\":\"0.0\",\"column8\":\"0.0\"}";
////       EquipmentData equipmentData = JSON.parseObject(str, new TypeReference<EquipmentData>(){
////        });
//        EquipmentData equipmentData = JSON.parseObject(str, EquipmentData.class);
//        System.out.println(equipmentData);
//
//        System.out.println(EquipmentData.class.getField("createDate").get(equipmentData));
//
//        String name = EquipmentData.class.getField("equipmentId").getName();
//        System.out.println(name);
//
//        EsUtils.Class2Array(equipmentData);

//        Test2 test2 = new Test2();
//        test2.name = "333";
//        test2.setAge("12");
//        System.out.println(test2);
//        System.out.println(test2.getAge());
//        System.out.println(JSON.toJSONString(test2));


        String dateTimeStr2 = "2018-08-08 13:30:45";
        DateFormat df1112 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//  df1112.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));    // 1533706245000  和 默认一样 是按照系统时区：东八区（GMT+08:00）
//  df1112.setTimeZone(TimeZone.getTimeZone("GMT+0:00"));    // 1533735045000  世界协调时间（UTC）/格林尼治时间（GMT）
        Date date222 = df1112.parse(dateTimeStr2); // 默认按照系统时区(东八区) 转换Date    Wed Aug 08 13:30:45 CST 2018
        Long time = date222.getTime(); // 默认按照系统时区(东八区) 转换Long   1533706245000
        System.out.println(time);   // 默认按照系统时区(东八区) 转换Long   1533706245000


        String baseName4 = "ass/management/admin/common/excel/config/FieldValue.class";
        String configs1 = Thread.currentThread().getContextClassLoader().getResource(baseName4).getPath();
        System.out.println(configs1);
       String configs = Thread.currentThread().getContextClassLoader().getResource("config/excel/excel-config.xml").getPath();
       System.out.println(configs);
    }

}
