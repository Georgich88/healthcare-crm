package com.isaev.ee.healthcarecrm.domain.treatment;

/**
 * Prescribed treatment types.
 */
public class Treatment {

    private Long id;

    private Subject subject;
    private String description;
    private String prescription;

    public Treatment(Subject subject, String description) {
        this.subject = subject;
        this.description = description;
    }


}
