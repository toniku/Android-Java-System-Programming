import java.io.*;

class FilesThread extends Thread {

  private String textInFile, line, newText;
  private String fileName = "text.txt";
  private int caseOperation;

  private FilesThreadInterface callbackInterface;

  public interface FilesThreadInterface {
    void readFile(String textInFile);
    void updateConsole(String progressText);
  }

  public FilesThread(FilesThreadInterface threadInterface, int operation, String textToWrite) {
    callbackInterface = threadInterface;
    caseOperation = operation;
    newText = textToWrite;
  }

  public void run() {
    switch (caseOperation) {
    case 0:
      try {
        StringBuilder stringBuilder = new StringBuilder();
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        while ((line = bufferedReader.readLine()) != null) {
          stringBuilder.append(line);
        }
        textInFile = stringBuilder.toString();
        bufferedReader.close();
        callbackInterface.readFile("File " + fileName + " contains text: " + textInFile);
      } catch (IOException e) {
        callbackInterface.updateConsole("Error reading file: " + fileName);
      }
      break;
    case 1:
      try {
        callbackInterface.updateConsole("\nWriting to file...");
        FileWriter fileWriter = new FileWriter(fileName);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(newText);
        bufferedWriter.close();
        callbackInterface.updateConsole("\nWriting done.");
      } catch (IOException e) {
        callbackInterface.updateConsole("Error writing to a file: " + fileName);
      }
      break;
    default:
      break;
    }
  }
}
