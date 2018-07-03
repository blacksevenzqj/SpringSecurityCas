package ass.management.security.test;

import ass.management.security.modules.business.ass.entity.Node;
import ass.management.security.modules.business.ass.service.AssNodeServiceImpl;
import ass.management.security.modules.sys.entity.UserInfo;
import ass.management.security.modules.sys.service.UserInfoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDataSource {

    @Autowired
    UserInfoServiceImpl userInfoServiceImpl;

    @Autowired
    AssNodeServiceImpl assNodeServiceImpl;


    @Test
    public void queryAllMenuId() {
//        UserInfo userInfo = userInfoServiceImpl.get(1L);
//        System.out.println(userInfo);

        UserInfo userInfo2 = userInfoServiceImpl.queryAllPerms("thinkgem");
        System.out.println(userInfo2);
    }

    @Test
    public void queryNode() {
        Node node = new Node();
//        node.setParentId(parentId);
        node.setType(1);
        List<Node> list = assNodeServiceImpl.findList(node);
        System.out.println(list);
    }


}
