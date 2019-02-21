package com.todoList.Entities;

public class Task {
  private String status;
  private String taskText;

  public Task(){
    status = StatusList.TODO;
  }

  public String getStatus(){
    return status;
  }
  public void setStatus(String status){
    this.status = status;
  }

  public String getTaskText(){
    return taskText;
  }


  public void setTaskText(String taskText){
    this.taskText = taskText;
  }
}
