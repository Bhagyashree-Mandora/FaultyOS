import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Counter {
    public static void main(String[] args) throws IOException, InterruptedException {
        String fileName = "count.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        for (int i=1; i<=100; i++){
            writer.write(String.valueOf(i) + "\n");
            writer.flush();
            Thread.sleep(100);
        }
        writer.close();
        Thread.sleep(100000);
    }
}
