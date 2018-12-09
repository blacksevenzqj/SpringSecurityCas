package ass.management.admin.test;

public class TestInteger {

    public static void main(String[] args){
        Integer aa = 1000;   //自动装箱
        Integer bb = 1000;
        System.out.println("aa==bb?" + (aa == bb));
        Integer cc = 10;  // Integer的缓存，不new Integer(10);
        Integer dd = 10;
        System.out.println("cc==dd?" + (cc == dd));
        Integer ee = new Integer(10);
        Integer ff = new Integer(10);
        System.out.println("ee==ff?" + (ee == ff));
        System.out.println("ee==ff?" + (ee.compareTo(ff)));

    }


}
