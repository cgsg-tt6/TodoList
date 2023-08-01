/**
 * Project name: Todo-List.
 * Programmer: Troitskaya Tamara (github https://github.com/cgsg-tt6).
 * Pet project, August 2023.
 */
package org.example.managers;

import org.example.task.Priority;
import org.example.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import static org.example.task.Priority.*;

/**
 * Inputs data safely from console or from file.
 */
public class InputManager {
    private Scanner sc;
    private Writer writer;

    /**
     * Creates input manager for console input.
     */
    public InputManager() {
        this.sc = new Scanner(System.in);
        writer = new Writer();
    }

    /**
     * Creates input manager for file input.
     * @param inputFile script with commands and data.
     * @param outputFile file for kind of logs (not a json file for collection!)
     */
    public InputManager(File inputFile, File outputFile) {
        try {
            this.sc = new Scanner(inputFile);
            writer = new Writer(outputFile);
        } catch (FileNotFoundException e) {
            System.err.println("Input manager: File not found");
        }
    }

    private Priority readPriority(String inp) {
        String high, medium, low, def;
        inp = inp.toUpperCase();
        if (inp.equals(HIGH.toString())) {
            return HIGH;
        } else if (inp.equals(MEDIUM.toString())) {
            return MEDIUM;
        } else if (inp.equals(LOW.toString()))
            return LOW;
        return DEFAULT;
    }

    // TODO make it possible to input null.
    private Time inpDuration() {
        try {
            return Time.valueOf(sc.nextLine());
        } catch (IllegalArgumentException e) {
            writer.write("Incorrect time format. Input again (hh:mm:ss): ", true);
            return inpDuration();
        }
    }

    // TODO more convenient (input data then time).
    // TODO exception when creation time > due time
    private LocalDateTime inpDueTime() {
        try {
            return LocalDateTime.parse(sc.nextLine());
        } catch (DateTimeParseException e) {
            writer.write("Incorrect time format. Input again (yyyy-mm-dd\'T\'hh:mm:ss): ", true);
            return inpDueTime();
        }
    }

    public Task readTask() {
        writer.write("Input Task:\nname: ", false);
        Task res = new Task(sc.nextLine());
        writer.write("description: ", false);
        res.setDescription(sc.nextLine());
        writer.write("priority (high/medium/low): ", false);
        res.setPriority(readPriority(sc.nextLine()));
        writer.write("time to complete task (hh:mm:ss): ", false);
        res.setTimeToComplete(inpDuration());
        writer.write("due date: ", false);
        res.setDueDate(inpDueTime());
        return res;
    }

}
