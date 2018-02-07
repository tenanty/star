/**
 * 
 */
package com.channelsoft.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 权限信息
 *
 * @author tenanty
 */
@Entity(name = "permission")
public class Permission {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="permission_id")
	private String permissionId;
	@Column(name="permission_name")
	private String permissionName;
	@Column(name="permission_eng_name")
	private String permissionEngName;
	
	public Permission() {
		super();
	}

	public Permission(String permissionId) {
		super();
		this.permissionId = permissionId;
	}

	public String getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public String getPermissionEngName() {
		return permissionEngName;
	}

	public void setPermissionEngName(String permissionEngName) {
		this.permissionEngName = permissionEngName;
	}

	@Override
	public String toString() {
		return "Permission [permissionId=" + permissionId + ", permissionName="
				+ permissionName + ", permissionEngName=" + permissionEngName
				+ "]";
	}
	
}
