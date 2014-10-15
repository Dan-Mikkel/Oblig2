public class TaskManager {

  Task taskTree;
  int numberOfNodes;

  public TaskManager(String fileName) {

    TaskScanner scanner = new TaskScanner();
    scanner.scanFile(fileName);
    this.taskTree = scanner.getTaskTree();//The task tree is still unoptimized
    this.numberOfNodes = scanner.getTotalTasks();

    //Check for cyclic

  }

  public boolean isCircular(Task task) {

    if (task == null) return false;


    //This array will know whenever a node has been visited, that is also including the dummy startnode.
    boolean[] visitedState = new boolean[numberOfNodes+1];



    return false;

  }

  private boolean isCircularHelper(Task task, boolean[] visitedState) {

    if (task == null) return false;

    boolean result = false;
    visitedState[task.getID()] = true;

    for (Task t : task.getOutEdges()) {



    }

  }

  public void optimizeTasksOnTime(Task task) {

    //Getting the first level of tasks
    Task[] currentTask = task.getOutEdges();

  }

  public Task getTaskTree() {return this.taskTree;}

}
