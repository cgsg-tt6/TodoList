/**
 * Project name: Todo-List.
 * Programmer: Troitskaya Tamara (github https://github.com/cgsg-tt6).
 * Pet project, August 2023.
 */
package org.example;

import org.example.managers.InputManager;
import org.example.managers.Writer;
import org.example.task.Task;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Handles clients requests.
 */
public class Client {
    public void run(Scanner sc) {
        Writer wr = new Writer();
        InputManager im = new InputManager();
        Map<Integer, Task> tasks = new HashMap<>();
        int lastId = 0;

        /* TODO
         *  save
         *  done, delete
         *  edit
         *  history
         *  sort
         *  filter
         */
        String command;

        while (true) {
            wr.write("Input command name:", false);
            command = sc.nextLine();
            switch (command) {
                case "add" -> {
                    wr.write("ADD:", true);
                    lastId += 1;
                    tasks.put(lastId, im.readTask().setId(lastId));
                    wr.write("Task added\n", true);
                }
                case "show" -> {
                    wr.write("SHOW:", true);
                    for (var key : tasks.keySet()) {
                        wr.write(tasks.get(key).toString(), true);
                    }
                    wr.write("", true);
                }
                case "help" -> {
                    wr.write("HELP:\nhelp\nadd\nshow\nexit\n", true);
                }
                case "exit" -> {
                    wr.write("Exit...", true);
                    System.exit(0);
                }
                default -> wr.write(command + ": command does not exist\n", true);
            }
        }
    }
}
