package todoList;

import com.todoList.Command.Command;
import com.todoList.Entities.TaskList;
import com.todoList.Utils.CommandParser.CommandParser;
import com.todoList.Utils.CorrectParams;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CommandParserTest {
  private static List<TaskList> listOfTaskLists = new ArrayList<>();

  @Test (expected = IllegalArgumentException.class)
  public void parseBadCommand(){
    runCommand("sdadfsd");
  }

  @Test
  public void parseCommandCreateList(){
    runCommand("create -firsList");
    runCommand("CREATE -secondList");
    //runCommand("create thirdList");
  }

  @Test (expected = IllegalArgumentException.class)
  public void parseBadCommandCreateList(){
    runBadCommand("create -firsList");
    runBadCommand("create thirdList");
  }

  @Test
  public void parseCommandShowall(){
    runCommand("showall");
  }

  @Test (expected = IllegalArgumentException.class)
  public void parseBadCommandShowall(){
    runCommand("showall sdfsfs");
  }

  @Test
  public void parseCommandDelete(){
    runBadCommand("delete -firsList");
  }

  @Test (expected = IllegalArgumentException.class)
  public void parseBadCommandDelete() {
    runBadCommand("delete -FIRSTList");
    runBadCommand("delete -firsList");
    runBadCommand("delete firsList");
  }

  @Test
  public void parseCommandAdd(){
    runCommand("add -secondList -new task");
    runCommand("add -secondList -new task 2");
    runCommand("add -secondList -new task 2");
  }

  @Test (expected = IllegalArgumentException.class)
  public void parseBadCommandAdd(){
    runCommand("add secondList new task");
    runCommand("add -secondList");
  }

  @Test
  public void parseCommandEditTask(){
    runCommand("edit -secondList -1 -updated Task");
  }

  @Test (expected = IllegalArgumentException.class)
  public void parseBadCommandEditTask2(){
    runCommand("edit secondList 1 updated Task 2");
  }

  @Test
  public void parseCommandEditTaskStatus(){
    runCommand("setstatus -secondList -2 -done");
    runCommand("setstatus -secondList -3 -cancelled");
  }

  @Test (expected = NumberFormatException.class)
  public void parseBadCommandEditTaskStatus1(){
    runBadCommand("setstatus -secondList -100 -cancelled");
    runBadCommand("setstatus -secondList -asa -cancelled");
  }

  @Test (expected = IllegalArgumentException.class)
  public void parseBadCommandEditTaskStatus2() {
    runCommand("setstatus -secondList -3 -cccc");
  }

  @Test
  public void parseCommandShow(){
    runBadCommand("show -secondList");
  }

  @Test (expected = IllegalArgumentException.class)
  public void parseBadCommandShow(){
    runCommand("show secondList");
  }

  private void runCommand(String commandString){
    CorrectParams correctParams = new CorrectParams();
    CommandParser commandParser = new CommandParser();
    Command command = commandParser.parseUserCommand(commandString, correctParams);
    Assert.assertTrue(command.perform(listOfTaskLists));
  }

  private void runBadCommand(String commandString){
    CorrectParams correctParams = new CorrectParams();
    CommandParser commandParser = new CommandParser();
    Command command = commandParser.parseUserCommand(commandString, correctParams);
    Assert.assertFalse(command.perform(listOfTaskLists));
  }
}
