package com.assetmanager.app.api.rest.endpoint;

import com.assetmanager.app.api.rest.auth.JwtSecured;
import com.assetmanager.app.bean.MachineryBeanI;
import com.assetmanager.app.model.entity.Machinery.Machinery;
import com.assetmanager.app.model.entity.UserRole;
import com.assetmanager.app.model.entity.computer.Computer;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/machinery")
@JwtSecured({UserRole.ADMIN,UserRole.SUPER})
public class MachineryApi {
    @EJB
    MachineryBeanI machineryBean;

    @Path("/")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateMachinery(Machinery machinery) {
        Machinery updateMachinery = machineryBean.addOrUpdate(machinery);
        return Response.status(Response.Status.CREATED).entity(updateMachinery).build();
    }
}
