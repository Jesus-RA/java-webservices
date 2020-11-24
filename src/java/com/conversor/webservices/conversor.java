/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conversor.webservices;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author JesúsRA
 */
@WebService(serviceName = "conversor")
public class conversor {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "inchToCm")
    public double inchToCm(@WebParam(name = "inches") double inches) {
        //TODO write your implementation code here:
        return inches * 2.54;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "cmToInch")
    public double cmToInch(@WebParam(name = "centimeters") double centimeters) {
        //TODO write your implementation code here:
        return centimeters / 2.54;
    }
}
