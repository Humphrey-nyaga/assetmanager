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
    public Assignee create(Assignee newAssignee) {

        try {
            Connection connection = MysqlDatabase.getDatabaseInstance().getConnection();
            PreparedStatement createAssigneeStmt = connection.prepareStatement("INSERT INTO assignee(staff_id,firstname,lastname,email, date_of_birth,national_id)VALUES(?,?,?,?,?,?);");
            createAssigneeStmt.setString(1, newAssignee.getStaffNumber());
            createAssigneeStmt.setString(2, newAssignee.getFirstName());
            createAssigneeStmt.setString(3,newAssignee.getLastName());
            createAssigneeStmt.setString(4,newAssignee.getEmail());
            createAssigneeStmt.setDate(5, Date.valueOf(newAssignee.getDateOfBirth()));
            createAssigneeStmt.setString(6, newAssignee.getIdentificationNumber());
            createAssigneeStmt.execute();
            return newAssignee;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Optional<Assignee> getAssigneeByStaffId(String staffID) {
        return Optional.ofNullable(assignees.stream()
                .filter(assignee -> assignee.getStaffNumber().equals(staffID))
                .findFirst().orElseThrow(
                        () -> new AssigneeDoesNotExistException("Staff with id " + staffID + " does not exist")));
    }

}
