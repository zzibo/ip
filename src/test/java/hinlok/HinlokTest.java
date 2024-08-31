package hinlok;  //same package as the class being tested

import hinlok.exceptions.HinlokException;
import hinlok.tasks.TaskList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HinlokTest {
    @Test
    public void testTodoCreation() throws HinlokException {
        TaskList tasks = new TaskList(new ArrayList<>());
        tasks.addTodo("homework");
        assertEquals(tasks.getTaskByIndex(0).toString(), "[T][ ] homework");
    }

    @Test
    public void testDeadlineCreation() throws HinlokException {
        TaskList tasks = new TaskList(new ArrayList<>());
        tasks.addDeadline("homework /by 2022-10-11");
        System.out.println(tasks.getTaskByIndex(0).toString());
        assertEquals(tasks.getTaskByIndex(0).toString(), "[D][ ] homework (by: Oct 11 2022)");
    }
}