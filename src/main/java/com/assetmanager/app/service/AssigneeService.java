package com.assetmanager.app.service;

import com.assetmanager.app.model.entity.AssetRequest;
import com.assetmanager.app.model.entity.Assignee;
import com.assetmanager.app.model.entity.AssigneeType;
import com.assetmanager.app.view.html.SummaryHtmlCard;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AssigneeService implements SummaryService<Assignee> {

    @SummaryHtmlCard(name = "Assignees By Category")

    @Override
    public Map<String, Long> countByCategory(List<Assignee> assigneeList) {
        return assigneeList.stream()
                .collect(Collectors.groupingBy(
                        assignee -> assignee.getEmployeeType().getDisplayName(),
                        Collectors.counting()
                ));

    }

    @SummaryHtmlCard(name = "Available Assignees")
    public Integer totalCount(List<Assignee> assignees) {
        return assignees.size();
    }

}
