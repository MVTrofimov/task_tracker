package com.example.task_tracker.entity;

public enum TaskStatus {

    TODO("TODO"),
    IN_PROGRESS("IN_PROGRESS"),
    DONE("DONE");

    public final String label;

    private TaskStatus(String label) {
        this.label = label;
    }

}
