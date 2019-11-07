class TamagotchiThread extends Thread {

  private int food;
  private String status;
  private int ID;
  TamagotchiInterface callbackInterface;
  boolean alive;

  public interface TamagotchiInterface {
    public void TamagotchiStatus(TamagotchiThread tamagotchi);
    public void updateTamagotchiStatus();
  }

  public TamagotchiThread(int startFood, int ID, TamagotchiInterface cInterface) {
    this.callbackInterface = cInterface;
    this.food = startFood;
    this.ID = ID;
    this.alive = true;
    this.status = "Happy and Alive!";
  }

  public void run() {
    while (alive) {
      try {
        food--;
        if (food < 1 || food > 20) {
          status = "Dead. Hungry.";
          alive = false;
        }
        callbackInterface.TamagotchiStatus(this);
        sleep(2000);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public boolean feed() {
    this.food += 10;
    return true;
  }

  public String getStatus() {
    if (food < 0 || food > 20) {
      this.status = "Tamagotchi dead";
    }
    return this.status;
  }

  public int getFood() {
    return this.food;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public void setFood(int food) {
      this.food += food;
  }

  public void setID(int ID) {
    this.ID = ID;
  }

  public int getID() {
    return this.ID;
  }
}
