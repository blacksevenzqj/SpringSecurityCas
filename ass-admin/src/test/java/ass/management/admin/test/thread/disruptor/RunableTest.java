package ass.management.admin.test.thread.disruptor;


import ass.management.admin.modules.sys.service.SysUserServiceImpl;

import java.util.List;

public class RunableTest implements Runnable{

    private SysUserServiceImpl sysUserServiceImpl;

    private Long userId;

    public RunableTest(SysUserServiceImpl sysUserServiceImpl, Long userId) {
        this.sysUserServiceImpl = sysUserServiceImpl;
        this.userId = userId;
    }

    @Override
    public void run() {
        List<Long> list = sysUserServiceImpl.queryAllMenuId(userId);
        System.out.println("RunableTest run !!!" + list);
    }
}
