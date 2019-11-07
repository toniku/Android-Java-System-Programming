import java.util.*;
import java.io.IOException;

class Tamagotchi implements TamagotchiThread.TamagotchiInterface {

  private int userInput = 5;

  public static void main(String[] args) {
    Tamagotchi mainTamagotchi = new Tamagotchi();
    clearConsole();
    mainTamagotchi.createTamagotchis();
    mainTamagotchi.userInput();
  }

  public ArrayList<TamagotchiThread> tamagotchis = new ArrayList<>();

  public void createTamagotchis() {
    System.out.println("Tamagotchi farm created!\n");
     for (int i = 0; i < 5; i++) {
       TamagotchiThread newTamagotchi = new TamagotchiThread(10, i, this);
       tamagotchis.add(newTamagotchi);
       newTamagotchi.start();
      }
  }

  public void TamagotchiStatus(TamagotchiThread tamagotchi) {
    tamagotchis.set(tamagotchi.getID(), tamagotchi);
    if(tamagotchi.getID() == 4) {
      updateTamagotchiStatus();
    }
  }

  public void updateTamagotchiStatus() {
    clearConsole();
    updateConsole();
  }

  public void userInput() {
    while (true) {
      userInput = new Scanner(System.in).nextInt();
      if (userInput > -1 && userInput < 5) {
        TamagotchiThread feedTamagotchi = tamagotchis.get(userInput);
        feedTamagotchi.feed();
        clearConsole();
        updateConsole();
      }
      }
    }

  public final static void clearConsole() {
    try {
      new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    } catch (Exception e) {
    }
  }

  public void updateConsole() {
    if (userInput < 5) {
      System.out.println("Tamagotchi " + userInput + ". Fed.\n");
    }
    System.out.println("Tamagotchi statuses:");
    TamagotchiThread tempTama = new TamagotchiThread(0, 0, this);
    for (int i = 0; i < tamagotchis.size(); i++) {
      tempTama = tamagotchis.get(i);
      System.out.println(tempTama.getID() + ". Tamagotchi " + tempTama.getStatus() + " Food left: " + tempTama.getFood());
    }
  }
}
