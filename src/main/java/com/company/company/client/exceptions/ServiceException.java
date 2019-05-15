package com.company.company.client.exceptions;

import com.company.company.data.services.beans.JobFailureCode;

public class ServiceException extends ClientExceptionBase {

    private static final long serialVersionUID = 1L;
    
    
    public ServiceException() {
        super();
    }

    public ServiceException(String msg, JobFailureCode cause) {
        super(msg, cause);
    }

    public ServiceException(String msg) {
        super(msg);
    }

    public ServiceException(JobFailureCode cause) {
        super(cause);
    }
    
}
