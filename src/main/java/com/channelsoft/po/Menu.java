/**
 * 
 */
package com.channelsoft.po;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.dexcoder.dal.annotation.Transient;

/**
 * 菜单信息
 * 
 * @author tenanty
 *
 */
@Entity(name = "menu")
public class Menu {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="menu_id")
	private String menuId;
	@Column(name="menu_name")
	private String menuName;
	@Column(name="menu_url")
	private String menuUrl;
	@Column(name="menu_order")
	private String menuOrder;
	@Column(name="menu_host")
	private String menuHost;
	@Column(name="menu_class")
	private String menuClass;

	/**
	 * joincolum为标识外键字段,而外键一定是在多的一侧,故当在manytoone下使用时,外键为本对象的字段
	 * 当在onetomany下使用时,外键为目标对象的字段
	 */
	@ManyToOne
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name="parent_id")
	private Menu parentMenu;

	/**
	 *  @ElementCollection
	 *  @CollectionTable(name="student", joinColumns=@JoinColumn(name="college_id"))
	 *  @Column(name="student_name")
	 *  private Set<String> students;
	 *  与@oneToMany映射类似,不过@oneToMany映射只能用于entity的映射,
	 *  @ElementCellection则可以用于基本类型和@Embeddable类的映射,主要用于非entity的映射
	 */
	@OneToMany(cascade=CascadeType.ALL,mappedBy="parentMenu",fetch=FetchType.EAGER)
	@javax.persistence.OrderBy(value="menu_order ASC")
	private List<Menu> subMenus = new ArrayList<Menu>();
	
	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(String menuOrder) {
		this.menuOrder = menuOrder;
	}

	public String getMenuHost() {
		return menuHost;
	}

	public void setMenuHost(String menuHost) {
		this.menuHost = menuHost;
	}

	public String getMenuClass() {
		return menuClass;
	}

	public void setMenuClass(String menuClass) {
		this.menuClass = menuClass;
	}
	@Transient
	public List<Menu> getSubMenus() {
		return subMenus;
	}

	public void setSubMenus(List<Menu> subMenus) {
		this.subMenus = subMenus;
	}

	@Override
	public String toString() {
		return "Menu [menuId=" + menuId + ", menuName=" + menuName
				+ ", menuUrl=" + menuUrl + ", menuOrder=" + menuOrder
				+ ", menuHost=" + menuHost + ", menuClass=" + menuClass
				+ ", parentMenu=" + parentMenu + ", subMenus=" + subMenus + "]";
	}

}
