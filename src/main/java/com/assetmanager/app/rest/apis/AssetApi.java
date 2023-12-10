package com.assetmanager.app.rest.apis;

import com.assetmanager.app.bean.AssetBeanI;
import com.assetmanager.app.model.entity.Asset;
import com.assetmanager.app.model.entity.Assignee;
import com.assetmanager.app.service.AssetsValuationI;

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
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAssets() {
        List<Asset> assetList = assetBean.list(new Asset());
        return Response.status(Response.Status.OK).entity(assetList).build();
    }
    @Path("/serialNo/{serialNo}")
    @GET
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
}