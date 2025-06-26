package com.example.collegeadmission.helper;

public enum Status {
    PENDING("Pending"),
    ACCEPTED("Accepted"),
    REJECTED("Rejected");
    private final String status;
    Status(String status) {
        this.status = status;
    }

}
