import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PrintAndWrite {
    File file;
    FileWriter writer;

    /**
     * Constructor for class PrintAndWrite, which creates file handle, an output txt
     * file if it does not already exist, and write stream.
     * 
     * @return no return.
     */
    PrintAndWrite() {
        try {
            // Creates file handle.
            file = new File("process_scheduling_output.txt");
            // Creates output text file if it does not already exist.
            file.createNewFile();
            // Creates handle to write stream.
            writer = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Outputs (print and write) output argument to console and output txt file.
     * 
     * @param output String containing the text to be output.
     * @return no return.
     */
    void printAndWrite(String output) {
        try {
            // Prints output to console.
            System.out.println(output);
            // Writes output to output file.
            writer.write(output);
            // Inserts line break in file.
            writer.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
