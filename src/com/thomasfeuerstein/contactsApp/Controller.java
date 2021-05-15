package com.thomasfeuerstein.contactsApp;

import com.thomasfeuerstein.contactsApp.datamodel.Contact;
import com.thomasfeuerstein.contactsApp.datamodel.ContactData;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import java.io.IOException;
import java.util.Optional;

public class Controller {
    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private TableView<Contact> contactsTable;

    private ContactData data;

    public void initialize() {
        data = new ContactData();
        data.loadContacts();
        contactsTable.setItems(data.getContacts());
    }

    @FXML
    public void handleAddNewContact() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Add new contact");
        dialog.setHeaderText("Fill in information and submit to create a new contact");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("newContactDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            DialogController controller = fxmlLoader.getController();
            Contact newContact = controller.processResults();
            data.addContact(newContact);
            data.saveContacts();
        }
    }

    @FXML
    public void handleEditContact() {
        Contact contactToEdit = contactsTable.getSelectionModel().getSelectedItem();
        if (contactToEdit == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Selection Error");
            alert.setHeaderText("No contact to edit selected");
            alert.showAndWait();
            return;
        }

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Edit selected contact");
        dialog.setHeaderText("Fill fields you want to edit and confirm to change contact");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("editContactDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        DialogController controller = fxmlLoader.getController();
        controller.editContact(contactToEdit);

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            controller.updateContact(contactToEdit);
            data.saveContacts();
        }
    }

    @FXML
    public void handleDeleteContact() {
        Contact contactToDel = contactsTable.getSelectionModel().getSelectedItem();
        if (contactToDel == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Selection Error");
            alert.setHeaderText("No contact to delete selected");
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Contact");
        alert.setHeaderText("Delete contact: " + contactToDel.getFirstName()
                                + " " + contactToDel.getLastName());
        alert.setContentText("Are you sure? OK to confirm CANCEL to back out");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            data.deleteContact(contactToDel);
            data.saveContacts();
        }
    }

    @FXML
    public void closeApplication() {
        Platform.exit();
    }
}
