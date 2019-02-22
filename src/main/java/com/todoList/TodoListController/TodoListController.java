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
    System.out.println("Create todo-list: create -listName");
    System.out.println("Delete todo-list: delete -listName");
    System.out.println("Show list of all sheets: show all");
    System.out.println("Show todo-list: show -listName");
    System.out.println("Append task to todo-list: add -listName -taskText");
    System.out.println("Edit task text: edit -listName -taskNumber -newTaskText");
    System.out.println("Edit task status: set status -listName -taskNumber -newStatus[done, cancelled]");
    System.out.println("Edit list name: edit list name -listName -newNameOfList");
    System.out.println("-----------------------------------------------------------");
    return true;
  }
}
