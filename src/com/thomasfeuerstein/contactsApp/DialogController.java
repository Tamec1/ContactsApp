package com.thomasfeuerstein.contactsApp;

import com.thomasfeuerstein.contactsApp.datamodel.Contact;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class DialogController {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private TextField notesField;

    public Contact processResults() {
        StringProperty firstName = new SimpleStringProperty(firstNameField.getText().trim());
        StringProperty lastName = new SimpleStringProperty(lastNameField.getText().trim());
        StringProperty phoneNumber = new SimpleStringProperty(phoneNumberField.getText().trim());
        StringProperty notes = new SimpleStringProperty(notesField.getText());
        return new Contact(firstName,lastName,phoneNumber,notes);
    }

    public void editContact(Contact contact) {
        firstNameField.setText(contact.getFirstName());
        lastNameField.setText(contact.getLastName());
        phoneNumberField.setText(contact.getPhoneNumber());
        notesField.setText(contact.getNotes());
    }

    public void updateContact(Contact contact) {
        contact.setFirstName(firstNameField.getText());
        contact.setLastName(lastNameField.getText());
        contact.setPhoneNumber(phoneNumberField.getText());
        contact.setNotes(notesField.getText());
    }
}
