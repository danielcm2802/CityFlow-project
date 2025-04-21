package server.gui.body;

import server.gui.myColors;
import server.models.Edge;
import server.models.Graph;
import server.models.Vehicle;
import server.models.Vertex;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;

public class CityMap extends JPanel {
    public Graph cityGraph;
    public ArrayList<Vehicle> vehicles;
    private final int width = 600;
    private final int height = 400;

    public CityMap(Graph cityGraph, ArrayList<Vehicle> vehicles) {
        this.cityGraph = cityGraph;
        this.vehicles = vehicles;
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(myColors.backgroundColor);
        this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawCityMap((Graphics2D) g);
    }

    private void drawCityMap(Graphics2D g2d) {
//       cityGraph.printForApp();
        float xsize = cityGraph.vertices.getLast().x_cord;
        float ysize = cityGraph.vertices.getLast().y_cord;
        int x1,y1,x2,y2;
        int margx,margy;

        g2d.setStroke(new BasicStroke(5));
        for(Vertex v : cityGraph.vertices) {
            for (Edge e: v.adjacent_edges){
                Vertex v2 = cityGraph.vertices.get(e.to);
                margx = 7 + ((v2.id < v.id)? 6:0);
                margy = 7 + ((v2.id > v.id)? 6:0);
                x1 = Math.round((v.x_cord / xsize) * (width - 45))+10+margx;
                y1 = Math.round((v.y_cord / ysize) * (height - 45))+10+margy;
                x2 = Math.round((v2.x_cord / xsize) * (width - 45))+10+margx;
                y2 = Math.round((v2.y_cord / ysize) * (height - 45))+10+margy;
                g2d.setColor(new Color(Math.min(91+5*e.total_cars,255),
                        Math.max(103-5*e.total_cars,0),
                        Math.max(112-5*e.total_cars,0)));
                g2d.drawLine(x1, y1, x2, y2);
//                System.out.println("x1: " + x1 + " y1: " + y1 + " x2: " + x2 + " y2: " + y2);
            }
        }
        for (Vertex v : cityGraph.vertices) {
            x1 = Math.round((v.x_cord / xsize) * (width - 45))+10;
            y1 = Math.round((v.y_cord / ysize) * (height - 45))+10;
            g2d.setColor(myColors.nodeOutlineColor);
            g2d.fillOval(x1-2, y1-2, 24, 24);
            g2d.setColor(myColors.nodeColor);
            g2d.fillOval(x1, y1, 20, 20);

        }
        g2d.setColor(myColors.carColor);
        synchronized (vehicles) {
            for (Vehicle v : vehicles) {
                if (v.routes.getFirst().id == -1) {
                    int seconds = (int) Duration.between(LocalTime.now(), v.next_removal).toSeconds();

                    x1 = (int) ((cityGraph.vertices.get(v.last_route_id).x_cord * seconds +
                            cityGraph.vertices.get(v.routes.get(1).id).x_cord * (v.routes.getFirst().seconds - seconds))
                            / v.routes.getFirst().seconds);
                    y1 = (int) ((cityGraph.vertices.get(v.last_route_id).y_cord * seconds +
                            cityGraph.vertices.get(v.routes.get(1).id).y_cord * (v.routes.getFirst().seconds - seconds))
                            / v.routes.getFirst().seconds);
                    margx = 7 + ((v.routes.get(1).id < v.last_route_id) ? 6 : 0) * (Math.abs(v.routes.get(1).id - v.last_route_id) == 1 ? 0 : 1);
                    margy = 7 + ((v.routes.get(1).id > v.last_route_id) ? 6 : 0) * (Math.abs(v.routes.get(1).id - v.last_route_id) != 1 ? 0 : 1);

                } else {
                    x1 = (int) cityGraph.vertices.get(v.routes.getFirst().id).x_cord;
                    y1 = (int) cityGraph.vertices.get(v.routes.getFirst().id).y_cord;
                    margx = 10;
                    margy = 10;
                }

                x1 = Math.round((x1 / xsize) * (width - 45)) + 5 + margx;
                y1 = Math.round((y1 / ysize) * (height - 45)) + 5 + margy;
                g2d.fillOval(x1, y1, 10, 10);

            }
        }
    }
}
