import java.util.Scanner;
import java.io.File;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.io.FileNotFoundException;

public class TaskScanner {

  private Task startTask = new Task(0);
  private int totalTasks = 0;

  public void scanFile(String fileName) {

    Scanner fileScanner = null;

    try {

      File fileToBeScanned = new File(System.getProperty("user.dir")+"/"+fileName);
      fileScanner = new Scanner(fileToBeScanned);
      fileScanner.useDelimiter("\r\n\t");

      //Starting scanning
      this.totalTasks = fileScanner.nextInt();

      Task[] allTasks = new Task[totalTasks];

      while (fileScanner.hasNextLine()) {

        int id = fileScanner.nextInt();
        String name = fileScanner.next();
        int estimatedTime = fileScanner.nextInt();
        int estimatedStaff = fileScanner.nextInt();




      }


    } catch (InputMismatchException ex) {

      System.out.println("Incorrect format on file!");

    } catch (NoSuchElementException ex) {

      System.out.println("Error! Reached end of file!");

    } catch (FileNotFoundException ex) {

      System.out.println("Error! The file " + fileName + " was not found!");

    } finally {

      if (fileScanner != null) fileScanner.close();

    }

    return;

  }

  public Task getStartTask() {return this.startTask;}

}
