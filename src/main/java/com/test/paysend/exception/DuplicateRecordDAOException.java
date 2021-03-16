package com.test.paysend.exception;

public class DuplicateRecordDAOException extends DAOException {

    public DuplicateRecordDAOException() {
    }

    public DuplicateRecordDAOException(String message) {
        super(message);
    }

    public DuplicateRecordDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateRecordDAOException(Throwable cause) {
        super(cause);
    }

    public DuplicateRecordDAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
