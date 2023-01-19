package fr.luclyoko.osufrlivelogs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class LogManager {

    private Main main;

    private File logsDir;

    private SimpleDateFormat simpleDateFormat;

    public LogManager(Main main) {
        this.main = main;
        this.logsDir = new File("plugins/osufrlivelogs");
        this.simpleDateFormat = new SimpleDateFormat("[MM/dd/yyyy - HH:mm:ss] - ");
        this.simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+1"));
        checkLogDir();
    }

    public void addLog(String log) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(logsDir + "/" + getDateNameFile() + ".txt", true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        PrintWriter out = new PrintWriter(fw);
        out.println(simpleDateFormat.format(System.currentTimeMillis()) + log);
        out.close();
    }

    private void checkLogDir() {
        if (!(logsDir.exists() && logsDir.isDirectory())) logsDir.mkdir();
    }

    private String getDateNameFile() {
        SimpleDateFormat date = new SimpleDateFormat("yyyy_MM_dd");
        date.setTimeZone(TimeZone.getTimeZone("GMT+1"));
        return date.format(System.currentTimeMillis());
    }
}
