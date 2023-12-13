package com.assetmanager.app.api.rest.endpoint;

import com.assetmanager.app.bean.AssetBeanI;
import com.assetmanager.app.dto.AssetDTO;
import com.assetmanager.app.model.entity.Asset;
import com.assetmanager.app.service.AssetsValuationI;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Path("/asset")
public class AssetApi {


    @EJB
    AssetBeanI assetBean;

    @Inject
    AssetsValuationI assetsValuation;


    @Path("/")
    @GET
    @RolesAllowed("ADMIN")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAssets() {
        List<Asset> assetList = assetBean.list(new Asset());
        return Response.status(Response.Status.OK).entity(assetList).build();
    }

    @GET
    @RolesAllowed("ADMIN")
    @Path("/assets-name-and-serial-no")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAssetsNameAndSerialNo() {
        List<AssetDTO> assets = assetBean.findAllAssetsNameAndSerialNo();
        return Response.status(Response.Status.OK).entity(assets).build();
    }

    @GET
    @RolesAllowed("ADMIN")
    @Path("/serialNo/{serialNo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAssetBySerialNumber(@PathParam("serialNo") String serialNo) {
        Asset asset = assetBean.findAssetBySerialNumber(serialNo,new Asset());
        return Response.status(Response.Status.OK).entity(asset).build();
    }
    @Path("/statistics/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response assetsValueByCount() {
        Map<Object, Object> statistics = new HashMap<>();
        statistics.put("Total Assets Value", assetsValuation.totalAssetsValue(assetBean.list(new Asset())));
        statistics.put("Assets Value By Category", assetsValuation.totalAssetValueByCategory(assetBean.list(new Asset())));
        statistics.put("Asset Count By Category",assetsValuation.countAssetsByCategory(assetBean.list(new Asset())));
        statistics.put("Total Assets",assetsValuation.totalAssets(assetBean.list(new Asset())));
        return Response.status(Response.Status.OK).entity(statistics).build();
    }

    @DELETE
    @RolesAllowed("ADMIN")
    @Path("/serialNo/{serialNo}")
    public Response delete(@PathParam("serialNo") String serialNo) {
        try {
            assetBean.deleteBySerialNumber(new Asset(), serialNo);
            return Response.status(Response.Status.OK).entity("Success").build();
        }catch (IllegalArgumentException ex) {
            return Response.status(Response.Status.NOT_FOUND).entity("Not Found").build();
        }
    }

    @Path("/vehicle-and-machinery")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVehiclesAndMachinery() {
        List<Asset> assetList = assetBean.vehicleAndMachineryOnlyList(new Asset());
        return Response.status(Response.Status.OK).entity(assetList).build();
    }
}