package com.todoList.Utils.CommandParser;

import com.todoList.Command.Command;
import com.todoList.Entities.CommandList;
import com.todoList.Utils.CorrectParams;
import com.todoList.Utils.Messages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class CommandParser {
  public static final String REGEX= " -";
  private List<String> commandParts;

  public Command parseUserCommand(String commandString, CorrectParams correctParams){
    commandParts = parseCommandParams(commandString);
    checkParamsCorrectness(commandParts, correctParams);
    String command = commandParts.get(0).toLowerCase();
    removeElementFromUserString();

    Command userCommand = correctParams.getCommandTypes().get(command);
    userCommand.setCommandType(command);
    userCommand.setCommandParamsList(commandParts);
    return userCommand;
  }

  public boolean checkParamsCorrectness(List<String> commandParts, CorrectParams correctParams){
    String command = commandParts.get(0).toLowerCase();
    int statusIndex = 3;
    int taskNumberIndex = 2;

    if(!correctParams.getCommandTypes().containsKey(command)) {
      throw new IllegalArgumentException(Messages.wrongCommandFromUser);
    }
    if (commandParts.size()!=correctParams.getCommandParamsQuantity().get(command)) {
      throw new IllegalArgumentException(Messages.wrongParamsQuantityMessage);
    }
    try {
      if ((command.equals(CommandList.EDIT_TASK) || command.equals(CommandList.SET_STATUS))){
        Integer.parseInt(commandParts.get(taskNumberIndex));
      }
    }
    catch(Exception ex){
      throw new NumberFormatException(Messages.wrongTaskNumberFormat);
    }
    if (command.equals(CommandList.SET_STATUS) && !correctParams.getStatusList().contains(commandParts.get(statusIndex))){
      throw new IllegalArgumentException(Messages.wrongStatusFromUser);
    }
    return true;
  }

  public void removeElementFromUserString(){
    commandParts.remove(0);
  }

  public List<String> parseCommandParams(String commandString){
    Pattern p = Pattern.compile(REGEX);
    List<String> result = new ArrayList<String>(Arrays.asList(p.split(commandString)));
    return result;
  }

   /*
    public List<String> splitUserCommand(String commandString){
    commandParts = new ArrayList<String>(Arrays.asList(commandString.split(" ")));
    return commandParts;
  }

  public String parseTodoListName(List<String>commandParts){
    removeElementFromUserString(commandParts);
    String listName = commandParts.get(0);
    return listName;
  }

  public Integer parseTaskNumber(List<String>commandParts){
    int taskNum = 0;
    removeElementFromUserString(commandParts);
    taskNum = Integer.parseInt(commandParts.get(0));
    return taskNum;
  }

  public String parseTask(List<String>commandParts){
    removeElementFromUserString(commandParts);
    String taskString = String.join(" ", commandParts);
    return taskString;
  }

  public String parseStatus(List<String>commandParts){
    removeElementFromUserString(commandParts);
    String status = commandParts.get(0);
    return status;
  }

    public String getUserParamsString(List<String> commandParts) {
    removeElementFromUserString(commandParts);
    return(String.join(" ", commandParts));
  }

  */

   /*
  public Command defineCommandType(String commandName, List<String> commandString){
    Command userCommand = null;

    switch (commandName){
      case SHOWALL:
        userCommand = new ShowAllCommand();
        break;
      case SHOW:
        userCommand = new ShowListCommand();
        break;
      case CREATE:
        userCommand = new CreateCommand();
        break;
      case DELETE:
        userCommand = new DeleteCommand();
        break;
      case ADD:
        userCommand = new AddCommand();
        break;
      case EDITTASK:
        userCommand = new EditTaskCommand();
        break;
      case SETSTATUS:
        userCommand = new EditTaskStatusCommand();
        break;
    }

    userCommand.setCommandType(commandName);
    userCommand.setCommandParamsList(commandString);
    return userCommand;
  }
  */


}
