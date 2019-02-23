package com.todoList.Command.UserCommands;

import com.todoList.Command.Command;
import com.todoList.Entities.Task;
import com.todoList.Entities.TaskList;
import com.todoList.Utils.ConsoleWriter;
import com.todoList.Utils.Messages;

import java.util.List;

public class AddCommand extends Command {

  public boolean perform(List<TaskList> listOfTaskLists){
    Task task = new Task();
    String listName = commandParts.get(0);
    task.setTaskText(commandParts.get(1));
    boolean listExist = false;

    for (TaskList taskList : listOfTaskLists){
      if(taskList.getListName().toLowerCase().equals(listName.toLowerCase())){
        listExist = true;
        taskList.getTaskList().add(task);
        ConsoleWriter.printMessage(Messages.taskIsAppend);
      }
    }

    if (!listExist){
      ConsoleWriter.printMessage(Messages.wrongListName);
      return false;
    }
    return true;
  }
}
