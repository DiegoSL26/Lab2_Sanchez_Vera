/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package co.edu.javeriana.as.jakarta.personapp.web.services;

import co.edu.javeriana.as.jakarta.personapp.ejb.beans.PersonaFacadeLocal;
import co.edu.javeriana.as.jakarta.personapp.ejb.entities.Persona;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import com.google.gson.Gson;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("personas")
@RequestScoped
public class PersonasResource {

    @Context
    private UriInfo context;

    @EJB
    private PersonaFacadeLocal personaFacadeLocal;


    /**
     * Creates a new instance of GenericResource
     */

    public PersonasResource() {
    }

    /**
     * Retrieves representation of an instance of
     * co.edu.javeriana.as.jakarta.personapp.web.serices.GenericResource
     * 
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String get() {
        try {
            List<Persona> personas = personaFacadeLocal.findAll();
            String json = new Gson().toJson(personas);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"Respuesta\": \"Error al buscar la persona\"}";
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public String getPersonaId(@PathParam("id") String id) {
        try {
            Persona persona = personaFacadeLocal.find(Integer.valueOf(id));
            String json = new Gson().toJson(persona);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"Respuesta\": \"Error al buscar la persona por id\"}";
        }
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/createPersona")
    public String createPersona(Persona jsonPersona) {
        try {
            personaFacadeLocal.create(jsonPersona);
            return "{\"Respuesta\": \"Persona creada con exito!\"}";
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"Respuesta\": \"Error al crear la persona\"}";
        }
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/removePersona")
    public String removePersona(Persona jsonPersona) {
        try {
            personaFacadeLocal.remove(jsonPersona);
            return "{\"Respuesta\": \"Persona eliminada con exito!\"}";
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"Respuesta\": \"Error al eliminar la persona\"}";
        }
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/removePersonaById/{id}")
    public String removePersonaById(@PathParam("id") String id) {
        try {
            Persona persona = personaFacadeLocal.find(Integer.valueOf(id));
            personaFacadeLocal.remove(persona);
            return "{\"Respuesta\": \"Persona eliminada por su id con exito!\"}";
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"Respuesta\": \"Error al eliminar la persona por id\"}";
        }
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/updatePersona")
    public String updatePersona(Persona jsonPersona) {
        try {
            personaFacadeLocal.edit(jsonPersona);
            return "{\"Respuesta\": \"Persona editada con exito!\"}";
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"Respuesta\": \"Error al actualizar la persona\"}";
        }
    }
}