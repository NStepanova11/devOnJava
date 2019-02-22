package com.todoList.Entities;

import com.todoList.Command.Command;
import com.todoList.Command.UserCommands.*;

import java.util.HashMap;
import java.util.Map;

public class CommandList {
  public static final String SHOW_ALL = "show all";
  public static final String CREATE = "create";
  public static final String DELETE = "delete";
  public static final String SHOW = "show";
  public static final String ADD = "add";
  public static final String EDIT_TASK = "edit";
  public static final String SET_STATUS = "set status";
  public static final String EXIT = "exit";
  public static final String EDIT_LIST_NAME = "edit list name";

  public static final int SHOW_ALL_PARAM_QUANTITY = 1;
  public static final int CREATE_PARAM_QUANTITY = 2;
  public static final int DELETE_PARAM_QUANTITY = 2;
  public static final int SHOW_PARAM_QUANTITY = 2;
  public static final int ADD_PARAM_QUANTITY = 3;
  public static final int EDIT_TASK_PARAM_QUANTITY = 4;
  public static final int SET_STATUS_PARAM_QUANTITY = 4;
  public static final int EDIT_LIST_NAME_PARAM_QUANTITY = 3;

  public static Map<String, Command> defineCommandList(){
    Map<String, Command> commandTypes = new HashMap<>();
    commandTypes.put(SHOW_ALL, new ShowAllCommand());
    commandTypes.put(CREATE, new CreateCommand());
    commandTypes.put(DELETE, new DeleteCommand());
    commandTypes.put(SHOW, new ShowListCommand());
    commandTypes.put(ADD, new AddCommand());
    commandTypes.put(EDIT_TASK, new EditTaskCommand());
    commandTypes.put(SET_STATUS, new EditTaskStatusCommand());
    commandTypes.put(EDIT_LIST_NAME, new EditTaskNameCommand());
    return commandTypes;
  }

  public static Map<String, Integer> defineCommandParamsQuantity(){
    Map<String, Integer> commandParamsQuantity = new HashMap<>();
    commandParamsQuantity.put(SHOW_ALL, SHOW_ALL_PARAM_QUANTITY);
    commandParamsQuantity.put(CREATE, CREATE_PARAM_QUANTITY);
    commandParamsQuantity.put(DELETE, DELETE_PARAM_QUANTITY);
    commandParamsQuantity.put(SHOW, SHOW_PARAM_QUANTITY);
    commandParamsQuantity.put(ADD, ADD_PARAM_QUANTITY);
    commandParamsQuantity.put(EDIT_TASK, EDIT_TASK_PARAM_QUANTITY);
    commandParamsQuantity.put(SET_STATUS, SET_STATUS_PARAM_QUANTITY);
    commandParamsQuantity.put(EDIT_LIST_NAME, EDIT_LIST_NAME_PARAM_QUANTITY);
    return commandParamsQuantity;
  }
}
