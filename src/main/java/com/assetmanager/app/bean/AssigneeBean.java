package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.Assignee;

import com.assetmanager.exceptions.AssigneeDoesNotExistException;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;
import java.util.Optional;

@Stateless
@Remote
public class AssigneeBean extends GenericBean<Assignee> implements AssigneeBeanI {

    @Override
    public Optional<Assignee> getAssigneeByStaffId(String staffID) {
        return Optional.ofNullable(list(Assignee.class).stream()
                .filter(assignee -> assignee.getStaffNumber().equals(staffID))
                .findFirst().orElseThrow(
                        () -> new AssigneeDoesNotExistException("Staff with id " + staffID + " does not exist")));
    }

}
