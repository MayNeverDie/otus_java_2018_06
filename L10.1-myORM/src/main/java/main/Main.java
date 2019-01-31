package main;

import base.DBService;
import simple.DBServiceSimple;

public class Main {
    public static void main(String[] args) throws Exception {
        new Main().run();
    }

    public void run() throws Exception {
        try(DBService dbService = new DBServiceSimple() ){
            System.out.println(dbService.getMetaData());
            dbService.prepareTables();
            dbService.addUsers("tully","sully");
            dbService.dropTables();
        }
    }
}
