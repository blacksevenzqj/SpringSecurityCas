package ass.management.admin.modules.business.school.service;

import ass.management.db.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ass.management.business.school.entity.HtTopic;
import ass.management.business.school.service.HtTopicServiceImpl;

import java.util.Date;
import java.util.Map;

@Slf4j
@Service(value = "topicAdminServiceImpl")
public class TopicAdminServiceImpl {

    @Autowired
    HtTopicServiceImpl htTopicServiceImpl;

    public PageUtils<HtTopic> topicQueryPageMap(Map<String, Object> params){
        return htTopicServiceImpl.queryPageMap(params);
    }
    public HtTopic topicInfo(int id){
        return htTopicServiceImpl.get(id);
    }

    public HtTopic saveOrUpDateTopic(HtTopic topic){
    	topic.setCreateTime(new Date());
        return htTopicServiceImpl.save(topic);
    }

    public void delTopicByIds(Integer[] ids){
    	htTopicServiceImpl.deleteBatchByIds(ids);
    }
   
}
