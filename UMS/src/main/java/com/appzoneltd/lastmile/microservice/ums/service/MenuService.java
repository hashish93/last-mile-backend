package com.appzoneltd.lastmile.microservice.ums.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.ums.dao.MenuRepository;
import com.appzoneltd.lastmile.microservice.ums.dto.MenuDto;
import com.appzoneltd.lastmile.microservice.ums.entity.MenuEntity;
import com.appzoneltd.lastmile.microservice.ums.entity.ModulePrivilegeEntity;
import com.appzoneltd.lastmile.microservice.ums.entity.RolePrivilegeEntity;
import com.appzoneltd.lastmile.microservice.ums.entity.UserEntity;
import com.appzoneltd.lastmile.microservice.ums.entity.UserRoleEntity;
import com.appzoneltd.lastmile.microservice.ums.transformer.MenuTransformer;

@Service
@Qualifier("menuService")
public class MenuService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private MenuRepository menuRepository;

	public List<MenuDto> getAllUserMenus(Long userId) {
		Set<MenuEntity> menuEntities= getAllUserMenuEntities(userId);
		menuEntities.addAll(getAllBaseMenu());
		return MenuTransformer.getMenusDtoFromMenuEntities(menuEntities);		
	}
	
	public List<MenuEntity> getAllBaseMenu(){
		List <MenuEntity> baseMenuEntities = new ArrayList<>();
		baseMenuEntities = menuRepository.getAllBaseMenus();
		return baseMenuEntities;
	}

	private Set<MenuEntity> getAllUserMenuEntities(Long userId) {
		UserEntity userEntity = userService.getUserById(userId);
		Set<MenuEntity> menuEntities = new HashSet<>();
		if (userEntity != null) {			
			for (UserRoleEntity userRoleEntity : userEntity.getUsersRoles()) {
				menuEntities.addAll(getRoleMenus(userRoleEntity));
			}
		}
		return menuEntities;
	}

	private List<MenuEntity> getRoleMenus(UserRoleEntity userRoleEntity) {
		List<MenuEntity> menuEntities = new ArrayList<>();
		if (userRoleEntity.getRoles() != null) {
			for (RolePrivilegeEntity rolePrivilegeEntity : userRoleEntity.getRoles().getRolePrivileges()) {
				ModulePrivilegeEntity modulePrivilegeEntity = rolePrivilegeEntity.getModulePrivilege();
				if (modulePrivilegeEntity != null) {
					MenuEntity menuEntity = modulePrivilegeEntity.getMenuItem();
					if(menuEntity!=null){
						menuEntities.add(menuEntity);
						if(menuEntity.getParentMenu()!=null){
							menuEntities.add(menuEntity.getParentMenu());
						}
					}					
				}
			}
		}
		return menuEntities;
	}

}
