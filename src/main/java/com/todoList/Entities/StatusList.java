package com.todoList.Entities;

import java.util.ArrayList;
import java.util.List;

public class StatusList {

  public static final String TODO = "todo";
  public static final String DONE = "done";
  public static final String CANCELLED = "cancelled";

  public static List<String> defineStatusTypes(){
    List<String> statusType = new ArrayList<>();
    statusType.add(TODO);
    statusType.add(DONE);
    statusType.add(CANCELLED);
    return statusType;
  }
}
