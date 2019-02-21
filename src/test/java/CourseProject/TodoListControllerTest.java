package CourseProject;

import com.todoList.TodoListController.TodoListController;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TodoListControllerTest {

  @Test
  public void createList() throws IOException {
    TodoListController todoListController = new TodoListController();
    todoListController.processUserCommand("create -list 1");
    Assert.assertEquals(todoListController.getListOfTaskLists().size(), 1);
    Assert.assertEquals(todoListController.getListOfTaskLists().get(0).getListName(), "list 1");
    Assert.assertEquals(todoListController.getListOfTaskLists().get(0).getTaskList().size(), 0);
  }

  @Test
  public void addTaskToList() throws IOException {
    TodoListController todoListController = new TodoListController();
    todoListController.processUserCommand("create -list 1");
    todoListController.processUserCommand("add -list 1 -task 1");
    Assert.assertEquals(todoListController.getListOfTaskLists().get(0).getTaskList().size(), 1);
    Assert.assertEquals(todoListController.getListOfTaskLists().get(0).getTaskList().get(0).getTaskText(), "task 1");
    Assert.assertEquals(todoListController.getListOfTaskLists().get(0).getTaskList().get(0).getStatus(), "todo");
  }

  @Test
  public void deleteList() throws IOException {
    TodoListController todoListController = new TodoListController();
    todoListController.processUserCommand("create -list 1");
    todoListController.processUserCommand("create -list 2");
    todoListController.processUserCommand("delete -list 1");
    Assert.assertEquals(todoListController.getListOfTaskLists().size(), 1);
    Assert.assertEquals(todoListController.getListOfTaskLists().get(0).getListName(), "list 2");
  }

  @Test
  public void editTaskStatus() throws IOException {
    TodoListController todoListController = new TodoListController();
    todoListController.processUserCommand("create -listName");
    todoListController.processUserCommand("add -listName -task 1");
    todoListController.processUserCommand("setstatus -listName -1 -done");
    Assert.assertEquals(todoListController.getListOfTaskLists().get(0).getTaskList().get(0).getStatus(), "done");
  }

  @Test
  public void editTask() throws IOException {
    TodoListController todoListController = new TodoListController();
    todoListController.processUserCommand("create -listName");
    todoListController.processUserCommand("add -listName -task 1");
    todoListController.processUserCommand("edit -listName -1 -updated task");
    Assert.assertEquals(todoListController.getListOfTaskLists().get(0).getTaskList().get(0).getTaskText(), "updated task");
  }

  /*
  @Test
  public void saveListsToFile() throws IOException {
    String FILE_PATH = "todoListCatalog\\test.json";
    TodoListController todoListController = new TodoListController();
    todoListController.processUserCommand("create -list");
    todoListController.processUserCommand("add -list -task 1");
    todoListController.saveResult();
    List<TaskList> listOfTaskLists = new ArrayList<>();
    FileController fileController = new FileController();
    listOfTaskLists = fileController.loadListOfTaskLists(FILE_PATH);
    Assert.assertEquals(listOfTaskLists.size(), 1);
    Assert.assertEquals(listOfTaskLists.get(0).getListName(), "list");
    Assert.assertEquals(listOfTaskLists.get(0).getTaskList().size(), 1);
    Assert.assertEquals(listOfTaskLists.get(0).getTaskList().get(0).getStatus(), "todo");
    Assert.assertEquals(listOfTaskLists.get(0).getTaskList().get(0).getTaskText(), "task 1");
  }
  */
}
