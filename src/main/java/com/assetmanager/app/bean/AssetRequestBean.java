package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.AssetRequest;

import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote
public class AssetRequestBean extends GenericBean<AssetRequest> implements AssetRequestBeanI {

}
