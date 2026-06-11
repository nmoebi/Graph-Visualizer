package visualizer.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import visualizer.graph.coloring.ColoringManager;
import visualizer.util.Pair;

public final class Graph {

    ColoringManager coloringManager = new ColoringManager(this);

    private final VertexFactory vertexFactory = new VertexFactory();
    private final EdgeFactory edgeFactory = new EdgeFactory();

    private final ArrayList<Vertex>                         vertices = new ArrayList<>();
    private final HashMap<Integer, Vertex>              verticesByID = new HashMap<>();
    private final HashMap<Vertex, HashSet<Vertex>>      neighborsMap = new HashMap<>();

    private final ArrayList<Edge>                              edges = new ArrayList<>();
    private final HashMap<Pair<Vertex, Vertex>, Edge>        edgeMap = new HashMap<>();

    public void reset() {
        vertices.clear();
        verticesByID.clear();
        neighborsMap.clear();
        edges.clear();
        edgeMap.clear();
        vertexFactory.reset();
    }

    public Vertex newVertex(int xPosition, int yPosition) {
        Vertex v = vertexFactory.createVertex(xPosition, yPosition);
        System.out.println("Vertex " + v.getVertexID() + " created");
        vertices.add(v);
        verticesByID.put(v.getVertexID(), v);
        neighborsMap.put(v, new HashSet<>());
        return v;
    }

    public Edge newEdge(Vertex a, Vertex b) {

        if(a == b || a == null || b == null) throw new IllegalArgumentException("Error: bad vertices input for edge");

        Pair<Vertex, Vertex> key = Pair.ofOrdered(a, b);

        if(!edgeMap.containsKey(key)) {
            Edge e = edgeFactory.createEdge(a, b);
            System.out.println("Edge (" + a + ", " + b + ") created");
            edges.add(e);
            edgeMap.put(key, e);

            neighborsMap.get(a).add(b);
            neighborsMap.get(b).add(a);  
  
            return e;
        }
        else return edgeMap.get(key);
    }

    public ArrayList<Vertex> getVertices() {
        return vertices;
    }

    public Vertex getVertexByLocation(int x, int y) {
        for(Vertex v : vertices) {
            if(Math.abs(v.getXPosition() - x) < v.getVertexSize()/2 
            && Math.abs(v.getYPosition() - y) < v.getVertexSize()/2)
                return v;
        }
        throw new IllegalArgumentException("Error: no vertex here");
    }

    public Vertex getVertexByID(int id) {
        return verticesByID.get(id);
    }

    public HashSet<Vertex> getVertexNeighbors(Vertex v) {
        return neighborsMap.get(v);
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public Edge getEdge(Vertex a, Vertex b) {
        Pair<Vertex, Vertex> key = Pair.ofOrdered(a, b);
        if(!edgeMap.containsKey(key)) 
            throw new IllegalArgumentException("Error: Accessing non-existing edge");
        
        return edgeMap.get(key);
    }    

    public ColoringManager getColoringManager() {
        return coloringManager;
    }

    public HashMap<Vertex, HashSet<Vertex>> getNeighborsMap() {
        return neighborsMap;
    }

    public int getMaxDegree() {
        int maxDegree = 0;

        for (Vertex v : vertices) {
            if(v.getDegree() > maxDegree) {
                maxDegree = v.getDegree();
            }
        }
        return maxDegree;
    }
}