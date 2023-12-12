package com.assetmanager.app.rest.apis;

import com.assetmanager.app.bean.ComputerBeanI;
import com.assetmanager.app.bean.VehicleBeanI;
import com.assetmanager.app.model.entity.Assignee;
import com.assetmanager.app.model.entity.computer.Computer;
import com.assetmanager.app.model.entity.vehicle.Vehicle;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/computer")
public class ComputerApi {

    @EJB
    ComputerBeanI computerBean;

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addComputer(Computer computer) {
        Computer newComputer = computerBean.addOrUpdate(computer);
        return Response.status(Response.Status.CREATED).entity(newComputer).build();
    }

    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getComputers() {
        List<Computer> computers = computerBean.list(new Computer());
        return Response.status(Response.Status.OK).entity(computers).build();
    }

    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getComputerById(@PathParam("id") Long id) {
        Computer computer = computerBean.findById(Computer.class, id);
        if (computer != null) {
            return Response.status(Response.Status.OK).entity(computer).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Not Found").build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            computerBean.deleteById(Assignee.class, id);
            return Response.status(Response.Status.OK).entity("Success").build();
        }catch (IllegalArgumentException ex) {
            return Response.status(Response.Status.NOT_FOUND).entity("Not Found").build();
        }
    }

    @Path("/")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateComputer(Computer computer) {
        Computer updatedComputer = computerBean.addOrUpdate(computer);
        return Response.status(Response.Status.CREATED).entity(updatedComputer).build();
    }

}
