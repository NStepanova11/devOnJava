package com.todoList;

import com.todoList.Command.Command;
import com.todoList.Entities.Task;
import com.todoList.Entities.TaskList;
import com.todoList.TodoListController.TodoListController;
import com.todoList.Utils.CommandParser.CommandParser;
import com.todoList.Utils.CorrectParams;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TodoListTest {
  private static List<TaskList> listOfTaskLists = new ArrayList<>();

  @Test (expected = IllegalArgumentException.class)
  public void parseBadCommand(){
    runCommand("sdadfsd");
  }

  @Test
  public void parseCommandCreateList(){
    runCommand("create -firsList");
    runCommand("CREATE -secondList");
  }

  @Test (expected = IllegalArgumentException.class)
  public void parseBadCommandCreateList(){
    runBadCommand("create -firsList");
    runBadCommand("create thirdList");
  }

  @Test
  public void parseCommandShowall(){
    runCommand("show all");
  }

  @Test (expected = IllegalArgumentException.class)
  public void parseBadCommandShowall(){
    runCommand("show all sdfsfs");
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
    runCommand("set status -secondList -2 -done");
    runCommand("set status -secondList -3 -cancelled");
  }

  @Test (expected = NumberFormatException.class)
  public void parseBadCommandEditTaskStatus1(){
    runBadCommand("set status -secondList -100 -cancelled");
    runBadCommand("set status -secondList -asa -cancelled");
  }

  @Test (expected = IllegalArgumentException.class)
  public void parseBadCommandEditTaskStatus2() {
    runCommand("set status -secondList -3 -cccc");
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

  @Test
  public void saveResultTest() throws IOException {
    TodoListController todoListController = new TodoListController();
    Assert.assertTrue(todoListController.processUserCommand("create -listName"));
  }

  @Test
  public void loadListTest() throws IOException {
    TodoListController todoListController = new TodoListController();
    todoListController.processUserCommand("create -first");
    todoListController.processUserCommand("create -second");
    todoListController.loadAllTodoLists();
    Assert.assertEquals(todoListController.getListOfTaskLists().size(), 2);
    Assert.assertEquals(todoListController.getListOfTaskLists().get(0).getListName(), "first");
    Assert.assertEquals(todoListController.getListOfTaskLists().get(1).getTaskList().size(), 0);
  }

}