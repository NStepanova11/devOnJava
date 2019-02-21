package com.todoList.Utils;

import com.todoList.Command.Command;
import com.todoList.Entities.CommandList;
import com.todoList.Entities.StatusList;

import java.util.List;
import java.util.Map;

public class CorrectParams {
  private Map<String, Command> commandTypes;
  private Map<String, Integer> commandParamsQuantity;
  private List<String> statusList;

  public CorrectParams(){
    commandTypes = CommandList.defineCommandList();
    commandParamsQuantity = CommandList.defineCommandParamsQuantity();
    statusList = StatusList.defineStatusTypes();
  }

  public Map<String, Command> getCommandTypes(){
    return commandTypes;
  }

  public Map<String, Integer> getCommandParamsQuantity(){
    return commandParamsQuantity;
  }

  public List<String> getStatusList(){
    return statusList;
  }
}
