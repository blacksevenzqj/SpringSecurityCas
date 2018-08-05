package ass.management.security.test.thread;

import ass.management.security.modules.sys.entity.UserInfo;
import ass.management.security.modules.sys.service.UserInfoServiceImpl;

public class RunableTest implements Runnable{

    private UserInfoServiceImpl userInfoServiceImpl;

    private String username;

    public RunableTest(UserInfoServiceImpl userInfoServiceImpl, String username) {
        this.userInfoServiceImpl = userInfoServiceImpl;
        this.username = username;
    }

    @Override
    public void run() {
        UserInfo userInfo = userInfoServiceImpl.queryAllPerms(username);
        System.out.println("RunableTest run !!!" + userInfo);
    }
}
