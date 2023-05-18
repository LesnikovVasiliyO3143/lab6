package com.lesnikov.commands;

public class ExitCommand {

    public String execute() {
        System.out.println("Finishing a program...");
        System.exit(0);
        return "Program finished!\n";
    }

}