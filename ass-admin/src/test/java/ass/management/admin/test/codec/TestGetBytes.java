package ass.management.admin.test.codec;


import ass.management.admin.test.Test1;
import ass.management.admin.test.Test2;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class TestGetBytes {

    public static void main(String[] args) throws Exception{

//        Test2 t3 = new Test2();
//        Test1 t4 = t3;
//        t4.name = "123";   不同包中的 其他类 不可以访问protected属性。

        String downloadFielName = new String("白马东区17幢1单元601.txt".getBytes("UTF-8"),
                "iso-8859-1");
        System.out.println(downloadFielName);

        String utf_8 = new String(downloadFielName.getBytes("iso-8859-1"), "UTF-8");
        System.out.println(utf_8);


        String str = java.net.URLEncoder.encode("白马东区17幢1单元601.txt", "UTF-8");
        System.out.println(str);
        String str2 = java.net.URLDecoder.decode(str, "UTF-8");
        System.out.println(str2);

        String v = "%E4%B8%AD";
        System.out.println(java.net.URLDecoder.decode(v, "UTF-8"));

        String w = "ä¸&_";
        String w1 = "中";
        String str3 = new String(w1.getBytes("UTF-8"), "iso-8859-1");
        System.out.println(str3);

        byte[] arr = w1.getBytes("UTF-8");
        for(byte b : arr){
            System.out.println(b);   // 输出  ASCII值
        }
        testInputStream();
//        testBytesInputStream();
    }


    public static void testInputStream() throws Exception{
        FileInputStream fis = new FileInputStream("C:\\Users\\dell\\Desktop\\222.txt");
        FileOutputStream fos = new FileOutputStream("C:\\Users\\dell\\Desktop\\333.txt");
        int b;
        while((b = fis.read()) != -1) {
            System.out.println(b);
            //fos.write(arr);
        }
        fis.close();
        fos.close();
    }

    public static void testBytesInputStream() throws Exception{
        FileInputStream fis = new FileInputStream("C:\\Users\\dell\\Desktop\\222.txt");
        FileOutputStream fos = new FileOutputStream("C:\\Users\\dell\\Desktop\\333.txt");
        int len;
//        byte[] arr = new byte[1024 * 8];//自定义字节数组
        byte[] arr = new byte[1024];//自定义字节数组

        while((len = fis.read(arr)) != -1) {  // len 在这里保存的是 字节数组的 长度，arr字节数组中
            System.out.println(arr.length);
            //fos.write(arr);
            fos.write(arr, 0, len);//写出字节数组写出有效个字节个数
        }
        //IO流(定义小数组)
        //write(byte[] b)
        //write(byte[] b, int off, int len)写出有效的字节个数
        fis.close();
        fos.close();
    }

}
