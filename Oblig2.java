public class Oblig2 {

  public static void main(String[] args) {

    System.out.println("Starting task manager for: " + args[0]);
    TaskManager tm = new TaskManager(args[0]);
    tm.printAllTasks();

  }

}
