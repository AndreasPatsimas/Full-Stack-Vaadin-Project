package com.company.company.client.exceptions;

import com.company.company.data.services.beans.JobFailureCode;

public class RestServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
    private JobFailureCode errorResponse;
    
    public RestServiceException( String msg, JobFailureCode errorResponse ) {
        super(msg);
        this.errorResponse = errorResponse;
    }

    public RestServiceException(String msg) {
        super(msg);
    }

    public RestServiceException( JobFailureCode errorResponse ) {
    	this.errorResponse = errorResponse;
    }

    public JobFailureCode getErrorResponse() {
		return errorResponse;
	}
 
}
