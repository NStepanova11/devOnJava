package com.todoList;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

  public enum Commands {CREATE, DELETE, SHOW, SHOWALL, EDIT}

  public static void main (String[] args) throws IOException {

    String commandName = args[0];
    Commands command = Commands.valueOf(commandName.toUpperCase());

    TodoListController todoListController = new TodoListController();
    switch(command){
      case SHOWALL:
        todoListController.ShowAll();
        break;
      case CREATE:
        todoListController.CreateNewTodoList("bts");
        break;
      case DELETE:
        todoListController.DeleteTodoList("exo-k");
        break;
      case SHOW:
        todoListController.ShowList("bts");
        break;
      case EDIT:
        todoListController.EditTodoList("bts");
        break;
      default:
        System.out.println("error command");
    }
  }
}
