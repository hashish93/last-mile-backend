package com.appzoneltd.lastmile.microservice.email.service.sender.util.factory;

import org.springframework.beans.factory.config.AbstractFactoryBean;

import com.appzoneltd.lastmile.microservice.email.service.sender.util.basic.BasicSenderUtil;

public class SenderUtilFactory extends AbstractFactoryBean<BasicSenderUtil> {

	private String typeName;
	public SenderUtilFactory(String typa) {
		this.typeName = typa;
	}

	@Override
	protected BasicSenderUtil createInstance() throws Exception {
		
		return (BasicSenderUtil) Class.forName(typeName).newInstance();
	}

	@Override
	public Class<?> getObjectType() {
		try {
			return Class.forName(typeName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Object.class;
		}
	}


}
