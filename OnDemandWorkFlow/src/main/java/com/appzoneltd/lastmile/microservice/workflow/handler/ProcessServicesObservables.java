package com.appzoneltd.lastmile.microservice.workflow.handler;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import lombok.NonNull;

public class ProcessServicesObservables implements Clearable {

	private PublishSubject<Boolean> processObservable;
	private PublishSubject<Action> serviceObservable;
	private Disposable disposable;
	private ProcessServiceKey processServiceKey;
	private Map<ProcessServiceKey, ?> instances;

	public ProcessServicesObservables(@NonNull ProcessServiceKey processServiceKey, @NonNull Map<ProcessServiceKey, ?> instances) {
		this.processServiceKey = processServiceKey;
		this.instances = instances;
		this.processObservable = PublishSubject.create();
		this.serviceObservable = PublishSubject.create();
		this.disposable = createDisposable();
	}

	public void processConsumed() {

		System.out.println("************************************************************");
		System.out.println(ProcessServicesObservables.class.getSimpleName() + ".processConsumed() : "+processServiceKey);
		System.out.println("************************************************************");
		processObservable.onNext(true);
	}

	public void serviceConsumed(@NonNull Action action) {
		System.out.println("************************************************************");
		System.out.println(ProcessServicesObservables.class.getSimpleName() + ".serviceConsumed() : "+processServiceKey);
		System.out.println("************************************************************");
		serviceObservable.onNext(action);
	}

	private Disposable createDisposable() {
		return Observable.zip(processObservable, serviceObservable, zipConsumedResults()).
				subscribe(executeAction());

	}

	private BiFunction<Boolean, Action, Action> zipConsumedResults() {
		return new BiFunction<Boolean, Action, Action>() {

			@Override
			public Action apply(Boolean processConsumed, Action serviceAction) throws Exception {
				return serviceAction;
			}

		};
	}

	private Consumer<Action> executeAction() {
		return new Consumer<Action>() {

			@Override
			public void accept(Action action) throws Exception {
				System.out.println("************************************************************");
				System.out.println(ProcessServicesObservables.class.getSimpleName() + ".executeAction() : "+processServiceKey);
				System.out.println("************************************************************");
				
				removeInstanceForPackageId(processServiceKey);
				clear();
				
				action.run();
				
			}

		};
	}
	
	private void removeInstanceForPackageId(ProcessServiceKey processServiceKey) {
		System.out.println("************************************************************");
		System.out.println("PROCESS SERVICES VALUES : "+instances.keySet());
		System.out.println("BEFORE CLEAR PACKAGE ID "+processServiceKey+" INSTANCE : "+instances.size());
		instances.remove(processServiceKey);
		System.out.println("AFTER CLEAR PACKAGE ID "+processServiceKey+" INSTANCE : "+instances.size()+" KEY REMOVED : "+processServiceKey);
		System.out.println("************************************************************");
		instances = null;
	}


	public void clear() {
		if (disposable != null) {
			if (!disposable.isDisposed()) {
				disposable.dispose();
			}
			disposable = null;
		}
		processObservable = null;
		serviceObservable = null;
		
	}

	
}
