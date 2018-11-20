package com.appzoneltd.lastmile.microservice.packge.service;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import com.appzoneltd.lastmile.microservice.packge.dto.PackageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.appzoneltd.lastmile.microservice.packge.dto.VerifiedPackageDTO;
import com.appzoneltd.lastmile.microservice.packge.entity.PackageEntity;
import com.appzoneltd.lastmile.microservice.packge.entity.PackageImagesEntity;
import com.appzoneltd.lastmile.microservice.packge.entity.PackageLabelEntity;
import com.appzoneltd.lastmile.microservice.packge.entity.PackageLabelingEntity;
import com.appzoneltd.lastmile.microservice.packge.entity.StaticContentEntity;
import com.appzoneltd.lastmile.microservice.packge.entity.VerifiedPackageEntity;
import com.appzoneltd.lastmile.microservice.packge.repository.PackageImagesRepository;
import com.appzoneltd.lastmile.microservice.packge.repository.PackageLabelingRepository;
import com.appzoneltd.lastmile.microservice.packge.repository.PackageRepository;
import com.appzoneltd.lastmile.microservice.packge.repository.VerifiedPackageRepository;
import com.appzoneltd.lastmile.microservice.packge.transformer.PackageTransformer;
import com.appzoneltd.lastmile.microservice.utility.Utils;

@Service
public class PackageService {

	@Autowired
	private PackageRepository packageRepository;

	@Autowired
	private PackageTransformer packageTransformer;

	@Autowired
	private PackageImagesRepository packageImagesRepository;

	@Autowired
	private PackageLabelingRepository packageLabelingRepository;

	@Autowired
	private VerifiedPackageRepository verifiedPackageRepository;

	@Transactional
	public PackageEntity saveOrUpdatePackage(PackageDTO packageDTO) {

		PackageEntity packageEntity = packageTransformer.getPackageEntityFromPackageDto(packageDTO);
		packageRepository.save(packageEntity);
		SavePackageImages(packageEntity, packageDTO.getImageIds());
		SavePackageLabels(packageEntity, packageDTO.getLabelIds());
		return packageEntity;
	}

	private void SavePackageImages(PackageEntity packageEntity, Set<Long> imageIds) {
		deletePackageImages(packageEntity);
		List<StaticContentEntity> staticContentEntities = packageTransformer.getStaticContentEntities(imageIds);
		for (StaticContentEntity staticContentEntity : staticContentEntities) {
			PackageImagesEntity packageImagesEntity = new PackageImagesEntity();
			packageImagesEntity.setId(Utils.generateUUID());
			packageImagesEntity.setPackageEntity(packageEntity);
			packageImagesEntity.setStaticContent(staticContentEntity);
			packageImagesEntity.setVersion(0L);
			packageImagesRepository.save(packageImagesEntity);
		}
	}

	private void deletePackageImages(PackageEntity packageEntity) {
		if (packageEntity != null) {
			List<PackageImagesEntity> packageImagesEntities = packageEntity.getPackageImages();
			if (packageImagesEntities != null && packageImagesEntities.size() > 0) {
				packageImagesRepository.delete(packageImagesEntities);
			}
		}
	}

	private void SavePackageLabels(PackageEntity packageEntity, Set<Long> labelIds) {
		deletePackageLabels(packageEntity);
		List<PackageLabelEntity> packageLabelEntities = packageTransformer.getPackageLabelEntities(labelIds);
		for (PackageLabelEntity packageLabelEntity : packageLabelEntities) {
			PackageLabelingEntity packageLabelingEntity = new PackageLabelingEntity();
			packageLabelingEntity.setId(Utils.generateUUID());
			packageLabelingEntity.setPackageEntity(packageEntity);
			packageLabelingEntity.setPackageLabel(packageLabelEntity);
			packageLabelingEntity.setVersion(0L);
			packageLabelingRepository.save(packageLabelingEntity);
		}
	}

	private void deletePackageLabels(PackageEntity packageEntity) {
		if (packageEntity != null) {
			List<PackageLabelingEntity> packageLabelingEntities = packageEntity.getPackageLabelings();
			if (packageLabelingEntities != null && packageLabelingEntities.size() > 0) {
				packageLabelingRepository.delete(packageLabelingEntities);
			}
		}
	}

	public PackageDTO getPackageById(Long packageId) {
		return packageTransformer.getPackageFromId(packageId);
	}

	public boolean updatePackageStatus(Long packageId, String status) {
		System.out.println("package Id "+packageId);
		System.out.println("package status "+status);
		if (packageId != null) {
			System.out.println("package Id "+packageId);
			System.out.println("package status "+status);
			PackageEntity packageEntity = packageRepository.findOne(packageId);
			if (packageEntity != null) {
				if (status != null) {
					packageEntity.setStatus(status);
					packageRepository.save(packageEntity);
					return true;
				}
			}
		}
		return false;
	}

	public VerifiedPackageDTO findVerifiedPackageById(Long packageId) throws EntityNotFoundException {
		VerifiedPackageEntity verifiedPackageEntity = verifiedPackageRepository.findOne(packageId);
		if (verifiedPackageEntity == null)
			throw new EntityNotFoundException("package.packageid.exist");
		return packageTransformer.getVerifiedPackageDTOFromEntity(verifiedPackageEntity);
	}
}
