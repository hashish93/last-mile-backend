package com.appzoneltd.lastmile.privilege.transform;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.appzoneltd.lastmile.privilege.dto.ModuleDto;
import com.appzoneltd.lastmile.privilege.dto.PermissionDto;
import com.appzoneltd.lastmile.privilege.model.ModulePrivilege;

public class ModulePrivilegeTransformer {

	
	public List<ModulePrivilege> getEntitiesFromDTOs(List<ModuleDto> moduleDtos){
		return null;
	}
	
	public static List<ModuleDto> getDTOsFromEntities(List<ModulePrivilege> modulePrivileges){
		return fillingModuletoDTO(modulePrivileges);
	}
	
	/*******************************************************
	* Getting Role Privilege
	*******************************************************/
	private static List<ModuleDto> fillingModuletoDTO(List<ModulePrivilege> modulePrivileges){
		/// List of ModuleDto to be returned 
		List<ModuleDto> moduleDtos=new ArrayList<ModuleDto>();
		/// List of All Available Modules 
		Set<ModulePrivilege> modules=new HashSet<ModulePrivilege>();
		/// Extract the Modules from ModulePrivilege List
		for(ModulePrivilege modulePrivilege:modulePrivileges){
			modules.add(modulePrivilege);
		}//end for Loop 
		
		/*******************************************************
		* Building the Tree of the Module Privilege DTO
		*******************************************************/
		for(ModulePrivilege module:modules){
			// Init PermissionDTO to have main permissions
			List<PermissionDto> permissionDtos=new ArrayList<>();
			// Init ModuleDTO
			ModuleDto moduleDto=new ModuleDto();
			
			// Getting privilege List Per Module
			for(ModulePrivilege modulePrivilege:modulePrivileges){
				// Choose the Root Privilege
				if((modulePrivilege.getModule().getId()==module.getId())
						&& (modulePrivilege.getParent()==null)){
					// Define new ModuleDTO
					 // Assign Permission
					moduleDto.setId(modulePrivilege.getModule().getId());
					moduleDto.setName(modulePrivilege.getModule().getName());
					
					 PermissionDto permissionDto=new PermissionDto();
					 permissionDto.setId(modulePrivilege.getId());
			   		 permissionDto.setDisplayName(modulePrivilege.getPrivilege().getDisplayName());
			   		 permissionDto.setName(modulePrivilege.getIdentifierName());
			   		 permissionDto.setValue(modulePrivilege.isValue());
			   		 permissionDto.setChildren(getPermissionChildren(modulePrivilege));
			   		 permissionDtos.add(permissionDto);
			   	     // Add to Permission
			   		 moduleDto.setPermissions(permissionDtos);
			   		 //Adding to List
					 moduleDtos.add(moduleDto);
				}//end if Condotion
			}//end for Loop 
		}//end for Loop
		// return result
		return moduleDtos;
	}//gettingRoleModules
	//////////////////////////////////////////////////////////////////////////////////////
		public static List<PermissionDto> getPermissionChildren(ModulePrivilege modulePrivilege){
			// Init List of Permission DTO
			List<PermissionDto> child=new ArrayList<PermissionDto>();
			// Assign the Children 
			for(ModulePrivilege children:modulePrivilege.getChild()){
				PermissionDto permissionDto=new PermissionDto();
				permissionDto.setId(children.getId());
				permissionDto.setName(children.getIdentifierName());
				permissionDto.setDisplayName(children.getPrivilege().getDisplayName());
				permissionDto.setValue(children.isValue());
				permissionDto.setChildren(getPermissionChildren(children));
		        child.add(permissionDto);		
				}//end for Each 
			// return result 
			return child;
		}
	
}
