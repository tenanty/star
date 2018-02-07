/**
 * 
 */
package com.channelsoft.po;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Where;

/**
 * 角色信息
 *
 * @author tenanty
 */
@Entity(name = "role")
public class Role {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="role_id")
	private String roleId;
	@Column(name="role_name")
	private String roleName;
	@Column(name="role_eng_name")
	private String roleEngName;
	@Column(name="role_type")
	private String roleType;
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(name="role_menu_rela",joinColumns={@JoinColumn(name="role_id")},inverseJoinColumns={@JoinColumn(name="menu_id")})
	@javax.persistence.OrderBy(value="menu_order ASC")
	@Where(clause="parent_id=0")
	private Set<Menu> menus;
	/**
	 * 使用set会过滤掉重复值,使用List时出现重复值将会报错
	 *
	 */
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(name="role_permission_rela",joinColumns={@JoinColumn(name="role_id")},inverseJoinColumns={@JoinColumn(name="permission_id")})
	private Set<Permission> permission;
	

	public Role() {
		super();
	}

	public Role(String roleId) {
		super();
		this.roleId = roleId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleEngName() {
		return roleEngName;
	}

	public void setRoleEngName(String roleEngName) {
		this.roleEngName = roleEngName;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public Set<Menu> getMenus() {
		return menus;
	}

	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}

	public Set<Permission> getPermission() {
		return permission;
	}

	public void setPermission(Set<Permission> permission) {
		this.permission = permission;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName
				+ ", roleEngName=" + roleEngName + ", roleType=" + roleType
				+ ", menus=" + menus + ", permission=" + permission + "]";
	}

}
