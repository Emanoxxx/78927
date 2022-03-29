package mx.uv.t4is.SaludosBd;

import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.*;

import https.t4is_uv_mx.saludos.BorrarSaludoRequest;
import https.t4is_uv_mx.saludos.BorrarSaludoResponse;
import https.t4is_uv_mx.saludos.BuscarSaludosResponse;
import https.t4is_uv_mx.saludos.ModificarSaludoRequest;
import https.t4is_uv_mx.saludos.ModificarSaludoResponse;
import https.t4is_uv_mx.saludos.SaludarRequest;
import https.t4is_uv_mx.saludos.SaludarResponse;
import https.t4is_uv_mx.saludos.BuscarSaludosResponse.Saludo;

@Endpoint
public class SaludosEndPoint {
    // private int id = 0;
    // ArrayList<BuscarSaludosResponse.Saludo> saludos = new ArrayList<BuscarSaludosResponse.Saludo>(); // Create an ArrayList object
    @Autowired
    private Isaludadores isaludadores;

    @PayloadRoot(namespace = "https://t4is.uv.mx/saludos", localPart = "SaludarRequest")

    @ResponsePayload
    public SaludarResponse saludar(@RequestPayload SaludarRequest peticion) {
        SaludarResponse respuesta = new SaludarResponse();
        respuesta.setRespuesta("Hola " + peticion.getNombre());

        // Agregar saludo a BD
        Saludadores saludo = new Saludadores();
        saludo.setNombre(peticion.getNombre());
        isaludadores.save(saludo);

        return respuesta;
    }

    @PayloadRoot(namespace = "https://t4is.uv.mx/saludos", localPart = "BuscarSaludosRequest")
    @ResponsePayload
    public BuscarSaludosResponse buscar() {
        BuscarSaludosResponse respuesta = new BuscarSaludosResponse();
        isaludadores.findAll().forEach((saludo) ->{
            BuscarSaludosResponse.Saludo sal=new BuscarSaludosResponse.Saludo();
            sal.setId(saludo.getId());
            sal.setNombre(saludo.getNombre());
            respuesta.getSaludo().add(sal);
        });
        return respuesta;
    }

    @PayloadRoot(namespace = "https://t4is.uv.mx/saludos", localPart = "ModificarSaludoRequest")
    @ResponsePayload
    public ModificarSaludoResponse modificar(@RequestPayload ModificarSaludoRequest peticion) {
        ModificarSaludoResponse respuesta = new ModificarSaludoResponse();
        int id = peticion.getId();
        String nombre = peticion.getNombre();
        Optional<Saludadores> saludofound=isaludadores.findById(id);
        if(saludofound.isPresent()){
            Saludadores saludo =saludofound.get();
            saludo.setNombre(nombre);
            isaludadores.save(saludo);
        }else{  
            respuesta.setRespuesta(false);
            return respuesta;
        }
        respuesta.setRespuesta(true);
        return respuesta;
    }
    
    @PayloadRoot(namespace = "https://t4is.uv.mx/saludos", localPart = "BorrarSaludoRequest")
    @ResponsePayload
    public BorrarSaludoResponse borrar(@RequestPayload BorrarSaludoRequest peticion) {
        BorrarSaludoResponse respuesta = new BorrarSaludoResponse();
        int id = peticion.getId();
        Optional<Saludadores> saludofound=isaludadores.findById(id);
        if(saludofound.isPresent()){
            Saludadores saludo =saludofound.get();
            isaludadores.delete(saludo);
        }else{  
            respuesta.setRespuesta(false);
            return respuesta;
        }
        respuesta.setRespuesta(true);
        return respuesta;
    }
    
}