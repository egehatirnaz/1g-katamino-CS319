package GameManagement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.beans.property.SimpleStringProperty;
import  java.util.concurrent.TimeUnit;
import javafx.scene.text.Text;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

public class TimeKeeper extends Thread{
    private Thread thread = null;
    private SimpleDateFormat sdf = new SimpleDateFormat("mm:ss:S");
    private String[] split;
    public SimpleStringProperty min, sec, millis, sspTime;
    private long time;

    public static void main(String[] args) throws InterruptedException {
        TimeKeeper t = new TimeKeeper();
        t.run();
        System.out.println(t.getSspTime());
        TimeUnit.SECONDS.sleep(10);
        System.out.println(t.getTime());
        //t.stopTimer(t);

    }

    public TimeKeeper() {
        min = new SimpleStringProperty("00");
        sec = new SimpleStringProperty("00");
        millis = new SimpleStringProperty("00");
        sspTime = new SimpleStringProperty("00:00:00");
    }

    public void startTimer(long time) {
        this.time = time;
        thread = new Thread(this);
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.start();
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

    public void run(Text tL) {
        try {
            while (!thread.isInterrupted()) {
                setTime(time);
                sleep(10);
                time = time + 10;
                //tL.setText(min.get() + ":" + sec.get() + ":" + millis.get());
                System.out.println(getSspTime());
            }
        } catch (Exception e) {
        }

    }
}
