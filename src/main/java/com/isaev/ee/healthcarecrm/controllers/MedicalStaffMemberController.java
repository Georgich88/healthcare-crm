package com.isaev.ee.healthcarecrm.controllers;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.isaev.ee.healthcarecrm.domain.people.MedicalStaffMember;
import com.isaev.ee.healthcarecrm.services.MedicalStaffMemberService;


@Controller
public class MedicalStaffMemberController {

    private static final String MEDICAL_STAFF_MEMBER_FORM_RESOURSE_NAME = "medical-staff-member";
    private static final String MEDICAL_STAFF_MEMBERS_LIST_FORM_RESOURSE_NAME = "medical-staff-members";

    @Autowired
    private MedicalStaffMemberService teacherService;

    @GetMapping("/medical-staff-members")
    public String getAllMedicalStaffMembers(Model model, Principal principal) {

        List<MedicalStaffMember> medicalStaffMembers = teacherService.findAll();

        model.addAttribute("members", medicalStaffMembers);
        model.addAttribute("activeAll", true);

        return MEDICAL_STAFF_MEMBERS_LIST_FORM_RESOURSE_NAME;
    }

    @GetMapping(value = "/medical-staff-members/new")
    public String showNewMedicalStaffMemberForm(Model model, Principal principal) {
    	MedicalStaffMember teacher = new MedicalStaffMember();
        model.addAttribute("teacher", teacher);
        return MEDICAL_STAFF_MEMBER_FORM_RESOURSE_NAME;
    }

    @GetMapping(value = "/medical-staff-members/{id}")
    public String showUpdateForm(@PathVariable("id") UUID id, Model model) {
        MedicalStaffMember teacher = teacherService.findById(id);
        model.addAttribute("teacher", teacher);
        return MEDICAL_STAFF_MEMBER_FORM_RESOURSE_NAME;
    }

    @PostMapping(value = "/medical-staff-members/{id}/save")
    public String saveMedicalStaffMember(@ModelAttribute("teacher") MedicalStaffMember teacher) {
        teacherService.save(teacher);
        return "redirect:/" + MEDICAL_STAFF_MEMBERS_LIST_FORM_RESOURSE_NAME;
    }

}
