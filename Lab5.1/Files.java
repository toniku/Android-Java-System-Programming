import java.util.Scanner;

class Files implements FilesThread.FilesThreadInterface {

  public static void main(String[] args) {
    Files main = new Files();
    main.threadReadFile();
  }

  private void threadReadFile() {
    FilesThread readFileThread = new FilesThread(this, 0, "");
    readFileThread.start();
  }

  private void threadWriteToFile() {
    System.out.print("\n\nPlease enter new text:-> ");
    Scanner scanner = new Scanner(System.in);
    FilesThread writeToFileThread = new FilesThread(this, 1, scanner.nextLine());
    writeToFileThread.start();
    scanner.close();
  }

  @Override
  public void updateConsole(String progressText) {
    System.out.println(progressText);
  }

  @Override
  public void readFile(String textInFile) {
    System.out.print("\n" + textInFile);
    threadWriteToFile();
  }
}
