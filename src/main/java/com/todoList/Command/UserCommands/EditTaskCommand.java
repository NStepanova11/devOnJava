package com.todoList.Command.UserCommands;

import com.todoList.Command.Command;
import com.todoList.Entities.TaskList;
import com.todoList.Utils.ConsoleWriter;
import com.todoList.Utils.Messages;

import java.util.List;

public class EditTaskCommand extends Command{
  public boolean perform(List<TaskList> listOfTaskLists) throws NumberFormatException{
    String listName = commandParts.get(0);
    int taskNumber = Integer.parseInt(commandParts.get(1));
    String taskText = commandParts.get(2);
    boolean listExist = false;

    for (TaskList list: listOfTaskLists) {
      if (list.getListName().equals(listName)) {
        listExist = true;
        if (list.getTaskList().size() >= (taskNumber - 1) && (taskNumber - 1) >= 0) {
          list.getTaskList().get(taskNumber - 1).setTaskText(taskText);
        } else {
          ConsoleWriter.printMessage(Messages.incorrectTaskNumber+taskNumber);
          return false;
        }
      }
    }
    if(!listExist){
      System.out.println(Messages.wrongListName);
      return false;
    }
    return true;
  }
}