package client;

import client.gui.MainWindow;

//the main systems. connects all components
public class mainSys {
    public ServerConn conn;
    public MainWindow window;
    public mainSys() {
        conn = new ServerConn();
        window = new MainWindow(conn);
    }
}
