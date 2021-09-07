/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Pedro Fachetti Carvalho
 */

package ucf.assignments.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import ucf.assignments.Info.Items;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable
{
    // mainScene ID's - START

    @FXML private ComboBox<String> sortChoiceBox;
    @FXML private ListView<Items> listDisplay;      // list that will display the user's defined to-do lists
    @FXML private Label appTitle;
    @FXML private Button addItemBtn;
    @FXML private Button editItemBtn;
    @FXML private Button deleteItemBtn;
    @FXML private Button markItemCompleteBtn;
    @FXML private Button clearListBtn;

    // mainScene ID's - END



    // createItemScene ID's - START

    @FXML private TextField dueDateField;
    @FXML private TextField itemNameField;
    @FXML private TextArea descriptionBox;
    @FXML private Button finishCreatingBtn;
    @FXML private Label errorLabelDescription;
    @FXML private Label errorLabelDueDate;
    @FXML private Label errorItemName;

    // createItemScene ID's - END



    // editItemScene ID's - START

    @FXML TextField editItemNameField;
    @FXML TextField editDueDateField;
    @FXML TextArea editDescArea;
    @FXML Button finishEditingBtn;
    @FXML private Label errorLabelDescriptionE;
    @FXML private Label errorLabelDueDateE;
    @FXML private Label errorItemNameE;

    // editItemScene ID's - END



    // start of mainScene methods


    // wrapper method
    public void addItemButtonClicked(ActionEvent event) throws IOException
    {
        // scene change call
        switchToCreateItemScene(event);
    }

    // wrapper method
    public void editItemBtnClicked(ActionEvent event) throws IOException
    {
        // checks if user uses the button properly
        if (!manageErrorsForButtons())
            return;

        // scene change call
        switchToEditItemScene(event);
    }

    public void deleteBtnClicked(ActionEvent event)
    {
        // checks if user uses the button properly
        if (!manageErrorsForButtons())
            return;

        // delete the items from both lists
        App.listInfo.deleteItem();
        listDisplay.getItems().remove(App.listInfo.currItemIndex);

        // creates a dialog box informing the user the item was deleted
        Dialog<ButtonType> d = new Alert(Alert.AlertType.WARNING);
        d.setContentText("The item was deleted");
        d.show();

        // set the index to a invalid value to prevent future uses issues
        App.listInfo.currItemIndex = -1;
    }

    public void clearButtonClicker(ActionEvent event)
    {
        // delete the items from both lists
        App.listInfo.deleteAllItems();
        listDisplay.getItems().clear();

        // creates a dialog box informing the user the item was deleted
        Dialog<ButtonType> d = new Alert(Alert.AlertType.WARNING);
        d.setContentText("All items were deleted");
        d.show();
    }

    public void markCompleteBtnClicked(ActionEvent event)
    {
        // checks if user uses the button properly
        if (!manageErrorsForButtons())
            return;

        // change the Item's complete state
        boolean inverseVal = !App.listInfo.itemsList.get(App.listInfo.currItemIndex).isCompleteState();
        App.listInfo.itemsList.get(App.listInfo.currItemIndex).setCompleteState(inverseVal);

        // creates a dialog box informing the user of the Item's changed complete state
        Dialog<ButtonType> d = new Alert(Alert.AlertType.WARNING);
        d.setContentText("The item \"" + App.listInfo.itemsList.get(App.listInfo.currItemIndex) + "\" has been marked as \"" + App.listInfo.itemsList.get(App.listInfo.currItemIndex).completeStateAsPhrased() + "\"");
        d.show();
    }

    // manage the user errors regarding the edit button
    private boolean manageErrorsForButtons()
    {
        // if the ListView is not empty, check for the item
        if (listDisplay != null)
        {
            Items currItem = listDisplay.getSelectionModel().getSelectedItem();

            // if no item was selected, show an error
            if (currItem == null)
            {
                // creates the dialog box as an error alert
                Dialog<ButtonType> d = new Alert(Alert.AlertType.ERROR);;

                d.setContentText("Choose an Item from the list");
                d.show();
                return false;
            }

            // set the index to the one found from the list
            App.listInfo.currItemIndex = currItem.findItem(App.listInfo.itemsList, currItem);

            // checks if something went wrong
            if (App.listInfo.currItemIndex < 0)
            {
                return false;
            }
        }

        // if the list is empty, show error
        else
        {
            // creates the dialog box as an error alert
            Dialog<ButtonType> d = new Alert(Alert.AlertType.ERROR);;

            d.setContentText("The list is empty");
            d.show();
            return false;
        }

        return true;
    }

    private void switchToCreateItemScene(ActionEvent event) throws IOException
    {
        // scene change -> create new item scene
        changeScene(event, "CreateItemScene.fxml", "Create Item - Tchu Tchu List");
    }

    private void switchToEditItemScene(ActionEvent event) throws IOException
    {
        // scene change -> edit existing item scene
        changeScene(event, "editItemScene.fxml", "Edit List - Tchu Tchu List");
    }


    // end of mainScene methods





    // start of createItemScene methods


    // wrapper method
    public void finishCreating(ActionEvent event) throws IOException, ParseException
    {
        // make labels blank
        errorItemName.setText("");
        errorLabelDescription.setText("");
        errorLabelDueDate.setText("");

        // get input as Strings from text fields/boxes
        String descriptionInput = descriptionBox.getText().trim();
        String dueDateInput = dueDateField.getText().trim();
        String itemNameInput = itemNameField.getText().trim();

        // if the input is bad, output label will be shown and return nothing
        if (!itemInputCheck(descriptionInput, dueDateInput, itemNameInput))
            return;

        // creates new Item and add to the list
        App.listInfo.addItemToList(itemNameInput, descriptionInput, dueDateInput);

        // switches to main
        switchToMainScene(event);
    }

    // method takes care of when enter key is pressed (when pressing it on the create scene)
    public void enterKeyPressedOnCreateItemScene(ActionEvent givenEvent)
    {
        dueDateField.setOnKeyReleased(defEvent ->
        {
            if (defEvent.getCode().equals(KeyCode.ENTER))
            {
                try
                {
                    finishCreating(givenEvent);
                }

                catch (IOException | ParseException e)
                {
                    e.printStackTrace();
                }
            }
        });
    }


    // end of createScene methods





    // editItemScene methods - START


    public void finishEditing(ActionEvent event) throws IOException, ParseException {
        // make labels blank
        errorItemNameE.setText("");
        errorLabelDescriptionE.setText("");
        errorLabelDueDateE.setText("");

        // get input as Strings from text fields/boxes
        String descriptionInput = editDescArea.getText().trim();
        String dueDateInput = editDueDateField.getText().trim();
        String itemNameInput = editItemNameField.getText().trim();

        // if the input is bad, output label will be shown and return nothing
        if (!itemInputCheck(descriptionInput, dueDateInput, itemNameInput))
            return;

        // edits the item to the list
        App.listInfo.editItemInList(itemNameInput, descriptionInput, dueDateInput);

        // set the index to a invalid value to prevent future uses issues
        App.listInfo.currItemIndex = -1;

        // switches to main
        switchToMainScene(event);
    }


    // editItemScene methods - END






    // general methods - START


    // checks the input from new item
    private boolean itemInputCheck(String descriptionInput, String dueDateInput, String itemNameInput)
    {
        // if the item name field is empty, do nothing and show error
        if (itemNameInput.isEmpty())
        {
            errorItemName.setText("Enter a title name");
            return false;
        }

        // if the description field is empty, do nothing and show error
        if (descriptionInput.isEmpty())
        {
            errorLabelDescription.setText("Enter a description");
            return false;
        }

        else if (!Items.checksDescription(descriptionInput))
        {
            errorLabelDescription.setText("Enter a valid description");
            return false;
        }

        // if the due date field is empty, do nothing and show error
        if (dueDateInput.isEmpty())
        {
            errorLabelDueDate.setText("Enter a due date");
            return false;
        }

        else if (!Items.checksDate(dueDateInput))
        {
            errorLabelDueDate.setText("Enter a valid date");
            return false;
        }

        return true;
    }

    // method to change scenes (used multiple times)
    private void changeScene(ActionEvent event, String fileName, String sceneTitle) throws IOException
    {
        // set the root to the filename
        Parent root = FXMLLoader.load(getClass().getResource(fileName));

        // scene changing variables
        Stage currStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // set appropriate title and show
        currStage.setTitle(sceneTitle);
        currStage.setScene(new Scene(root));
        currStage.show();
    }

    // shows information of clicked item
    private void handleListItemsClicked()
    {
        listDisplay.setOnMouseClicked(mouseEvent ->
        {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY))
            {
                if (mouseEvent.getClickCount() == 2)
                {
                    // get the Item from the ListView
                    Items actualItem = listDisplay.getSelectionModel().getSelectedItem();

                    // creates the dialog box as an alert
                    Dialog<ButtonType> d = new Alert(Alert.AlertType.INFORMATION);

                    d.setContentText(Items.infoText(actualItem));

                    d.show();
                }
            }
        });
    }

    // makes the necessary adjustments to go back to main scene
    private void switchToMainScene(ActionEvent event) throws IOException
    {
        // scene change
        changeScene(event, "MainScene.fxml", "Menu - Tchu Tchu List");
    }

    // changes the display to show only incomplete Items
    private void showIncompleteItems()
    {
        List<Items> retList = new ArrayList<>();

        for (Items each : App.listInfo.itemsList)
        {
            if (each.isCompleteState() == false)
            {
                retList.add(each);
            }
        }

        listDisplay.getItems().setAll(retList);
    }

    // changes the display to show only complete Items
    private void showCompleteItems()
    {
        List<Items> retList = new ArrayList<>();

        for (Items each : App.listInfo.itemsList)
        {
            if (each.isCompleteState() == true)
            {
                retList.add(each);
            }
        }

        listDisplay.getItems().setAll(retList);
    }

    // changes the display to show the original order in which the list was inputted
    private void showDefault()
    {
        listDisplay.getItems().setAll(App.listInfo.itemsList);
    }


    // changes the display to sort Items by due date
    private void sortByDueDate()
    {
        List<Items> sortedList = new ArrayList<>(App.listInfo.itemsList);

        sortedList.sort(Comparator.comparing(Items::getDueDate));
        listDisplay.getItems().setAll(sortedList);

    }



    // general methods - END



    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        // ensures that no NullPointerException will occur when setting default and other values to list
        if (listDisplay != null)
        {
            listDisplay.getItems().setAll(App.listInfo.itemsList);
        }

        // ensures that no NullPointerException will occur when setting multiple selection on list view
        if (listDisplay != null)
        {
            handleListItemsClicked();

            // set options for combo box
            sortChoiceBox.getItems().addAll("Default", "Show Complete Items", "Show Incomplete Items", "Sort by Due Date");

            // actions for sortBox choices
            sortChoiceBox.setOnAction((event) ->
            {
                int selectedIndex = sortChoiceBox.getSelectionModel().getSelectedIndex(); // use index

                // evaluates the user choice to make the appropriate call
                switch (selectedIndex)
                {
                    case 0 -> showDefault();
                    case 1 -> showCompleteItems();
                    case 2 -> showIncompleteItems();
                    case 3 -> sortByDueDate();
                }
            });
        }
    }
}





