package com.thomasfeuerstein.contactsApp.datamodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Contact {
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty phoneNumber;
    private StringProperty notes;

    public Contact() {
    }

    public Contact(StringProperty firstName, StringProperty lastName,
                   StringProperty phoneNumber, StringProperty notes) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.notes = notes;
    }

    public void setFirstName(String value) {
        firstNameProperty().set(value);
    }

    public String getFirstName() {
        return firstNameProperty().get();
    }

    public StringProperty firstNameProperty() {
        if (firstName == null)
            firstName = new SimpleStringProperty(this, "firstName");
        return firstName;
    }

    public void setLastName(String value) {
        lastNameProperty().set(value);
    }

    public String getLastName() {
        return lastNameProperty().get();
    }

    public StringProperty lastNameProperty() {
        if (lastName == null)
            lastName = new SimpleStringProperty(this, "lastName");
        return lastName;
    }

    public void setPhoneNumber(String value) {
        phoneNumberProperty().set(value);
    }

    public String getPhoneNumber() {
        return phoneNumberProperty().get();
    }

    public StringProperty phoneNumberProperty() {
        if (phoneNumber == null)
            phoneNumber = new SimpleStringProperty(this, "phoneNumber");
        return phoneNumber;
    }

    public void setNotes(String value) {
        noteProperty().set(value);
    }

    public String getNotes() {
        return noteProperty().get();
    }

    public StringProperty noteProperty() {
        if (notes == null)
            notes = new SimpleStringProperty(this, "notes");
        return notes;
    }

}
