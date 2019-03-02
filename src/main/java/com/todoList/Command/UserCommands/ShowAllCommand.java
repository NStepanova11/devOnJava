package com.todoList.Command.UserCommands;

import com.todoList.Command.Command;
import com.todoList.Entities.TaskList;
import com.todoList.Utils.ConsoleWriter;
import com.todoList.Utils.Messages;

import java.util.List;

public class ShowAllCommand extends Command {

  public boolean perform(List<TaskList> listOfTaskLists){
    if (listOfTaskLists.isEmpty()) {
      System.out.println(Messages.emptyListOfLists);
      return false;
    }

    ConsoleWriter.printMessage(Messages.allTodoLists);
    for (TaskList list: listOfTaskLists){
      System.out.println("- "+list.getListName());
    }
    System.out.println();
    return true;
  }
}
