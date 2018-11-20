package com.appzoneltd.lastmile.microservice.customer.dto;

import com.appzoneltd.lastmile.microservice.modelobjects.EndPointParameter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Created by alaa.nabil on 3/7/2017.
 */
public class PagingParameters extends EndPointParameter implements Pageable {

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
        return new Sort(getOrderBy()==null?Sort.Direction.DESC: Sort.Direction.valueOf(getOrderBy().name()), "customerId");
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
