package com.assetmanager.app.rest.apis;

import com.assetmanager.app.bean.VehicleBeanI;
import com.assetmanager.app.model.entity.vehicle.Vehicle;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/vehicle")
public class VehicleApi {
    @EJB
    VehicleBeanI vehicleBean;

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addVehicle(Vehicle vehicle) {
        Vehicle newVehicle = vehicleBean.addOrUpdate(vehicle);
        return Response.status(Response.Status.CREATED).entity(newVehicle).build();
    }


    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVehicles() {
        List<Vehicle> vehicles = vehicleBean.list(new Vehicle());
        return Response.status(Response.Status.OK).entity(vehicles).build();
    }


    @GET
    @Path("/serialNumber")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVehicleBySerialNumber(@QueryParam("serialNumber") String serialNumber) {
        if (serialNumber != null && !serialNumber.isEmpty()) {
            Vehicle vehicle = vehicleBean.getVehicleBySerialNumber(serialNumber);
            if (vehicle != null) {
                return Response.status(Response.Status.OK).entity(vehicle).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Not Found").build();
    }
    // select * from tours where destination = 'nairobi' and date=2023-12-12 and limit=50;


    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVehicleById(@PathParam("id") Long id) {
        Vehicle vehicle = vehicleBean.findById(Vehicle.class, id);
        if (vehicle != null) {
            return Response.status(Response.Status.OK).entity(vehicle).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Not Found").build();
    }


    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            vehicleBean.deleteById(Vehicle.class, id);
            return Response.status(Response.Status.OK).entity("Success").build();
        } catch (IllegalArgumentException ex) {
            return Response.status(Response.Status.NOT_FOUND).entity("Not Found").build();
        }
    }

}
