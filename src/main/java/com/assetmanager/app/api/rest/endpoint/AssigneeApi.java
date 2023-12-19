package com.assetmanager.app.api.rest.endpoint;

import com.assetmanager.app.api.rest.auth.JwtSecured;
import com.assetmanager.app.bean.AssigneeBeanI;
import com.assetmanager.app.dto.AssigneeDTO;
import com.assetmanager.app.model.entity.Assignee;
import com.assetmanager.app.model.entity.UserRole;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

@Path("/assignee")
@JwtSecured({UserRole.ADMIN,UserRole.REGULAR,})
public class AssigneeApi {


    @EJB
    AssigneeBeanI assigneeBean;

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAssignee(Assignee assignee) {
        Assignee assignee1 = assigneeBean.addOrUpdate(assignee);
        return Response.status(Response.Status.CREATED).entity(assignee1).build();
    }


    @Path("/")
    @GET
    @JwtSecured({UserRole.ADMIN,UserRole.SUPER})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAssignees() {
        List<Assignee> assignees = assigneeBean.list(new Assignee());
        return Response.status(Response.Status.OK).entity(assignees).build();
    }

    @Path("/assignee-name-and-id")
    @GET
    @JwtSecured({UserRole.ADMIN,UserRole.SUPER})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAssigneeNameAndID() {
        List<AssigneeDTO> assignees = assigneeBean.findAssigneeNameAndId();
        return Response.status(Response.Status.OK).entity(assignees).build();
    }

    @GET
    @Path("/assigneeByStaffId")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAssigneeByStaffId(@QueryParam("staffId") String staffId) {
        if (staffId != null && !staffId.isEmpty()) {
            Assignee assignee = assigneeBean.getAssigneeByStaffId(staffId);
            if (assignee != null) {
                return Response.status(Response.Status.OK).entity(assignee).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Not Found").build();
    }

    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAssignee(@PathParam("id") Long id) {
        Assignee assignee = assigneeBean.findById(Assignee.class,id);
        if (assignee != null) {
            return Response.status(Response.Status.OK).entity(assignee).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Not Found").build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            assigneeBean.deleteById(Assignee.class, id);
            return Response.status(Response.Status.OK).entity("Success").build();
        }catch (IllegalArgumentException ex) {
            return Response.status(Response.Status.NOT_FOUND).entity("Not Found").build();
        }
    }
    @Path("/")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAssignee(Assignee assignee) {
        Assignee updatedAssignee = assigneeBean.addOrUpdate(assignee);
        return Response.status(Response.Status.CREATED).entity(updatedAssignee).build();
    }


}
