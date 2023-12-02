package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.Assignee;

import com.assetmanager.app.model.entity.AssigneeType;
import com.assetmanager.database.MysqlDatabase;
import com.assetmanager.exceptions.AssigneeDoesNotExistException;
import com.assetmanager.util.SerialIDGenerator.SerialIDGenerator;
import com.assetmanager.util.ageValidator.ValidAgeI;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Stateless
public class AssigneeBean extends GenericBean<Assignee> implements AssigneeBeanI, Serializable {

    @EJB
    MysqlDatabase database;
    @Inject
    @Named("AssigneeID")
    SerialIDGenerator serialIDGenerator;

    @Inject
    ValidAgeI validAge;

    @Override
    public void create(Assignee assignee) {
        if (validAge.validWorkingAge(assignee.getDateOfBirth())) {
            assignee.setStaffNumber(serialIDGenerator.generate());
            getDao().create(assignee);
        } else {
            throw new RuntimeException("Invalid Age for employee");
        }


    }

    @Override
    public Optional<Assignee> getAssigneeByStaffId(String staffID) {
        String query = "SELECT * FROM assignee WHERE staff_id = ?";

        try {
            Connection connection = database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, staffID);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String staffNumber = resultSet.getString("staff_id");
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                String email = resultSet.getString("email");
                AssigneeType employeeType = AssigneeType.valueOf(resultSet.getString("employee_type"));
                LocalDate dateOfBirth = resultSet.getDate("date_of_birth").toLocalDate();
                String identificationNumber = resultSet.getString("national_id");

                Assignee assignee = new Assignee(staffNumber, firstName, lastName, email, dateOfBirth, identificationNumber, employeeType);
                return Optional.of(assignee);

            } else {
                throw new AssigneeDoesNotExistException("Staff with id " + staffID + " does not exist");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }


}
