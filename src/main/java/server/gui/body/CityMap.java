package server.gui.body;

import server.gui.myColors;
import server.models.Edge;
import server.models.Graph;
import server.models.Vertex;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class CityMap extends JPanel {
    private final Graph cityGraph;
    private final int width = 600;  // Set large enough for full canvas
    private final int height = 400;

    public CityMap(Graph cityGraph) {
        this.cityGraph = cityGraph;
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(myColors.backgroundColor); // Replace with myColors.accentColor if needed
        this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawCityMap((Graphics2D) g);
    }

    private void drawCityMap(Graphics2D g2d) {
       cityGraph.printForApp();
        float xsize = cityGraph.vertices.getLast().x_cord;
        float ysize = cityGraph.vertices.getLast().y_cord;
        g2d.setColor(myColors.roadColor);
        g2d.setStroke(new BasicStroke(5));
        for(Vertex v : cityGraph.vertices) {
            for (Edge e: v.adjacent_edges){
                Vertex v2 = cityGraph.vertices.get(e.to);
                int marg = 7 + ((v2.id > v.id)? 6:0);
                int x1 = Math.round((v.x_cord / xsize) * (width - 45))+10+marg;
                int y1 = Math.round((v.y_cord / ysize) * (height - 45))+10+marg;
                int x2 = Math.round((v2.x_cord / xsize) * (width - 45))+10+marg;
                int y2 = Math.round((v2.y_cord / ysize) * (height - 45))+10+marg;
                g2d.drawLine(x1, y1, x2, y2);
//                System.out.println("x1: " + x1 + " y1: " + y1 + " x2: " + x2 + " y2: " + y2);
            }
        }
        for (Vertex v : cityGraph.vertices) {
            int x = Math.round((v.x_cord / xsize) * (width - 45))+10;
            int y = Math.round((v.y_cord / ysize) * (height - 45))+10;
            g2d.setColor(myColors.nodeOutlineColor);
            g2d.fillOval(x-2, y-2, 24, 24);
            g2d.setColor(myColors.nodeColor);
            g2d.fillOval(x, y, 20, 20);

        }
    }
}
