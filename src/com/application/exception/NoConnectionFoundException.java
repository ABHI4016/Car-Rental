package com.application.exception;

/**
 * Created by cts1 on 19/7/17.
 */
public class NoConnectionFoundException extends Exception {
    String message="No Such Neighbour";
    public String  getMessage(){
        return this.message;
    }

}
