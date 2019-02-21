package com.todoList.Command;

import com.todoList.Entities.TaskList;

import java.util.ArrayList;
import java.util.List;

abstract public class Command {
  protected List<String> commandParts;
  protected String commandType;

  public Command(){
    commandParts = new ArrayList<>();
    //commandParser = new CommandParser();
  }

  public void setCommandParamsList(List<String> commandPartsFromUser){
    this.commandParts = commandPartsFromUser;
  }

  public void setCommandType(String commandType){
    this.commandType = commandType;
  }
  public boolean perform(List<TaskList> listOfTaskLists){
    return true;
  }
}


/*
 protected String listName;
  protected String task;
  protected int taskNumber;
  protected String status;

  public void setListName(String listName){
    this.listName = listName;
  }

  public void setTask(String task){
    this.task = task;
  }

  public void setTaskNumber(int taskNumber){
    this.taskNumber = taskNumber;
  }

  public void setStatus (String status){
    this.status = status;
  }

  public boolean perform(List<TaskList> listOfTaskLists){
    return true;
  }

* */