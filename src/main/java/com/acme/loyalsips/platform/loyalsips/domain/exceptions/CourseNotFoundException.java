package com.acme.loyalsips.platform.loyalsips.domain.exceptions;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(Long courseId) {
        super("Course with id " + courseId + " not found");
    }
}
