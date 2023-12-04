package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.Assignee;

import com.assetmanager.app.model.entity.AssigneeType;
import com.assetmanager.exceptions.AssigneeDoesNotExistException;
import com.assetmanager.util.SerialIDGenerator.SerialIDGenerator;
import com.assetmanager.util.ageValidator.ValidAgeI;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

@Stateless
public class AssigneeBean extends GenericBean<Assignee> implements AssigneeBeanI, Serializable {

    @PersistenceContext
    EntityManager em;
    @Inject
    @Named("AssigneeID")
    SerialIDGenerator serialIDGenerator;

    @Inject
    ValidAgeI validAge;

    @Override
    public void addOrUpdate(Assignee assignee) {
        if (validAge.validWorkingAge(assignee.getDateOfBirth())) {
            assignee.setStaffNumber(serialIDGenerator.generate());
            getDao().addOrUpdate(assignee);
        } else {
            throw new RuntimeException("Invalid Age for employee");
        }


    }

    @Override
    public Optional<Assignee> getAssigneeByStaffId(String staffToSearchID) {
        Assignee assignee = em.createQuery("FROM Assignee a where a.staffNumber=:staffID", Assignee.class)
                .setParameter("staffID",staffToSearchID)
                .getSingleResult();
        return Optional.of(assignee);
    }


}
