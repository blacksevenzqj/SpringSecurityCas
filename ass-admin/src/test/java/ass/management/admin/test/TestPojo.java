package ass.management.admin.test;

import ass.management.admin.modules.sys.entity.SysUserEntity;
import ass.management.admin.modules.sys.shiro.ShiroUtils;

public class TestPojo {

    public static void main(String[] args){
//        SysUserEntity sysUserEntity = new SysUserEntity();
//        System.out.println(sysUserEntity);
//        String str = "123456";
//        String salt = "YzcmCZNvbXocrsz9dm8e";
//        System.out.println(ShiroUtils.sha256(str, salt));

        int i = 253;
        System.out.println(Integer.toBinaryString(i));
        int a = i << 2;
        System.out.println(Integer.toBinaryString(a));

    }

}
