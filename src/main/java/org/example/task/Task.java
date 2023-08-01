/**
 * Project name: Todo-List.
 * Programmer: Troitskaya Tamara (github https://github.com/cgsg-tt6).
 * Pet project, August 2023.
 */
package org.example.task;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.function.Function;

import static org.example.task.Priority.*;

public class Task {
    private int id; // not null
    private String name; // not null
    private String description;
    private Priority priority;
    private Time timeToComplete;
    private LocalDateTime creationDate; // generated automatically
    private LocalDateTime dueDate;

    @Override
    public String toString() {
        Function<Priority, String> priorToString = (pr) -> (pr == HIGH ? "high" : pr == MEDIUM ? "medium" : pr == LOW ? "low" : "default");
        Function<Object, String> notNull = (ob) -> (ob == null ? "-" : ob.toString());
        return "name: " + name + "\ndescription: " + notNull.apply(description) + "\npriority: " + priorToString.apply(priority) +
                "\ntime to complete: " + notNull.apply(timeToComplete) + "\ncreation date: " + creationDate +
                "\ndue date: " + notNull.apply(dueDate) + "\n";
    }

    public Task(String name) {
        this.name = name;
        setCreationDate();
    }

    public Task setId(int id) {
        this.id = id;
        return this;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Task setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Task setPriority(Priority priority) {
        this.priority = priority;
        return this;
    }

    public Priority getPriority() {
        return priority;
    }

    public Task setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public Task setTimeToComplete(Time timeToComplete) {
        this.timeToComplete = timeToComplete;
        return this;
    }

    public Time getTimeToComplete() {
        return timeToComplete;
    }

    public void setCreationDate() {
        this.creationDate = LocalDateTime.now();
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }
}
