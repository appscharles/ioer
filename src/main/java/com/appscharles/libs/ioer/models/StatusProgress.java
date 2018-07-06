package com.appscharles.libs.ioer.models;

import javafx.beans.property.*;

/**
 * The type Status progress.
 */
public class StatusProgress {

    private DoubleProperty progress;

    private StringProperty status;

    /**
     * Instantiates a new Status progress.
     */
    public StatusProgress() {
        this.progress = new SimpleDoubleProperty(0.0);
        this.status = new SimpleStringProperty("");
    }

    /**
     * Gets progress.
     *
     * @return the progress
     */
    public Double getProgress() {
        return progress.get();
    }

    /**
     * Progress property double property.
     *
     * @return the double property
     */
    public DoubleProperty progressProperty() {
        return progress;
    }

    /**
     * Sets progress.
     *
     * @param progress the progress
     */
    public void setProgress(Double progress) {
        this.progress.set(progress);
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public String getStatus() {
        return status.get();
    }

    /**
     * Status property string property.
     *
     * @return the string property
     */
    public StringProperty statusProperty() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(String status) {
        this.status.set(status);
    }
}
