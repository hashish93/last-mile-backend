package com.appzoneltd.lastmile.privilege.transform;

import com.appzoneltd.lastmile.privilege.dto.PrivilegeResponseDTO;
import com.appzoneltd.lastmile.privilege.dto.RoleResponseDTO;
import com.appzoneltd.lastmile.privilege.dto.RoleUserDTO;
import com.appzoneltd.lastmile.privilege.model.ModulePrivilege;
import com.appzoneltd.lastmile.privilege.model.Role;
import com.appzoneltd.lastmile.privilege.model.RolePrivilege;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleTransformer {

	
	public RoleTransformer() {
	}



	public RoleResponseDTO getDTOsFromEntities(Role role) {
		return fillingRoleTO(role);
	}

	public RoleUserDTO getRoleUserDTOFromRoles(List<Role> roles){
		/// Init Object
		RoleUserDTO roleUserDTO=new RoleUserDTO();
		// Set 
		Set<PrivilegeResponseDTO> privilegeResponseDTOs=new HashSet<PrivilegeResponseDTO>();
	    // Loop 
		for(Role role:roles){
			for(RolePrivilege rolePrivilege:role.getRolePrivileges()){
				// Getting Module Privilege
				if(rolePrivilege.isEnabled()){
					ModulePrivilege modulePrivilege=rolePrivilege.getModulePrivilege();
					// Init Object
					PrivilegeResponseDTO  privilegeResponseDTO=new PrivilegeResponseDTO();
					privilegeResponseDTO.setId(modulePrivilege.getId());
					privilegeResponseDTO.setName(modulePrivilege.getIdentifierName());
					privilegeResponseDTO.setDisplayName(modulePrivilege.getPrivilege().getDisplayName());
					privilegeResponseDTO.setValue(rolePrivilege.isEnabled());
					/// Adding it ro Set
					privilegeResponseDTOs.add(privilegeResponseDTO);
				}//end if
			}//end Inner Loop 
		}//end for Each
		roleUserDTO.setPrivilegeResponseDTOs(privilegeResponseDTOs);
		// return result 
		return roleUserDTO;
	}
	
	private RoleResponseDTO fillingRoleTO(Role role) {
		// Init the RoleResponseDTO Object to be returned
		RoleResponseDTO roleResponseDTO = new RoleResponseDTO();
		/// Filling Role Information
		roleResponseDTO.setId(role.getId());
		roleResponseDTO.setName(role.getName());
		roleResponseDTO.setDescription(role.getDescription());
		roleResponseDTO.setEnabled(role.isEnabled());
		//// filling privilegeDTO
		List<PrivilegeResponseDTO> privilegeResponseDTOs = new ArrayList<PrivilegeResponseDTO>();
		// for Each for all Assigned Role for User
		if(role.getRolePrivileges()!=null){
		for (RolePrivilege rolePrivilege : role.getRolePrivileges()) {
			if(rolePrivilege.isEnabled()==true){
				PrivilegeResponseDTO privilegeResponseDTO = new PrivilegeResponseDTO();
				privilegeResponseDTO.setId(rolePrivilege.getModulePrivilege().getId());
				privilegeResponseDTO.setName(rolePrivilege.getModulePrivilege().getIdentifierName());
				privilegeResponseDTO.setDisplayName(rolePrivilege.getModulePrivilege().getPrivilege().getDisplayName());
				privilegeResponseDTO.setValue(rolePrivilege.isEnabled());
				// Add Object to List
				privilegeResponseDTOs.add(privilegeResponseDTO);
			}//end if 
		} // end for Each
		}//end outer If
		roleResponseDTO.setPrivilegeResponseDTOs(privilegeResponseDTOs);
		roleResponseDTO.setUsers(role.getUsers());
		// return result
		return roleResponseDTO;
	}// end fillingRoleTO

}
