package section3InputOutput.characterStreams;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Main {
    static String inFileStr = "files/text.txt";

    public static void main(String[] args) {
        fileReadWithBufferAndArray(inFileStr);
    }

    public static void fileReadWithBufferAndArray(String inFileStr) {
        System.out.println("\nInside fileReadWithBufferAndArray ...");

        // used for benchmarking
        long startTime, elapsedTime;

        // print file length
        File fileIn = new File(inFileStr);
        System.out.println("File size is " + fileIn.length() + " bytes");

        try (
                FileInputStream fis = new FileInputStream(inFileStr);
                InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr)
        ) {
            startTime = System.nanoTime();

            char[] charBuf = new char[4000];
            int numCharsRead;
            // read characters into the buffer
            while ((numCharsRead = br.read(charBuf)) != -1) {
                System.out.print(new String(charBuf, 0, numCharsRead));
            }

            elapsedTime = System.nanoTime() - startTime;
            System.out.println();
            System.out.println("---");
            System.out.println("Elapsed Time is " + (elapsedTime / 1000000.0) + " msec");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
