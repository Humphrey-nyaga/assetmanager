package com.assetmanager.app.service;

import com.assetmanager.app.model.entity.AssetRequest;
import com.assetmanager.app.model.entity.Assignee;
import com.assetmanager.app.view.html.SummaryHtmlCard;

import java.util.List;

public class AssigneeService {
    @SummaryHtmlCard(name = "Available Assignees")
    public Integer assigneesCount(List<Assignee> assignees) {
        return assignees.size();
    }

}
