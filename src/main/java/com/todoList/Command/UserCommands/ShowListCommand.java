package com.todoList.Command.UserCommands;

import com.todoList.Command.Command;
import com.todoList.Entities.Task;
import com.todoList.Entities.TaskList;
import com.todoList.Utils.ConsoleWriter;
import com.todoList.Utils.Messages;

import java.util.List;

public class ShowListCommand extends Command {
  public boolean perform(List<TaskList> listOfTaskLists) {
    String listName = commandParts.get(0);
    boolean listExist = false;

    for (TaskList list: listOfTaskLists) {
      if (list.getListName().equals(listName)) {
        listExist = true;
        if (list.getTaskList().size() != 0) {
          int i = 1;
          for (Task task : list.getTaskList()) {
            System.out.println(i+". "+task.getTaskText() + " : " + task.getStatus());
            i++;
          }
        } else {
          System.out.println(Messages.todoListIsEmpty);
        }
      }
    }
    if(!listExist){
      ConsoleWriter.printMessage(Messages.wrongListName);
      return false;
    }
    return true;
  }
}
