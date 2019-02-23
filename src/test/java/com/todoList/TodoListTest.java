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

/*
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
    runCommand("create -firsList");
    runBadCommand("create thirdList");
  }

  @Test
  public void parseCommandShowall(){
    runCommand("create -thirdList");
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
    runCommand("create -list");
    runCommand("add -list -first task");
    runCommand("add -list -second task");
    runCommand("show -list");
  }

  @Test (expected = IllegalArgumentException.class)
  public void parseBadCommandShow2(){
    runCommand("show secondList");
  }

  @Test
  public void editTaskListNameTest(){
    runCommand("create -listName");
    runCommand("edit list name -listName -updatedName");
  }

  @Test
  public void deleteListCommandTest(){
    runCommand("create -listName");
    runCommand("delete -listName");
  }


  @Test
  public void deleteTaskCommandTest(){
    runCommand("create -listName");
    runCommand("add -LISTNAME -task");
    runCommand("delete task -listName -1");
  }

  @Test
  public void badDeleteTaskCommandTest1(){
    runCommand("create -listName");
    runCommand("add -LISTNAME -task");
    runCommand("delete task -listName -1");
    runBadCommand("delete task -listName -1");
  }



  @Test
  public void saveResultTest() throws IOException {
    TodoListController todoListController = new TodoListController();
    Assert.assertTrue(todoListController.processUserCommand("create -listName"));
  }

  @Test
  public void userManualTest(){
    TodoListController t = new TodoListController();
    Assert.assertTrue(t.userManual());
  }

  @Test
  public void CommandTypeTest(){
    CommandParser c = new CommandParser();
    CorrectParams correctParams = new CorrectParams();
    Command command = c.parseUserCommand("show all", correctParams);
    Assert.assertEquals(command.getCommandType(), "show all");
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
  */
}