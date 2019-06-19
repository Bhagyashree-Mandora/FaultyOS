import java.io.*;
import java.nio.channels.FileLock;

public class ReaderWriter {

    // private static final String FILE_NAME = "/home/bhago/workspace/IdeaProjects/FaultyOS/out/production/FaultyOS/count.txt";
    private static final String FILE_NAME = "/home/bhago/Desktop/count.txt";
    private FileLock lock;
    private File f;
    private RandomAccessFile randomAccessFile;
    private String caller;

    public ReaderWriter(String caller) throws IOException {
        this.caller = caller;
        f = new File(FILE_NAME);
        if(!f.exists()) {
            initializeTempFile();
        }
    }

    public void lock() throws IOException {
        randomAccessFile = new RandomAccessFile(f, "rw");
        lock = randomAccessFile.getChannel().lock();
    }

    public void unlock() throws IOException {
        if(lock != null){
            lock.release();
        }
    }

    private void initializeTempFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));
        writer.write(String.valueOf(0));
        writer.flush();
        writer.close();
    }

    public int read() throws IOException {
        String line = randomAccessFile.readLine();
        if(line == null || line.isEmpty()){
            return 0;
        }
        return Integer.parseInt(line);
    }

    public void write(int val) throws IOException {
        System.out.println("["+caller+"] Writing: " + val);
        randomAccessFile.setLength(0);
        randomAccessFile.writeBytes(String.valueOf(val));
    }

    public void close() throws IOException {
        if (randomAccessFile != null) {
            randomAccessFile.close();
        }
    }
}
