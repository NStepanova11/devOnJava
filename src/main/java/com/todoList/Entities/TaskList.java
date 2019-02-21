package com.todoList.Entities;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
  private String listName;
  private List<Task> taskList;

  public TaskList(){
    this.taskList = new ArrayList<Task>();
  }

  public String getListName(){
    return listName;
  }

  public void setListName(String listName){
    this.listName = listName;
  }

  public List<Task> getTaskList(){
    return taskList;
  }

  public void setTaskList(List<Task> taskList){
    this.taskList = taskList;
  }
}
