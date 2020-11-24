/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.file.webservices;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author JesúsRA
 */
@WebService(serviceName = "file")
public class file {

    String route = "C:\\Users\\JesúsRA\\Documents\\NetBeansProjects\\distribuida\\";
    /**
     * Regresa el número de vocales de la palabra recibida
     * @param word Palabra a la que se desea contar las vocales
     * @return int Número de vocales de la palabra recibida
     */
    @WebMethod(operationName = "cuentaVocales")
    public int cuentaVocales(@WebParam(name = "word") String word) {
        //TODO write your implementation code here:
        int vocales = 0;
        for( int i = 0; i < word.length(); i++){
            
            switch(word.charAt(i)){
                
                case 'a': case 'á':
                case 'e': case 'é':
                case 'i': case 'í':
                case 'o': case 'ó':
                case 'u': case 'ú':
                    vocales++;
                break;
                
            }
            
        }
        return vocales;
    }

    /**
     * Devuelve la cantidad de líneas que contiene un archivo
     * 
     * @return int Cantidad de líneas del archivo
     * @throws java.io.FileNotFoundException
     */
    @WebMethod(operationName = "cuentaLineas")
    public int cuentaLineas(@WebParam(name = "fileName") String fileName) throws FileNotFoundException {
        //TODO write your implementation code here:
        File file = new File( route + fileName);
        Scanner reader = new Scanner(file);
        int lines = 0;
        
        while(reader.hasNextLine()){
            reader.nextLine();
            lines++;
            if(lines>5){
                return -1;
            }
        }
        
        reader.close();
        
        return lines;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "writeContent")
    public String writeContent(@WebParam(name = "fileName") String fileName) throws FileNotFoundException {
        //TODO write your implementation code here:
        File file = new File( route + fileName);
        Scanner reader = new Scanner(file);
        //int fileSize = cuentaLineas(fileName);
        //int i = 0;
        
        //String[] fileContent = new String[fileSize];
        String fileContent = "";
        
        while( reader.hasNextLine() ){
            //fileContent[i++] = reader.nextLine();
            fileContent += reader.nextLine() + "\n";
        }
        reader.close();
        
        return fileContent;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "print")
    public String print(@WebParam(name = "fileName") String fileName) throws FileNotFoundException {

        File file = new File( route + fileName);
        Scanner reader = new Scanner(file);
        String fileContent = "";
        
        while(reader.hasNextLine()){
            fileContent += reader.nextLine() + "\n";
        }
        
        reader.close();
        
        return fileContent;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "backup")
    public String backup(@WebParam(name = "fromFile") String fromFile, @WebParam(name = "toFile") String toFile) throws FileNotFoundException, IOException {
        
        try{
            
            File file = new File( route + fromFile );
            File destFile = new File( route + toFile );
            Scanner reader = new Scanner(file);
            BufferedWriter bwriter = new BufferedWriter( new FileWriter(destFile) );
            
//            if(file.exists()){
//                bwriter.write("ya existe wee");
//            }else{
//                //BufferedWriter bwriter = new BufferedWriter( new FileWriter(destFile) );
//                bwriter.write("Archivo creado");
//            }

            while( reader.hasNextLine() ){
                bwriter.write( reader.nextLine() + "\n" );
            }

            reader.close();
            bwriter.close();
            //return destFile.getAbsolutePath() + "\n" + destFile.getAbsoluteFile();
            return "Archivo respaldado satisfactoriamente.";
            
        }catch(Exception e){
            System.out.println(e);
        }
        
        return "No se pudo respaldar el archivo";

    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "copy")
    public String copy(@WebParam(name = "fromFile") String fromFile, @WebParam(name = "toFile") String toFile) throws IOException {
        
        String response = backup(fromFile, toFile);
        
        if( response == "Archivo respaldado satisfactoriamente."){
            return "Archivo copiado satisfactoriamente.";
        }
        return "No fue posible copiar el archivo.";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "rename")
    public String rename(@WebParam(name = "currentName") String currentName, @WebParam(name = "newName") String newName) {
        
        File currentFile = new File( route + currentName);
        File newFile = new File( route + newName );
        
        if( currentFile.renameTo(newFile) ){
            return "Archivo renombrado satisfactoriamente";
        }
        
        return "No se pudo renombrar el archivo";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "delete")
    public String delete(@WebParam(name = "fileName") String fileName) {
        
        File file = new File( route + fileName );
        
        if( file.delete() ){
            return "Archivo eliminado satisfactoriamente";
        }
        
        return "No se ha podido eliminar el archivo";
    }
    
    
    
}
