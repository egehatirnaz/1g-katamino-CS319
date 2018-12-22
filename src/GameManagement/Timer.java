package GameManagement;

import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.beans.property.SimpleStringProperty;

public class Timer extends Thread{


    private Thread thread = null;
    private SimpleDateFormat sdf = new SimpleDateFormat("mm:ss:S");
    private String[] split;
    private SimpleStringProperty min, sec, millis, sspTime;
    private long time;
    private boolean finished;
/*
    public static void main(String[] args) {
        Timer t = new Timer();
        t.startTimer(00, finished );
    }*/

    public Timer() {
        min = new SimpleStringProperty("00");
        sec = new SimpleStringProperty("00");
        millis = new SimpleStringProperty("00");
        sspTime = new SimpleStringProperty("00:00:00");
        finished = false;
        this.startTimer( time, finished);
    }

    public void startTimer(long time, boolean isFinished) {
        this.time = time;
        thread = new Thread(this);
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.start();
        if( isFinished )
            stopTimer(time);

    }

    public void stopTimer(long time) {
        if (thread != null) {
            thread.interrupt();
        }
        this.time = time;
        setTime(time);
    }

    public void setTime(long time) {
        this.time = time;
        split = sdf.format(new Date(time)).split(":");
        min.set(split[0]);
        sec.set(split[1]);

        if (split[2].length() == 1) {
            split[2] = "0" + split[2];
        }
        millis.set(split[2].substring(0, 2));

        sspTime.set(min.get() + ":" + sec.get() + ":" + millis.get());
    }

    public long getTime() {
        return time;
    }

    public SimpleStringProperty getSspTime() {
        return sspTime;
    }

    @Override
    public void run() {
        try {
            while (!thread.isInterrupted()) {
                setTime(time);
                sleep(10);
                time = time + 10;
            }
        } catch (Exception e) {
        }

    }
}
