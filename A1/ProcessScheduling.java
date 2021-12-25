import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Iterator;

public class ProcessScheduling {
    /**
     * Simulates a process scheduler of a computer system: Reads processes from an
     * input txt file, initializes and executes the simulation, and outputs the
     * simulation to the console and an output txt file. Priority queue with custom
     * comparator and Process are used as data structure.
     * 
     * @param args command-line args as array of String objects.
     * @return no return.
     */
    public static void main(String[] args) {
        // Initializes variables.
        PriorityQueue<Process> d = new PriorityQueue<>(new CustomComparatorD()); // Creates empty priority queue d.
        int currentTime = 0;
        float totalWaitTime = 0.0F;
        final int MAXWAITTIME = 30;
        boolean running = false;
        PriorityQueue<Process> q = new PriorityQueue<>(new CustomComparatorQ()); // Creates empty priority queue q.
        Process currentlyRunningProcess = null;
        int totalNumberProcesses = 0;

        // Creates PrintAndWrite object to output strings to console and to write
        // strings in output text file.
        PrintAndWrite output = new PrintAndWrite();

        // Reads input txt file and stores values in priority queue d.
        try {
            Scanner scanner = new Scanner(new File("process_scheduling_input.txt"));
            String[] line;
            while (scanner.hasNext()) {
                line = scanner.nextLine().split("\\s+"); // Splits string by whitespace.
                // Creates Process object; sets process id, priority, duration, and arrival time
                // as integers.
                d.add(new Process(Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2]),
                        Integer.parseInt(line[3])));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Clones priority queue d to output processes in cloneD via remove() in correct
        // order.
        PriorityQueue<Process> cloneD = new PriorityQueue<>(d);
        Process p;
        String outputString;
        while (!cloneD.isEmpty()) {
            p = cloneD.remove();
            // Builds outputString.
            outputString = "Id = " + p.id + ", priority = " + p.pr + ", duration = " + p.duration + ", arrival time = "
                    + p.arrivalTime;
            // Outputs outputString to console and output text file.
            output.printAndWrite(outputString);
        }

        // Outputs MAXWAITTIME to console and output text file.
        output.printAndWrite("");
        output.printAndWrite("Maximum wait time = " + MAXWAITTIME);
        output.printAndWrite("");

        // True until priority queues d and q are empty and no more processes are
        // executed.
        while (!d.isEmpty() || !q.isEmpty() || running) {
            // If true, removes process from priority queue d and inserts it in priority
            // queue q. (First process in d has lowest arrivalTime, since d is prioritized
            // by arrivalTime.)
            if (!d.isEmpty() && d.peek().arrivalTime <= currentTime) {
                q.add(d.remove());

                // True if priority queue d has become empty.
                if (d.isEmpty()) {
                    // Outputs that d has become empty to console and output text file.
                    output.printAndWrite("");
                    output.printAndWrite("D becomes empty at time " + currentTime);
                    output.printAndWrite("");
                }
            }

            // True if a process is running and end of process running time has been
            // reached.
            if (running && currentTime >= currentlyRunningProcess.finishTime) {
                running = false;
                output.printAndWrite("Process " + currentlyRunningProcess.id + " finished at time " + currentTime);
                output.printAndWrite("");

                // Updates priorities of processes that have been waiting longer than
                // MAXWAITTIME; uses iterator to traverse q.
                output.printAndWrite("Update priority:");
                Iterator<Process> iteratorQ = q.iterator();
                Process process;
                while (iteratorQ.hasNext()) {
                    process = iteratorQ.next();
                    // Updates process waitTime (wait time in q).
                    process.waitTime = currentTime - process.arrivalTime;
                    if (process.waitTime > MAXWAITTIME) {
                        output.printAndWrite("PID = " + process.id + ", wait time = " + process.waitTime
                                + ", current priority = " + process.pr);
                        // Updates process priority (lower value means higher priority).
                        process.pr -= 1;
                        // Priority of a process cannot fall below 1.
                        if (process.pr < 1) {
                            process.pr = 1;
                        }
                        output.printAndWrite("PID = " + process.id + ", new priority = " + process.pr);
                    }
                }
                output.printAndWrite("");
            }

            // True if priority queue q is not empty and no process is running.
            if (!q.isEmpty() && !running) {
                // Removes process with smallest priority from q, assigns removed process to
                // currentlyRunningProcess, and sets running true.
                currentlyRunningProcess = q.remove();
                running = true;
                // Updates totalWaitTime, finishTime, and totalNumberProcesses.
                totalWaitTime += currentlyRunningProcess.waitTime;
                currentlyRunningProcess.finishTime = currentTime + currentlyRunningProcess.duration;
                totalNumberProcesses += 1;

                // Outputs removed processes (currentlyRunningProcess) to console and output
                // text file.
                output.printAndWrite("Process removed from queue is: id = " + currentlyRunningProcess.id + ", at time "
                        + currentTime + ", wait time = " + currentlyRunningProcess.waitTime + " Total wait time = "
                        + totalWaitTime);
                output.printAndWrite("Process id = " + currentlyRunningProcess.id);
                output.printAndWrite("\tPriority = " + currentlyRunningProcess.pr);
                output.printAndWrite("\tArrival = " + currentlyRunningProcess.arrivalTime);
                output.printAndWrite("\tDuration = " + currentlyRunningProcess.duration);
            }

            // Increments currentTime by 1, since 1 iteration of while loop corresponds to 1
            // unit of time.
            currentTime += 1;
        }

        // Outputs totalWaitTime and average wait time to console and output text file.
        output.printAndWrite("Total wait time = " + totalWaitTime);
        output.printAndWrite("Average wait time = " + totalWaitTime / totalNumberProcesses);

        // Closes FileWriter.
        try {
            output.writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
