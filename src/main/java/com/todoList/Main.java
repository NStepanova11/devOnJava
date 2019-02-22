package com.todoList;

import com.todoList.TodoListController.TodoListController;

import java.io.IOException;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) throws IOException {
    TodoListController todoListController = new TodoListController();
    todoListController.userManual();
    todoListController.loadAllTodoLists();
    Scanner inputStream = new Scanner(System.in);
    String commandString;

    while (!(commandString = inputStream.nextLine()).equals("exit")){
      todoListController.processUserCommand(commandString);
    }
    todoListController.saveResult();
  }
}
