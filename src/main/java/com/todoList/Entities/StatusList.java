package com.todoList.Entities;

import java.util.ArrayList;
import java.util.List;

public class StatusList {

  public static final String TODO = "todo";
  public static final String DONE = "done";
  public static final String CANCEL = "cancel";

  public static List<String> defineStatusTypes(){
    List<String> statusType = new ArrayList<>();
    statusType.add(TODO);
    statusType.add(DONE);
    statusType.add(CANCEL);
    return statusType;
  }
}
