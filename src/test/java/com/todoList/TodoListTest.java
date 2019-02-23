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
  public void testCreateListCommand(){
    runCommand("create -createTestList");
    runBadCommand("create -createTestList");
    runBadCommand("create");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAddTaskCommand(){
    runCommand("create -addTestList");
    runCommand("ADD -addTestList -first task");
    runBadCommand("add");
    runBadCommand("add -secondList -task");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testDeleteListCommand(){
    runCommand("create -deteteTestList");
    runCommand("delete -DELETEtestLIST");
    runBadCommand("delete -deleteTestList");
    runBadCommand("delete");
  }

  @Test
  public void testDeleteTaskCommand(){
    String list = "-deleteTaskTestList";
    runCommand("create "+list);
    runCommand("add "+list+" -task 1");
    runCommand("delete task "+list+" -1");
    runBadCommand("delete task "+list+" -0");
    runBadCommand("delete task -wrongName -1");
  }

  @Test
  public void testEditTaskCommand(){
    String list = "-editTaskList";
    runCommand("create "+list);
    runCommand("add "+list+" -task");
    runCommand("edit "+list+" -1 -updated task");
    runBadCommand("edit "+list+" -10 -updated task");
    runBadCommand("edit -wrongName -1 -update");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSetStatusCommand(){
    String list = "-setStatusList";
    runCommand("create "+list);
    runCommand("add "+list+" -task");
    runCommand("set status "+list+" -1 -cancelled");
    runBadCommand("set status "+list+" -10 -done");
    runBadCommand("set status -wrongName -1 -done");
    runBadCommand("set status "+list+" -1 -ws");
  }

  @Test
  public void testEditListNameCommand(){
    String list = "-listName";
    runCommand("create "+list);
    runCommand("edit list name "+list+" -newListName");
    runBadCommand("edit list name -wrongName -newName");
  }

  @Test
  public void testShowAllCommand(){
    runBadCommand("show all");
    runCommand("create -showList1");
    runCommand("create -showList2");
    runCommand("show all");
  }

  @Test
  public void testShowListCommand(){
    String list = "-showListName";
    runCommand("create "+list);
    runCommand("add "+list+" -task");
    runCommand("show "+list);
  }

  @Test
  public void testCommandType(){
    CorrectParams correctParams = new CorrectParams();
    CommandParser commandParser = new CommandParser();
    Command command = commandParser.parseUserCommand("create -listName", correctParams);
    Assert.assertEquals(command.getCommandType(), "create");
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
    Assert.assertTrue(todoListController.processUserCommand("create -saveResultList"));
  }

  @Test
  public void userManualTest(){
    TodoListController t = new TodoListController();
    Assert.assertTrue(t.userManual());
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