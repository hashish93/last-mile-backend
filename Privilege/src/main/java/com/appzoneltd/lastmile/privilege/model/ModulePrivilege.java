package com.appzoneltd.lastmile.privilege.model;

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
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="module_privilege",schema="lastmile_authorization_server")
public class ModulePrivilege {

	private Integer id;
	private Privilege privilege;
	private Module module; 
    private String identifierName;
    private boolean value=false;
    private List<ModulePrivilege> child;
    private ModulePrivilege parent;
    private Menu menu;
    
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	@JsonIgnore
	@JoinColumn(name="privilege_id" , nullable=true)
	@ManyToOne(fetch = FetchType.EAGER,cascade = javax.persistence.CascadeType.ALL)
	public Privilege getPrivilege() {
		return privilege;
	}
	
	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}
	
	@JsonIgnore
	@JoinColumn(name="module_id" , nullable=true)
	@ManyToOne(fetch = FetchType.EAGER,cascade = javax.persistence.CascadeType.ALL)
	public Module getModule() {
		return module;
	}
	
	public void setModule(Module module) {
		this.module = module;
	}
	
	@Column(name="identifier_name",nullable =true)
	public String getIdentifierName() {
		return identifierName;
	}
	
	public void setIdentifierName(String identifierName) {
		this.identifierName = identifierName;
	}
	
	@OneToMany(cascade={CascadeType.ALL})
	@JoinColumn(name="parent")
	@Where(clause = "parent IS NOT NULL")
	public List<ModulePrivilege> getChild() {
		return child;
	}

	public void setChild(List<ModulePrivilege> child) {
		this.child = child;
	}

	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="parent")
	public ModulePrivilege getParent() {
		return parent;
	}

	public void setParent(ModulePrivilege parent) {
		this.parent = parent;
	}

	@Column(name="default_value")
	public boolean isValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}
	
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="menu_id")
	@OrderBy("theOrder asc")
	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	@Override
	public String toString() {
		String result= "ModulePrivilege [id=" + id + ", privilege=" + privilege + ", module=" + module + ", identifierName="
				+ identifierName + ", Has Childs = ";
					for(ModulePrivilege children:child){
						result+="\n CHILD : "+children.toString();	
					}
				result+="]";
		
		return result;
	}
    
    
	
}
