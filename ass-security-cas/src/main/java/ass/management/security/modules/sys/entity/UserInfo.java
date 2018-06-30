package ass.management.security.modules.sys.entity;

import ass.management.db.pojo.IncrementDataEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户信息
 */
public class UserInfo extends IncrementDataEntity implements UserDetails {

	private static final long serialVersionUID = -1041327031937199938L;

	@Override
	public boolean isNewRecord() {
		return id == null;
	}

	/**
	 * 用户ID
	 */
	private Long id;

	/**
	 * 用户名称
	 */
	private String name;

	/**
	 * 登录名称
	 */
	private String username;

	/**
	 * 登录密码
	 */
	private String password;

	/**
	 * 盐值
	 */
	private String salt;

	/**
	 * 账户状态：0：删除 1：正常 2：未启用
	 */
	private Integer state;

	/**
	 * 人员类别  0:农户  1：企业
	 */
	private Integer persontype;



	private boolean isAccountNonExpired = true;

	private boolean isAccountNonLocked = true;

	private boolean isCredentialsNonExpired = true;

	private boolean isEnabled = true;

	private Set<AuthorityInfo> authorities = new HashSet<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return isEnabled;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getPersontype() {
		return persontype;
	}
	public void setPersontype(Integer persontype) {
		this.persontype = persontype;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		isAccountNonExpired = accountNonExpired;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		isAccountNonLocked = accountNonLocked;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		isCredentialsNonExpired = credentialsNonExpired;
	}

	public void setEnabled(boolean enabled) {
		isEnabled = enabled;
	}

	public void setAuthorities(Set<AuthorityInfo> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String toString() {
		return "UserInfo{" +
				"id=" + id +
				", name='" + name + '\'' +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", salt='" + salt + '\'' +
				", state=" + state +
				", persontype=" + persontype +
				", isAccountNonExpired=" + isAccountNonExpired +
				", isAccountNonLocked=" + isAccountNonLocked +
				", isCredentialsNonExpired=" + isCredentialsNonExpired +
				", isEnabled=" + isEnabled +
				", authorities=" + authorities +
				'}';
	}
}
