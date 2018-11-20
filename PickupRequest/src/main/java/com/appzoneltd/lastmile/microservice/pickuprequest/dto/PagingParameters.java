package com.appzoneltd.lastmile.microservice.pickuprequest.dto;

import com.appzoneltd.lastmile.microservice.modelobjects.EndPointParameter;
import lombok.Data;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Date;

/**
 * Created by alaa.nabil on 3/7/2017.
 */
@Data
public class PagingParameters extends EndPointParameter implements Pageable {
    private String packageId;
    private String  requestId;
    private String packageTrackingNumber;
    private String packageType;
    private String recipientName;
    private String recipientPhone;
    private String requestStatus;
    private String requestType;
    private String requesterName;
    private String senderPhone;
    private String status;

    @Override
    public int getPageNumber() {
        return getPage() - 1;
    }

    @Override
    public int getPageSize() {
        return getMaxResult();
    }

    @Override
    public int getOffset() {
        return getPageNumber() * getPageSize();
    }

    @Override
    public Sort getSort() {
        return new Sort(getOrderBy()==null?Sort.Direction.DESC: Sort.Direction.valueOf(getOrderBy().name()), "created");
    }

    @Override
    public Pageable next() {
        return null;
    }

    @Override
    public Pageable previousOrFirst() {
        return null;
    }

    @Override
    public Pageable first() {
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }
}
