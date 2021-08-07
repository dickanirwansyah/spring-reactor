package com.dicka.reactivex.reactivexresearch.exception;




public class ErrorException extends RuntimeException {

    private static final int errorCode = 100;

    public ErrorException(String message){
        super(message);
    }

    public int getErrorCode(){
        return errorCode;
    }


}
