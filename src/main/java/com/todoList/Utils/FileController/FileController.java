package com.todoList.Utils.FileController;

import com.todoList.Entities.Task;
import com.todoList.Entities.TaskList;
import com.todoList.Utils.ConsoleWriter;
import com.todoList.Utils.Messages;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileController {
  private static final String DIRECTORY = "todoListCatalog";
  private static final String FILE_PATH = DIRECTORY + "\\todoLists.json";
  public static final String LIST_NAME = "listName";
  public static final String TASK_ARRAY = "tasks";
  public static final String TASK_TEXT = "task";
  public static final String STATUS = "status";

  public List<TaskList> loadListOfTaskLists() throws IOException {
    List<TaskList> listOfTaskLists = new ArrayList<>();
    try {
      listOfTaskLists = readListOfTaskList(readStringsFromFile());
    }
    catch (IOException ex){
      ConsoleWriter.printMessage(Messages.fileReadingError);
    }
    return listOfTaskLists;
  }

  public List<String> readStringsFromFile() throws IOException {
    List<String> stringsOfTaskLists = new ArrayList<>();
    File file = new File(FILE_PATH);
    if (file.exists()){
      BufferedReader reader = new BufferedReader(new FileReader(file));
      while (reader.ready()){
        stringsOfTaskLists.add(reader.readLine());
      }
      reader.close();
    }
    return stringsOfTaskLists;
  }

  public List<TaskList> readListOfTaskList(List<String> stringsOfTaskList){
    List<TaskList> listOfTaskList = new ArrayList<>();
    for (String jsonTaskListString : stringsOfTaskList) {
      listOfTaskList.add(readTaskListFromJson(jsonTaskListString));
    }
    return listOfTaskList;
  }

  public TaskList readTaskListFromJson(String jsonTaskListString){
    TaskList taskList = new TaskList();
    List<Task> tasksOfList = new ArrayList<>();
    JSONObject jsonTaskListObject = new JSONObject(jsonTaskListString);
    JSONArray tasksArray = jsonTaskListObject.getJSONArray(TASK_ARRAY);

    for (int i = 0; i < tasksArray.length(); i++) {
      JSONObject taskObject = tasksArray.getJSONObject(i);
      tasksOfList.add(readTaskFromJsonArray(taskObject));
    }
    taskList.setListName(jsonTaskListObject.get(LIST_NAME).toString());
    taskList.setTaskList(tasksOfList);
    return taskList;
  }

  public Task readTaskFromJsonArray(JSONObject taskObject){
    Task task = new Task();
    task.setTaskText(taskObject.get(TASK_TEXT).toString());
    task.setStatus(taskObject.get(STATUS).toString());
    return task;
  }

  public static List<String> performTaskListsToJson(List<TaskList> listOfTaskLists){
    List<String> todoListsInJson = new ArrayList<>();

    for (TaskList list: listOfTaskLists){
      JSONObject taskListObject = new JSONObject();
      JSONArray taskArray = performTasksToJson(list.getTaskList());
      taskListObject.put(LIST_NAME, list.getListName());
      taskListObject.put(TASK_ARRAY, taskArray);
      todoListsInJson.add(String.valueOf(taskListObject));
    }
    return todoListsInJson;
  }

  public static JSONArray performTasksToJson(List<Task> tasksOfList){
    JSONArray taskArray = new JSONArray();
    for (Task task : tasksOfList){
      JSONObject taskObject = new JSONObject();
      taskObject.put("id", tasksOfList.lastIndexOf(task)+1);
      taskObject.put(STATUS, task.getStatus());
      taskObject.put(TASK_TEXT, task.getTaskText());
      taskArray.put(taskObject);
    }
    return taskArray;
  }

  public static void checkDirectoryExist(){
    File directory = new File(DIRECTORY);
    if (!directory.exists()){
      directory.mkdir();
    }
  }

  public static boolean saveToFile(List<TaskList> listOfTaskLists) throws IOException {
    List<String> todoList = performTaskListsToJson(listOfTaskLists);
    checkDirectoryExist();
    FileWriter writer = new FileWriter(FILE_PATH, false);
    for(String list : todoList) {
      writer.write(list+",");
      writer.write("\r\n");
    }
    writer.close();

    return true;
  }
}