package com.assetmanager.app.rest.apis;

import com.assetmanager.app.bean.AssetRequestBeanI;
import com.assetmanager.app.model.entity.Asset;
import com.assetmanager.app.model.entity.AssetRequest;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/request")

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
}
