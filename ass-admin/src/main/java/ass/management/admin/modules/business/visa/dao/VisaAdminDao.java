package ass.management.admin.modules.business.visa.dao;


import ass.management.admin.modules.business.visa.entity.VisaComboVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface VisaAdminDao {

    List<VisaComboVo> visaComboVoQueryList(Map<String, Object> queryMap);

    void cbaseInformationDelBatchBycomboIds(@Param("ids") Integer[] ids);

    void cneedKnowDelBatchBycomboIds(@Param("ids") Integer[] ids);

    void handleProceduresDelBatchBycomboIds(@Param("ids") Integer[] ids);

    void roleMaterialDelBatchBycomboIds(@Param("ids") Integer[] ids);

}
