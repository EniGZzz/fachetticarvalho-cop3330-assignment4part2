package ucf.assignments;

import org.junit.jupiter.api.Test;
import ucf.assignments.Info.Items;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class FileCommunicatorTest
{
    // tests requirement # 13
    @Test
    void saves_info_correctly_to_file() throws ParseException, IOException
    {
        // given
        FileCommunicator testObj = new FileCommunicator("storage files/testFile.txt");
        Items usedItem = new Items("I will work!", "2019-10-03", "This is the Test!", true);

        // makes the calls to save given info to test file
        List<Items> inputList = new ArrayList<>();
        inputList.add(usedItem); // adds to the list
        testObj.saveInfoToFile(inputList); // saves to test file

        // calls for loading info from file to the expected list, which will be used to obtain expected values
        List<Items> expectedList = testObj.loadInfoFromFile(); // loads from the test file to another list


        // when we get description and due date for testing
        String expectedDescription = expectedList.get(0).getDescription();
        String actualDescription = "I will work!";

        String expectedDueDate = expectedList.get(0).getDueDate();
        String actualDueDate = "2019-10-03";


        // then, we assert if they are equal
        assertEquals(expectedDescription, actualDescription);
        assertEquals(expectedDueDate, actualDueDate);
    }

    // tests requirement # 14
    @Test
    void loads_properly_to_file() throws ParseException, IOException
    {
        // given
        FileCommunicator testObj = new FileCommunicator("storage files/testFile.txt");
        Items usedItem = new Items("I will work!", "2019-10-03", "This is the Test!", true);

        // makes the calls to save given info to test file
        List<Items> inputList = new ArrayList<>();
        inputList.add(usedItem); // adds to the list
        testObj.saveInfoToFile(inputList); // saves to test file

        // calls for loading info from file to the expected list, which will be used to obtain expected values
        List<Items> expectedList = testObj.loadInfoFromFile(); // loads from the test file to another list


        // when we get description and due date for testing
        String expectedDescription = expectedList.get(0).getDescription();
        String actualDescription = "I will work!";

        String expectedDueDate = expectedList.get(0).getDueDate();
        String actualDueDate = "2019-10-03";

        // then, we assert if they are equal
        assertEquals(expectedDescription, actualDescription);
        assertEquals(expectedDueDate, actualDueDate);
    }
}