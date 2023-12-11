package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.Assignee;

import com.assetmanager.util.SerialIDGenerator.SerialIDGenerator;
import com.assetmanager.util.ageValidator.ValidAgeI;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;

@Stateless
@Remote
public class AssigneeBean extends GenericBean<Assignee> implements AssigneeBeanI, Serializable {

    @PersistenceContext
    EntityManager em;
    @Inject
    @Named("AssigneeID")
    SerialIDGenerator serialIDGenerator;

    @Inject
    ValidAgeI validAge;

    @Override
    public Assignee addOrUpdate(Assignee assignee) {
        if (assignee.getId() == null) {
            assignee.setStaffNumber(serialIDGenerator.generate());
        }

        if (validAge.validWorkingAge(assignee.getDateOfBirth())) {
            return getDao().addOrUpdate(assignee);
        } else {
            throw new RuntimeException("Invalid Age for employee");
        }
    }


    @Override
    public Assignee getAssigneeByStaffId(String staffToSearchID) {
        TypedQuery<Assignee> query = em.createQuery("FROM Assignee a WHERE a.staffNumber = :staffId", Assignee.class)
                .setParameter("staffId", staffToSearchID);
        return query.getSingleResult();
    }

}
