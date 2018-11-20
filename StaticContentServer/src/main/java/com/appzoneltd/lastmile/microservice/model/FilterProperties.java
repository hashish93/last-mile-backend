package com.appzoneltd.lastmile.microservice.model;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class FilterProperties {
	
	private final List<FileFilterData> list = new ArrayList<>();

    public List<FileFilterData> getListFilter() {
        return this.list;
    }
}
