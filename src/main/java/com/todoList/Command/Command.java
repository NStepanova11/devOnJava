package com.todoList.Command;

import com.todoList.Entities.TaskList;

import java.util.ArrayList;
import java.util.List;

abstract public class Command {
  protected List<String> commandParts;
  protected String commandType;

  public Command(){
    commandParts = new ArrayList<>();
  }

  public void setCommandParamsList(List<String> commandPartsFromUser){
    this.commandParts = commandPartsFromUser;
  }

  public String getCommandType(){
    return commandType;
  }

  public void setCommandType(String commandType){
    this.commandType = commandType;
  }
  public boolean perform(List<TaskList> listOfTaskLists){
    return true;
  }
}