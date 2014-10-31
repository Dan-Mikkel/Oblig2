import java.util.LinkedList;

public class Task {

  private int id, estimatedTime, estimatedStaff;
  private String name;
  private int earliestStart = 0, latestStart = 0;
  private LinkedList<Task> dependencyEdges, outEdges;
  private boolean completedStatus = false, canStart = false;
  private int[] dependencies;
  private boolean hasBeenVisited = false;
  private boolean hasBeenStarted = false;
  private boolean isCritical = false;
  private boolean finished = false;
  public static int nWorkers = 0;

  public Task(int id) {

    this.id = id;
    this.estimatedTime = 0;
    this.estimatedStaff = 0;
    this.name = null;
    this.dependencyEdges = new LinkedList<Task>();
    this.outEdges = new LinkedList<Task>();
    this.isCritical = true;

  }

  public Task(int id, int estimatedTime, int estimatedStaff, String name, int[] dependencies) {

    this.id = id;
    this.estimatedTime = estimatedTime;
    this.estimatedStaff = estimatedStaff;
    this.name = name;
    this.dependencies = dependencies;
    this.dependencyEdges = new LinkedList<Task>();
    this.outEdges = new LinkedList<Task>();

  }

  public void setEstimatedTime(int estimatedTime) {this.estimatedTime = estimatedTime;}

  public void setEstimatedStaff(int estimatedStaff) {this.estimatedStaff = estimatedStaff;}

  public void setName(String name) {this.name = name;}

  public void setDependencies(int[] dependencies) {this.dependencies = dependencies;}

  public void addDependencyEdge(Task task) {this.dependencyEdges.add(task);}

  public void addOutEdge(Task task) {this.outEdges.add(task);}

  public void setStartTimes(int[] startTimes) {

    this.earliestStart = startTimes[0];
    this.latestStart = startTimes[1];

  }

  public int getID() {return this.id;}

  public int getEstimatedTime() {return this.estimatedTime;}

  public int getEstimatedStaff() {return this.estimatedStaff;}

  public String getName() {return this.name;}

  public int[] getDependencies() {return this.dependencies;}

  public int getEarliestStart() {return this.earliestStart;}

  public void setEarliestStart(int earliestStart) {this.earliestStart = earliestStart;}

  public int getLatestStart() {return this.latestStart;}

  public void setLatestStart(int latestStart) {this.latestStart = latestStart;}

  public Task[] getDependencyEdges() {return this.dependencyEdges.toArray(new Task[0]);}

  public Task[] getOutEdges() {return this.outEdges.toArray(new Task[0]);}

  public int getSlack() {return this.latestStart-this.earliestStart;}

  public boolean getCompletedStatus() {return this.completedStatus;}

  public boolean canStart() {return this.canStart;}

  public boolean isCritical() {return this.isCritical;}

  public void setCritical() {this.isCritical = true;}

  public void visit() {this.hasBeenVisited = true;}

  public void leave() {

    this.hasBeenVisited = false;

  }

  public void leaveAll() {

    this.hasBeenVisited = false;
    for (Task t : this.getOutEdges()) t.leaveAll();

  }

  public boolean hasBeenVisited() {return this.hasBeenVisited;}

  public void tick(int time) {

    int timeThisTaskIsDone = this.estimatedTime+this.earliestStart;

    if (time == this.earliestStart && !this.hasBeenStarted) {

      System.out.println("\tStarting: " + this.id);
      this.hasBeenStarted = true;
      nWorkers += this.estimatedStaff;

      for (Task t : this.outEdges) {
        t.tick(time);
      }

      return;

    } else if (time >= timeThisTaskIsDone) {

      if (time == timeThisTaskIsDone && !finished) {
        System.out.println("\tFinished: " + this.id);
        nWorkers -= this.estimatedStaff;
        this.finished = true;
      }

      for (Task t : this.outEdges) {
        t.tick(time);
      }

      return;

    } else {

      return;

    }

  }

  public void printAllInfo() {

    if (this.hasBeenVisited()) {

      return;

    }

    this.visit();
    System.out.println("Task: " + this.getID());
    System.out.println("\tName of the task: " + this.getName());
    System.out.println("\tEstimated time: " + this.getEstimatedTime() + ". Estimated staff: " + this.getEstimatedStaff());
    System.out.println("\tEstimated slack: " + this.getSlack());
    System.out.println("\tEarliest starting time: " + this.getEarliestStart() + ". Latest starting time: " + this.getLatestStart());
    if (this.isCritical) System.out.println("\tTASK IS CRITICAL!!!");
    System.out.print("\tTasks that depend on this task:\n\t\t");

    for (Task t : this.getOutEdges()) {

      System.out.print(t.getID() + " ");

    }

    System.out.println("\n");

    for (Task t : this.getOutEdges()) {

      t.printAllInfo();

    }

  }

}
