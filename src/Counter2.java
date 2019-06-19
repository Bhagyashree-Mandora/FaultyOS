import java.io.IOException;
import java.util.Random;

public class Counter2 {

    public static void main(String[] args) throws IOException, InterruptedException {
        Random random = new Random();
        String workerID = "worker-" + random.nextInt(1000);

        Spawner spawner = new Spawner(workerID);
        spawner.start();

        System.out.println("Starting "+workerID);
        ReaderWriter readerWriter = new ReaderWriter(workerID);
        try{
            readerWriter.lock();

            int i = readerWriter.read();
            System.out.println("["+workerID+"] Starting value: " + i);
            while (i<100){
                i++;
                readerWriter.write(i);
                Thread.sleep(2000);
            }
        } finally {
            readerWriter.unlock();
            readerWriter.close();
        }
        spawner.stop();
    }

}
