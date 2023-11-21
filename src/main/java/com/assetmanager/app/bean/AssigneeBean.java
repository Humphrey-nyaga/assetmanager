package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.Assignee;
import com.assetmanager.database.Database;
import com.assetmanager.database.MysqlDatabase;
import com.assetmanager.exceptions.AssigneeDoesNotExistException;
import com.mysql.cj.MysqlConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AssigneeBean extends GenericBean<Assignee> implements AssigneeBeanI {


    List<Assignee> assignees = database.getAssigneeList();


    @Override
    public Optional<Assignee> getAssigneeByStaffId(String staffID) {
        return Optional.ofNullable(assignees.stream()
                .filter(assignee -> assignee.getStaffNumber().equals(staffID))
                .findFirst().orElseThrow(
                        () -> new AssigneeDoesNotExistException("Staff with id " + staffID + " does not exist")));
    }

}
