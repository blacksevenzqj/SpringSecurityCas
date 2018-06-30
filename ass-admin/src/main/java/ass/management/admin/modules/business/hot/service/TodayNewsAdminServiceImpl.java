package ass.management.admin.modules.business.hot.service;

import ass.management.db.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ass.management.admin.common.annotation.EsBusiness;
import ass.management.business.hot.entity.TodayNews;
import ass.management.business.hot.service.TodayNewsServiceImpl;
import ass.management.elasticsearch.entity.EsHotNew;

import java.util.Date;
import java.util.Map;

@Slf4j
@Service(value = "todayNewsAdminServiceImpl")
public class TodayNewsAdminServiceImpl {

    @Autowired
    TodayNewsServiceImpl todayNewsServiceImpl;

    public PageUtils<TodayNews> todayNewsQueryPageMap(Map<String, Object> params){
        return todayNewsServiceImpl.queryPageMap(params);
    }
    public TodayNews todayNewsInfo(int id){
        return todayNewsServiceImpl.get(id);
    }

    @EsBusiness(classType=EsHotNew.class, serviceUrl="http://118.190.26.38:9090/schoolpa/hot/getTodayNews", save=true)
    public TodayNews saveOrUpDateTodayNews(TodayNews todayNews){
    	todayNews.setCreateTime(new Date());
        return todayNewsServiceImpl.save(todayNews);
    }

    public void delTodayNewsByIds(Integer[] ids){
    	todayNewsServiceImpl.deleteBatchByIds(ids);
    }
   
}
