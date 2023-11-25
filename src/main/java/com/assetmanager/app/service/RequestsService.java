package com.assetmanager.app.service;

import com.assetmanager.app.bean.AssetRequestBean;
import com.assetmanager.app.bean.AssetRequestBeanI;
import com.assetmanager.app.model.entity.Asset;
import com.assetmanager.app.model.entity.AssetRequest;
import com.assetmanager.app.model.entity.Category;
import com.assetmanager.app.model.entity.RequestStatus;
import com.assetmanager.app.view.html.SummaryHtmlCard;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RequestsService {
    public RequestsService() {
    }

    @SummaryHtmlCard(name = "Request Count By Category")
    public Map<RequestStatus, Long> countRequestsByStatus(List<AssetRequest> assetRequests) {
        return assetRequests.stream()
                .collect(Collectors.groupingBy(
                        AssetRequest::getRequestStatus,
                        Collectors.counting()
                ));
    }

    @SummaryHtmlCard(name = "Total Requests")
    public Integer totalRequests(List<AssetRequest> assetRequests) {
        return assetRequests.size();
    }

}
