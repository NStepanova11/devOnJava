package com.todoList.TodoListController;

import com.todoList.Command.Command;
import com.todoList.Entities.TaskList;
import com.todoList.Utils.CommandParser.CommandParser;
import com.todoList.Utils.ConsoleWriter;
import com.todoList.Utils.CorrectParams;
import com.todoList.Utils.FileController.FileController;
import com.todoList.Utils.Messages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TodoListController {
  private List<TaskList> listOfTaskLists;
  private CorrectParams correctParams;
  public static final String commandManualFormat = "%-90s%n";
  public static final String commandHeaderFormat = "%-27s";

  public TodoListController(){
    listOfTaskLists = new ArrayList<>();
    correctParams = new CorrectParams();
  }

  public List<TaskList> getListOfTaskLists(){
    return listOfTaskLists;
  }

  public boolean  processUserCommand(String userCommandString) throws IOException {
    try {
      CommandParser commandParser = new CommandParser();
      Command command = commandParser.parseUserCommand(userCommandString, correctParams);
      command.perform(listOfTaskLists);
      saveResult();
    }
    catch (Exception ex){
      System.out.println(ex.getMessage());
      return false;
    }
    return true;
  }

  public void loadAllTodoLists() throws IOException {
    FileController fileController = new FileController();
    listOfTaskLists = fileController.loadListOfTaskLists();
  }

  public boolean saveResult() throws IOException {
    try{
      FileController.saveToFile(listOfTaskLists);
    }
    catch (IOException ex){
      ConsoleWriter.printMessage(Messages.fileWritingError);
      return false;
    }
    return true;
  }

  public boolean userManual(){
    System.out.printf(commandHeaderFormat,"Create todo-list:");
    System.out.printf(commandManualFormat, "create -listName");
    System.out.printf(commandHeaderFormat,"Delete todo-list:");
    System.out.printf(commandManualFormat, "delete -listName");
    System.out.printf(commandHeaderFormat,"Show list of all sheets:");
    System.out.printf(commandManualFormat, "show all");
    System.out.printf(commandHeaderFormat,"Show todo-list: ");
    System.out.printf(commandManualFormat, "show -listName");
    System.out.printf(commandHeaderFormat,"Append task to todo-list: ");
    System.out.printf(commandManualFormat, "add -listName -taskText");
    System.out.printf(commandHeaderFormat,"Edit task text: ");
    System.out.printf(commandManualFormat, "edit -listName -taskNumber -newTaskText");
    System.out.printf(commandHeaderFormat,"Edit task status: ");
    System.out.printf(commandManualFormat, "set status -listName -taskNumber -newStatus[done or cancelled]");
    System.out.printf(commandHeaderFormat,"Edit list name: ");
    System.out.printf(commandManualFormat, "edit list name -listName -newNameOfList");
    System.out.println("-----------------------------------------------------------");
    return true;
  }
}
