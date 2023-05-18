package com.lesnikov.server;

import com.lesnikov.commands.HelpCommand;
import com.lesnikov.commands.InfoCommand;
import com.lesnikov.commands.InsertCommand;
import com.lesnikov.commands.ShowCommand;
import com.lesnikov.model.Movie;
import com.lesnikov.serializers.CollectionSerializer;
import com.lesnikov.wrappers.Request;
import com.lesnikov.wrappers.Response;
import com.lesnikov.commands.*;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

public class CommandInvoker {

    private final CollectionManager collectionManager;

    public CommandInvoker(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public void run() {
        try {
            // Создаем серверный канал
            Selector selector = Selector.open();
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(6060));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("Server started on port " + 6060);
                while (true) {
                    int readyChannels = selector.selectNow();
                    if (readyChannels == 0) {
                        continue;
                    }
                    Set<SelectionKey> selectedKeys = selector.selectedKeys();
                    Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
                    while (keyIterator.hasNext()) {
                        SelectionKey key = keyIterator.next();
                        if (key.isAcceptable()) {
                            // Принимаем соединение от клиента
                            SocketChannel clientChannel = serverSocketChannel.accept();
                            clientChannel.configureBlocking(false);
                            clientChannel.register(selector, SelectionKey.OP_READ);
                            System.out.println("Accepted connection from " + clientChannel.getRemoteAddress());
                        } else if (key.isReadable()) {
                            // Читаем данные от клиента
                            SocketChannel clientChannel = (SocketChannel) key.channel();
                            ByteBuffer buffer = ByteBuffer.allocate(100000);
                            int count = clientChannel.read(buffer);
                            if (count == -1) {
                                // Клиент отключился
                                clientChannel.close();
                                key.cancel();
                                System.out.println("Client " + clientChannel.getRemoteAddress() + " disconnected");
                                continue;
                            }
                            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer.array());
                            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                            Request request = (Request) objectInputStream.readObject();
                            System.out.println("Received request from " + clientChannel.getRemoteAddress() + ": " + request);

                            String responseMessage = "";
                            try {
                                switch (request.getCommand()) {
                                    case "help":
                                        responseMessage = new HelpCommand(this.collectionManager).execute();
                                        break;
                                    case "info":
                                        responseMessage = new InfoCommand(this.collectionManager).execute();
                                        break;
                                    case "show":
                                        responseMessage = new ShowCommand(this.collectionManager).execute();
                                        break;
                                    case "insert":
                                        if (request.getKey() == null || request.getMovieWrapper() == null) {
                                            responseMessage = "Incorrect arguments of a command.";
                                        } else {
                                            Long keyForInsert = request.getKey();
                                            Movie movieForInsert = request.getMovieWrapper().unwrap();
                                            responseMessage = new InsertCommand(this.collectionManager)
                                                    .execute(keyForInsert, movieForInsert);
                                        }
                                        break;
                                    case "update":
                                        if (request.getKey() == null || request.getMovieWrapper() == null) {
                                            responseMessage = "Incorrect arguments of a command.";
                                        } else {
                                            Long keyForUpdate = request.getKey();
                                            Movie movieForUpdate = request.getMovieWrapper().unwrap();
                                            responseMessage = new UpdateCommand(this.collectionManager)
                                                    .execute(keyForUpdate, movieForUpdate);
                                        }
                                        break;
                                    case "remove_key":
                                        if (request.getKey() == null) {
                                            responseMessage = "Incorrect arguments of a command.";
                                        } else {
                                            Long keyForRemove = request.getKey();
                                            responseMessage = new RemoveKeyCommand(this.collectionManager).execute(keyForRemove);
                                        }
                                        break;
                                    case "clear":
                                        responseMessage = new ClearCommand(this.collectionManager).execute() + "\n";
                                        break;
                                    case "execute_script":
                                        if (request.getCommandList() == null || request.getCommandList().isEmpty()) {
                                            responseMessage = "Incorrect arguments for a command" + "\n";
                                        } else {
                                            responseMessage = new ExecuteScriptCommand(this.collectionManager)
                                                    .execute(request.getCommandList()) + "\n";
                                        }
                                        break;
                                    case "filter_greater_than_operator":
                                        if (request.getPerson() == null) {
                                            responseMessage = "Incorrect arguments for a command" + "\n";
                                        } else {
                                            responseMessage = new FilterGreaterThanOperatorCommand(this.collectionManager).execute(
                                                    request.getPerson()) + "\n";
                                        }
                                        break;
                                    case "remove_greater":
                                        if (request.getMovieWrapper() == null) {
                                            responseMessage = "Incorrect arguments for a command" + "\n";
                                        } else {
                                            responseMessage = new RemoveGreaterCommand(this.collectionManager)
                                                    .execute(request.getMovieWrapper().unwrap()) + "\n";
                                        }
                                        break;
                                    case "remove_lower":
                                        if (request.getMovieWrapper() == null) {
                                            responseMessage = "Incorrect arguments for a command" + "\n";
                                        } else {
                                            responseMessage = new RemoveLowerCommand(this.collectionManager)
                                                    .execute(request.getMovieWrapper().unwrap()) + "\n";
                                        }
                                        break;
                                    case "print_field_descending_operator":
                                        responseMessage = new PrintFieldDescendingOperatorCommand(this.collectionManager)
                                                .execute() + "\n";
                                        break;
                                    case "print_unique_total_box_office":
                                        responseMessage = new PrintUniqueTotalBoxOfficeCommand(this.collectionManager).execute() + "\n";
                                        break;
                                    case "replace_if_greater":
                                        if (request.getKey() == null || request.getMovieWrapper() == null) {
                                            responseMessage = "Incorrect arguments for a command" + "\n";
                                        } else {
                                            Long keyy = request.getKey();
                                            Movie movie = request.getMovieWrapper().unwrap();
                                            responseMessage = new ReplaceIfGreaterCommand(this.collectionManager)
                                                    .execute(keyy, movie) + "\n";
                                        }
                                        break;
                                    default:
                                        responseMessage = "Unknown command!" + "\n";
                                        break;
                                }
                                this.collectionManager.saveCollection(CollectionSerializer.serialize(
                                                this.collectionManager.getCollection()));
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                                e.printStackTrace();
                                System.exit(1);
                            }

                            // Отправляем ответ клиенту

                            ByteArrayOutputStream out = new ByteArrayOutputStream();
                            ObjectOutputStream oos = new ObjectOutputStream(out);
                            oos.writeObject(new Response(responseMessage));
                            oos.flush();
                            byte[] bytes = out.toByteArray();

                            buffer.clear();
                            buffer.put(bytes);
                            buffer.flip();
                            clientChannel.write(buffer);
                        }
                        keyIterator.remove();
                    }
                }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Internal server error.");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
