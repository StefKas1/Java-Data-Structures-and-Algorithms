public class Process {
    int id;
    int pr;
    int duration;
    int arrivalTime;

    int waitTime;
    int finishTime;

    /**
     * Constructor for class Process, which initializes Process with given values
     * ​​of arguments.
     * 
     * @param id          integer which represents process id.
     * @param pr          integer which represents priority of process.
     * @param duration    integer which represents execution time of process.
     * @param arrivalTime integer which represents time when process arrives at
     *                    system.
     * @return no return.
     */
    Process(int id, int pr, int duration, int arrivalTime) {
        this.id = id;
        this.pr = pr;
        this.duration = duration;
        this.arrivalTime = arrivalTime;
    }

}
