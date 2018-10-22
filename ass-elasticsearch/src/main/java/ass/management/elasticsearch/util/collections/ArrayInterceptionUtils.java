package ass.management.elasticsearch.util.collections;

import ass.management.elasticsearch.entity.base.EsBaseEntity;

import java.util.ArrayList;
import java.util.List;

public class ArrayInterceptionUtils {

    public static <T extends EsBaseEntity> List<List<T>> dealBySubList(List<T> sourList, int batchCount){
        int sourListSize = sourList.size();
        int subCount = sourListSize%batchCount==0 ? sourListSize/batchCount : sourListSize/batchCount+1;
        int startIndext = 0;
        int stopIndext = 0;
        List<List<T>> resultList = new ArrayList();
        for(int i=0;i<subCount;i++){
            stopIndext = (i==subCount-1) ? stopIndext + sourListSize%batchCount : stopIndext + batchCount;
            List<T> tempList = new ArrayList<>(sourList.subList(startIndext, stopIndext));
            resultList.add(tempList);
            startIndext = stopIndext;
        }
        return resultList;
    }


}
