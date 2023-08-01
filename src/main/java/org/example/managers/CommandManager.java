/**
 * Project name: Todo-List.
 * Programmer: Troitskaya Tamara (github https://github.com/cgsg-tt6).
 * Pet project, August 2023.
 */
package org.example.managers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.task.Task;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Executes commands.
 */
public class CommandManager {
    private final Scanner sc;
    private final Writer wr = new Writer();
    private final InputManager im = new InputManager();
    private Map<Integer, Task> tasks = new HashMap<>();
    private int lastId = 0;
    private final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

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

    public void save() {
        //wr.write("Input path to file: ", false);
        String path = "C:\\Z\\work\\coding\\Todo-List\\src\\main\\resources\\out.json";
        try (BufferedWriter output = new BufferedWriter(new FileWriter(path))) {
            String json = mapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(tasks);
            output.write(json);
            wr.write("Data saved. (src\\main\\resources\\out.json)\n", true);
        }
        catch (IOException e) {
            //e.printStackTrace();
            wr.write("Save: incorrect path.\n", true);
            //save();
        }
    }

    public void load() {
        String path = "C:\\Z\\work\\coding\\Todo-List\\src\\main\\resources\\out.json";
        HashMap<Integer, Task> taskHashMap = new HashMap<>();
        try {
            TypeReference<HashMap<Integer, Task>> typeRef = new TypeReference<>() {};
            tasks = mapper.readValue(Paths.get(path).toFile(), typeRef);
            wr.write("Collection loaded.\n", true);
            show();
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
            wr.write("Load : incorrect path.\n", true);
        }
    }

}
