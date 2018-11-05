package com.springbootorm.api.association;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssociationService {

    @Autowired
    private AssociationRepository associationRepository;

    // Create
    public void addAssociation(Association association) {
        associationRepository.save(association);
    }

    // Read all
    public List<Association> getAllAssociations(String active_flag) {
        List<Association> allAssociations = new ArrayList<>();
        List<Association> activeAssociations = new ArrayList<>();
        List<Association> inActiveAssociations = new ArrayList<>();

        associationRepository.findAll().forEach(allAssociations::add);
        for (Association association : allAssociations) {
            if (association.getActivity()) {
                activeAssociations.add(association);
            } else {
                inActiveAssociations.add(association);
            }
        }
        if (active_flag.equals("active")) {
            return activeAssociations;
        } else if (active_flag.equals("inactive")) {
            return inActiveAssociations;
        } else {
            return allAssociations;
        }
    }

    // Read one
    public ArrayList<Association> getAssociation(Integer id) {
        ArrayList<Association> associationList = new ArrayList<>();
        associationList.add(associationRepository.findById(id).get());
        return associationList;
    }

    // Update
    public void updateAssociation(Association association, Integer id) {
        association.setAssociation_id(id);
        Association dbAssociation = associationRepository.findById(id).get();
        if (association.getName() != null && !association.getName().equals("")) {
            dbAssociation.setName(association.getName());
        }
        if (association.getDescription() != null && !association.getDescription().equals("")) {
            dbAssociation.setDescription(association.getDescription());
        }

        associationRepository.save(dbAssociation);
    }

    // Delete
    public void deleteAssociation(Integer id) {
        Association associationToDelete = associationRepository.findById(id).get();
        associationToDelete.setActivity(false);
        updateAssociation(associationToDelete, id);
    }

    // Check if exists
    public boolean checkIfExists(Integer id) {
        return associationRepository.existsById(id);
    }
}
