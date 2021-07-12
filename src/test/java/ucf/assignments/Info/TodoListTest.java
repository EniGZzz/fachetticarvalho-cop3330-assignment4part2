package ucf.assignments.Info;

import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class TodoListTest
{

    // tests requirement # 5
    @Test
    void deletes_single_item_properly() throws ParseException
    {
        // given
        TodoList givenList = new TodoList();
        givenList.itemsList.add(new Items("I will work!", "2019-10-03", "This is the Test!", true));
        givenList.itemsList.add(new Items("Hello", "9999-12-31", "random Item", true));

        // we delete the first inserted item
        givenList.currItemIndex = 0;
        givenList.deleteItem();

        // we set the to-be tested variables to their values
        int expectedSize = givenList.itemsList.size();
        int actualSize = 1;

        // then we assert them
        assertEquals(expectedSize, actualSize);
    }

    // tests requirement # 7
    @Test
    void edits_description_correctly() throws ParseException
    {
        // given
        TodoList givenList = new TodoList();
        givenList.itemsList.add(new Items("I will work!", "2019-10-03", "This is the Test!", true));
        givenList.itemsList.add(new Items("Hello", "9999-12-31", "random Item", true));

        // we edit the second inserted item
        givenList.currItemIndex = 1;
        givenList.editItemInList("It should be like this!","randomItem", "0001-01-01");

        // we set the to-be tested variables to their values
        String expectedDescription = givenList.itemsList.get(1).getDescription();
        String actualDescription = "randomItem";

        // then assert them
        assertEquals(expectedDescription, actualDescription);
    }

    // tests requirement # 8
    @Test
    void edits_due_date_correctly() throws ParseException
    {
        // given
        TodoList givenList = new TodoList();
        givenList.itemsList.add(new Items("I will work!", "2019-10-03", "This is the Test!", true));
        givenList.itemsList.add(new Items("Hello", "9999-12-31", "random Item", true));

        // we edit the second inserted item
        givenList.currItemIndex = 1;
        givenList.editItemInList("It should be like this!","randomItem", "0001-01-01");

        // we set the to-be tested variables to their values
        String expectedDueDate = givenList.itemsList.get(1).getDueDate();
        String actualDueDate = "0001-01-01";

        // then assert them
        assertEquals(expectedDueDate, actualDueDate);
    }


    // tests requirement # 4
    @Test
    void adds_item_properly() throws ParseException
    {
        // given
        TodoList givenList = new TodoList();

        // adds an item to the list
        givenList.addItemToList("Hello", "random", "9999-12-31");

        // we set the to-be tested variables to their values
        String expectedDueDate = givenList.itemsList.get(0).getDueDate();
        String actualDueDate = "9999-12-31";

        // then assert them
        assertEquals(expectedDueDate, actualDueDate);
    }

    // tests requirement # 6
    @Test
    void deleteAllItems() throws ParseException
    {
        // given
        TodoList givenList = new TodoList();
        givenList.itemsList.add(new Items("I will work!", "2019-10-03", "This is the Test!", true));
        givenList.itemsList.add(new Items("Hello", "9999-12-31", "random Item", true));

        // deletes all items
        givenList.deleteAllItems();

        // we set the to-be tested variables to their values
        boolean expected = givenList.itemsList.isEmpty();
        boolean actual = true;

        // then assert them
        assertEquals(expected, actual);
    }

    // tests requirement # 9
    @Test
    void toggle_item_status_correctly() throws ParseException
    {
        // given
        TodoList givenList = new TodoList();
        givenList.itemsList.add(new Items("I will work!", "2019-10-03", "This is the Test!", true));

        // set's the added item as false instead of true
        givenList.itemsList.get(0).setCompleteState(false);

        // we set the to-be tested variables to their values
        boolean expected = givenList.itemsList.get(0).isCompleteState();
        boolean actual = false;

        // then assert them
        assertEquals(expected, actual);
    }

    // tests requirement # 1
    @Test
    void list_can_have_100_items() throws ParseException
    {
        // given a newly empty created list
        TodoList givenList = new TodoList();

        // adds 101 items to the list
        for (int i = 0; i < 101; i++)
        {
            givenList.itemsList.add(new Items());
        }

        // we set the to-be tested variables to their values
        int expected = givenList.itemsList.size();
        int actual = 101;

        // then assert them
        assertEquals(expected, actual);
    }

    // tests requirement # 12
    @Test
    void display_only_complete_items_correctly() throws ParseException
    {
        // given a newly empty created list
        TodoList givenList = new TodoList();

        // adds items to it
        givenList.itemsList.add(new Items(true));
        givenList.itemsList.add(new Items(true));
        givenList.itemsList.add(new Items(true));
        givenList.itemsList.add(new Items(false));

        // make the call to only show complete items
        givenList.itemsList = givenList.showCompleteItems();

        // we set the to-be tested variables to their values
        int expected = givenList.itemsList.size();
        int actual = 3;

        // then assert them
        assertEquals(expected, actual);
    }


    // tests requirement # 11
    @Test
    void display_only_incomplete_items_correctly() throws ParseException
    {
        // given a newly empty created list
        TodoList givenList = new TodoList();

        // adds items to it
        givenList.itemsList.add(new Items(true));
        givenList.itemsList.add(new Items(true));
        givenList.itemsList.add(new Items(true));
        givenList.itemsList.add(new Items(false));

        // make the call to only show incomplete items
        givenList.itemsList = givenList.showIncompleteItems();

        // we set the to-be tested variables to their values
        int expected = givenList.itemsList.size();
        int actual = 1;

        // then assert them
        assertEquals(expected, actual);
    }

    // tests requirement # 10
    @Test
    void display_items_in_default_order_correctly() throws ParseException
    {
        // given a newly empty created list
        TodoList givenList = new TodoList();

        // adds items to it
        givenList.itemsList.add(new Items(true));
        givenList.itemsList.add(new Items(true));
        givenList.itemsList.add(new Items(true));
        givenList.itemsList.add(new Items(false));

        // makes it default
        givenList.itemsList = givenList.showDefault();

        // we set the to-be tested variables to their values
        int expected = givenList.itemsList.size();
        int actual = 4;

        // then assert them
        assertEquals(expected, actual);
    }
}