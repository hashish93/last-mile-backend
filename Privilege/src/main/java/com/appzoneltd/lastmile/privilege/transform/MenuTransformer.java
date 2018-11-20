package com.appzoneltd.lastmile.privilege.transform;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.privilege.dto.MenuDto;
import com.appzoneltd.lastmile.privilege.model.Menu;
import com.appzoneltd.lastmile.privilege.model.Role;
import com.appzoneltd.lastmile.privilege.model.RolePrivilege;

@Service
public class MenuTransformer {

	public Set<MenuDto> gettingAllMenusforRoles(List<Role> roles){
		// Define List of MenuDto
		Set<MenuDto> menuDtos=new HashSet<MenuDto>();
		// ForEach
		for(Role role:roles){
			for(RolePrivilege rolePrivilege:role.getRolePrivileges()){
			    	if(rolePrivilege.isEnabled()){
			    	  MenuDto menuDto= new MenuDto();
			    	  Menu menu=rolePrivilege.getModulePrivilege().getMenu();
			    	  if(menu!=null){
				    	  menuDto.setId(menu.getId());
				    	  menuDto.setName(menu.getName());
				    	  menuDto.setKey(menu.getKey());
				    	  menuDto.setUrl(menu.getUrl());
				    	  menuDto.setOrder(menu.getTheOrder());
				    	  menuDto.setActive(true);
				    	  if(menu.getParent()!=null){
				    		  menuDto.setParent(menu.getParent().getId());
				    		  menuDtos.add(menuDto);
				    		  // Adding ParentTo List 
				    		  Menu parent=menu.getParent();
//				    		  /// Setting Parent Data 
				    		  MenuDto menuDto2= new MenuDto();
				    		  menuDto2.setId(parent.getId());
				    		  menuDto2.setName(parent.getName());
				    		  menuDto2.setKey(parent.getKey());
				    		  menuDto2.setOrder(parent.getTheOrder());
				    		  menuDto2.setUrl(parent.getUrl());
				    		  menuDto2.setActive(true);
				    		  menuDto2.setParent(-1);
				    		  // Adding to Set
				    		  menuDtos.add(menuDto2);
				    	  }else{
				    		  menuDto.setParent(-1);
				    		  menuDtos.add(menuDto);
				    	  }//end If-Else Block
			    	  }//end Inner If
			    	  
			    	}//end if Condition 	  	
			}//end Inner ForEach
		}//end Outer ForEach
		
		// return result
		return menuDtos;
	}
	
	public MenuDto gettingDtoFromMenu(Menu menu){
		
		  MenuDto menuDto= new MenuDto();
		  menuDto.setId(menu.getId());
	  	  menuDto.setName(menu.getName());
	  	  menuDto.setKey(menu.getKey());
	  	  menuDto.setUrl(menu.getUrl());
	  	  menuDto.setOrder(menu.getTheOrder());
	  	  menuDto.setActive(true);
	  	  if(menu.getParent()==null){
	  		menuDto.setParent(-1);
	  	  }else{
	  		menuDto.setParent(menu.getParent().getId());  
	  	  }
	  	  
		
	  	  // return MenuDTO
	  	  return menuDto;
	}
	
}
