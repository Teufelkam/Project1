package dao;

import model.Group;
import serialization.JSONSerialization;
import serialization.XMLSerialization;

import java.util.ArrayList;
import java.util.List;

public class GroupRepository {

    public void fromJSONToDatabase(String filename) throws Exception {
        JSONSerialization<Group> jsonSerialization = new JSONSerialization<>();
        List<Group> list = jsonSerialization.listFromFile(filename, Group.class);

        GroupDao groupDao = new GroupDao();
        for (Group group : list) {
            groupDao.addGroupStudentsAndMarks(group);
        }
    }

    public void fromXMLToDatabase(String filename) throws Exception {
        XMLSerialization<Group> xmlSerialization = new XMLSerialization<>();
        List<Group> list = xmlSerialization.listFromFile(filename, Group.class);

        GroupDao groupDao = new GroupDao();
        for (Group group : list) {
            groupDao.addGroupStudentsAndMarks(group);
        }
    }
}


