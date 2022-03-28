package com.actividad.saludo;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;

import org.springframework.ws.server.endpoint.annotation.*;

import https.t4is_uv_mx.saludos.BuscarSaludosResponse;
import https.t4is_uv_mx.saludos.EliminarSaludoRequest;
import https.t4is_uv_mx.saludos.EliminarSaludoResponse;
import https.t4is_uv_mx.saludos.ModificarSaludoRequest;
import https.t4is_uv_mx.saludos.ModificarSaludoResponse;
import https.t4is_uv_mx.saludos.SaludarRequest;
import https.t4is_uv_mx.saludos.SaludarResponse;
@Endpoint
public class SaludosEndPoint {
        int id=0;
        ArrayList<BuscarSaludosResponse.Saludos> saludos = new ArrayList<BuscarSaludosResponse.Saludos>(); // Create an ArrayList object
        @PayloadRoot(namespace = "https://t4is.uv.mx/saludos" , localPart = "SaludarRequest")

        @ResponsePayload
        public SaludarResponse saludar(@RequestPayload SaludarRequest nombre){
            SaludarResponse respuesta = new SaludarResponse();
            respuesta.setRespuesta("Hola " + nombre.getNombre());
            BuscarSaludosResponse.Saludos saludo=new BuscarSaludosResponse.Saludos();
            saludo.setId(id);
            saludo.setNombre("Hola " + nombre.getNombre());
            saludos.add(saludo);
            id++;
            return respuesta;
        }
        @PayloadRoot(namespace = "https://t4is.uv.mx/saludos" , localPart = "BuscarSaludosRequest")

        @ResponsePayload
        public BuscarSaludosResponse buscar(){
            BuscarSaludosResponse respuesta = new BuscarSaludosResponse();
            saludos.forEach(saludo->respuesta.getSaludos().add(saludo));
            return respuesta;
        }
        @PayloadRoot(namespace = "https://t4is.uv.mx/saludos" , localPart = "ModificarSaludoRequest")

        @ResponsePayload
        public ModificarSaludoResponse modifySaludo(@RequestPayload ModificarSaludoRequest peticion){
            ModificarSaludoResponse respuesta = new ModificarSaludoResponse();
            BuscarSaludosResponse.Saludos saludo=new BuscarSaludosResponse.Saludos();
            
            saludo.setId(peticion.getId());
            saludo.setNombre("Hola " + peticion.getNombre());
            
            try {
                saludos.set(obtenerPosicionID(saludos,peticion.getId()), saludo);
                respuesta.setRespuesta("Success");
                return respuesta;
            } catch (Exception e) {
                respuesta.setRespuesta("Error");
                return respuesta;
            }
        }
        @PayloadRoot(namespace = "https://t4is.uv.mx/saludos" , localPart = "EliminarSaludoRequest")

        @ResponsePayload
        public EliminarSaludoResponse modifySaludo(@RequestPayload EliminarSaludoRequest peticion){
            EliminarSaludoResponse respuesta = new EliminarSaludoResponse();
            try {
                saludos.remove(obtenerPosicionID(saludos,peticion.getId()));
                respuesta.setRespuesta("Success");
                return respuesta;
            } catch (Exception e) {
                respuesta.setRespuesta("Error");
                return respuesta;
            }
            
            
        }
        public int obtenerPosicionID(ArrayList<BuscarSaludosResponse.Saludos> saludos, int id) throws Exception{
            int index=0;
            boolean encontrado = false;
            for (int i=0;i<saludos.size();i++) {
                if(saludos.get(i).getId()==id){
                    encontrado = true;
                    index = i;
                    return index;
                }
            } 
                
            if(encontrado==false){
                throw new Exception("No existe");
            }
            return index; 
        }
}