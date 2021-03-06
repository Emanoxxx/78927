package mx.uv.t4is.SaludosBd;

import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

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
        Iterable<Saludadores> saludadores = isaludadores.findAll();

        saludadores.forEach(isaludo -> {
            Saludo saludo = new Saludo();
            saludo.setId(isaludo.getId());
            saludo.setNombre(isaludo.getNombre());
            respuesta.getSaludo().add(saludo);
        });

        return respuesta;
    }

    @PayloadRoot(namespace = "https://t4is.uv.mx/saludos", localPart = "ModificarSaludoRequest")
    @ResponsePayload
    public ModificarSaludoResponse modificar(@RequestPayload ModificarSaludoRequest peticion) {
        ModificarSaludoResponse respuesta = new ModificarSaludoResponse();
        
        // Recuperar parametros de la petici??n 
        int id = peticion.getId();
        String nombre = peticion.getNombre();

        Optional<Saludadores> oSaludador = isaludadores.findById(id);
        if(oSaludador.isPresent()) {
            Saludadores saludador;
            saludador = oSaludador.get();
            saludador.setNombre(nombre);
            isaludadores.save(saludador);
            respuesta.setRespuesta(true);
        } else {
            respuesta.setRespuesta(false);
        }
        return respuesta;
    }

    @PayloadRoot(namespace = "https://t4is.uv.mx/saludos", localPart = "BorrarSaludoRequest")
    @ResponsePayload
    public BorrarSaludoResponse borrar(@RequestPayload BorrarSaludoRequest peticion) {
        BorrarSaludoResponse respuesta = new BorrarSaludoResponse();

        // Recuperar parametros de la petici??n 
        int id = peticion.getId();

        Optional<Saludadores> oSaludador = isaludadores.findById(id);
        if(oSaludador.isPresent()) {
            isaludadores.deleteById(id);
            respuesta.setRespuesta(true);
        } else {
            respuesta.setRespuesta(false);
        }
        return respuesta;
    }
}