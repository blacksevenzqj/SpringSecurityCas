package test;

import ass.management.elasticsearch.annotation.EsFieldData;
import ass.management.elasticsearch.entity.EsHotNew;
import ass.management.elasticsearch.util.EsUtils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MyTest {

    public static void main(String args[])throws Exception{

//        fangshe(EsHotNew.class);

//        class2Array(EsHotNew.class);

        List<String> list = new ArrayList();
        testList(list);

        testList(null);

        System.out.println("a".getBytes("utf-8"));
        System.out.println("a".getBytes("utf-8").length);
        byte[] bytes = "a".getBytes("utf-8");
        for(byte b : bytes){
            System.out.println(b);
        }

    }

    public static void testList(List<String> list) throws Exception{
        if(list != null) {
            for (String str : list) {
                System.out.println(str);
            }
        }
    }

    public static void class2Array(Class tClass) throws Exception{
        EsHotNew obj = new EsHotNew();
        obj.setTitle("feiji");
        obj.setDbId("111");
        obj.setCreateDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        obj.setServiceUrl("http://www.baidu.com");
        String[] strs = EsUtils.Class2Array(obj);
        for(String str : strs) {
            System.out.println(str);
        }
    }


    public static void fangshe(Class tClass){
        Field[] fields2 = tClass.getFields();
        for(Field field : fields2) {
            if(field.getAnnotation(EsFieldData.class) != null){
                System.out.println(field.getAnnotation(EsFieldData.class).dataName());
            }
            System.out.println(field);
        }
    }


}
