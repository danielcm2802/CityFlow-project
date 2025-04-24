package client;

import model.Route;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;

public class ServerConn {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    public static final int PORT = 5000;

    public ServerConn() {
    }

    public int getGridSize() throws IOException {
        HashMap<String,Object> map = new HashMap<>();
        map.put("GRID_SIZE", "");
        map = send_msg(map);
        return (int)map.get("GRID_SIZE");
    }
    public LinkedList<Route> getRoute(int source, int destination) throws IOException {
        HashMap<String,Object> map = new HashMap<>();
        map.put("GET_ROUTE", "");
        map.put("SOURCE", source);
        map.put("DEST", destination);
        map = send_msg(map);
        return (LinkedList<Route>)map.get("ROUTE");
    }

    public HashMap<String,Object> send_msg(HashMap<String,Object> map) throws IOException {
        try {
            socket = new Socket("127.0.0.1",5000);

        } catch (IOException e) {
            System.out.println("something went wrong");
            return null;
        }
        out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(map);
        out.flush();
        in = new ObjectInputStream(new BufferedInputStream(
                socket.getInputStream()));

        try {
            HashMap<String,Object> response = (HashMap<String,Object>)in.readObject();
            in.close();
            out.close();
            socket.close();
            return response;
        } catch (ClassNotFoundException e) {
            System.out.println("something went wrong");
            return null;
        }
    }
}
