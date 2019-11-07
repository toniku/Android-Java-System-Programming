import java.util.Scanner;
import java.net.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class HTTPThread extends Thread {

  private String urlString;

  public HTTPThread(String urlString){
    this.urlString = urlString;
  }

  public void run() {
    try {
      URL url = new URL(urlString);
      BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
      String line;
      while ((line = in.readLine()) != null) {
        System.out.println(line + "\n");
      }
      in.close();
    } catch (Exception e) {
      System.out.println("Malformed URL: " + e.getMessage());
    }
  }
}

class Files {

  public static void main(String args[]) {
    getURLFromUser();
  }

  public static void getURLFromUser() {
    System.out.print("Give URL:> ");
    String urlAddress = new Scanner(System.in).nextLine();
    if (isValidURL(urlAddress)){
      System.out.println("Starting downloading from: " + urlAddress);
      new HTTPThread(urlAddress).start();
    }
    else {
      System.out.println("URL WRONG!: " + urlAddress + "\nURL must start with 'https://'");
      getURLFromUser();
    }
  }

  public static boolean isValidURL(String urlString)
  {
    try {
      URL url = new URL(urlString);
      url.toURI();
      return true;
    } catch (Exception e)
    {
      return false;
    }
  }
}
