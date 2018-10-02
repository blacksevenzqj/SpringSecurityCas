package ass.management.admin.test.codec;


public class TestGetBytes {

    public static void main(String[] args) throws Exception{

        String downloadFielName = new String("白马东区17幢1单元601.txt".getBytes("UTF-8"),
                "iso-8859-1");
        System.out.println(downloadFielName);

        String utf_8 = new String(downloadFielName.getBytes("iso-8859-1"), "UTF-8");
        System.out.println(utf_8);


        String str = java.net.URLEncoder.encode("白马东区17幢1单元601.txt", "UTF-8");
        System.out.println(str);
        String str2 = java.net.URLDecoder.decode(str, "UTF-8");
        System.out.println(str2);
    }

}
