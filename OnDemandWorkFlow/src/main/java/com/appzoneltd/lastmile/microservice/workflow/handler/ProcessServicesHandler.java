package com.appzoneltd.lastmile.microservice.workflow.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import javax.validation.constraints.NotNull;

import io.reactivex.Observable;
import io.reactivex.functions.Predicate;

public class ProcessServicesHandler {

	private static final Map<ProcessServiceKey, ProcessServicesObservables> instances = new HashMap<>();
	private static final Object lock = new Object();

	public static ProcessServicesObservables get(@NotNull Long packageId, @NotNull String processName) {
		synchronized (lock) {
			return doGet(packageId, processName);
		}

	}

	private static ProcessServicesObservables doGet(Long packageId, String processName) {
		ProcessServiceKey currentInstanceKey = new ProcessServiceKey(packageId, processName);
		ProcessServicesObservables instance = instances.get(currentInstanceKey);
		if (instance == null) {
			instance = new ProcessServicesObservables(currentInstanceKey, instances);
			instances.put(currentInstanceKey, instance);
		}
		removeExpiredInstances(currentInstanceKey, instances);
		return instance;
	}

	private static void removeExpiredInstances(final ProcessServiceKey currentInstanceKey,
			Map<ProcessServiceKey, ProcessServicesObservables> instances) {

		List<ProcessServiceKey> expiredKeys = Observable.fromIterable(instances.keySet())
				.filter(filterByExpiredKeys(currentInstanceKey)).toList().blockingFirst();

		if (!expiredKeys.isEmpty()) {
			expiredKeys.forEach(removeExpiredKeysFromInstances(instances));
		}

		System.out.println(">>>>>>> expired " + ProcessServiceKey.class + " list : " + expiredKeys);

	}

	private static Consumer<ProcessServiceKey> removeExpiredKeysFromInstances(
			Map<ProcessServiceKey, ProcessServicesObservables> instances) {
		return new Consumer<ProcessServiceKey>() {

			@Override
			public void accept(ProcessServiceKey expiredKey) {
				instances.remove(expiredKey);
			}

		};
	}

	private static Predicate<ProcessServiceKey> filterByExpiredKeys(final ProcessServiceKey currentInstanceKey) {
		return new Predicate<ProcessServiceKey>() {

			@Override
			public boolean test(ProcessServiceKey currentKey) throws Exception {
				return !currentKey.equals(currentInstanceKey) && currentKey.isExpired();
			}

		};
	}

}
