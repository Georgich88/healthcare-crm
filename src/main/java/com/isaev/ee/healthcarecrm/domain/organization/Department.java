package com.isaev.ee.healthcarecrm.domain.organization;

import java.util.ArrayList;
import java.util.List;

import com.isaev.ee.healthcarecrm.domain.treatment.Subject;


/**
 * Clinic departments and branches.
 */
public class Department {

    private Long id;
    private String name;

    private List<Subject> subjects;


    public Department(String name) {
        this.name = name;
        this.subjects = new ArrayList<Subject>();
    }

    public Subject addNewSubject(String subjectName) {
        Subject subject = new Subject(subjectName);
        this.subjects.add(subject);
        return subject;
    }

}
