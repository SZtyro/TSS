/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package REST;

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

    public GenericResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Osoba> getJson() throws SQLException, ClassNotFoundException {   
        DataLogic logic = new DataLogic();
        List<Osoba> lista = logic.connectDatabase().fetchData();

        return lista;
    }

}
