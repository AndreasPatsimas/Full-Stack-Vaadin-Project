package com.company.company.client.exceptions;

import com.company.company.data.services.beans.JobFailureCode;

public abstract class ClientExceptionBase extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    
    private JobFailureCode serverFault;
    
    
    //<editor-fold desc="constructors">
    public ClientExceptionBase() {
        super();
    }

    public ClientExceptionBase(String msg, JobFailureCode cause) {
        super(msg);
        serverFault = cause;
    }

    public ClientExceptionBase(String msg) {
        super(msg);
    }

    public ClientExceptionBase(JobFailureCode cause) {
        super();
        serverFault = cause;
    }
    //</editor-fold>
    
    
    /**
     * @return server-side fault information
     */
    public JobFailureCode getServerFault() {
        return serverFault;
    }

}
