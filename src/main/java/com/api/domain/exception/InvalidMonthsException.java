package com.api.domain.exception;

public class InvalidMonthsException extends RuntimeException{

    public InvalidMonthsException (String menssage){
        super(menssage);
    }
}
