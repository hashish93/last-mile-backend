package com.appzoneltd.lastmile.microservice.details;

import com.appzoneltd.lastmile.microservice.details.dao.entity.*;
import com.appzoneltd.lastmile.microservice.details.dto.PackageDetails;
import com.appzoneltd.lastmile.microservice.details.dto.PackageLabel;
import com.appzoneltd.lastmile.microservice.details.dto.PackageType;
import com.appzoneltd.lastmile.microservice.details.dto.PickupCancellationReason;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class ObjectMapper {

	public final static PackageDetails toPackageDetails(PickupRequestEntity requestEntity,
                                                        RequestHistoryEntity historyEntity) {
		PackageDetails details = new PackageDetails();

		details.setPackageId(historyEntity.getPackageEntity().getPackageId());
		details.setNickname(historyEntity.getPackageEntity().getNickname());
		details.setType(toPackageType(historyEntity.getPackageEntity().getPackageType()));
		details.setWeight(historyEntity.getPackageEntity().getActualweight());
		details.setDescription(historyEntity.getPackageEntity().getDescription());
		details.setWrappingLabel(requestEntity.getLabelingtext());
		details.setPackagingLabelsIds(
				toListOfPackageLabelsIds(historyEntity.getPackageEntity().getListOfPackageLabel()));
		details.setImageIds(toListOfPackageImagesIds(historyEntity.getPackageEntity().getListOfStaticContent()));
		details.setPaymentType(requestEntity.getPaymenttype().toUpperCase());
		details.setPaymentMethod(requestEntity.getPaymentmethod().toUpperCase());
		details.setBoxing(requestEntity.getAdditionalservices());
		return details;
	}

	public static PackageType toPackageType(PackageTypeEntity entity) {
		if (entity == null)
			return null;
		return new PackageType(entity.getPackageTypeId(), entity.getPackagetype(), entity.getPackagedimension(),
				entity.getExpectedweight());
	}

	public static List<PackageType> toPackageTypes(List<PackageTypeEntity> entities) {
		List<PackageType> packageTypes = new ArrayList<>();
		for (PackageTypeEntity entity : entities) {
			packageTypes.add(new PackageType(entity.getPackageTypeId(), entity.getPackagetype(),
					entity.getPackagedimension(), entity.getExpectedweight()));
		}
		return packageTypes;
	}

	public static PackageLabel toPackageLabel(PackageLabelEntity entity) {
		if (entity == null)
			return null;
		return new PackageLabel(entity.getPackageLabelId(), entity.getLabel(), entity.getCreated(),
				entity.getVersion());
	}

	public static List<PackageLabel> toPackageLabels(List<PackageLabelEntity> entities) {
		List<PackageLabel> packageLabels = new ArrayList<>();
		for (PackageLabelEntity entity : entities) {
			packageLabels.add(new PackageLabel(entity.getPackageLabelId(), entity.getLabel(), entity.getCreated(),
					entity.getVersion()));
		}
		return packageLabels;
	}

	public static Set<Long> toListOfPackageLabelsIds(Set<PackageLabelEntity> entities) {
		Set<Long> labelIds = new HashSet<>();
		for (PackageLabelEntity entity : entities) {
			labelIds.add(entity.getPackageLabelId());
		}
		return labelIds;
	}

	public static Set<Long> toListOfPackageImagesIds(Set<StaticContentEntity> entities) {
		Set<Long> imageIds = new HashSet<>();
		for (StaticContentEntity entity : entities) {
			imageIds.add(entity.getContentId());
		}
		return imageIds;
	}

	public static List<PickupCancellationReason> toPickupCancellationReasons(List<CancelationReasonEntity> entities) {
		List<PickupCancellationReason> cancellationReasons = new ArrayList<>();
		for (CancelationReasonEntity entity : entities) {
			cancellationReasons.add(new PickupCancellationReason(entity.getId(), entity.getReason()));
		}
		return cancellationReasons;
	}

	public static PickupCancellationReason toPickupCancellationReason(CancelationReasonEntity entity) {
		if (entity == null)
			return null;
		return new PickupCancellationReason(entity.getId(), entity.getReason());
	}
}
