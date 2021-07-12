/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Pedro Fachetti Carvalho
 */

package ucf.assignments;

import ucf.assignments.Info.Items;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileCommunicator
{
    private final String filePath;

    // constructor
    public FileCommunicator(String filePath)
    {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    // COMPLETE
    public void saveInfoToFile(List<Items> fileInfo) throws IOException
    {
        // gets the file from path
        File databaseFile = new File(filePath);

        // opens a file writer
        FileWriter toEdit = new FileWriter(databaseFile);

        // writes info to file
        for (Items eachLine : fileInfo)
        {
            toEdit.write(eachLine.getItemName() + ",");
            toEdit.write(eachLine.getDescription() + ",");
            toEdit.write(eachLine.getDueDate() + ",");
            toEdit.write(Boolean.toString(eachLine.isCompleteState()));
            toEdit.write("\n");
        }

        // close file
        toEdit.close();
    }

    // COMPLETE
    public List<Items> loadInfoFromFile() throws FileNotFoundException, ParseException
    {
        // gets the file from path
        File file = new File(filePath);

        // create a scanner to read the file
        Scanner sc = new Scanner(file);

        List<Items> fileStore = new ArrayList<>();
        String[] namesFromInput;

        // gets info from file and stores in a list of elements
        while (sc.hasNextLine())
        {
            // stores info from file into the array
            namesFromInput = sc.nextLine().split(","); // CSV parser for file input
            fileStore.add(inputToItems(namesFromInput));
        }

        return fileStore;
    }

    // COMPLETE - converts input to a new Item
    private Items inputToItems(String[] namesFromInput) throws ParseException
    {
        final int ITEM_NAME_INDEX = 0;
        final int DESCRIPTION_INDEX = 1;
        final int DUE_DATE_INDEX = 2;
        final int COMPLETE_STATUS_INDEX = 3;

        // gets the info and store in Strings
        String itemName = namesFromInput[ITEM_NAME_INDEX];
        String description = namesFromInput[DESCRIPTION_INDEX];
        String dueDate = namesFromInput[DUE_DATE_INDEX];
        String completeStatus = namesFromInput[COMPLETE_STATUS_INDEX];

        // create new element the Strings created
        return new Items(description, dueDate, itemName, Boolean.parseBoolean(completeStatus));
    }
}
