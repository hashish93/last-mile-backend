package com.appzoneltd.lastmile.privilege.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;
import com.appzoneltd.lastmile.privilege.dto.MenuDto;
import com.appzoneltd.lastmile.privilege.holder.Parameters;
import com.appzoneltd.lastmile.privilege.model.Menu;
import com.appzoneltd.lastmile.privilege.model.Role;
import com.appzoneltd.lastmile.privilege.service.MenuService;
import com.appzoneltd.lastmile.privilege.service.RoleService;
import com.appzoneltd.lastmile.privilege.transform.MenuTransformer;

@RestController
public class MenuController {

	private static Logger log = LoggerFactory.getLogger(MenuController.class);

	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private MenuTransformer menuTransformer;
	 
	  @RequestMapping(value = "/menu/getByUserId", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	  //@PreAuthorize("hasRole('ROLE_listpackage')")
	  public ResponseEntity<?> getAllRolesBaseInfo(@RequestBody Parameters parameters) {
	         // Getting List of Roles 
		  List<Role> userRoles=roleService.findByUser(parameters.getId());
		  /// Set of MenuDTO
		  Set<MenuDto> menuDtos= new HashSet<MenuDto>();
		  // Filling All Base Menus
		  List<Menu> menus=menuService.getAllBaseMenus();
		  log.info(menus.toString());
		  if(menus.size()!=0){
			  for(Menu menu:menus){
				   MenuDto menuDto=menuTransformer.gettingDtoFromMenu(menu);
				   // Add to List 
				   menuDtos.add(menuDto);
			  }//end for Loop 
		  }//end if 	
		  
		  // Filling UserRoles Privileges if Found
		  if(userRoles.size()!=0){
			  Set<MenuDto> userMenuDto=menuTransformer.gettingAllMenusforRoles(userRoles);
			  menuDtos.addAll(userMenuDto);
	      }//end if 
		  
		  //// Decide where to Go 
		  if(menuDtos.isEmpty()){
			  // Error Message Return 
			  Message message=new Message(MessageType.ERROR,"No Menu","Has No Roles");  
			  return new ResponseEntity<Message>(message,HttpStatus.BAD_REQUEST); 
		  }else{
			  return new ResponseEntity<Set<MenuDto>>(menuDtos, HttpStatus.OK);
		  }
		  
	  }
	
}
