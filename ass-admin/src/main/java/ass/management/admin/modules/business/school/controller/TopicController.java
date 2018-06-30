package ass.management.admin.modules.business.school.controller;

import ass.management.admin.common.aspect.SysLogConfig;
import ass.management.admin.modules.business.school.service.TopicAdminServiceImpl;
import ass.management.admin.modules.sys.controller.AbstractController;
import ass.management.business.school.entity.HtTopic;
import ass.management.common.utils.R;
import ass.management.common.validator.ValidatorUtils;
import ass.management.common.validator.group.UpdateGroup;
import ass.management.db.utils.PageUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ass.management.admin.common.annotation.SysLog;

import java.util.Map;

@RestController
@RequestMapping("/school")
public class TopicController extends AbstractController {

    @Autowired
    TopicAdminServiceImpl topicAdminServiceImpl;

    /**
     * 热门话题
     */
    @RequestMapping(value = "/topic/list", method = RequestMethod.GET)
    @RequiresPermissions("business:school:topic:list")
    public R topicList(@RequestParam Map<String, Object> params){
        PageUtils page = topicAdminServiceImpl.topicQueryPageMap(params);
        return R.ok().put("page", page);
    }
    @RequestMapping(value = "/topic/info/{id}", method = RequestMethod.GET)
    @RequiresPermissions("business:school:topic:info")
    public R topicInfo(@PathVariable("id") int id){
        return R.ok().put("topic", topicAdminServiceImpl.topicInfo(id));
    }
    @RequestMapping(value = "/topic/save", method = RequestMethod.POST)
    @RequiresPermissions("business:school:topic:save")
    @SysLog(SysLogConfig.ADD + SysLogConfig.COLON  + SysLogConfig.SCHOOL + SysLogConfig.COLON + SysLogConfig.HTTOPIC)
    public R saveTopic(@RequestBody HtTopic htTopic){
        ValidatorUtils.validateEntity(htTopic);
        topicAdminServiceImpl.saveOrUpDateTopic(htTopic);
        return R.ok();
    }
    @RequestMapping(value = "/topic/update", method = RequestMethod.POST)
    @RequiresPermissions("business:school:topic:update")
    @SysLog(SysLogConfig.UPDATE + SysLogConfig.COLON  + SysLogConfig.SCHOOL + SysLogConfig.COLON + SysLogConfig.HTTOPIC)
    public R updateTopic(@RequestBody HtTopic htTopic){
        ValidatorUtils.validateEntity(htTopic, UpdateGroup.class);
        topicAdminServiceImpl.saveOrUpDateTopic(htTopic);
        return R.ok();
    }
    @RequestMapping(value = "/topic/del", method = RequestMethod.POST)
    @RequiresPermissions("business:school:topic:del")
    @SysLog(SysLogConfig.DEL + SysLogConfig.COLON  + SysLogConfig.SCHOOL + SysLogConfig.COLON + SysLogConfig.HTTOPIC)
    public R delTopic(@RequestBody Integer[] ids){
        topicAdminServiceImpl.delTopicByIds(ids);
        return R.ok();
    }

}
