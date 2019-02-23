package com.todoList.Command.UserCommands;

import com.todoList.Command.Command;
import com.todoList.Entities.Task;
import com.todoList.Entities.TaskList;
import com.todoList.Utils.ConsoleWriter;
import com.todoList.Utils.Messages;

import java.util.List;

public class EditTodoListNameCommand extends Command {
  public boolean perform(List<TaskList> listOfTaskLists) {
    String listName = commandParts.get(0);
    String newListName = commandParts.get(1);
    boolean listExist = false;

    for (TaskList taskList : listOfTaskLists) {
      if (taskList.getListName().toLowerCase().equals(listName.toLowerCase())) {
        listExist = true;
        taskList.setListName(newListName);
        ConsoleWriter.printMessage(Messages.listNameUpdated);
      }
    }

    if (!listExist) {
      ConsoleWriter.printMessage(Messages.wrongListName);
      return false;
    }
    return true;
  }
}
