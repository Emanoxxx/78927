package com.actividad.saludo;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;

import org.springframework.ws.server.endpoint.annotation.*;

import https.t4is_uv_mx.saludos.BuscarSaludosResponse;
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
            id++;
            saludo.setId(id);
            saludo.setNombre("Hola " + nombre.getNombre());
            saludos.add(saludo);
            return respuesta;
        }
        @PayloadRoot(namespace = "https://t4is.uv.mx/saludos" , localPart = "BuscarSaludosRequest")

        @ResponsePayload
        public BuscarSaludosResponse buscar(){
            BuscarSaludosResponse respuesta = new BuscarSaludosResponse();
            respuesta.setSaludos(saludos);
            return respuesta;
        }
}