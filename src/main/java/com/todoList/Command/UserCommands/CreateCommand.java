package com.todoList.Command.UserCommands;

import com.todoList.Command.Command;
import com.todoList.Entities.TaskList;
import com.todoList.Utils.ConsoleWriter;
import com.todoList.Utils.Messages;

import java.util.List;

public class CreateCommand extends Command {

  public boolean perform(List<TaskList> listOfTaskLists){
    String listName = commandParts.get(0);
    boolean listExist = false;

    for (TaskList list : listOfTaskLists){
      if (list.getListName().toLowerCase().equals(listName.toLowerCase())) {
        listExist = true;
        break;
      }
    }

    if (!listExist){
      TaskList newTaskList = new TaskList();
      newTaskList.setListName(listName);
      listOfTaskLists.add(newTaskList);
      ConsoleWriter.printMessage(Messages.listIsCreated);
    }
    else {
      ConsoleWriter.printMessage(" "+listName+Messages.listIsExist);
      return false;
    }
    return true;
  }
}
