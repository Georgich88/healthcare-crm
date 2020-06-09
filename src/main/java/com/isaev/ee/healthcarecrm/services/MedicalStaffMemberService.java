package com.isaev.ee.healthcarecrm.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;


import com.isaev.ee.healthcarecrm.dao.people.MedicalStaffMemberDao;
import com.isaev.ee.healthcarecrm.domain.people.MedicalStaffMember;

@Service
public class MedicalStaffMemberService {

    @Autowired
    private MedicalStaffMemberDao medicalStaffMemberDao;

    public List<MedicalStaffMember> findAll() {
        List<MedicalStaffMember> teachers = (List<MedicalStaffMember>) medicalStaffMemberDao.findAll();
        return teachers;
    }

    public void save(MedicalStaffMember teacher) {
    	medicalStaffMemberDao.save(teacher);
    }

    public MedicalStaffMember findById(@NonNull UUID id) {
        return medicalStaffMemberDao.findById(id).orElseThrow();
    }

}
