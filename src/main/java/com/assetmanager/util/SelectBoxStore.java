package com.assetmanager.util;

import com.assetmanager.app.bean.AssetBeanI;
import com.assetmanager.app.bean.AssigneeBeanI;
import com.assetmanager.app.model.entity.Asset;
import com.assetmanager.app.model.entity.Assignee;


import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Produces;
import java.io.Serializable;
import java.util.List;

@Produces
@ApplicationScoped
public class SelectBoxStore implements Serializable {

    @EJB
    private AssigneeBeanI assigneeBean;

    @EJB
    AssetBeanI assetBean;


    public List<Assignee> assignees(){
        return assigneeBean.list(new Assignee());
    }

    public List<Asset> assets(){
        return  assetBean.vehicleAndMachineryOnlyList(new Asset());
    }

}
