package com.todoList.Command.UserCommands;

import com.todoList.Command.Command;
import com.todoList.Entities.TaskList;
import com.todoList.Utils.ConsoleWriter;
import com.todoList.Utils.Messages;

import java.util.List;

public class DeleteCommand extends Command {

  public boolean perform(List<TaskList> listOfTaskLists){
    String listName = commandParts.get(0);
    boolean listExist = false;

    for (TaskList list: listOfTaskLists){
      if (list.getListName().equals(listName)){
        listExist = true;
        listOfTaskLists.remove(list);
        ConsoleWriter.printMessage(Messages.listIsDeleted);
        break;
      }
    }

    if (!listExist){
      System.out.println(Messages.wrongListName);
      return false;
    }
    return true;
  }
}
