/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Pedro Fachetti Carvalho
 */

package ucf.assignments.Info;

import ucf.assignments.Controller.App;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Items
{
    // basic item characteristics
    private String itemName;
    private String description;
    private Date dueDate;         // format : YYYY-MM-DD
    private boolean completeState;

    // constant for Item identification
    private final String ItemCode;

    // constructors
    public Items(String description, String dueDate, String itemName) throws ParseException // exception will probably never trigger due to checking early
    {
        this.itemName = itemName;
        this.description = description;
        this.dueDate = new SimpleDateFormat("yyyy-MM-dd").parse(dueDate);
        this.completeState = false;
        this.ItemCode = Integer.toHexString(hashCode());
    }

    public Items(String description, String dueDate, String itemName, boolean completeStatus) throws ParseException // exception will probably never trigger due to checking early
    {
        this.itemName = itemName;
        this.description = description;
        this.dueDate = new SimpleDateFormat("yyyy-MM-dd").parse(dueDate);
        this.completeState = completeStatus;
        this.ItemCode = Integer.toHexString(hashCode());
    }

    public Items() throws ParseException
    {
        this.itemName = "Default Name";
        this.description = "Default Description";
        this.dueDate = new SimpleDateFormat("yyyy-MM-dd").parse("9999-12-31");
        this.completeState = false;
        this.ItemCode = Integer.toHexString(hashCode());
    }

    public Items(boolean completeState) throws ParseException
    {
        this.itemName = "Default Name";
        this.description = "Default Description";
        this.dueDate = new SimpleDateFormat("yyyy-MM-dd").parse("9999-12-31");
        this.completeState = completeState;
        this.ItemCode = Integer.toHexString(hashCode());
    }

    // accessor methods
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDueDate() {
        return String.format("%tY-%tm-%td", this.dueDate, this.dueDate, this.dueDate);
    }
    public void setDueDate(String dueDate) throws ParseException
    {
        this.dueDate = new SimpleDateFormat("yyyy-MM-dd").parse(dueDate);
    }
    public boolean isCompleteState() {
        return completeState;
    }
    public void setCompleteState(boolean completeState) {
        this.completeState = completeState;
    }
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public String completeStateAsPhrased()
    {
        if (this.completeState) // if true
        {
            return "Complete";
        }

        return "Not Complete";
    }

    @Override
    public String toString() {
        return itemName;
    }


    // methods

    public int findItem(List<Items> itemsList, Items givenItem)
    {
        if (itemsList == null)
        {
            return -1;
        }

        int listLength =  itemsList.size();

        for (int i = 0; i < listLength; i++)
        {
            if (itemsList.get(i).ItemCode.equals(givenItem.ItemCode))
            {
                return i;
            }

            // debug
            System.out.println("");
        }

        return -1;
    }

    // checks if the description follows the parameters - COMPLETE
    public static boolean checksDescription(String description)
    {
        final int MAX_DESC_LEN = 256;
        final int MIN_DESC_LEN = 1;

        int strLength = description.length();

        return (strLength >= MIN_DESC_LEN) && (strLength <= MAX_DESC_LEN); // returns true if conditions are met
    }

    // makes sure the date is in the correct format (YYYY-MM-DD) - COMPLETE
    public static boolean checksDate(String date)
    {
        // constants
        final int YEAR_INDEX = 0;
        final int MONTH_INDEX = 1;
        final int MAX_MONTH = 12;
        final int MAX_DAY = 31;
        final int MIN_GENERAL = 0;

        // make split operation and calculate the length of the array
        String[] getFromDate = date.split("-");
        int length = getFromDate.length;

        // checks if there's 3 elements (year, month, day). If not, return false
        if (length != 3)
        {
            return false;
        }

        // loops through the array to check for formatting
        for (int i = 0; i < length; i++)
        {
            // sets variables values from current index
            String currStr = getFromDate[i];
            int currStrLen = currStr.length();
            int currStrAsInt = Integer.parseInt(currStr);

            // if input is not integer, return false
            if (!App.checksIfInteger(currStr))
            {
                return false;
            }

            // if year format is wrong, return false
            if ( (i == YEAR_INDEX) )
            {
                if ( (currStrLen != 4) || (currStrAsInt == MIN_GENERAL) )
                {
                    return false;
                }
            }

            // if month format is wrong, return false
            else if ( (i == MONTH_INDEX) )
            {
                if  ( (currStrLen != 2) || !(currStrAsInt > MIN_GENERAL && currStrAsInt <= MAX_MONTH) ) // NOT between 1 - 12
                {
                    return false;
                }
            }

            // if day format is wrong, return false
            else
            {
                if ( (currStrLen != 2) || !(currStrAsInt > MIN_GENERAL && currStrAsInt <= MAX_DAY) ) // NOT between 1 - 31
                {
                    return false;
                }
            }
        }

        // otherwise, return true
        return true;
    }

    // describes an Item's info in a specific way
    public static String infoText(Items item)
    {
        return (
                "Item Info:" + "\n" +
                        "-----------" + "\n\n" +
                        "Item Name: " + item.getItemName() + "\n\n" +
                        "Item Description: " + item.getDescription() + "\n\n" +
                        "Item Due Date: " + item.getDueDate() + "\n\n" +
                        "Item State: " + item.completeStateAsPhrased()
        );
    }
}
