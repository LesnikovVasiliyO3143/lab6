package com.lesnikov.server;

import java.util.Scanner;

public class Server {

    public static void main(String[] args) {
        CollectionManager collectionManager = CollectionManager.getInstance(System.getenv("filepath"));
        new CommandInvoker(collectionManager).run();
    }

}
