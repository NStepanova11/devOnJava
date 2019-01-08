package com.todoList;

import com.sun.deploy.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TodoListController {
  public static final String doneStatus = "[+]";
  public static final String cancelledStatus = "[x]";
  public static final String uncnownStatus = "[ ]";
  public static final int statusStartPos = 0;
  public static final int statusEndPos = 3;

  public static final int taskStartPos = 4;

  private File todoListFile;
  private String todoListsDirectoryName;
  private String fileFormat;
  private boolean statusIsCorrect;

  public enum EditCommand {ADD, CHANGE, SETSTATUS, SAVE}

  public TodoListController(){
    this.todoListsDirectoryName = "todoListFiles";
    this.fileFormat = ".txt";
    this.statusIsCorrect = true;
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

  public void ShowList(ArrayList<String> taskList) throws IOException {
    //List<String> taskList = ReadListFromFile(listName);
    for (String task : taskList){
      int taskNum = taskList.indexOf(task)+1;
      System.out.println(taskNum+". "+task);
    }
  }

  public void EditTodoList(String listName) throws IOException {
    //вывести на экран изменяемый лист
    ArrayList<String> currentTaskList = (ArrayList<String>) ReadListFromFile(listName);
    ShowList(currentTaskList);

    String listPath = GenerateTodoListPath(listName);
    System.out.print("-->");

    //получить команду изменения из консоли
    String inputCommand = GetEditCommandFromConcole();

    //разделить строку на части и выделить команду в первом слове
    List<String> commandParts = new ArrayList<String>(Arrays.asList(inputCommand.split(" ")));

    String editCommand = commandParts.get(0);
    commandParts.remove(0);
    EditCommand command = EditCommand.valueOf(editCommand.toUpperCase());

    switch (command){
      case ADD:
        //если команда ADD, то остаток сливаем в одну строку
        String task = StringUtils.join(commandParts, " ");
        System.out.println("task: "+ task);
        break;
      case SETSTATUS:
        //если команда SETSTATUS то выделяем номер строки и статус
        int taskNum = Integer.parseInt(commandParts.get(0));
        String statusCode = commandParts.get(1);
        String status="";
        status= "["+statusCode+"]";

        String editableTask = currentTaskList.get(taskNum-1);
        currentTaskList.set(taskNum-1,UpdatePartOfTask(editableTask, statusStartPos, statusEndPos, status));
        break;
      case CHANGE:
        int taskNum1 = Integer.parseInt(commandParts.get(0));
        commandParts.remove(0);
        String task1 = StringUtils.join(commandParts, " ");

        String editableTask1 = currentTaskList.get(taskNum1-1);
        currentTaskList.set(taskNum1-1,UpdatePartOfTask(editableTask1, taskStartPos, editableTask1.length(), task1));
        break;
      case SAVE:{
        SaveNewStateOfTodoList(listPath, currentTaskList);
      }
      default:
        System.out.println("problem");
        break;
    }
  }

  public void SaveNewStateOfTodoList(String listPath, ArrayList<String> updatedTodoList) throws IOException {
    FileWriter writer = new FileWriter(listPath, false);
    for(String task : updatedTodoList) {
      writer.write(task);
      writer.write("\r\n");
    }
    writer.close();
  }

  public String UpdatePartOfTask(String task, int startPos, int endPos, String newPart){
    String updatedTask =  task.replace(task.substring(startPos, endPos), newPart);
    return  updatedTask;
  }

  String GetEditCommandFromConcole(){
    String command ="";
    Scanner inputStream = new Scanner(System.in);
    command = inputStream.nextLine();
    return command;
  }

  /*
  public void AddToList(String listPath, String task) throws IOException {
    FileWriter writer = new FileWriter(listPath, true);
    writer.write(uncnownStatus+" "+task);
    writer.write("\r\n");
    writer.close();
  }


  public void UpdateStatusInList(String listPath, ArrayList<String> updatedTaskList) throws IOException {
    FileWriter writer = new FileWriter(listPath, false);
    for(String task : updatedTaskList) {
      writer.write(task);
      writer.write("\r\n");
    }
    writer.close();
  }
  */
}
