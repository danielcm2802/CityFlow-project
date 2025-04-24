package client;

import client.gui.MainWindow;

public class mainSys {
    public ServerConn conn;
    public MainWindow window;
    public mainSys() {
        conn = new ServerConn();
        window = new MainWindow(conn);
    }
}
