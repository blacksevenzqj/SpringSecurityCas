package ass.management.business.airticket.entity.vo;

import ass.management.business.airticket.entity.AirTicketOrderConfig;
import ass.management.business.airticket.entity.base.AirTicketOrder;
import ass.management.business.airticket.entity.fobook.DailyLowestPriceVO;
import lombok.Data;

import java.util.List;

@Data
public class AirTicketOrderVo extends AirTicketOrder {

    private String userName;

    private List<DailyLowestPriceVO> routingList;

    private String goCityName;
    private String returnCityName;

    private String goAirlineCompanyFlightNo;
    private String returnAirlineCompanyFlightNo;

    private String goAirportName;
    private String returnAirportName;

    private String goDateTime;
    private String returnDateTime;


    public void afterInit(){
        if(routingList != null && routingList.size() > 0){
            for(DailyLowestPriceVO dlp : routingList){
                setCityName(dlp);
                setAirlineCompanyFlightNo(dlp);
            }
        }
    }

    public void setCityName(DailyLowestPriceVO dlp){
        if(AirTicketOrderConfig.GO.equalsIgnoreCase(dlp.getTicketRoutingType())){
            setGoCityName(dlp.getOrgCityName() + ":" + dlp.getDstCityName());
        }else{
            setReturnCityName(dlp.getOrgCityName() + ":" + dlp.getDstCityName());
        }
    }

    public void setAirlineCompanyFlightNo(DailyLowestPriceVO dlp){
        if(AirTicketOrderConfig.GO.equalsIgnoreCase(dlp.getTicketRoutingType())){
            setGoAirlineCompanyFlightNo(dlp.getAirlineCompany() + ":" + dlp.getFlightNo());
        }else{
            setReturnAirlineCompanyFlightNo(dlp.getAirlineCompany() + ":" + dlp.getFlightNo());
        }
    }


}
