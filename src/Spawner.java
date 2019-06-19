import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Spawner extends Thread {
    private static final int TOTAL_COUNT = 3;
    String name;

    Spawner(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while(true) {
            try {
                reconcileWorkers();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void reconcileWorkers() throws IOException {
        System.out.println(name + " is reconciling");
        int workerCount = 0;
        Process process = Runtime.getRuntime().exec("ps -e -o command");
        BufferedReader r = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = null;

        while ((line = r.readLine()) != null) {
            if (line.startsWith("java")) {
                //System.out.println(line);
                workerCount++;
            }
        }
        //System.out.println("Worker count: " + workerCount);

        if (workerCount < TOTAL_COUNT) {
            spawnProcess();
        }
    }

    public void spawnProcess() throws IOException {
        System.out.println("Starting new worker");
        //Process process = Runtime.getRuntime().exec(new String[] {"java", "Counter3"},null);
        //System.out.println(process);
//        ProcessBuilder pb = new ProcessBuilder("java out/production/FaultyOS/Counter3");// /home/bhago/workspace/IdeaProjects/FaultyOS/out/production/FaultyOS/Counter3");
        ProcessBuilder pb = new ProcessBuilder("java", "Counter2");// /home/bhago/workspace/IdeaProjects/FaultyOS/out/production/FaultyOS/Counter3");
        //pb.directory(new File("out/production/FaultyOS"));
        pb.directory(new File("/home/bhago/workspace/IdeaProjects/FaultyOS/out/production/FaultyOS"));
        pb.redirectError(ProcessBuilder.Redirect.INHERIT);
        pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        pb.start();
    }
}
