package com.assetmanager.app.bean;

import com.assetmanager.app.dto.AssigneeDTO;
import com.assetmanager.app.model.entity.Assignee;

import com.assetmanager.util.SerialIDGenerator.SerialIDGenerator;
import com.assetmanager.util.ageValidator.ValidAgeI;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

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
    @Override
    @SuppressWarnings("unchecked")
    public List<AssigneeDTO> findAssigneeNameAndId(){
        Query query = em.createNamedQuery("Assignee.AssigneeNameAndId");
        List<Object[]> results = query.getResultList();

        return results.stream()
                .map(result -> new AssigneeDTO((Long) result[0], (String) result[1]))
                .collect(Collectors.toList());
    }

    @Override
    public Assignee getAssigneeByEmail(String email) {
        try {
            TypedQuery<Assignee> query = em.createQuery("FROM Assignee a WHERE a.email = :email", Assignee.class)
                    .setParameter("email", email);
            return query.getSingleResult();
        } catch (NoResultException | NullPointerException e) {
            return null;
        }
    }


}
