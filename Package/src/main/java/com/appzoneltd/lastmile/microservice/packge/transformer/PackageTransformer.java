package com.appzoneltd.lastmile.microservice.packge.transformer;

import com.appzoneltd.lastmile.microservice.packge.dto.PackageDTO;
import com.appzoneltd.lastmile.microservice.packge.dto.VerifiedPackageDTO;
import com.appzoneltd.lastmile.microservice.packge.entity.*;
import com.appzoneltd.lastmile.microservice.packge.repository.*;
import com.appzoneltd.lastmile.microservice.utility.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Scope("singleton")
public final class PackageTransformer {

    @Autowired
    private PackageRepository packageRepository;

    @Autowired
    private PackageTypeRepository packageTypeRepository;

    @Autowired
    private PackageLabelRepository packageLabelRepository;

    @Autowired
    private ShipmentServiceTypeRepository shipmentServiceTypeRepository;

    @Autowired
    private StaticContentRepository staticContentRepository;

    public final PackageEntity getPackageEntityFromPackageDto(PackageDTO packageDTO) {

        PackageEntity packageEntity = null;
        if (packageDTO.getPackageId() != null) {
            packageEntity = packageRepository.findOne(packageDTO.getPackageId());
        }

        if (packageEntity == null) {
            packageEntity = new PackageEntity();
            packageEntity.setPackageId(Utils.generateUUID());
            packageEntity.setCreated(new Date());

        } else {

        }

        if (packageDTO.getPackageTypeId() != null) {
            PackageTypeEntity packageTypeEntity = packageTypeRepository.findOne(packageDTO.getPackageTypeId());
            packageEntity.setPackageType(packageTypeEntity);
        }

        packageEntity.setNickname(packageDTO.getNickName());
        packageEntity.setDescription(packageDTO.getDescription());
        packageEntity.setPackageValue(packageDTO.getPackageValue());
        packageEntity.setActualweight(packageDTO.getActualWeight());
        packageEntity.setVersion(0L);

        if (packageDTO.getShipmentServiceTypeId() != null) {
            ShipmentServiceTypeEntity shipmentServiceTypeEntity = shipmentServiceTypeRepository.findOne(packageDTO.getShipmentServiceTypeId());
            packageEntity.setShipmentServiceType(shipmentServiceTypeEntity);
        }

        return packageEntity;

    }


    public List<StaticContentEntity> getStaticContentEntities(Set<Long> imageIds) {
        List<StaticContentEntity> staticContentEntities = new ArrayList<>();
        if (imageIds != null) {
            for (Long imageId : imageIds) {
                StaticContentEntity staticContentEntity = staticContentRepository.findOne(imageId);
                if (staticContentEntity != null) {
                    staticContentEntities.add(staticContentEntity);
                }
            }
        }
        return staticContentEntities;
    }

    public List<PackageLabelEntity> getPackageLabelEntities(Set<Long> labelIds) {
        List<PackageLabelEntity> packageLabelEntities = new ArrayList<>();
        if (labelIds != null) {
            for (Long labelId : labelIds) {
                PackageLabelEntity packageLabelEntity = packageLabelRepository.findOne(labelId);
                if (packageLabelEntity != null) {
                    packageLabelEntities.add(packageLabelEntity);
                }
            }
        }
        return packageLabelEntities;
    }

    public PackageDTO getPackageFromId(Long PackageId) {
        PackageEntity packageEntity = packageRepository.findOne(PackageId);
        PackageDTO packageDTODto = null;
        if (packageEntity != null) {
            packageDTODto = new PackageDTO();
            packageDTODto.setPackageId(packageEntity.getPackageId());
            packageDTODto.setPackageTypeId(packageEntity.getPackageType().getPackageTypeId());
            packageDTODto.setPackageType(packageEntity.getPackageType().getPackagetype());
            packageDTODto.setPackageValue(packageEntity.getPackageValue());
            packageDTODto.setActualWeight(packageEntity.getActualweight());
            packageDTODto.setCreated(packageEntity.getCreated());
            packageDTODto.setDescription(packageEntity.getDescription());
            packageDTODto.setNickName(packageEntity.getNickname());
            packageDTODto.setStatus(packageEntity.getStatus());
            packageDTODto.setVersion(packageEntity.getVersion());

            if (packageEntity.getShipmentServiceType() != null) {
                packageDTODto.setShipmentServiceTypeId(packageEntity.getShipmentServiceType().getShipmentServiceTypeId());
                packageDTODto.setSubShipmentService(packageEntity.getShipmentServiceType().getType());
                packageDTODto.setShipmentService(packageEntity.getShipmentServiceType().getShipmentService().getService());
            }


            if (packageEntity.getPackageImages() != null) {
                Set<Long> imageIds = new HashSet<>();
                for (PackageImagesEntity packageImagesEntity : packageEntity.getPackageImages()) {
                    imageIds.add(packageImagesEntity.getStaticContent().getContentId());
                }
                packageDTODto.setImageIds(imageIds);
            }

            if (packageEntity.getPackageLabelings() != null) {
                Set<Long> labelIds = new HashSet<>();
                for (PackageLabelingEntity packageLabelingEntity : packageEntity.getPackageLabelings()) {
                    labelIds.add(packageLabelingEntity.getPackageLabel().getPackageLabelId());
                }
                packageDTODto.setLabelIds(labelIds);
            }
        }
        return packageDTODto;
    }

    public VerifiedPackageDTO getVerifiedPackageDTOFromEntity(VerifiedPackageEntity verifiedPackageEntity) {
        if (verifiedPackageEntity == null)
            return null;
        VerifiedPackageDTO verifiedPackageDTO = new VerifiedPackageDTO();
        verifiedPackageDTO.setPackageId(verifiedPackageEntity.getPackageId());
        if (verifiedPackageEntity.getPackageType() != null) {
            verifiedPackageDTO.setPackageType(verifiedPackageEntity.getPackageType().getPackagetype());
            verifiedPackageDTO.setPackageTypeId(verifiedPackageEntity.getPackageType().getPackageTypeId());
        }

        verifiedPackageDTO.setActualweight(verifiedPackageEntity.getActualweight());
        if (verifiedPackageEntity.getVerifiedPackageImages() != null) {
            Set<Long> imageIds = new HashSet<>();
            for (VerifiedPackageImagesEntity verifiedPackageImagesEntity : verifiedPackageEntity.getVerifiedPackageImages()) {
                imageIds.add(verifiedPackageImagesEntity.getStaticContent().getContentId());
            }
            verifiedPackageDTO.setImageIds(imageIds);
        }
        verifiedPackageDTO.setDescription(verifiedPackageEntity.getDescription());
        verifiedPackageDTO.setCreated(verifiedPackageEntity.getCreated());
        verifiedPackageDTO.setVersion(verifiedPackageEntity.getVersion());
        verifiedPackageDTO.setPackageValue(verifiedPackageEntity.getPackageValue());
        return verifiedPackageDTO;

    }

}
