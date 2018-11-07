package ass.management.admin.test;

import ass.management.admin.modules.sys.entity.SysUserEntity;
import ass.management.admin.modules.sys.shiro.ShiroUtils;
import ass.management.common.concurrent.executor.*;
import ass.management.elasticsearch.util.CharacterSegmentUtil;

import java.util.ArrayList;
import java.util.List;

public class TestPojo {

    public static void main(String[] args) throws Exception{

//        Test1 t1 = new Test1();    // 父类实例不能赋值给子类实例
//        Test2 t2 = t1;

        Test2 t3 = new Test2();
        Test1 t4 = t3;
        t4.name = "123";   // 同一包中的 其他类 可以访问protected属性。

//        SysUserEntity sysUserEntity = new SysUserEntity();
//        System.out.println(sysUserEntity);
//        String str = "123456";
//        String salt = "YzcmCZNvbXocrsz9dm8e";
//        System.out.println(ShiroUtils.sha256(str, salt));

//        int i = 253;
//        System.out.println(Integer.toBinaryString(i));
//        int a = i << 2;
//        System.out.println(Integer.toBinaryString(a));

//        DefaultExecutorFactory.class.cast(DisruptorExecutorFactory.class.newInstance());

//        String strs = "\\中国南方电网责任有限公司\\云南电网有限责任公司\\云南电网有限责任公司曲靖供电局\\云南电网有限责任公司曲靖马龙供电局\\其他\\";
//        String[] aaa = CharacterSegmentUtil.SlashSegmentation(strs, CharacterSegmentUtil.REVERSE_SLANT);
//        for(String a : aaa){
//            System.out.println(a);
//        }
//        String strs2 = "/中国南方电网责任有限公司/云南电网有限责任公司/云南电网有限责任公司曲靖供电局/云南电网有限责任公司曲靖马龙供电局/其他/";
//        String[] bbb = CharacterSegmentUtil.SlashSegmentation(strs2, CharacterSegmentUtil.POSITIVE_SLANT);
//        for(String b : bbb){
//            System.out.println(b);
//        }
//
//        String strs3 = "施金润/企业管理部（全面深化改革办公室）/云南电网公司";
//        String[] ccc = CharacterSegmentUtil.SlashSegmentation(strs3, CharacterSegmentUtil.POSITIVE_SLANT);
//        for(String c : ccc){
//            System.out.println(c);
//        }

//        String strs4 = "UserLoginValidator/";
//        String[] ccc = CharacterSegmentUtil.SlashSegmentation(strs4, CharacterSegmentUtil.POSITIVE_SLANT);
//        for(String c : ccc){
//            System.out.println(c);
//        }
//
//        Object[] objShould = new Object[3];
//        for(Object o : objShould){
//            System.out.println(o);
//        }

//        List list = new ArrayList();
//        for(int i = 0; i < 53; i ++){
//            list.add(i);
//        }
//        dealBySubList(list, 10);
    }

    public static void dealBySubList(List<Integer> sourList, int batchCount){
        int sourListSize = sourList.size();
        int subCount = sourListSize%batchCount==0 ? sourListSize/batchCount : sourListSize/batchCount+1;
        int startIndext = 0;
        int stopIndext = 0;
        for(int i=0;i<subCount;i++){
            stopIndext = (i==subCount-1) ? stopIndext + sourListSize%batchCount : stopIndext + batchCount;
            List<Integer> tempList = new ArrayList<>(sourList.subList(startIndext, stopIndext));
            int b = tempList.get(0);
            b = 33;  // tempList中的值不会改变
            tempList.set(0, 66); // sourList中的值不会改变
            printList(tempList);
            startIndext = stopIndext;
        }
    }

    public static void printList(List<Integer> sourList){
        for(int j=0;j<sourList.size();j++){
            System.out.println(sourList.get(j));
        }
        System.out.println("------------------------");
    }

}
