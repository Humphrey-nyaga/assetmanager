package com.assetmanager.app.bean;

import com.assetmanager.app.dto.AssigneeDTO;
import com.assetmanager.app.model.entity.Assignee;

import java.util.List;

public interface AssigneeBeanI extends GenericBeanI<Assignee>{
    Assignee getAssigneeByStaffId(String staffToSearchID);

    @SuppressWarnings("unchecked")
    List<AssigneeDTO> findAssigneeNameAndId();
    Assignee getAssigneeByEmail(String email);


}
