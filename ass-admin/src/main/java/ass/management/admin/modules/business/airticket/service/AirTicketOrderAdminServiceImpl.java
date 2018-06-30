package ass.management.admin.modules.business.airticket.service;

import ass.management.admin.modules.business.airticket.dao.AirTicketOrderDao;
import ass.management.db.pojo.Paging;
import ass.management.db.utils.PageUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ass.management.business.airticket.entity.vo.AirTicketOrderVo;

import java.util.List;
import java.util.Map;

@Slf4j
@Service(value = "airTicketOrderAdminServiceImpl")
public class AirTicketOrderAdminServiceImpl {

    @Autowired
    AirTicketOrderDao airTicketOrderDao;

    public PageUtils<AirTicketOrderVo> airTicketOrderQueryPageMap(Map<String, Object> params){
        if(params.get("pageNum") == null){
            params.put("pageNum", 1);
            params.put("pageSize", 10);
        }
        Paging page = new Paging(Integer.valueOf(params.get("pageNum").toString()), Integer.valueOf(params.get("pageSize").toString()));
        PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderBy());
        List<AirTicketOrderVo> list = airTicketOrderDao.airTicketOrderQueryPageMap(params);
        for(AirTicketOrderVo ato : list){
            ato.afterInit();
        }
        PageInfo<AirTicketOrderVo> pageInfo = new PageInfo<>(list);
        return new PageUtils<>(pageInfo.getList(), pageInfo.getTotal(), pageInfo.getPageSize(), pageInfo.getPageNum());
    }

//    public VisaCombo visaComboInfo(Integer id){
//        return visaComboServiceImpl.get(id);
//    }


}
