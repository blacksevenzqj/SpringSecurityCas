package ass.management.security.common.config.security.cas.custom;

import ass.management.security.modules.sys.entity.UserInfo;
import ass.management.security.modules.sys.service.UserInfoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


/**
 * 用于加载用户信息 实现UserDetailsService接口，或者实现AuthenticationUserDetailsService接口
 */
@Slf4j
public class CustomUserDetailsService implements AuthenticationUserDetailsService<CasAssertionAuthenticationToken> {

	private UserInfoServiceImpl userInfoServiceImpl;

	public CustomUserDetailsService(UserInfoServiceImpl userInfoServiceImpl) {
		this.userInfoServiceImpl = userInfoServiceImpl;
	}

	@Override
	public UserDetails loadUserDetails(CasAssertionAuthenticationToken token) throws UsernameNotFoundException {
		log.info("当前的用户名是：" + token.getName());
		UserInfo userInfo = userInfoServiceImpl.queryAllPerms(token.getName());
		return userInfo;
	}

}


//public class CustomUserDetailsService implements UserDetailsService {
//	//实现UserDetailsService接口，实现loadUserByUsername方法
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		System.out.println("当前的用户名是：" + username);
//		//这里我为了方便，就直接返回一个用户信息，实际当中这里修改为查询数据库或者调用服务什么的来获取用户信息
//		UserInfo userInfo = new UserInfo();
//		userInfo.setUsername("admin");
//		userInfo.setName("admin");
//		Set<AuthorityInfo> authorities = new HashSet<AuthorityInfo>();
//		AuthorityInfo authorityInfo = new AuthorityInfo("TEST");
//		authorities.add(authorityInfo);
//		userInfo.setAuthorities(authorities);
//		return userInfo;
//	}
//}
