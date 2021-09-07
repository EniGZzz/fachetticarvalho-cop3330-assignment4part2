/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Pedro Fachetti Carvalho
 */

package ucf.assignments.Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ucf.assignments.FileCommunicator;
import ucf.assignments.Info.TodoList;

import java.io.IOException;
import java.text.ParseException;

public class App extends Application
{
    // The only To-do list related object, which will store all the data from the same session (used in the Controller class mainly)
    public static TodoList listInfo = new TodoList();

    private static final FileCommunicator fileManipulator = new FileCommunicator("storage_files/itemsInfo.txt");

    @Override
    public void start(Stage primaryStage)
    {
        try
        {
            // Read file fxml and draw interface
            Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));

            primaryStage.setTitle("Menu - Tchu Tchu List");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }

        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, ParseException
    {
        // use here to load files
        listInfo.itemsList = fileManipulator.loadInfoFromFile();

        launch(args);

        // use here to save files
        fileManipulator.saveInfoToFile(listInfo.itemsList);
    }

    public static boolean checksIfInteger(String checkTheInput)
    {
        try
        {
            Integer.parseInt(checkTheInput);
        }

        catch (NumberFormatException e)
        {
            e.printStackTrace();
            return false;
        }

        return true; // returns true if input is an integer
    }
}