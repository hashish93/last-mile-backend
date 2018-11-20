package com.appzoneltd.lastmile.microservice.ums.transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.appzoneltd.lastmile.microservice.ums.dto.MenuDto;
import com.appzoneltd.lastmile.microservice.ums.entity.MenuEntity;

public class MenuTransformer {

	public static List<MenuDto> getMenusDtoFromMenuEntities(Set<MenuEntity> menuEntities){
		List<MenuDto> menuDtos = new ArrayList<>();
		for(MenuEntity menuEntity : menuEntities){
			MenuDto menuDto = new MenuDto();
			menuDto.setId(menuEntity.getId());
			menuDto.setName(menuEntity.getName());
			menuDto.setKey(menuEntity.getKey());
			menuDto.setOrder(menuEntity.getTheOrder());
			if(menuEntity.getParentMenu() !=null){
				menuDto.setParent(menuEntity.getParentMenu().getId());
			}else{
				menuDto.setParent(-1L);
			}
			menuDto.setUrl(menuEntity.getUrl());
			menuDto.setActive(menuEntity.getActive());
			menuDtos.add(menuDto);
		}
		return menuDtos;
	}
	
}
