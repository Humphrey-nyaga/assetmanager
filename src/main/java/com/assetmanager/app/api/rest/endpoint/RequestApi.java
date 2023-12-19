package com.assetmanager.app.api.rest.endpoint;

import com.assetmanager.app.api.rest.auth.JwtSecured;
import com.assetmanager.app.bean.AssetRequestBeanI;
import com.assetmanager.app.model.entity.AssetRequest;
import com.assetmanager.app.model.entity.UserRole;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/request")
@JwtSecured({UserRole.ADMIN,UserRole.SUPER})
public class RequestApi {

    @EJB
    AssetRequestBeanI assetRequestBean;

    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAssetRequests() {
        List<AssetRequest> requestList = assetRequestBean.list(new AssetRequest());
        return Response.status(Response.Status.OK).entity(requestList).build();
    }

    @Path("/")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAssetRequest(AssetRequest assetRequest) {
        AssetRequest newAssetRequest = assetRequestBean.addOrUpdate(assetRequest);
        return Response.status(Response.Status.OK).entity(newAssetRequest).build();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRequestById(@PathParam("id") Long id) {
        AssetRequest assetRequest = assetRequestBean.findById(AssetRequest.class, id);
        if (assetRequest != null) {
            return Response.status(Response.Status.OK).entity(assetRequest).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Not Found").build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            assetRequestBean.deleteById(AssetRequest.class, id);
            return Response.status(Response.Status.OK).entity("Success").build();
        } catch (IllegalArgumentException ex) {
            return Response.status(Response.Status.NOT_FOUND).entity("Not Found").build();
        }
    }

    @Path("/")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAssetRequest(AssetRequest assetRequest) {
        AssetRequest updatedAssetRequest = assetRequestBean.addOrUpdate(assetRequest);
        return Response.status(Response.Status.CREATED).entity(updatedAssetRequest).build();
    }
    @Path("/assignee/{assigneeId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @JwtSecured({UserRole.ADMIN,UserRole.REGULAR})
    public Response getAssigneeRequests(@PathParam("assigneeId") Long assigneeId) {
        List<AssetRequest> requestList = assetRequestBean.getAssigneeAssetRequests(assigneeId);
        return Response.status(Response.Status.OK).entity(requestList).build();
    }
    @Path("/statistics/{assigneeId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @JwtSecured({UserRole.ADMIN,UserRole.REGULAR})
    public Response asserRequestsByAssigneeStats(@PathParam("assigneeId")Long assigneeId) {
        Map<String, Long> statistics = assetRequestBean.countAssigneeRequestsByCategory(assigneeId);
        return Response.status(Response.Status.OK).entity(statistics).build();
    }
}
