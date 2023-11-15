package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.Assignee;
import com.assetmanager.database.Database;
import com.assetmanager.exceptions.AssigneeDoesNotExistException;

import java.util.List;
import java.util.Optional;

public class AssigneeBean extends GenericBean<Assignee> implements AssigneeBeanI {
    Database database = Database.getDatabaseInstance();
    List<Assignee> assignees = database.getAssigneeList();

    @Override
    public Assignee createAssignee(Assignee newAssignee) {
        newAssignee.setId(100L);
        database.getAssigneeList().add(newAssignee);
        return newAssignee;
    }

    @Override
    public Optional<Assignee> getAssigneeByStaffId(String staffID) {
        return Optional.ofNullable(assignees.stream()
                .filter(assignee -> assignee.getStaffNumber().equals(staffID))
                .findFirst().orElseThrow(
                        () -> new AssigneeDoesNotExistException("Staff with id " + staffID + " does not exist")));
    }

}
