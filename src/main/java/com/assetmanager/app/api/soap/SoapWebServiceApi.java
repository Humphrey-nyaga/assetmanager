package com.assetmanager.app.api.soap;


import com.assetmanager.app.bean.AssetBeanI;
import com.assetmanager.app.bean.AssigneeBeanI;
import com.assetmanager.app.dto.AssetDTO;
import com.assetmanager.app.model.entity.Asset;
import com.assetmanager.app.model.entity.Assignee;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public class SoapWebServiceApi {

    @EJB
    AssigneeBeanI assigneeBean;
    @EJB
    AssetBeanI assetBean;
    @WebMethod
    public List<Assignee> getAllAssignees(){
        return  assigneeBean.list(new Assignee());
    }
    @WebMethod
    public List<Asset> getAllAssets(){
        return  assetBean.list(new Asset());
    }

    @WebMethod
    public Assignee getAssigneeById(@WebParam(name = "id") Long id) {
        return assigneeBean.findById(Assignee.class,id);
    }


    }
