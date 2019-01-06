package com.todoList;

import com.sun.deploy.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TodoListController {
  public static final String doneStatus = "done";
  public static final String cancelledStatus = "canceled";

  public static final String doneStatusCode = "d";
  public static final String cancelledStatusCode = "c";

  private File todoListFile;
  private String todoListsDirectoryName;
  private String fileFormat;

  public enum EditCommand {ADD, CHANGE, SETSTATUS}

  public TodoListController(){
    this.todoListsDirectoryName = "todoListFiles";
    this.fileFormat = ".txt";
  }

  public void CreateListsDirectory(){
    File directory = new File(todoListsDirectoryName);
    if (!directory.isDirectory()){
      directory.mkdir();
      System.out.println("directory created");
    }
    else{
      System.out.println("directory already exist");
    }
  }

  public String GenerateTodoListPath(String listName){
      String listPath = todoListsDirectoryName+"\\"+listName+fileFormat;
      return listPath;
  }

  public void CreateNewTodoList(String listName) throws IOException {
    CreateListsDirectory();
    String listPath = GenerateTodoListPath(listName);
    System.out.println(listPath);
    this.todoListFile = new File(listPath);
    this.todoListFile.createNewFile();
    System.out.println(" file created");
  }

  public void DeleteTodoList(String listName){
    String listPath = GenerateTodoListPath(listName);
    this.todoListFile = new File(listPath);
    if (this.todoListFile.delete()){
      System.out.println("file was deleted");
    }
    else{
      System.out.println("file not found");
    }
  }

  public void ShowAll(){
    File directory = new File(todoListsDirectoryName);
    for (File todoList : directory.listFiles())
    {
      String todoListName = todoList.getName();
      String onlyName = todoListName.substring(0, todoListName.length()-fileFormat.length());
      System.out.println(onlyName);
    }
  }

  public List<String> ReadListFromFile(String listName) throws IOException {
    String listPath = GenerateTodoListPath(listName);
    //создаем список для хранения строк
    List<String> taskList = new ArrayList<String>();

    File file = new File(listPath);
    BufferedReader reader = new BufferedReader(new FileReader(file));

    while (reader.ready())
    {
      taskList.add(reader.readLine());
    }
    reader.close();
    return taskList;
  }

  public void ShowList(String listName) throws IOException {
    List<String> taskList = ReadListFromFile(listName);
    for (String task : taskList){
      int taskNum = taskList.indexOf(task)+1;
      System.out.println(taskNum+". "+task);
    }
  }

  public void EditTodoList(String listName) throws IOException {
    String listPath = GenerateTodoListPath(listName);
    System.out.print("-->");
    Scanner inputStream = new Scanner(System.in);
    String inputCommand = inputStream.nextLine();

    List<String> commandParts = new ArrayList<String>(Arrays.asList(inputCommand.split(" ")));
    
    String editCommand = commandParts.get(0);
    commandParts.remove(0);
    EditCommand command = EditCommand.valueOf(editCommand.toUpperCase());
    switch (command){
      case ADD:
        String task = StringUtils.join(commandParts, " ");

        System.out.println("task: "+ task);
        AddToList(listPath,task);
        break;
      default:
        System.out.println("problem");
        break;
    }


  }

  public void AddToList(String listPath, String task) throws IOException {
    FileWriter writer = new FileWriter(listPath, true);
    writer.write(task);
    writer.write("\r\n");

    writer.close();
  }

}
