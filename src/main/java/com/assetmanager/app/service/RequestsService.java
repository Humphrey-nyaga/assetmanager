package com.assetmanager.app.service;

import com.assetmanager.app.model.entity.*;
import com.assetmanager.app.view.html.SummaryHtmlCard;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RequestsService implements SummaryService<AssetRequest> {
    public RequestsService() {
    }

    @Override
    @SummaryHtmlCard(name = "Request Count By Category")
    public Map<String, Long> countByCategory(List<AssetRequest> assetRequests) {
        return assetRequests.stream()
                .collect(Collectors.groupingBy(
                        assetRequest -> assetRequest.getRequestStatus().name(),
                        Collectors.counting()
                ));
    }

    @Override
    @SummaryHtmlCard(name = "Total Requests")
    public Integer totalCount(List<AssetRequest> assetRequests) {
        return assetRequests.size();
    }
}
