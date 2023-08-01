/**
 * Project name: Todo-List.
 * Programmer: Troitskaya Tamara (github https://github.com/cgsg-tt6).
 * Pet project, August 2023.
 */
package org.example.managers;

import org.example.task.Task;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Executes commands.
 */
public class CommandManager {
    private final Scanner sc;
    Writer wr = new Writer();
    InputManager im = new InputManager();
    Map<Integer, Task> tasks = new HashMap<>();
    int lastId = 0;

    public CommandManager() {
        this.sc = new Scanner(System.in);
    }

    public CommandManager(Scanner scanner) {
        this.sc = scanner;
    }

    public void execute(String commandName) {
        try {
            Method method = CommandManager.class.getMethod(commandName);
            method.invoke(this);
        } catch (NoSuchMethodException e) {
            wr.write(commandName + ": command does not exist\n", true);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void help() {
        wr.write("HELP:\nhelp\nadd\nshow\nexit\n", true);
    }

    public void add() {
        wr.write("ADD:", true);
        lastId += 1;
        tasks.put(lastId, im.readTask().setId(lastId));
        wr.write("Task added\n", true);
    }

    public void show() {
        wr.write("SHOW:", true);
        for (var key : tasks.keySet()) {
            wr.write(tasks.get(key).toString(), true);
        }
        wr.write("", true);
    }

    public void exit() {
        wr.write("Exit...", true);
        System.exit(0);
    }
}
