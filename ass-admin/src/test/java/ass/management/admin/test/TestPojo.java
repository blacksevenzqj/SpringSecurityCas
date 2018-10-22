package ass.management.admin.test;

import ass.management.admin.modules.sys.entity.SysUserEntity;
import ass.management.admin.modules.sys.shiro.ShiroUtils;
import ass.management.common.concurrent.executor.*;
import ass.management.elasticsearch.util.CharacterSegmentUtil;

public class TestPojo {

    public static void main(String[] args) throws Exception{
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

        String strs = "\\中国南方电网责任有限公司\\云南电网有限责任公司\\云南电网有限责任公司曲靖供电局\\云南电网有限责任公司曲靖马龙供电局\\其他\\";
        String[] aaa = CharacterSegmentUtil.SlashSegmentation(strs, "\\\\");
        for(String a : aaa){
            System.out.println(a);
        }
        String strs2 = "/中国南方电网责任有限公司/云南电网有限责任公司/云南电网有限责任公司曲靖供电局/云南电网有限责任公司曲靖马龙供电局/其他/";
        String[] bbb = CharacterSegmentUtil.SlashSegmentation(strs2, "/");
        for(String b : bbb){
            System.out.println(b);
        }

    }

}
