import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.*;
import java.io.IOException;

class ThreadsAndObservers implements ThreadClass.ThreadClassInterface {

  ThreadClass thread;

  public static void main(String[] args) {
    //ThreadClass thread = new ThreadClass(this);
    ThreadsAndObservers threadCaller = new ThreadsAndObservers();
    threadCaller.createThreads();
    threadCaller.userInput();
  }

  private void createThreads() {
    thread = new ThreadClass(this);
    thread.start();
  }

  private void userInput() {
    while (true) {
      String userInput = new Scanner(System.in).nextLine();
      if (userInput.equals("new")) {
        ThreadClass thread = new ThreadClass(this);
        thread.start();
      }
    }
  }

  @Override
  public void progressUpdate(int progress) {
    updateConsole(": On Progress: " + progress + "%");
  }

  @Override
  public void progressCompleted() {
    updateConsole(": On_Complete");
    Thread.currentThread().interrupt();
  }

  private void updateConsole(String updateText) {
    System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) + updateText);
  }

}
