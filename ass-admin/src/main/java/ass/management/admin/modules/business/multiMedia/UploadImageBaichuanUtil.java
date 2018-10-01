//package ass.management.admin.modules.business.multiMedia;
//
//import com.alibaba.media.MediaConfiguration;
//import com.alibaba.media.MediaFile;
//import com.alibaba.media.Result;
//import com.alibaba.media.client.MediaClient;
//import com.alibaba.media.client.impl.DefaultMediaClient;
//import com.alibaba.media.upload.UploadPolicy;
//import libs.fastjson.com.alibaba.fastjson.JSON;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//
///**
// * upyun上传 到云服务器
// */
//@Component
//@Slf4j
//public class UploadImageBaichuanUtil {
//    public static final String picSuffix = ".jpg";
//
//    public static final String ORIGINAL = "oldimg";
//
//    public static final String SMALL = "small";
//
//    public static final String SMALLSize = "56";
//
//    @Value("${pic.imageBasePath}")
//    private String imageBasePath;
//
//    @Value("${pic.customerPath}")
//    private String customerPath;
//
//
//    @Value("${mediaconf.ak}")
//    private String ak;
//    @Value("${mediaconf.sk}")
//    private String sk;
//    @Value("${mediaconf.namespace}")
//    private String namespace;
//    @Value("${mediaconf.urlprefix}")
//    private String urlprefix;
//
//
//    /**
//     * upyun上传原图和小图
//     * @param multipartFile
//     * @return
//     */
//    public String uploadOriginalAndSmall(MultipartFile multipartFile, String folderName, String picName){
//        String fileName = picName + UploadImageBaichuanUtil.picSuffix;
//
//        // 0.1 定义全局配置信息
// 		MediaConfiguration configuration = new MediaConfiguration();
//// 		configuration.setAk("23607021");
//// 		configuration.setSk("d9d7a17dae2a15f54bc2f6fb0cd2a0b7");
//// 		configuration.setNamespace("luckyaircar");
//        configuration.setAk(ak);
// 		configuration.setSk(sk);
// 		configuration.setNamespace(namespace);
//
//     	// 0.2 获取Client
// 		MediaClient client = new DefaultMediaClient(configuration);
//
// 		// 0.4 自定义上传策略
// 		UploadPolicy uploadPolicy = new UploadPolicy();
// 		uploadPolicy.setInsertOnly(UploadPolicy.INSERT_ONLY_NONE);
// 		uploadPolicy.setExpiration(System.currentTimeMillis() + 3600 * 1000);
//
// 		//client.multipartUpload(arg0)
//
// 		// 1. 简单上传接口，直接上传文件
// 		Result<MediaFile> result;
//		try {
//			result = client.upload(imageBasePath + folderName + "/", fileName, multipartFile.getInputStream(), multipartFile.getSize());
//			if (result.isSuccess()) {
//	             // 调用接口成功,打印出上传接口的返回信息
//	             log.info(JSON.toJSONString(result.getData()));
////                return "http://luckyaircar.image.alimmdn.com" + imageBasePath + folderName + "/" + fileName;
//                return urlprefix + imageBasePath + folderName + "/" + fileName;
//	         } else {
//	             // 调用接口失败,输出错误信息便于排查问题
//	             log.error(JSON.toJSONString(result));
//	             return null;
//	         }
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//        return null;
//    }
//
//    /**
//     * 实现upyun md5对字节流的处理
//     * @param inputStream
//     * @return
//     * @throws Exception
//     */
//    private String md5upyun(InputStream inputStream) throws Exception{
//        char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
//        MessageDigest md5 = null;
//
//        try {
//            md5 = MessageDigest.getInstance("MD5");
//            boolean encodedValue = false;
//            byte[] j = new byte[1024];
//
//            while(true) {
//                int var14 = inputStream.read(j);
//                if(var14 > 0) {
//                    md5.update(j, 0, var14);
//                }
//
//                if(var14 == -1) {
//                    inputStream.skip(0L);
//                    break;
//                }
//            }
//        } catch (NoSuchAlgorithmException var12) {
//            throw new RuntimeException(var12.getMessage());
//        } finally {
//            inputStream.close();
//        }
//
//        byte[] var15 = md5.digest();
//        int var16 = var15.length;
//        char[] finalValue = new char[var16 * 2];
//        int k = 0;
//
//        for(int i = 0; i < var16; ++i) {
//            byte encoded = var15[i];
//            finalValue[k++] = hexDigits[encoded >> 4 & 15];
//            finalValue[k++] = hexDigits[encoded & 15];
//        }
//
//        return new String(finalValue);
//
//    }
//}
