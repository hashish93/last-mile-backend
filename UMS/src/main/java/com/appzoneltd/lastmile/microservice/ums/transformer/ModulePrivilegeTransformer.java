package com.appzoneltd.lastmile.microservice.ums.transformer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.appzoneltd.lastmile.microservice.ums.dto.ModulePermission;
import com.appzoneltd.lastmile.microservice.ums.dto.SystemModule;
import com.appzoneltd.lastmile.microservice.ums.entity.ModulePrivilegeEntity;

public class ModulePrivilegeTransformer {
	
	public static List<SystemModule> getSystemModulesFromEntities(List<ModulePrivilegeEntity> modulePrivilegeEntities) {
		return transformEntityToSystemModule(modulePrivilegeEntities);
	}

	private static List<SystemModule> transformEntityToSystemModule(
			List<ModulePrivilegeEntity> modulePrivilegeEntities) {

		List<SystemModule> moduleDtos = new ArrayList<SystemModule>();
		Set<ModulePrivilegeEntity> modules = new HashSet<ModulePrivilegeEntity>();

		for (ModulePrivilegeEntity modulePrivilege : modulePrivilegeEntities) {
			modules.add(modulePrivilege);
		}

		for (ModulePrivilegeEntity module : modules) {
			List<ModulePermission> modulePermissions = new ArrayList<>();
			SystemModule systemModule = new SystemModule();
			for (ModulePrivilegeEntity modulePrivilegeEntity : modulePrivilegeEntities) {
				if ((modulePrivilegeEntity.getModule().getId() == module.getId())
						&& (modulePrivilegeEntity.getParent() == null)) {
					systemModule.setId(modulePrivilegeEntity.getModule().getId());
					systemModule.setName(modulePrivilegeEntity.getModule().getName());
					ModulePermission modulePermission = new ModulePermission();
					modulePermission.setId(modulePrivilegeEntity.getId());
					modulePermission.setDisplayName(modulePrivilegeEntity.getPrivilege().getDisplayName());
					modulePermission.setName(modulePrivilegeEntity.getIdentifierName());
					modulePermission.setValue(modulePrivilegeEntity.isDefaultValue());
					modulePermission.setChildren(getModulePermissionChildren(modulePrivilegeEntity));
					modulePermissions.add(modulePermission);
					systemModule.setModulePermissions(modulePermissions);
					moduleDtos.add(systemModule);
				}
			}
		}
		return moduleDtos;
	}

	private static List<ModulePermission> getModulePermissionChildren(ModulePrivilegeEntity modulePrivilegeEntity) {

		List<ModulePermission> ModulePermissionChild = new ArrayList<ModulePermission>();
		for (ModulePrivilegeEntity children : modulePrivilegeEntity.getChild()) {
			ModulePermission modulePermission = new ModulePermission();
			modulePermission.setId(children.getId());
			modulePermission.setName(children.getIdentifierName());
			modulePermission.setDisplayName(children.getPrivilege().getDisplayName());
			modulePermission.setValue(children.isDefaultValue());
			modulePermission.setChildren(getModulePermissionChildren(children));
			ModulePermissionChild.add(modulePermission);
		}
		return ModulePermissionChild;
	}
	
}
