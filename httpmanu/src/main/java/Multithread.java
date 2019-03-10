public class Multithread implements Runnable{
    private Thread thread;
    private GetRes res;

    public Multithread(Thread thread, GetRes res) {
        this.thread = thread;
        this.res = res;
    }

    public void run() {

    }
}
