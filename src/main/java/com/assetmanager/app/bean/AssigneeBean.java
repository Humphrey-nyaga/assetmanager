package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.Assignee;
import com.assetmanager.database.Database;

import java.util.List;

public class AssigneeBean implements AssigneeBeanI {
    Database database = Database.getDatabaseInstance();
    List<Assignee> assignees = database.getAssigneeList();

    @Override
    public Assignee createAssignee(Assignee newAssignee) {
        newAssignee.setId(100L);
        database.getAssigneeList().add(newAssignee);
        return newAssignee;
    }

    @Override
    public Assignee updateAssignee(Assignee newAssignee) {
        return null;
    }

    @Override
    public void deleteAssignee(Assignee newAssignee) {

    }


    @Override
    public Assignee getAssigneeByNationalId() {
        return null;
    }

    @Override
    public Assignee getAssigneeByEmail() {
        return null;
    }

    @Override
    public List<Assignee> getAllAssignees() {

        return database.getAssigneeList();
    }

}
