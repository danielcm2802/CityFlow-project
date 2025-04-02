package server.models;

public class Vertex {
    public static short counter=0;
    public short id;
    public boolean has_lights;

    public Vertex(boolean has_lights) {
        counter++;
        this.id = counter;
        this.has_lights = has_lights;
    }

    public float calculateWeight() {
        return 10 + (has_lights ? 40 : 0);
    }

    public void disconnect() {
        counter--;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "id=" + id +
                ", has_lights=" + has_lights +
                '}';
    }
}
