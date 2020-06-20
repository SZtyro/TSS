/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.webapplicationservlet.main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import pl.data.CRUD;
import pl.data.DataLogic;
import pl.resources.Osoba;

/**
 * REST Web Service
 *
 * @author fabix
 */
@Path("/users")
public class GenericResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }

    /**
     * Retrieves representation of an instance of pl.webapplicationservlet.main.GenericResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Osoba> getJson() throws SQLException, ClassNotFoundException {   
        DataLogic logic = new DataLogic();
        List<Osoba> lista = logic.connectDatabase().fetchData();
        //logic.closeConnection();
        //CRUD crud = new CRUD();
        //logic.connectDatabase();
        //crud.setLogic(logic);
        //return crud.fetchData();
        return lista;
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     * @param content representation for the resource
     */
//    @PUT
//    @Consumes(MediaType.APPLICATION_JSON)
//    public void putJson(String content) {
//    }
}
