package com.appzoneltd.lastmile.microservice.details.service;

import com.appzoneltd.lastmile.microservice.details.dto.CancelRequest;
import com.appzoneltd.lastmile.microservice.details.dto.Documents;
import com.appzoneltd.lastmile.microservice.details.dto.Invoice;
import com.appzoneltd.lastmile.microservice.details.dto.PackageDetails;
import com.appzoneltd.lastmile.microservice.details.service.component.ConfirmationCodeError;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.security.Principal;

public interface PackageVerificationService {

    boolean verifyPackageDetails(PackageDetails details, Principal principal)
            throws EntityNotFoundException, JsonProcessingException;

    Invoice generateInvoice(Long requestId, String requestType, Principal principal) throws EntityNotFoundException, JsonProcessingException;

    void confirmInvoice(Principal principal, Long requestId, String requestType) throws EntityNotFoundException, JsonProcessingException;

    Boolean addDocuments(Documents documents, Principal principal)
            throws JsonProcessingException, EntityNotFoundException, ConfirmationCodeError;

    void cancelRequest(CancelRequest cancelRequest, Principal principal) throws JsonProcessingException;
}
