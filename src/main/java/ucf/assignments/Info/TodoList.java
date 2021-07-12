/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Pedro Fachetti Carvalho
 */

package ucf.assignments.Info;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class TodoList
{
    // list of Items
    public List<Items> itemsList;

    // holds the index of the curr Item that is being looked for (used for deleting and editing the list)
    public int currItemIndex;

    public TodoList()
    {
        this.itemsList = new ArrayList<>();
        this.currItemIndex = 0;
    }

    // COMPLETE
    private Items createItem(String itemName, String itemDescription, String itemDueDate) throws ParseException {
        return new Items(itemDescription, itemDueDate, itemName);
    }

    // COMPLETE
    public void deleteItem()
    {
        this.itemsList.remove(this.currItemIndex);
    }

    // COMPLETE
    public void editItemInList(String itemName, String itemDescription, String itemDueDate) throws ParseException {
        // sets the old's Item info to the new info
        this.itemsList.get(this.currItemIndex).setItemName(itemName);
        this.itemsList.get(this.currItemIndex).setDescription(itemDescription);
        this.itemsList.get(this.currItemIndex).setDueDate(itemDueDate);
    }

    // COMPLETE
    public void addItemToList(String itemName, String itemDescription, String itemDueDate) throws ParseException
    {
        // creates new Item
        Items newItem = createItem(itemName, itemDescription, itemDueDate);

        // add to the list
        this.itemsList.add(newItem);
    }

    // COMPLETE
    public void deleteAllItems()
    {
        this.itemsList.clear();
    }

    // changes the display to show only incomplete Items
    public List<Items> showIncompleteItems()
    {
        List<Items> retList = new ArrayList<>();

        for (Items each : this.itemsList)
        {
            if (each.isCompleteState() == false)
            {
                retList.add(each);
            }
        }

        return retList;
    }

    // changes the display to show only complete Items
    public List<Items> showCompleteItems()
    {
        List<Items> retList = new ArrayList<>();

        for (Items each : this.itemsList)
        {
            if (each.isCompleteState() == true)
            {
                retList.add(each);
            }
        }

        return retList;
    }

    // changes the display to show the original order in which the list was inputted
    public List<Items> showDefault()
    {
        return this.itemsList;
    }

    @Override
    public String toString() {
        return "TodoList{" +
                "itemsList=" + itemsList +
                '}';
    }
}
