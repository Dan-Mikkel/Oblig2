import java.util.LinkedList;

public class Task {

  private int id, estimatedTime, estimatedStaff;
  private String name;
  private int earliestStart = -1, latestStart = -1;
  private LinkedList<Task> dependencyEdges = new LinkedList<Task>(), outEdges = new LinkedList<Task>();
  private boolean completedStatus = false, canStart = false;

  public Task(int id) {

    this.id = id;
    this.estimatedTime = -1;
    this.estimatedStaff = -1;
    this.name = null;
    this.dependencyEdges = null;
    this.outEdges = null;

  }

  public Task(int id, int estimatedTime, int estimatedStaff, String name, LinkedList<Task> dependencyEdges, LinkedList<Task> outEdges) {

    this.id = id;
    this.estimatedTime = estimatedTime;
    this.estimatedStaff = estimatedStaff;
    this.name = name;
    this.dependencyEdges = dependencyEdges;
    this.outEdges = outEdges;

  }

  public void setEstimatedTime(int estimatedTime) {this.estimatedTime = estimatedTime;}

  public void setEstimatedStaff(int estimatedStaff) {this.estimatedStaff = estimatedStaff;}

  public void setName(String name) {this.name = name;}

  public void addDependencyEdge(Task task) {this.dependencyEdges.add(task);}

  public void addOutEdges(Task task) {this.outEdges.add(task);}

  public void setStartTimes(int[] startTimes) {

    this.earliestStart = startTimes[0];
    this.latestStart = startTimes[1];

  }

  public int getID() {return this.id;}

  public int getEstimatedTime() {return this.estimatedTime;}

  public int getEstimatedStaff() {return this.estimatedStaff;}

  public String getName() {return this.name;}

  public int getEarliestStart() {return this.earliestStart;}

  public int getLatestStart() {return this.latestStart;}

  public LinkedList<Task> getDependencyEdges() {return this.dependencyEdges;}

  public LinkedList<Task> getOutEdges() {return this.outEdges;}

  public int getSlack() {return this.latestStart-this.earliestStart;}

  public boolean getCompletedStatus() {return this.completedStatus;}

  public boolean canStart() {return this.canStart;}

  public boolean isCritical() {return (this.getSlack() == 0);}

}
