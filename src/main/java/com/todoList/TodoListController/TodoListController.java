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
  private static final String FILE_PATH = "todoListCatalog\\todoLists.json";
  private List<TaskList> listOfTaskLists;
  private CorrectParams correctParams;

  public TodoListController(){
    listOfTaskLists = new ArrayList<>();
    correctParams = new CorrectParams();
  }

  public List<TaskList> getListOfTaskLists(){
    return listOfTaskLists;
  }

  public void  processUserCommand(String userCommandString) throws IOException {
    try {
      CommandParser commandParser = new CommandParser();
      Command command = commandParser.parseUserCommand(userCommandString, correctParams);
      command.perform(listOfTaskLists);
    }
    catch (Exception ex){
      System.out.println(ex.getMessage());
    }
  }

  public void loadAllTodoLists() throws IOException {
    FileController fileController = new FileController();
    listOfTaskLists = fileController.loadListOfTaskLists(FILE_PATH);
  }

  public void saveResult() throws IOException {
    try{
    if (!listOfTaskLists.isEmpty()) {
      FileController.saveToFile(listOfTaskLists, FILE_PATH);
    }
    ConsoleWriter.printMessage(Messages.resultSaved);
    }
    catch (IOException ex){
      ConsoleWriter.printMessage(Messages.fileWritingError);
    }
  }

  public void userManual(){
    System.out.println("Создать todo-лист: create -listName");
    System.out.println("Удалить todo-лист: delete -listName");
    System.out.println("Показать список всех листов: showall");
    System.out.println("Показать todo-лист: show -listName");
    System.out.println("Добавить задачу в todo-лист: add -listName -taskText");
    System.out.println("Изменить текст задачи: edit -listName -taskNumber -newTaskText");
    System.out.println("Изменить статус задачи: setstatus -listName -taskNumber -newStatus[done, cancelled]");
  }
}
