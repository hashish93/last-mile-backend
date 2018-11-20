package com.appzoneltd.lastmile.microservice.customer.dao;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;

import com.appzoneltd.lastmile.microservice.customer.model.FirebaseTokenObject;
import com.couchbase.client.java.repository.annotation.Id;

public class CustomerFirebaseToken implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -1437513057547486290L;

    @Id
    private Long _ID;
    private Long userId;
    private Set<FirebaseTokenObject> firebaseTokens;

    public CustomerFirebaseToken() {
    }

    /**
     * @param _ID
     * @param userId
     * @param firebaseTokens
     */
    public CustomerFirebaseToken(Long _ID, Long userId, Set<FirebaseTokenObject> firebaseTokens) {
        super();
        this._ID = _ID;
        this.userId = userId;
        this.firebaseTokens = firebaseTokens;
    }

    /**
     * @return the _ID
     */
    public Long get_ID() {
        return _ID;
    }

    /**
     * @param _ID the _ID to set
     */
    public CustomerFirebaseToken set_ID(Long _ID) {
        this._ID = _ID;
        return this;
    }

    /**
     * @return the userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Set<FirebaseTokenObject> getFirebaseTokens() {
        return firebaseTokens;
    }

    public void setFirebaseTokens(Set<FirebaseTokenObject> firebaseTokens) {
        this.firebaseTokens = firebaseTokens;
    }
    
    public void removeFirebaseToken(String tokenToRevoke) {
    		
    		Iterator<FirebaseTokenObject> iterator = this.firebaseTokens.iterator();
    		
    		while (iterator.hasNext()) {
    			
    			FirebaseTokenObject currentfirebaseObject = (FirebaseTokenObject) iterator.next();
    			
    			if (tokenToRevoke.equals(currentfirebaseObject.getFirebaseToken())) {
    				iterator.remove();
			}
    	}
    }
}
