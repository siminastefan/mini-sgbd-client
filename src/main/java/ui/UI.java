package ui;

import service.DatabaseService;
import service.TableService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UI {

    private DatabaseService databaseService;
    private TableService tableService;

    public UI(DatabaseService databaseService, TableService tableService) {
        this.databaseService = databaseService;
        this.tableService = tableService;
    }

    public void runUi() throws Exception {
        displayMenu();
        while (true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println(">");
            String command = null;
            try {
                command = reader.readLine().strip();
                if (command.matches("create database [a-zA-Z0-9]+")) { createDatabaseUi(command); }
                else if (command.matches("create table .*")) { createTableUi(command); }
                else if (command.matches("exit")) return;
                else {
                    System.out.println("misspelled command");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void createTableUi(String command) throws Exception {
        this.tableService.addTable(command);
    }

    private void createDatabaseUi(String command) throws Exception {
        String databaseName = command.split(" ")[2];
        this.databaseService.addDatabase(databaseName);
    }

    private static void displayMenu() {
        String menu = "Menu:\n";
        menu += "\tcreate database\n";
        menu += "\tcreate table\n";
        menu += "\tdrop database\n";
        menu += "\tdrop table\n";
        menu += "\tcreate index\n";
        menu += "\texit\n";
        System.out.println(menu);
    }


}
