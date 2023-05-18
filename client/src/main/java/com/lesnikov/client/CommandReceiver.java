package com.lesnikov.client;

import com.lesnikov.serializers.MovieSerializer;
import com.lesnikov.serializers.PersonSerializer;
import com.lesnikov.utils.InputReader;
import com.lesnikov.wrappers.Request;
import com.lesnikov.wrappers.MovieWrapper;
import com.lesnikov.wrappers.Response;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.*;

public class CommandReceiver {

    private static final Set<String> callStack = new LinkedHashSet<>();

    public CommandReceiver() {}

    public void run() {
        while (true) {
            try {
                SocketChannel socketChannel = SocketChannel.open(
                        new InetSocketAddress("localhost", 6060));
                Scanner scanner = new Scanner(System.in);
                String[] splitCommand;
                String command;
                while ((command = scanner.nextLine()) != null) {
                    splitCommand = command.trim().toLowerCase().split(" ", 2);
                    try {
                        Request commandWrapper = new Request();
                        switch (splitCommand[0]) {
                            case "help":
                                commandWrapper.setCommand("help");
                                break;
                            case "info":
                                commandWrapper.setCommand("info");
                                break;
                            case "show":
                                commandWrapper.setCommand("show");
                                break;
                            case "insert":
                                commandWrapper.setCommand("insert");
                                commandWrapper.setKey(Long.parseLong(splitCommand[1]));
                                commandWrapper.setMovieWrapper(MovieWrapper.wrap(InputReader.receiveMovie()));
                                break;
                            case "update":
                                commandWrapper.setCommand("update");
                                commandWrapper.setKey(Long.parseLong(splitCommand[1]));
                                commandWrapper.setMovieWrapper(MovieWrapper.wrap(InputReader.receiveMovie()));
                                break;
                            case "remove_by_id":
                                commandWrapper.setCommand("remove_by_id");
                                commandWrapper.setKey(Long.parseLong(splitCommand[1]));
                                break;
                            case "clear":
                                commandWrapper.setCommand("clear");
                                break;
                            case "execute_script":
                                commandWrapper.setCommand("execute_script");
                                commandWrapper.setCommandList(parseScript(splitCommand[1]));
                                break;
                            case "exit":
                                System.out.println("Disconnecting from server and finishing a program...");
                                System.exit(0);
                                break;
                            case "filter_greater_than_operator":
                                commandWrapper.setCommand("filter_greater_than_operator");
                                commandWrapper.setPerson(InputReader.receivePerson());
                                break;
                            case "remove_greater":
                                commandWrapper.setCommand("remove_greater");
                                commandWrapper.setMovieWrapper(MovieWrapper.wrap(InputReader.receiveMovie()));
                                break;
                            case "remove_lower":
                                commandWrapper.setCommand("remove_lower");
                                commandWrapper.setMovieWrapper(MovieWrapper.wrap(InputReader.receiveMovie()));
                                break;
                            case "print_field_descending_operator":
                                commandWrapper.setCommand("print_field_descending_operator");
                                break;
                            case "print_unique_total_box_office":
                                commandWrapper.setCommand("print_unique_total_box_office");
                                break;
                            case "replace_if_greater":
                                commandWrapper.setCommand("replace_if_greater");
                                commandWrapper.setKey(Long.parseLong(splitCommand[1]));
                                commandWrapper.setMovieWrapper(MovieWrapper.wrap(InputReader.receiveMovie()));
                                break;
                            default:
                                System.out.println("Unknown command! Type [help] for reading the manual.");
                        }
                        ByteBuffer buffer = ByteBuffer.allocate(100000);

                        // creating byte-request
                        ByteArrayOutputStream out = new ByteArrayOutputStream();
                        ObjectOutputStream oos = new ObjectOutputStream(out);
                        oos.writeObject(commandWrapper);
                        oos.flush();
                        byte[] bytes = out.toByteArray();

                        buffer.put(bytes);
                        buffer.flip();
                        socketChannel.write(buffer);
                        buffer.clear();

                        socketChannel.read(buffer);
                        bytes = buffer.array();
                        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
                        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                        Response response = (Response) objectInputStream.readObject();
                        System.out.println(response.getMessage());

                    } catch (Exception e) {
                        System.out.println("Incorrect format of command entering. Type [help] for checking the manual.");
                        System.out.println(e.getMessage());
                        e.printStackTrace();
                    }
                }
                scanner.close();
            } catch (IOException ioException) {
                System.out.println("Cannot connect to the server. Reconnecting...");
            }
        }
    }

    private List<Request> parseScript(String filepath) {
        List<Request> commandsFromScript = new ArrayList<>();
        if (!callStack.contains(filepath)) {
            callStack.add(filepath);
            try {
                BufferedReader reader = new BufferedReader(new FileReader(filepath));
                String[] scriptSplitCommand;
                String command;
                while ((command = reader.readLine()) != null) {
                    scriptSplitCommand = command.trim().split(" ", 2);
                    switch (scriptSplitCommand[0]) {
                        case "help":
                            Request helpCommand = new Request();
                            helpCommand.setCommand("help");
                            commandsFromScript.add(helpCommand);
                            break;
                        case "info":
                            Request infoCommand = new Request();
                            infoCommand.setCommand("info");
                            commandsFromScript.add(infoCommand);
                            break;
                        case "show":
                            Request showCommand = new Request();
                            showCommand.setCommand("show");
                            commandsFromScript.add(showCommand);
                            break;
                        case "insert":
                            Request insertCommand = new Request();
                            insertCommand.setCommand("insert");
                            insertCommand.setKey(Long.parseLong(scriptSplitCommand[1]));
                            insertCommand.setMovieWrapper(MovieWrapper
                                    .wrap(MovieSerializer.deserialize(scriptSplitCommand[1])));
                            commandsFromScript.add(insertCommand);
                            break;
                        case "update":
                            Request updateCommand = new Request();
                            updateCommand.setCommand("update");
                            updateCommand.setKey(Long.parseLong(scriptSplitCommand[1]));
                            updateCommand.setMovieWrapper(MovieWrapper
                                    .wrap(MovieSerializer.deserialize(scriptSplitCommand[1])));
                            commandsFromScript.add(updateCommand);
                            break;
                        case "remove_by_id":
                            Request removeByIdCommand = new Request();
                            removeByIdCommand.setCommand("remove_by_id");
                            removeByIdCommand.setKey(Long.parseLong(scriptSplitCommand[1]));
                            commandsFromScript.add(removeByIdCommand);
                            break;
                        case "clear":
                            Request clearCommand = new Request();
                            clearCommand.setCommand("clear");
                            commandsFromScript.add(clearCommand);
                            break;
                        case "execute_script":
                            commandsFromScript.addAll(parseScript(scriptSplitCommand[1]));
                            break;
                        case "filter_greater_than_operator":
                            Request filterGreaterThanOperatorCommand = new Request();
                            filterGreaterThanOperatorCommand.setCommand("filter_greater_than_operator");
                            filterGreaterThanOperatorCommand.setPerson(
                                    PersonSerializer.deserialize(scriptSplitCommand[1]));
                            commandsFromScript.add(filterGreaterThanOperatorCommand);
                            break;
                        case "remove_greater":
                            Request removeGreaterCommand = new Request();
                            removeGreaterCommand.setCommand("remove_greater");
                            removeGreaterCommand.setMovieWrapper(MovieWrapper
                                    .wrap(MovieSerializer.deserialize(scriptSplitCommand[1])));
                            commandsFromScript.add(removeGreaterCommand);
                            break;
                        case "remove_lower":
                            Request removeLowerCommand = new Request();
                            removeLowerCommand.setCommand("remove_lower");
                            removeLowerCommand.setMovieWrapper(MovieWrapper
                                    .wrap(MovieSerializer.deserialize(scriptSplitCommand[1])));
                            commandsFromScript.add(removeLowerCommand);
                            break;
                        case "print_field_descending_operator":
                            Request printFieldDescendingOperatorCommand = new Request();
                            printFieldDescendingOperatorCommand
                                    .setCommand("print_field_descending_operator");
                            commandsFromScript.add(printFieldDescendingOperatorCommand);
                            break;
                        case "print_unique_total_box_office":
                            Request printUniqueTotalBoxOfficeCommand = new Request();
                            printUniqueTotalBoxOfficeCommand
                                    .setCommand("print_unique_total_box_office");
                            commandsFromScript.add(printUniqueTotalBoxOfficeCommand);
                            break;
                        case "replace_if_greater":
                            Request replaceIfGreaterCommand = new Request();
                            replaceIfGreaterCommand.setCommand("replace_if_greater");
                            replaceIfGreaterCommand.setKey(Long.parseLong(scriptSplitCommand[1]));
                            replaceIfGreaterCommand.setMovieWrapper(MovieWrapper
                                    .wrap(MovieSerializer.deserialize(scriptSplitCommand[2])));
                            commandsFromScript.add(replaceIfGreaterCommand);
                            break;
                        default:
                            break;
                    }
                }
            } catch (IOException ioException) {
                return new ArrayList<>();
            }
            callStack.remove(filepath);
            return commandsFromScript;
        } else {
            return new ArrayList<>();
        }
    }
}
