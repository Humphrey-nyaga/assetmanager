package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.Asset;
import com.assetmanager.app.model.entity.AssetRequest;
import com.assetmanager.app.model.entity.Category;
import com.assetmanager.app.model.entity.RequestStatusEnum;
import com.assetmanager.database.Database;
import com.assetmanager.database.MysqlDatabase;

import javax.ejb.Stateless;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Stateless
public class AssetRequestBean extends GenericBean<AssetRequest> implements AssetRequestBeanI {

}
