package domain.organization;

import java.util.List;

/**
 * Clinics and organizations.
 */
public class Clinic {

    private Long id;
    private String name;

    private List<Department> departments;

    public Department addNewDepartment(String facultyName) {
        Department faculty = new Department(facultyName);
        this.departments.add(faculty);
        return faculty;
    }

}
