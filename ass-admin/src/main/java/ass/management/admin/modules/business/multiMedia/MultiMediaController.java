//package ass.management.admin.modules.business.multiMedia;
//
//import ass.management.common.utils.R;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.MultipartHttpServletRequest;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Controller
//@Slf4j
//@RequestMapping("/multiMedia")
//public class MultiMediaController {
//
//    @Autowired
//    private UploadImageBaichuanUtil uploadImageBaichuanUtil;
//
//    @RequestMapping(value = "/findUpload", method = RequestMethod.GET)
//    public String batchUplaod() {
//        return "modules/business/multimedia/upload";
//    }
//
//    @RequestMapping(value = "/batch/uplaod", method = RequestMethod.POST)
//    @ResponseBody
//    public R batchUplaod(@RequestParam String folderName, HttpServletRequest request) {
//        List<MultipartFile> files =((MultipartHttpServletRequest)request).getFiles("image");
//        if(files.size()==0){
//            return R.error(10001, "请选选择上传图片");
//        }
//        Map<String, Object> result = new HashMap<String, Object>();
//        MultipartFile file = null;
//        String imgUrls = "";
//        for (int i = 0; i < files.size(); ++i) {
//            String imgURL = "";
//            file = files.get(i);
//            if (!file.isEmpty()) {
//                checkSize(file);
//                checkExt(file.getOriginalFilename());
//                //上传upyun & 上传fastDFS
//                imgURL = uploadImageBaichuanUtil.uploadOriginalAndSmall(file, folderName, String.valueOf(System.currentTimeMillis()));
//                if(StringUtils.isNotBlank(imgURL)){
//                    if(StringUtils.isEmpty(imgUrls)){
//                        imgUrls = imgURL;
//                    }else{
//                        imgUrls = imgUrls + "#" + imgURL;
//                    }
//                }
//            }
//        }
//        if(StringUtils.isBlank(imgUrls)){
//            return R.error(10002, "图片上传错误");
//        }
//        log.info(imgUrls);
//        result.put("urls", imgUrls);
//        //修改用户数据库记录 & 返回
//        return R.ok(result);
//    }
//
//    /**
//     * 图片大小检察
//     * @param multipartFile 文件大小不能超过4M
//     * @throws RuntimeException
//     */
//    private void checkSize(MultipartFile multipartFile) throws RuntimeException{
//        if (multipartFile.getSize() > 10 * 1024 * 1024) {
//            throw new RuntimeException("上传图片过大,不能超过10M");
//        }
//    }
//
//    /**
//     * 扩展名检察
//     * @param fileName jpg/jpeg/bmp/png/gif
//     * @throws RuntimeException
//     */
//    private void checkExt(String fileName) throws RuntimeException{
//        if(fileName.contains(".")){
//            String[] extCheckList = {"jpg", "jpeg", "bmp", "png", "gif"};
//            String ext = fileName.substring(fileName.lastIndexOf(".")+ 1);
//            if(!Arrays.asList(extCheckList).parallelStream().anyMatch(extName -> extName.equals(ext))){
//                throw new RuntimeException("图片上传错误");
//            }
//        }else {
//            throw new RuntimeException("上传图片格式不正确");
//        }
//    }
//
//}
