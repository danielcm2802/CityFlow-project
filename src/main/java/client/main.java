package client;

import model.Route;

import java.io.IOException;
import java.util.LinkedList;

public class main {
    public static void main(String[] args) throws IOException {
        mainSys m = new mainSys();
        LinkedList<Route> r = m.conn.getRoute(0,20);
        System.out.println(r);
//        ServerConn serverConn = new ServerConn();
//        HashMap<String,Object> map = new HashMap<>();
//        map.put("GET_ROUTE","");
//        map.put("SOURCE",0);
//        map.put("DEST",17);
//        System.out.println(serverConn.send_msg(map));
    }
}
