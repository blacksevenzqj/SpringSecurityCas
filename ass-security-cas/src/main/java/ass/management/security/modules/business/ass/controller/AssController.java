package ass.management.security.modules.business.ass.controller;

import ass.management.security.modules.business.ass.entity.DeviceData;
import ass.management.security.modules.business.ass.entity.JsonResult;
import ass.management.security.modules.business.ass.entity.Node;
import ass.management.security.modules.business.ass.service.AssDeviceDataServiceImpl;
import ass.management.security.modules.business.ass.service.AssNodeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * api接口
 */
@RestController
@RequestMapping({"/ass"})
public class AssController {

    @Autowired
    AssNodeServiceImpl assNodeServiceImpl;

    @Autowired
    AssDeviceDataServiceImpl assDeviceDataServiceImpl;


    /**
     * 拓扑节点列表
     * @param parentId 父节点
     * @param type 节点类型
     * @return
     */
    @RequestMapping(value = "/node_list")
    @ResponseBody
    public JsonResult getNodeList(@RequestParam(value = "parent_id", defaultValue = "0") long parentId, @RequestParam(value = "type", defaultValue = "0") int type) {

        Map<String, Object> data = new HashMap<String, Object>();
        List<Node> list = assNodeServiceImpl.getNodeList(parentId, type);

        data.put("list", list);

        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(0);
        jsonResult.setMsg("success");
        jsonResult.setData(data);

        return jsonResult;
    }


    /**
     * 统计分析查询
     * @param areaId
     * @param districtId
     * @param siteId
     * @param deviceId
     * @param beginTime
     * @param endTime
     * @param timeType
     * @param dataType
     * @return
     */
    @RequestMapping(value = "/device_data_list")
    @ResponseBody
    public JsonResult getDeviceDataList(@RequestParam(value = "area_id", defaultValue = "0") long areaId, @RequestParam(value = "district_id", defaultValue = "0") long districtId, @RequestParam(value = "site_id", defaultValue = "0") long siteId, @RequestParam(value = "device_id", defaultValue = "0") long deviceId, @RequestParam(value = "begin_time", defaultValue = "0") long beginTime, @RequestParam(value = "end_time", defaultValue = "0") long endTime, @RequestParam(value = "time_type", defaultValue = "0") int timeType, @RequestParam(value = "data_type", defaultValue = "0") int dataType) {

        String areaName = assNodeServiceImpl.getNodeName(areaId);
        String districtName = assNodeServiceImpl.getNodeName(districtId);
        String siteName = assNodeServiceImpl.getNodeName(siteId);
        String deviceName = assNodeServiceImpl.getNodeName(deviceId);

        Map<String, Object> data = new HashMap<String, Object>();
        List<DeviceData> list = assDeviceDataServiceImpl.getDeviceDataList(deviceId, timeType, dataType, beginTime, endTime, areaName, districtName, siteName, deviceName);

        long count = assDeviceDataServiceImpl.getDeviceDataListCount(deviceId, timeType, beginTime, endTime);

        Map<String, Object> summary = assDeviceDataServiceImpl.getDeviceDataListSummary(deviceId, timeType, dataType, beginTime, endTime);
        data.put("total", count);
        data.put("data", list);
        data.put("summary", summary);

        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(0);
        jsonResult.setMsg("success");
        jsonResult.setData(data);

        return jsonResult;
    }

}
