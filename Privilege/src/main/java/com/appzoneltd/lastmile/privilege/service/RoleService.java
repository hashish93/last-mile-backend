package com.appzoneltd.lastmile.privilege.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appzoneltd.lastmile.privilege.dao.RoleDao;
import com.appzoneltd.lastmile.privilege.dao.RolePrivilegeDao;
import com.appzoneltd.lastmile.privilege.dto.SimpleRoleDTO;
import com.appzoneltd.lastmile.privilege.dto.ModuleDto;
import com.appzoneltd.lastmile.privilege.dto.PermissionDto;
import com.appzoneltd.lastmile.privilege.dto.PrivilegeResponseDTO;
import com.appzoneltd.lastmile.privilege.dto.RoleDTO;
import com.appzoneltd.lastmile.privilege.dto.RoleResponseDTO;
import com.appzoneltd.lastmile.privilege.holder.PrivilegeHolder;
import com.appzoneltd.lastmile.privilege.holder.RoleHolder;
import com.appzoneltd.lastmile.privilege.model.Module;
import com.appzoneltd.lastmile.privilege.model.ModulePrivilege;
import com.appzoneltd.lastmile.privilege.model.Role;
import com.appzoneltd.lastmile.privilege.model.RolePrivilege;
import com.appzoneltd.lastmile.privilege.model.User;

@Service
public class RoleService {

	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModulePrivilegeService modulePrivilegeService;
	
	@Autowired
	private RolePrivilegeDao  rolePrivilegeDao;
	/*******************************************************
	 * Findall Method
	 *******************************************************/
	public List<SimpleRoleDTO> gettingAllActiveRolesDTOS()
	{
		List<Role> roles=findAllActive();
		/// List of DTOs
		List<SimpleRoleDTO> activeRolesDTOs=new ArrayList<SimpleRoleDTO>();
		// For Each for All  roles 
		for(Role role:roles){
			SimpleRoleDTO activeRolesDTO = new SimpleRoleDTO();
			activeRolesDTO.setId(role.getId());
			activeRolesDTO.setName(role.getName());
			// Adding Object to List 
			activeRolesDTOs.add(activeRolesDTO);
		}//end for-Each 
		// return result
		return activeRolesDTOs;
	}
	
	public Role findById(Integer roleId){
		return roleDao.findById(roleId);
	}
	
	public List<Role> findAllActive(){
		return roleDao.findAllActive();
	}
	
	//@Transactional
	public List<Role> findAll(){
		return (List<Role>) roleDao.findAll();
	}//end return AllRules

	/*******************************************************
	 * SaveOrUpdate Method
	 *******************************************************/
	public Role saveOrUpdate(Role role){
		return roleDao.save(role);
	}
	
	/*******************************************************
	 * activateOrDeActivate Method
	 *******************************************************/
	
	public Role activateOrDeActivate(Integer roleId,boolean enabled){
		// Getting role 
		Role role=roleDao.findById(roleId);
		if(role!=null){
		role.setEnabled(enabled);
		// SaveOrUpdate
		saveOrUpdate(role);
		}
		// return result 
		return role;
	}//end activate function
	
	/*******************************************************
	 *  saveOrUpdate 
	 *******************************************************/
	public Role saveOrUpdateDTO(RoleDTO roleDTO){
		// Getting List of Roles 
    	//Role newRole=roleDao.save(role);
    	
    	Role role=new Role();
		role.setId(roleDTO.getId());
		role.setName(roleDTO.getName().toLowerCase());
		role.setDescription(roleDTO.getDescription());
		Role newRole=roleDao.save(role);

    	return newRole;
	}//end
	
	/*******************************************************
	 * findByID 
	 *******************************************************/
	public Role findFullRole(Integer roleId){
		// return role 
		Role role = roleDao.findFullRole(roleId);
		if(role!=null){
			for (User u : role.getUsers()) {
				if ("INACTIVE".equalsIgnoreCase(u.getStatus())) {
					role.getUsers().remove(u);
				}
			}//end for
		}
		return role;
	}//end
	
	/******************************************************* 
	 *  findByUser
	 *******************************************************/
	public List<Role> findByUser(Integer userId){
		// return role 
    	return roleDao.findByUser(userId);
	}//end
	 
	public List<Role> findAllExceptSuperUser(){
		return roleDao.findAllExceptSuperUser();
	}
	
	/*******************************************************
	 * Getting Role By UserIdentifier
	 *******************************************************/
	public Role findByLoginData(String userIdentifier){
		return roleDao.findByLoginData(userIdentifier);
	}
	
	/*******************************************************
	 * Method getFullRoleDto
	 *******************************************************/
	public RoleDTO getFullRoleDto(Integer roleId){
		// Getting Role Object 
		Role role=roleDao.findFullRole(roleId);
		// Init RoleDto to be returned 
		RoleDTO roleDTO=null;
		// Check if Role is Already Found
		if(role!=null){
			// Setting Role Main Information
			roleDTO=new RoleDTO();
			roleDTO.setId(role.getId());
			roleDTO.setName(role.getName());
			roleDTO.setDescription(role.getDescription());
			/// Setting the Modules Stucture Per Role
			List<ModuleDto> moduleDtos=gettingRoleModules(role);
			// Assign the Module List to RoleDTO
			roleDTO.setModules(moduleDtos);			
		}//end if Condition
		// return RoleDto Object
		return roleDTO;
	}
	
	/*******************************************************
	 * Getting Role Privilege
	 *******************************************************/
	public RoleHolder getRolePrivilages(String userIdentifer){
		// Getting Role
		Role role=findByLoginData(userIdentifer);
		// init roleHolder
		RoleHolder roleHolder=new RoleHolder(); 
        List<PrivilegeHolder> privilages=new ArrayList<PrivilegeHolder>(); 
        // Fill RoleHolder
    	roleHolder.setId(role.getId());
        roleHolder.setName(role.getName());
        // Filling List
        for(RolePrivilege rolePrivilege:role.getRolePrivileges()){
        	PrivilegeHolder privilegeHolder=new PrivilegeHolder();
        	privilegeHolder.setName(rolePrivilege.getModulePrivilege().getIdentifierName());
        	privilegeHolder.setActive(rolePrivilege.isEnabled());
        	// Assing Privilege to Privilege Holder
        	privilages.add(privilegeHolder);
        }///end For Each 
        roleHolder.setPrivileges(privilages);
        // return result
        return roleHolder;
	}//End Getting Role Privilege
	
	/*******************************************************
	* Getting Role Privilege
	*******************************************************/
	private List<ModuleDto> gettingRoleModules(Role role){
		/// Summing Object List from Role to Set of Module Object 
		Set<Module> modules=new HashSet<Module>();
		// Init the ModuleDTO List
		List<ModuleDto> moduleDtos=new ArrayList<ModuleDto>();
		// Process Copying the Modules from Role Object
		for(RolePrivilege rolePrivilege:role.getRolePrivileges()){
			modules.add(rolePrivilege.getModulePrivilege().getModule());
		}//end for Loop 
		/*******************************************************
		* Fotmulating the Module ad permission Tree
		*******************************************************/
		for(Module module:modules){
			// Init PermissionDTO to have main permissions
			List<PermissionDto> permissionDtos=new ArrayList<>();
			// Init ModuleDTO
			ModuleDto moduleDto=new ModuleDto();
			moduleDto.setId(module.getId());
			moduleDto.setName(module.getName());
			// Getting privilege List Per Module
			for(RolePrivilege rolePrivilege:role.getRolePrivileges()){
				// Choose the Root Privilege
				if((rolePrivilege.getModulePrivilege().getModule().getId()==module.getId())
						&& (rolePrivilege.getModulePrivilege().getParent()==null)){
					// Define new ModuleDTO
					 // Assign Permission
					 PermissionDto permissionDto=new PermissionDto();
					 permissionDto.setId(rolePrivilege.getModulePrivilege().getId());
			   		 permissionDto.setDisplayName(rolePrivilege.getModulePrivilege().getPrivilege().getDisplayName());
			   		 permissionDto.setName(rolePrivilege.getModulePrivilege().getIdentifierName());
			   		 permissionDto.setValue(rolePrivilege.isEnabled());
			   		 permissionDto.setChildren(getPermissionChildren(role,rolePrivilege.getModulePrivilege()));
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
		public List<PermissionDto> getPermissionChildren(Role role,ModulePrivilege modulePrivilege){
			// Init List of Permission DTO
			List<PermissionDto> child=new ArrayList<PermissionDto>();
			// Assign the Children 
			for(ModulePrivilege children:modulePrivilege.getChild()){
				PermissionDto permissionDto=new PermissionDto();
				permissionDto.setId(children.getPrivilege().getId());
				permissionDto.setName(children.getIdentifierName());
				permissionDto.setDisplayName(children.getPrivilege().getDisplayName());
				RolePrivilege rolePrivilege=roleDao.getRolePrivilageForModulePrivilage(role.getId(),children.getId());
				permissionDto.setValue(rolePrivilege.isEnabled());
				permissionDto.setChildren(getPermissionChildren(role,children));
		        child.add(permissionDto);		
				}//end for Each 
			// return result 
			return child;
		}
	
		
		@Transactional
		public Role saveOrUpdateFromResponseDTO(RoleResponseDTO roleResponseDTO) {
		    /// 
			Role role=findFullRole(roleResponseDTO.getId());
			/////// Check if In Save Operation 
			if(role==null){
				role=new Role();
				role.setName(roleResponseDTO.getName().toLowerCase());
				role.setDescription(roleResponseDTO.getDescription());
				role.setEnabled(roleResponseDTO.isEnabled());
				Role newRole=saveOrUpdate(role);
				/// Adding Default Role Privilege for Role
				List<ModulePrivilege> modulePrivileges=modulePrivilegeService.findAll();
				for(ModulePrivilege modulePrivilege:modulePrivileges){
					RolePrivilege privilege=new RolePrivilege();
					privilege.setEnabled(false);
					privilege.setModulePrivilege(modulePrivilege);
					privilege.setRole(newRole);
					privilege.setId(0);
					rolePrivilegeDao.save(privilege);
				}//end
				/// Adding New Relation 
				Set<RolePrivilege> privileges=new HashSet<RolePrivilege>();
				for (PrivilegeResponseDTO privilegeResponseDTO : roleResponseDTO.getPrivilegeResponseDTOs()) {
					RolePrivilege rolePrivilege = rolePrivilegeDao.findForRoleAndModule(role.getId(), privilegeResponseDTO.getId());
					// Assing the RolePrivilege
					rolePrivilege.setEnabled(true);
					privileges.add(rolePrivilege);
				} // end for Loop
				// Setting Users 
				role.setUsers(null);
				//// Lsit Users 
				Set<User> users=new HashSet<User>();
				for(User myUser:roleResponseDTO.getUsers()){
					User user=userService.findById(myUser.getId());
					users.add(user);
				}//end for Loop 
				role.setUsers(users);
				// Updating Data 
				saveOrUpdate(role);
			}else{
				role.setName(roleResponseDTO.getName().toLowerCase());
				role.setDescription(roleResponseDTO.getDescription());
				role.setEnabled(roleResponseDTO.isEnabled());
				// Clean old
				Set<RolePrivilege> privileges=new HashSet<RolePrivilege>();
				for(RolePrivilege rolePrivilege:role.getRolePrivileges()){
					rolePrivilege.setEnabled(false);
					privileges.add(rolePrivilege);
				}//end for Loop
				/// Adding New Relation 
				for (PrivilegeResponseDTO privilegeResponseDTO : roleResponseDTO.getPrivilegeResponseDTOs()) {
					RolePrivilege rolePrivilege = rolePrivilegeDao.findForRoleAndModule(role.getId(), privilegeResponseDTO.getId());
					// Assing the RolePrivilege
					rolePrivilege.setEnabled(true);
					privileges.add(rolePrivilege);
				} // end for Loop
				/// Adding Users
				// Setting Users 
				role.setUsers(null);
				//// List Users 
				Set<User> users=new HashSet<User>();
				for(User myUser:roleResponseDTO.getUsers()){
					User user=userService.findById(myUser.getId());
					users.add(user);
				}//end for Loop 
				role.setUsers(users);
				//Saving Object
				saveOrUpdate(role);
			}//end else
		
			// return result
			return role;
		}// getRoleFromDTO
		
		
		
		
}
