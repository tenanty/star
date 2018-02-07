package com.channelsoft.auth;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.channelsoft.po.Permission;
import com.channelsoft.po.Role;
import com.channelsoft.po.UserInfo;
import com.channelsoft.service.IUserInfoService;
/**
 * 用户校验
 * 
 * @author tenanty
 */
@Service("userRealm")
public class UserRealm extends AuthorizingRealm {
	private Log logger = LogFactory.getLog(this.getClass());
	@Autowired
	private IUserInfoService userInfoService;
	
	public UserRealm() {
		super();
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		String username = String.valueOf(principals.getPrimaryPrincipal());

		final List<Role> roleInfos = userInfoService.queryRolesByUserName(username);
		for (Role role : roleInfos) {
			// 添加角色
			logger.debug(String.format("用户%s获取到角色%s", username, role.getRoleEngName()));
			authorizationInfo.addRole(role.getRoleEngName());

			final List<Permission> permissions = userInfoService.queryPermissionByRoleId(role.getRoleId());
			for (Permission permission : permissions) {
				// 添加权限
				logger.debug(String.format("==>用户%s获取到权限%s", username, permission.getPermissionEngName()));
				authorizationInfo.addStringPermission(permission.getPermissionEngName());
			}
		}
		return authorizationInfo;

	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		String username = String.valueOf(authcToken.getPrincipal());
		String password = new String((char[]) authcToken.getCredentials());
		// 通过数据库进行验证
		final UserInfo authentication = userInfoService.findUserInfo(new UserInfo(username, password));
		if (authentication == null) {
			throw new AuthenticationException("用户名或密码错误.");
		}
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, password, getName());
		return authenticationInfo;
	}

}
