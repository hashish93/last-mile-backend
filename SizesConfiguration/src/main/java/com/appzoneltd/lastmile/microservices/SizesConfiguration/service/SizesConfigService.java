package com.appzoneltd.lastmile.microservices.SizesConfiguration.service;

import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;
import com.appzoneltd.lastmile.microservice.utility.Utils;
import com.appzoneltd.lastmile.microservices.SizesConfiguration.DAO.SizesConfigDao;
import com.appzoneltd.lastmile.microservices.SizesConfiguration.DTO.SizesConfigDto;
import com.appzoneltd.lastmile.microservices.SizesConfiguration.model.SizesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class SizesConfigService {

	@Autowired
	private SizesConfigDao sizesConfigDao;

	@Autowired
	private MessageSource messageSource;

	public List<SizesConfigDto> findAllSizesConfig() {
		List<SizesConfigDto> info = new ArrayList<SizesConfigDto>();

		List<SizesConfig> SizesConfigAll = new ArrayList<SizesConfig>();
		SizesConfigAll = (List<SizesConfig>) sizesConfigDao.findAllOrderByDate();

		for (SizesConfig data : SizesConfigAll) {
			SizesConfigDto sizeConfigDto = new SizesConfigDto();
			sizeConfigDto.setSizeId(data.getSizeId());
			sizeConfigDto.setSizeName(data.getSizeName());
			sizeConfigDto.setHeight(data.getHeight());
			sizeConfigDto.setLength(data.getLength());
			sizeConfigDto.setWidth(data.getWidth());
			sizeConfigDto.setCorrespondence(data.getCorrespondence());

			info.add(sizeConfigDto);

		}

		Collections.sort(info, new Comparator<SizesConfigDto>() {
			@Override
			public int compare(SizesConfigDto o1, SizesConfigDto o2) {
				return o1.getSizeId().compareTo(o2.getSizeId());
			}
		});

		return info;

	}

	public List<Message> saveSizesConfig(SizesConfigDto sizesConfigDto) {
		List<Message> message = new ArrayList<Message>();
		Long sizeId = Utils.generateUUID();

		SizesConfig sizesConfig = new SizesConfig();
		sizesConfig.setSizeId(sizeId);
		sizesConfig.setSizeName(sizesConfigDto.getSizeName().toLowerCase());
		sizesConfig.setWidth(sizesConfigDto.getWidth());
		sizesConfig.setLength(sizesConfigDto.getLength());
		sizesConfig.setHeight(sizesConfigDto.getHeight());
		sizesConfig.setCorrespondence(sizesConfigDto.getCorrespondence());
		sizesConfig.setStatus("ACTIVE");

		List<SizesConfig> ChkExistingName = sizesConfigDao
				.CheckSizesNameExist(sizesConfigDto.getSizeName().toLowerCase());
		if (ChkExistingName.isEmpty()) {

			sizesConfigDao.save(sizesConfig);
			message = null;
		}

		else {
			message.add(
					new Message(MessageType.ERROR, "sizeName", messageSource.getMessage("sizesConfig.sizeName.Existing",
							null, "sizesConfig.sizeName.Existing", LocaleContextHolder.getLocale())));
		}

		return message;

	}

	public List<Message> updateSizesConfig(SizesConfigDto sizesConfigDto) throws Exception {
		List<Message> message = new ArrayList<Message>();
		boolean error = false;

		SizesConfig sizesConfig = sizesConfigDao.findOne(sizesConfigDto.getSizeId());

		sizesConfig.setSizeId(sizesConfigDto.getSizeId());
		sizesConfig.setSizeName(sizesConfigDto.getSizeName().toLowerCase());
		sizesConfig.setWidth(sizesConfigDto.getWidth());
		sizesConfig.setLength(sizesConfigDto.getLength());
		sizesConfig.setHeight(sizesConfigDto.getHeight());
		sizesConfig.setCorrespondence(sizesConfigDto.getCorrespondence());
		sizesConfig.setVersion(sizesConfigDto.getVersion());
		sizesConfig.setStatus("ACTIVE");

		List<SizesConfig> ChkExistingName = sizesConfigDao
				.CheckSizesNameExist(sizesConfigDto.getSizeName().toLowerCase());

		for (SizesConfig entity : ChkExistingName) {
			if (!entity.getSizeId().equals(sizesConfigDto.getSizeId())) {
				if (entity.getSizeName().toLowerCase().equals(sizesConfigDto.getSizeName().toLowerCase())) {
					message.add(new Message(MessageType.ERROR, "sizeName",
							messageSource.getMessage("sizesConfig.sizeName.Existing", null,
									"sizesConfig.sizeName.Existing", LocaleContextHolder.getLocale())));
					error = true;
				}

			}

		}

		if (!error) {
			sizesConfigDao.save(sizesConfig);
			message = null;

		}

		return message;

	}

	public SizesConfigDto findbyId(Long sizeId) {

		SizesConfig sizesConfigInfo = sizesConfigDao.findOne(sizeId);

		SizesConfigDto sizesConfigDto = new SizesConfigDto();

		sizesConfigDto.setSizeId(sizeId);
		sizesConfigDto.setSizeName(sizesConfigInfo.getSizeName());
		sizesConfigDto.setHeight(sizesConfigInfo.getHeight());
		sizesConfigDto.setLength(sizesConfigInfo.getLength());
		sizesConfigDto.setWidth(sizesConfigInfo.getWidth());
		sizesConfigDto.setCorrespondence(sizesConfigInfo.getCorrespondence());
		sizesConfigDto.setVersion(sizesConfigInfo.getVersion());
		sizesConfigDto.setStatus(sizesConfigInfo.getStatus());

		return sizesConfigDto;

	}

	public SizesConfig deleteSizesConfig(SizesConfigDto sizesConfigDto) {

		SizesConfig sizeConfig = sizesConfigDao.findOne(sizesConfigDto.getSizeId());

		sizeConfig.setStatus(sizesConfigDto.getStatus());

		SizesConfig deactivatedSizeConfig = sizesConfigDao.save(sizeConfig);

		return deactivatedSizeConfig;
	}

}
