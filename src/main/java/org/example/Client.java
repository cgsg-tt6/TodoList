/**
 * Project name: Todo-List.
 * Programmer: Troitskaya Tamara (github https://github.com/cgsg-tt6).
 * Pet project, August 2023.
 */
package org.example;

import org.example.managers.CommandManager;
import org.example.managers.Writer;

import java.util.Scanner;

/**
 * Entry point.
 * Handles clients requests.
 */
public class Client {

    // TODO write read.me and maybe todo-list.
    // TODO save to json.
    // TODO write scripts and test.
    public static void main(String[] args) {
        Client client = new Client();
        client.run(new Scanner(System.in));
    }
    public void run(Scanner sc) {
        Writer wr = new Writer();
        CommandManager cm = new CommandManager(sc);

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
            cm.execute(command.trim());
        }
    }
}
