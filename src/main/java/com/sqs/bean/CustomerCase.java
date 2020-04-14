package com.sqs.bean;

public class CustomerCase {
    private String caseId;
    private String name;

    public CustomerCase(String caseId, String name) {
        this.caseId = caseId;
        this.name = name;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
