/**
 * Project name: Todo-List.
 * Programmer: Troitskaya Tamara (github https://github.com/cgsg-tt6).
 * Pet project, August 2023.
 */
package org.example.managers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Allows to write output to console or to file.
 * Kind of a logger.
 */
public class Writer {
    private final boolean toConsole;
    private File outputFile;

    public Writer() {
        this.toConsole = true;
    }

    public Writer(File file) {
        toConsole = false;
        outputFile = file;
    }

    /**
     * Displays the message.
     * @param message the message to be displayed.
     * @param toFile sometimes it is meaningful to show some messages only in console.
     *               In that case this parameter should be false.
     */
    public void write(String message, boolean toFile) {
        if (toConsole) {
            System.out.println(message);
        } else if (toFile) {
            try {
                BufferedWriter bf = new BufferedWriter(new FileWriter(outputFile));
                bf.write(message + "\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
