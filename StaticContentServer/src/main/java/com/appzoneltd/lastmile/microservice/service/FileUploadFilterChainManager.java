package com.appzoneltd.lastmile.microservice.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import com.appzoneltd.lastmile.microservice.exception.FileFilterException;
import com.appzoneltd.lastmile.microservice.filter.FileFilter;
import com.appzoneltd.lastmile.microservice.model.FileFilterData;
import com.appzoneltd.lastmile.microservice.model.LastMileFile;

public class FileUploadFilterChainManager {

	private List<FileFilterData> filterChain;

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private Environment environment;

	public void initialize() {

		filterChain = new ArrayList<>();
		String filterId = null;
		for (int i = 0; i < 3; i++) {
			filterId = "Filters.list[" + i + "]";
			String filterBeanName = environment.getRequiredProperty(filterId + ".beanName");
			String filterFileTypes = environment.getRequiredProperty(filterId + ".fileExtensions");

			FileFilterData fileFilterData = new FileFilterData();
			fileFilterData.setBeanName(filterBeanName);

			List<String> fileTypes = new ArrayList<>();

			if (filterFileTypes.contains(",")) {
				String[] fileTypesStringArray = filterFileTypes.split(",");
				fileTypes.addAll(Arrays.asList(fileTypesStringArray));
			} else {
				fileTypes.add(filterFileTypes);
			}

			fileFilterData.setFileTypes(fileTypes);
			filterChain.add(fileFilterData);
		}
	}

	public void startFileFilterChain(LastMileFile lastMileFile) throws FileFilterException {
		for (FileFilterData fileFilterData : filterChain) {
			if (fileFilterData.getFileTypes().contains("/")
					|| fileFilterData.getFileTypes().contains(lastMileFile.getExtension())) {
				FileFilter fileFilter = (FileFilter) applicationContext.getBean(fileFilterData.getBeanName());
				fileFilter.execute(lastMileFile);
			}
		}
	}

}
