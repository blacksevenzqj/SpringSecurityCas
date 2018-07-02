package ass.management.security.modules.business.test.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/advisory")
public class AdvisoryController {

    @RequestMapping("/hello")
    public String hello() {
        return "不验证哦";
    }

    @PreAuthorize("hasAuthority('sys:menu:edit')")//有TEST权限的才能访问
    @RequestMapping("/security")
    public String security() {
        return "hello world security";
    }


}
