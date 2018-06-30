package ass.management.admin.modules.business.hot.controller;

import ass.management.admin.common.aspect.SysLogConfig;
import ass.management.admin.modules.business.hot.service.TodayNewsAdminServiceImpl;
import ass.management.admin.modules.sys.controller.AbstractController;
import ass.management.common.utils.R;
import ass.management.common.validator.ValidatorUtils;
import ass.management.common.validator.group.UpdateGroup;
import ass.management.db.utils.PageUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ass.management.admin.common.annotation.SysLog;
import ass.management.business.hot.entity.TodayNews;

import java.util.Map;

@RestController
@RequestMapping("/hot")
public class TodayNewsController extends AbstractController {

    @Autowired
    TodayNewsAdminServiceImpl hotAdminServiceImpl;

    /**
     * 今日热点
     */
    @RequestMapping(value = "/todaynews/list", method = RequestMethod.GET)
    @RequiresPermissions("business:hot:todaynews:list")
    public R todayNewsList(@RequestParam Map<String, Object> params){
        PageUtils page = hotAdminServiceImpl.todayNewsQueryPageMap(params);
        return R.ok().put("page", page);
    }
    @RequestMapping(value = "/todaynews/info/{id}", method = RequestMethod.GET)
    @RequiresPermissions("business:hot:todaynews:info")
    public R todayNewsInfo(@PathVariable("id") int id){
        return R.ok().put("todaynews", hotAdminServiceImpl.todayNewsInfo(id));
    }
    @RequestMapping(value = "/todaynews/save", method = RequestMethod.POST)
    @RequiresPermissions("business:hot:todaynews:save")
    @SysLog(SysLogConfig.ADD + SysLogConfig.COLON  + SysLogConfig.HOT + SysLogConfig.COLON + SysLogConfig.TODAYNEWS)
    public R saveTodayNews(@RequestBody TodayNews todayNews){
        ValidatorUtils.validateEntity(todayNews);
        hotAdminServiceImpl.saveOrUpDateTodayNews(todayNews);
        return R.ok();
    }
    @RequestMapping(value = "/todaynews/update", method = RequestMethod.POST)
    @RequiresPermissions("business:hot:todaynews:update")
    @SysLog(SysLogConfig.UPDATE + SysLogConfig.COLON  + SysLogConfig.HOT + SysLogConfig.COLON + SysLogConfig.TODAYNEWS)
    public R updateTodayNews(@RequestBody TodayNews todayNews){
        ValidatorUtils.validateEntity(todayNews, UpdateGroup.class);
        hotAdminServiceImpl.saveOrUpDateTodayNews(todayNews);
        return R.ok();
    }
    @RequestMapping(value = "/todaynews/del", method = RequestMethod.POST)
    @RequiresPermissions("business:hot:todaynews:del")
    @SysLog(SysLogConfig.DEL + SysLogConfig.COLON  + SysLogConfig.HOT + SysLogConfig.COLON + SysLogConfig.TODAYNEWS)
    public R delTodayNews(@RequestBody Integer[] ids){
    	hotAdminServiceImpl.delTodayNewsByIds(ids);
        return R.ok();
    }

}
