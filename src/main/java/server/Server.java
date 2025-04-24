package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server extends Thread {
    public MainSystem mainSystem;
    private ServerSocket server;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    public static final int PORT = 5000;
    public static int count = 0;

    public Server() {
        mainSystem = new MainSystem(6,5, 200, (short) 2, 150, (short) 1);
        try{
            server = new ServerSocket(PORT);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public HashMap<String,Object> handle_msg(HashMap<String,Object> msg) {
        HashMap<String,Object> response = new HashMap<>();
        if(msg.containsKey("GRID_SIZE")) {
            response.put("GRID_SIZE",mainSystem.cityGraph.rows*mainSystem.cityGraph.cols);
        }
        if(msg.containsKey("GET_ROUTE")) {
            response.put("ROUTE",mainSystem.add_vehicle(count,(int)msg.get("SOURCE"), (int) msg.get("DEST")));
            count++;
        }
        return response;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Socket client = server.accept();
                in = new ObjectInputStream(new BufferedInputStream(
                        client.getInputStream()));
                HashMap<String,Object> msg = (HashMap<String, Object>) in.readObject();

                System.out.println(client + ": " + msg);
                HashMap<String,Object> response =  handle_msg(msg);

                out = new ObjectOutputStream(client.getOutputStream());
                out.writeObject(response);
                out.flush();
                in.close();
                out.close();

                client.close();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
}
