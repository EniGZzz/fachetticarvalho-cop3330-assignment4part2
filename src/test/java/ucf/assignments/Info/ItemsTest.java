package ucf.assignments.Info;

import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class ItemsTest {

    @Test
    void description_exists_in_item_creation() throws ParseException
    {
        // given a new default item
        Items testItem = new Items();

        // when values are assigned
        String expected = testItem.getDescription();
        String actual = "Default Description";

        // then
        assertEquals(expected, actual);
    }

    @Test
    void due_date_exists_in_item_creation() throws ParseException
    {
        // given a new default item
        Items testItem = new Items();

        // when values are assigned
        String expected = testItem.getDueDate();
        String actual = "9999-12-31";

        // then
        assertEquals(expected, actual);
    }

    @Test
    void complete_state_exists_in_item_creation() throws ParseException {
        // given a new default item
        Items testItem = new Items();

        // when values are assigned
        boolean expected = testItem.isCompleteState();
        boolean actual = false;

        // then
        assertEquals(expected, actual);
    }

    @Test
    void item_name_exists_in_item_creation() throws ParseException {
        // given a new default item
        Items testItem = new Items();

        // when values are assigned
        String expected = testItem.getItemName();
        String actual = "Default Name";

        // then
        assertEquals(expected, actual);
    }
}