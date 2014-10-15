import java.util.Scanner;
import java.io.File;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.io.FileNotFoundException;

public class TaskScanner {

  private Task startTask = null;//This one is to keep track of the start of the project
  private int totalTasks = 0;

  public void scanFile(String fileName) {

    Scanner fileScanner = null;

    try {

      File fileToBeScanned = new File(System.getProperty("user.dir")+"/"+fileName);
      fileScanner = new Scanner(fileToBeScanned);

      //Scan total amount of tasks
      this.totalTasks = Integer.parseInt(fileScanner.nextLine());

      //Making a variable assisting in the linking process
      Task[] allTasks = new Task[totalTasks];
      startTask = new Task(0);

      //Skip the pesky new line that does nothing useful
      fileScanner.nextLine();

      //Read the file
      while (fileScanner.hasNextLine()) {

        //Scan a line
        String scannedLine = fileScanner.nextLine();

        System.out.println("Line scanned: " + scannedLine);

        //Split the line by it's whitespaces
        String[] splitLine = scannedLine.split("\\s+");

        //Get the ID
        int id = Integer.parseInt(splitLine[0]);

        //Get the name
        String name = splitLine[1];

        //Get the estimated time
        int estimatedTime = Integer.parseInt(splitLine[2]);

        //Get the estimated staff
        int estimatedStaff = Integer.parseInt(splitLine[3]);

        //Process dependencies
        int dependencyZeroIndex = 4;//This is where the dependencies start

        int[] dependencies = new int[splitLine.length-dependencyZeroIndex];

        //Generating dependency array
        for (int i = dependencyZeroIndex; i < splitLine.length; i++) {

          int dependency = Integer.parseInt(splitLine[i]);

          //Filling the demand in the assignment that we had to generate a task the moment we read about it. I don't understand why you would want me to do that, but ok.
          if (dependency != 0 && allTasks[dependency-1] == null) allTasks[dependency-1] = new Task(dependency);

          //System.out.println(dependency);
          dependencies[i-dependencyZeroIndex] = dependency;

        }

        //Making a new task
        if (allTasks[id-1] == null) {

          allTasks[id-1] = new Task(id, estimatedTime, estimatedStaff, name, dependencies);

        } else {//Filling out the rest of the data if the task already exists

          allTasks[id-1].setEstimatedTime(estimatedTime);
          allTasks[id-1].setEstimatedStaff(estimatedStaff);
          allTasks[id-1].setName(name);
          allTasks[id-1].setDependencies(dependencies);

        }

      }

      //Linking tasks together based on dependencies
      for (int i = 0; i < allTasks.length; i++) {

        Task currentTask = allTasks[i];
        int[] taskDependencies = currentTask.getDependencies();

        for (int j = 0; j < taskDependencies.length; j++) {

          if (taskDependencies[j] == 0) {

            //If the task doesn't depend on anything, then it is a start task.
            if (j == 0) {

              currentTask.addDependencyEdge(startTask);
              startTask.addOutEdge(currentTask);

            }
            break;

          } else {

            Task dependantTask = allTasks[taskDependencies[j]-1];
            currentTask.addDependencyEdge(dependantTask);
            dependantTask.addOutEdge(currentTask);

          }

        }

      }

      //Printout for testing
      for (int i = 0; i < allTasks.length; i++) {

        Task t = allTasks[i];
        System.out.println("TaskID: " + t.getID() + ". Task name: " + t.getName()+ ". Dependencies:");
        Task[] dependencies = t.getDependencyEdges();
        System.out.print("\t");

        for (int j = 0; j < dependencies.length; j++) {

          System.out.print("Task: " + dependencies[j].getID() + " ");

        }
        System.out.println("\n");

      }

    } catch (InputMismatchException ex) {

        System.out.println("Error! Incorrect format on file!");
        //ex.printStackTrace();

      } catch (NoSuchElementException ex) {

        System.out.println("Error! Reached end of file!");

      } catch (FileNotFoundException ex) {

        System.out.println("Error! The file " + fileName + " was not found!");

      } catch (NumberFormatException ex) {

        System.out.println("Error! Incorrect format on file! Problem scanning the lines");
        ex.printStackTrace();

      } finally {

        if (fileScanner != null) fileScanner.close();

      }

  }

  public Task getTaskTree() {return this.startTask;}

  public int getTotalTasks() {return this.totalTasks;}

}
