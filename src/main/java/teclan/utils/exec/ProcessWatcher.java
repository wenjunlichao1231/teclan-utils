package teclan.utils.exec;

import java.util.Timer;
import java.util.TimerTask;

public class ProcessWatcher {

    private long    timeout;
    private Process process;
    private boolean timedOut;
    private Timer   timer;

    public ProcessWatcher(Process process, long timeout) {
        this.timeout = timeout;
        this.process = process;
    }

    public void start() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                process.destroy();
                timedOut = true;
            }
        }, timeout);
    }

    public void cancel() {
        timer.cancel();
    }

    public boolean timedOut() {
        return timedOut;
    }

}
