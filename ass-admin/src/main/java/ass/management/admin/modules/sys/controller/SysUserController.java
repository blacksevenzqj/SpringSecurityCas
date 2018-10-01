package ass.management.admin.modules.sys.controller;

import ass.management.admin.common.excel.ExcelContext;
import ass.management.admin.common.excel.model.BookModel;
import ass.management.admin.common.excel.model.StudentModel;
import ass.management.admin.common.excel.result.ExcelImportResult;
import ass.management.admin.modules.sys.entity.SysUserEntity;
import ass.management.admin.modules.sys.service.SysUserRoleServiceImpl;
import ass.management.admin.modules.sys.service.SysUserServiceImpl;
import ass.management.common.utils.R;
import ass.management.common.validator.Assert;
import ass.management.common.validator.ValidatorUtils;
import ass.management.common.validator.group.AddGroup;
import ass.management.common.validator.group.UpdateGroup;
import ass.management.db.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ass.management.admin.common.annotation.SysLog;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 系统用户
 */
@RestController
@RequestMapping("/sys/user")
@Slf4j
public class SysUserController extends AbstractController {

	@Autowired
	private SysUserServiceImpl sysUserService;

	@Autowired
	private SysUserRoleServiceImpl sysUserRoleService;

	@Autowired
	ExcelContext excelContext;
	
	/**
	 * 所有用户列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:user:list")
	public R list(@RequestParam Map<String, Object> params){
		PageUtils page = sysUserService.queryPageMap(params);
		return R.ok().put("page", page);
	}
	
	/**
	 * 获取登录的用户信息
	 */
	@RequestMapping("/info")
	public R info(){
		return R.ok().put("user", getUser());
	}
	
	/**
	 * 修改登录用户密码
	 */
	@SysLog("修改密码")
	@RequestMapping("/password")
	public R password(String password, String newPassword){
		Assert.isBlank(newPassword, "原始密码不为能空");
		Assert.isBlank(newPassword, "新密码不为能空");
		//更新密码
		boolean flag = sysUserService.updatePassword(getUser(), password, newPassword);
		if(!flag){
			return R.error("原密码不正确");
		}
		return R.ok();
	}
	
	/**
	 * 用户信息
	 */
	@RequestMapping("/info/{userId}")
	@RequiresPermissions("sys:user:info")
	public R info(@PathVariable("userId") Long userId){
		SysUserEntity user = sysUserService.get(userId);
		//获取用户所属的角色列表
		List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
		user.setRoleIdList(roleIdList);
		return R.ok().put("user", user);
	}
	
	/**
	 * 保存用户
	 */
	@SysLog("保存用户")
	@RequestMapping("/save")
	@RequiresPermissions("sys:user:save")
	public R save(@RequestBody SysUserEntity user){
		ValidatorUtils.validateEntity(user, AddGroup.class);
		sysUserService.saveUser(user);
		return R.ok();
	}
	
	/**
	 * 修改用户
	 */
	@SysLog("修改用户")
	@RequestMapping("/update")
	@RequiresPermissions("sys:user:update")
	public R update(@RequestBody SysUserEntity user){
		ValidatorUtils.validateEntity(user, UpdateGroup.class);
		sysUserService.upDateUser(user);
		return R.ok();
	}
	
	/**
	 * 删除用户
	 */
	@SysLog("删除用户")
	@RequestMapping("/delete")
	@RequiresPermissions("sys:user:delete")
	public R delete(@RequestBody Long[] userIds){
		if(ArrayUtils.contains(userIds, 1L)){
			return R.error("系统管理员不能删除");
		}
		if(ArrayUtils.contains(userIds, getUserId())){
			return R.error("当前用户不能删除");
		}
		sysUserService.deleteBatch(userIds);
		return R.ok();
	}


	@RequestMapping("/importExcel")
	public R importExcel(HttpServletRequest request) throws Exception{
		long  startTime=System.currentTimeMillis();
		//将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
		CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());
		//检查form中是否有enctype="multipart/form-data"
		if(multipartResolver.isMultipart(request)) {
			//将request变成多部分request
			MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
			//获取multiRequest 中所有的文件名
			Iterator iter = multiRequest.getFileNames();
			while(iter.hasNext()) {
				//一次遍历所有文件
				MultipartFile file = multiRequest.getFile(iter.next().toString());
				if (file != null) {
					System.out.println(file);
					ExcelImportResult result = excelContext.readExcel("student", 2, file.getInputStream());
					System.out.println(result.getHeader());
					List<StudentModel> stus = result.getListBean();
					for (StudentModel stu : stus) {
						System.out.println(stu);
						BookModel book = stu.getBook();
						System.out.println(book);
						if (book != null) {
							System.out.println(book.getAuthor());
						}
					}
				}
			}
		}
		long  endTime=System.currentTimeMillis();
		System.out.println("方法三的运行时间："+String.valueOf(endTime-startTime)+"ms");
		return R.ok();
	}


	@RequestMapping(value = "/cruiseDownload", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<byte[]> cruiseDownload(HttpServletRequest request, @RequestParam("id") String id)throws Exception {
		return cruiseDownloadUtils(request, id);
	}

	public ResponseEntity<byte[]> cruiseDownloadUtils(HttpServletRequest request, String id){
		// 目录分隔符
		String separator = File.separator;
		//下载文件路径
        String basePath = request.getServletContext().getRealPath("");
        String filePath = "/WEB-INF/classes/statics/白马东区17幢1单元601.txt";
		try {
			log.info(basePath + filePath);
			File file = new File(basePath + filePath);
			HttpHeaders headers = new HttpHeaders();
			//下载显示的文件名，解决中文名称乱码问题
			String downloadFielName = new String("白马东区17幢1单元601.txt".getBytes("UTF-8"), "iso-8859-1");
			//通知浏览器以attachment（下载方式）打开图片
			headers.setContentDispositionFormData("attachment", downloadFielName);
			//application/octet-stream ： 二进制流数据（最常见的文件下载）。
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
		}catch (Exception e){
			log.error("下载失败！" + e);
			return null;
		}
	}

}
