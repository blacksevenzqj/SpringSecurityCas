package ass.management.security.modules.business.test.controller;

import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.util.AssertionHolder;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.cas.authentication.CasAuthenticationToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/myAdvisory")
public class MyAdvisoryController {

    @RequestMapping("/hello")
    public String hello(HttpServletRequest request) {
        final CasAuthenticationToken token = (CasAuthenticationToken) request.getUserPrincipal();
        AttributePrincipal principal = token.getAssertion().getPrincipal();
        System.out.println(principal.getName());
        Map<String, Object> map = principal.getAttributes();

        return "不验证哦";
    }

    @PreAuthorize("hasAuthority('sys:menu:edit')")//有TEST权限的才能访问
    @RequestMapping("/security")
    public String security() {
        return "hello world security";
    }


}
