class ThreadClass extends Thread {

  boolean progressCompleted = false;
  ThreadClassInterface callbackInterface;
  private int progress = 0;

  public interface ThreadClassInterface {
    public void progressUpdate(int progress);

    public void progressCompleted();
  }

  public ThreadClass(ThreadClassInterface threadInterface) {
    this.callbackInterface = threadInterface;
  }

  public void run() {
    try {
      while (true) {
        progress += 10;
        callbackInterface.progressUpdate(progress);
        if (progress == 100) {
          callbackInterface.progressCompleted();
        }
        sleep(1000);
      }
    } catch (Exception e) {
    }
  }
}
