/**
 * Project name: Todo-List.
 * Programmer: Troitskaya Tamara (github https://github.com/cgsg-tt6).
 * Pet project, August 2023.
 */
package org.example;

import java.util.Scanner;

/**
 * Entry point.
 */
public class App {
    // TODO write read.me and maybe todo-list.
    // TODO save to json.
    // TODO write scripts and test.
    public static void main(String[] args) {
        Client client = new Client();
        client.run(new Scanner(System.in));
    }
}
