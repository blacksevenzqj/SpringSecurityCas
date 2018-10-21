package ass.management.admin.modules.sys.controller;

import ass.management.admin.common.excel.ExcelContext;
import ass.management.admin.common.excel.config.ExcelConfig;
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
import org.apache.poi.ss.usermodel.Workbook;
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
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.OutputStream;
import java.util.*;

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
					ExcelImportResult result = excelContext.readExcel("student", file.getInputStream(), 2);
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
			logger.info(basePath + filePath);
			File file = new File(basePath + filePath);
			HttpHeaders headers = new HttpHeaders();
			//下载显示的文件名，解决中文名称乱码问题
//			String downloadFielName = new String("白马东区17幢1单元601.txt".getBytes("UTF-8"), "iso-8859-1");
			String downloadFielName = java.net.URLEncoder.encode("白马东区17幢1单元601.txt", "UTF-8");
			//通知浏览器以attachment（下载方式）打开图片
			headers.setContentDispositionFormData("attachment", downloadFielName);
			//application/octet-stream ： 二进制流数据（最常见的文件下载）。
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
		}catch (Exception e){
			logger.error("下载失败！" + e);
			return null;
		}
	}

	@RequestMapping(value = "/testDecode", method = { RequestMethod.GET, RequestMethod.POST })
	public R testDecode(HttpServletRequest request, @RequestParam("testDecode") String testDecode)throws Exception {
		logger.info(testDecode);
		log.info(testDecode);
		System.out.println(testDecode);
		System.out.println(testDecode.equals("中"));
		return R.ok();
	}

	/**
	 * 二级任务导入
	 */
//	@RequestMapping(value = "/importDbxItemList", method = RequestMethod.POST)
//	public @ResponseBody
//	Object importDbxItemList(HttpServletRequest request) throws Exception {
//		String dcdbInfoId = request.getParameter("dcdbInfoId");
//		JSONObject ret = new JSONObject();
//		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession()
//				.getServletContext());
//		if (multipartResolver.isMultipart(request)) {
//			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
//			Iterator iter = multiRequest.getFileNames();
//			Map<String, Integer> tempMap = new HashMap();
//			while (iter.hasNext()) {
//				MultipartFile file = multiRequest.getFile(iter.next().toString());
//				if (file != null) {
//					ExcelImportResult excelImportResult = null;
//					try {
//						Map map = new HashMap();
//						map.put(ExcelConfig.Convert.DCDB_INFO_ID, dcdbInfoId);
//						excelImportResult = excelContext.readExcel(ExcelConfig.Bean.DB_XITEM_AO, 0, file
//								.getInputStream(), map);
//					} catch (ExcelException e) {
//						ret.put("status", false);
//						ret.put("result", e.getMessage());
//						return retString(ret);
//					}
//					List<DbxitemAO> dbxitemAOList = excelImportResult.getListBean();
//					for (int i = 0; i < dbxitemAOList.size(); i++) {
//						if (dbxitemAOList.get(i) != null) {
//							Integer tempValue = tempMap.get(dbxitemAOList.get(i).getDbxsequence());
//							if (tempValue == null) {
//								// 设置序号
//								int maxSort = 0;
//								DbxitemCriteria exampleSort = new DbxitemCriteria();
//								exampleSort.setOrderByClause("sort");
//								exampleSort.createCriteria().andDcdbinfoidEqualTo(dbxitemAOList.get(i).getDcdbinfoid())
//										.andDbxsequenceEqualTo(dbxitemAOList.get(i).getDbxsequence());
//								ServiceResult<List<DbxitemAO>> dbxAO = dcdbDbxService.selectByCriteria(exampleSort);
//								if (dbxAO.isSucceed() && dbxAO.getData() != null && dbxAO.getData().size() > 0) {
//									List<DbxitemAO> listSort = dbxAO.getData();
//									maxSort = listSort.get(listSort.size() - 1).getSort();
//								}
//								tempMap.put(dbxitemAOList.get(i).getDbxsequence(), maxSort);
//							}
//							tempMap.put(dbxitemAOList.get(i).getDbxsequence(), tempMap.get(dbxitemAOList.get(i)
//									.getDbxsequence()) + 1);
//							dbxitemAOList.get(i).setSort(
//									tempMap.get(dbxitemAOList.get(i).getDbxsequence()).shortValue());
//							dbxitemAOList.get(i).setStatus("未到办结期限");
//							ServiceResult<DbxitemAO> serviceResult = dcdbDbxService.saveOrUpdateRetAO(dbxitemAOList
//									.get(i));
//							if (serviceResult.isSucceed() && serviceResult.getData() != null) {
//								ret.put("status", true);
//							} else {
//								ret.put("result", serviceResult.getMsg());
//							}
//						}
//					}
//				}
//				return retString(ret);
//			}
//			ret.put("status", false);
//			ret.put("result", "请选择上传的Execl文件");
//		}
//		return retString(ret);
//	}


	/**
	 * 二级任务下载
	 */
//	@RequestMapping("/exportExcel")
//	public void exportExcel(String dcdbInfoId, HttpServletResponse response) throws Exception {
//		response.setContentType("application/x-download");
//		String fileName = dcdbInfoId + "二级任务下载.xlsx";
//		response.addHeader("content-disposition", "attachment;filename="
//				+ java.net.URLEncoder.encode(fileName, "UTF-8"));
//		OutputStream outs = response.getOutputStream();
//
//		DbxitemCriteria example = new DbxitemCriteria();
//		example.setOrderByClause("sort");
//		example.createCriteria().andDcdbinfoidEqualTo(dcdbInfoId);
//		ServiceResult<List<DbxitemAO>> searchServiceResult = dcdbDbxService.selectByCriteria(example);
//
//		if (searchServiceResult != null && searchServiceResult.isSucceed() && searchServiceResult.getData() != null
//				&& searchServiceResult.getData().size() > 0) {
//			List<DbxitemAO> list = searchServiceResult.getData();
//			ExcelExportResult exportResult = excelContext.createExcelForPart(ExcelConfig.Bean.DB_XITEM_AO, list);
//			Workbook workbook = exportResult.build();
//			workbook.write(outs);
//			workbook.close();
//		}
//	}


	/**
	 * 任务反馈下载
	 */
//	@RequestMapping("/exportExcel")
//	public void exportExcel(String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		User user = userUtils.getUserFromRequest(request);
//		List<Group> gList = groupService.getGroupsOfUser(user.getCode()).getData();
//		boolean gxdcdbms = false;
//		if (gList != null && gList.size() > 0) {
//			for (Group g : gList) {
//				if (g.getCode().contains("gxdcdbdbms") || g.getCode().contains("gxdcdbshr")) {
//					gxdcdbms = true;
//				}
//			}
//		}
//		String orgUnitCode = user.getOrgunitcode();
//		DcdbFkInfoAllAO dcdbFk = dcdbFkInfoService.getDbFkInfoById(id).getData();
//
//		response.setContentType("application/x-download");
//		String fileName = dcdbFk.getTitle() + ".xlsx";
//		response.addHeader("content-disposition", "attachment;filename="
//				+ java.net.URLEncoder.encode(fileName, "UTF-8"));
//		OutputStream outs = response.getOutputStream();
//
//		List<DcdbgzmbfjAO> mbfjList = new ArrayList<DcdbgzmbfjAO>();
//		DcdbInfoAO dcdbInfo = new DcdbInfoAO();
//		String dcdbLxInfoId = dcdbFk.getDcdbLxInfoId();
//
//		List<DcdbFkInfoItemAOExport> excelList = new ArrayList<DcdbFkInfoItemAOExport>();
//		int index = 0;
//		if (dcdbFk != null) {
//			//新加判断空
//			if (dcdbLxInfoId != null && dcdbLxInfoId != "") {
//				mbfjList = dcdbgzmbfjService.getDcdbgzmbfjInfoByDcdbId(dcdbLxInfoId);
//			}
//			if (mbfjList != null && mbfjList.size() > 0) {
//				for (int i = 0; i < mbfjList.size(); i++) {
//					DcdbgzmbfjAO ao = mbfjList.get(i);
//					if (!Strings.isEmptyOrNull(ao.getZrunitcode())) {
//						if (orgUnitCode.contains(ao.getZrunitcode()) || gxdcdbms) {
//							ao.setQxFlag("1");//有权限
//						} else {
//							ao.setQxFlag("0");//无权限
//						}
//					} else {
//						ao.setQxFlag("0");//无权限
//					}
//					List<DcdbFkInfoItemAO> fkItemList = dcdbFkInfoItemService.getFkItemInfoByMbfjId(ao.getId(), id);
//					if (fkItemList != null && fkItemList.size() > 0) {
//						for (DcdbFkInfoItemAO fk : fkItemList) {
//							DcdbFkInfoItemAOExport dcdbFkInfoItemAOExport = new DcdbFkInfoItemAOExport();
//							dcdbFkInfoItemAOExport.setIndex(++index);
//							dcdbFkInfoItemAOExport.setMbfjdseq(ao.getMbfjdseq());
//							dcdbFkInfoItemAOExport.setZrunitname(ao.getZrunitname());
//							dcdbFkInfoItemAOExport.setWorktarget(ao.getWorktarget());
//							if (Strings.isEmptyOrNull(fk.getDbxItemId())) {
//								dcdbFkInfoItemAOExport.setBjqxStr(ao.getBfqxStr());
//							} else {
//								DbxitemAO item = dbxitemService.getById(fk.getDbxItemId()).getData();
//								if (item != null) {
//									dcdbFkInfoItemAOExport.setBjqxStr(item.getDbxbfqxStr());
//								}
//							}
//							dcdbFkInfoItemAOExport.setWorkCont(fk.getWorkCont());
//							dcdbFkInfoItemAOExport.setCurProgress(fk.getCurProgress());
//							dcdbFkInfoItemAOExport.setSituateDescript(fk.getSituateDescript());
//							dcdbFkInfoItemAOExport.setTroubleAndSolv(fk.getTroubleAndSolv());
//							dcdbFkInfoItemAOExport.setNextWork(fk.getNextWork());
//							excelList.add(dcdbFkInfoItemAOExport);
//						}
//					}
//				}
//				ExcelExportResult exportResult = excelContext.createExcelForPart(ExcelConfig.Bean.DCDB_KF_ITEM_AO,
//						excelList);
//				Workbook workbook = exportResult.build();
//				workbook.write(outs);
//				workbook.close();
//			}
//		}
//	}


}
