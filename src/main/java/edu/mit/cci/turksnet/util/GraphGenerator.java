package edu.mit.cci.turksnet.util;

import edu.mit.cci.snatools.util.jung.DefaultJungEdge;
import edu.mit.cci.snatools.util.jung.DefaultJungGraph;
import edu.mit.cci.snatools.util.jung.DefaultJungNode;
import edu.mit.cci.snatools.util.jung.DefaultUndirectedJungGraph;
import edu.mit.cci.turksnet.plugins.GraphCreationException;
import edu.uci.ics.jung.algorithms.metrics.Metrics;
import edu.uci.ics.jung.algorithms.shortestpath.UnweightedShortestPath;
import edu.uci.ics.jung.algorithms.util.MapSettableTransformer;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.io.PajekNetWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: jintrone
 * Date: 2/4/12
 * Time: 3:04 PM
 */
public class GraphGenerator {

    public static DefaultUndirectedJungGraph generateRandomGraph(int nodes, int degree) {
        DefaultUndirectedJungGraph graph = (DefaultUndirectedJungGraph) DefaultUndirectedJungGraph.getFactory().create();


        return graph;
    }

    public static DefaultUndirectedJungGraph generateSmallWorld(int nodes, int degree) {
        DefaultUndirectedJungGraph graph = (DefaultUndirectedJungGraph) DefaultUndirectedJungGraph.getFactory().create();
        return graph;
    }

    public static DefaultUndirectedJungGraph generateLatticeGraph(int nodes, int degree) {
        if (2 * (degree / 2) != degree) {
            // return generateOddLatticeGraph(nodes,degree);
            System.err.println("Not supported");
            return null;
        } else return generateEvenLatticeGraph(nodes, degree);
    }

    public static void checkDegree(int min, int max, int value) throws GraphCreationException {
        if (value <min || value > max) {
            throw new GraphCreationException("Degree must be in the range ["+min+" - "+max+"]");
        }
    }

    public static DefaultUndirectedJungGraph generateSquareLatticeGraph(int nodes, int degree) throws GraphCreationException {
            checkDegree(3,5,degree);
        nodes = nodes/2 * 2;


        DefaultUndirectedJungGraph graph = (DefaultUndirectedJungGraph) DefaultUndirectedJungGraph.getFactory().create();

        for (int i = 0; i < nodes; i++) {
            DefaultJungNode node = DefaultJungNode.getFactory().create();
            graph.addVertex(node);
        }
        List<DefaultJungNode> nlist = new ArrayList<DefaultJungNode>(graph.getVertices());
        for (int outer = 0; outer < nlist.size() / 2; outer++) {


            int inner = nlist.size() / 2 + outer;
            graph.addEdge(DefaultJungEdge.getFactory().create(), nlist.get(outer), nlist.get(inner));
            //System.err.println(outer + " --> " + inner);
            if (degree >= 3) {
                //System.err.println(outer + " --> " + ((outer + 1) % (nlist.size() / 2)));
                graph.addEdge(DefaultJungEdge.getFactory().create(), nlist.get(outer), nlist.get((outer + 1) % (nlist.size() / 2)));
                //System.err.println(inner + " --> " + ((outer + 1) % (nlist.size() / 2) + nlist.size() / 2));
                graph.addEdge(DefaultJungEdge.getFactory().create(), nlist.get(inner), nlist.get((outer + 1) % (nlist.size() / 2) + nlist.size() / 2));
            }
            if (degree >= 4) {
                //System.err.println("*" + inner + " --> " + ((outer + 1) % (nlist.size() / 2)));
                graph.addEdge(DefaultJungEdge.getFactory().create(), nlist.get(inner), nlist.get((outer + 1) % (nlist.size() / 2)));
            }
            if (degree >= 5) {
                graph.addEdge(DefaultJungEdge.getFactory().create(), nlist.get(outer), nlist.get((outer + 1) % (nlist.size() / 2) + nlist.size() / 2));
            }
        }
        return graph;
    }

    private static DefaultJungNode n() {
        return DefaultJungNode.getFactory().create();
    }

    private static DefaultJungEdge e() {
        return DefaultJungEdge.getFactory().create();
    }


    public static DefaultUndirectedJungGraph generateBowtie(int nodes) throws GraphCreationException {
        DefaultUndirectedJungGraph graph = (DefaultUndirectedJungGraph) DefaultUndirectedJungGraph.getFactory().create();
        if (nodes < 6) {
            throw new GraphCreationException("Bowtie network requires > 6 nodes");

        }
        nodes -= 6;
        DefaultJungNode[] firsttri = addTri(graph);
        DefaultJungNode[] currtri = addTri(graph);
        graph.addEdge(e(), firsttri[1], currtri[1]);

        DefaultJungNode[] lasttri = currtri;
        while (nodes >= 4) {
            DefaultJungNode lnode = n();
            graph.addEdge(e(), lasttri[0], lnode);
            graph.addEdge(e(), lasttri[2], lnode);
            lasttri = addTri(graph);
            graph.addEdge(e(), lnode, lasttri[1]);
            nodes -= 4;
        }

        if (nodes == 0) {
            graph.addEdge(e(), lasttri[0], firsttri[0]);
            graph.addEdge(e(), lasttri[2], firsttri[2]);
        } else if (nodes == 1) {
            DefaultJungNode lnode = n();
            graph.addEdge(e(), lasttri[0], lnode);
            graph.addEdge(e(), lasttri[2], lnode);
            graph.addEdge(e(), lnode, firsttri[0]);
        } else if (nodes == 2) {
            DefaultJungNode lnode = n();
            graph.addEdge(e(), lasttri[0], lnode);
            graph.addEdge(e(), lasttri[2], lnode);

            lnode = n();
            graph.addEdge(e(), lnode, firsttri[0]);
            graph.addEdge(e(), lnode, firsttri[2]);
        } else if (nodes == 3) {
            DefaultJungNode[] t = addTri(graph);
            graph.addEdge(e(), lasttri[0], t[0]);
            graph.addEdge(e(), lasttri[2], t[2]);
            graph.addEdge(e(), t[1], firsttri[0]);

        }
        return graph;


    }

    public static DefaultUndirectedJungGraph generateBowtieCircle(int nodes) {
        DefaultUndirectedJungGraph graph = (DefaultUndirectedJungGraph) DefaultUndirectedJungGraph.getFactory().create();
        if (nodes < 6) {
            System.err.println("Bowtie circle requires > 6 nodes");
            return null;
        }
        List<DefaultJungNode[]> a = new ArrayList<DefaultJungNode[]>();


        while (nodes >= 6) {
            a.add(addTri(graph));
            a.add(addTri(graph));
            nodes -= 6;
        }
        for (int i = 0; i < a.size() / 2; i++) {
            DefaultJungNode[] t1 = a.get(i);
            DefaultJungNode[] t2 = a.get(i + a.size() / 2);

            graph.addEdge(e(), t1[1], t2[1]);


        }
        for (int i = 0; i < a.size(); i++) {
            graph.addEdge(e(), a.get(i)[2], a.get((i + 1) % a.size())[0]);


        }
        return graph;


    }


    private static DefaultJungNode[] addTri(DefaultUndirectedJungGraph graph) {
        DefaultJungNode[] t = new DefaultJungNode[]{n(), n(), n()};
        graph.addEdge(e(), t[0], t[1]);
        graph.addEdge(e(), t[1], t[2]);
        graph.addEdge(e(), t[2], t[0]);
        return t;
    }


    public static DefaultUndirectedJungGraph generateEvenLatticeGraph(int nodes, int degree) {
        assert 2 * (degree / 2) == degree;
        DefaultUndirectedJungGraph graph = (DefaultUndirectedJungGraph) DefaultUndirectedJungGraph.getFactory().create();

        for (int i = 0; i < nodes; i++) {
            DefaultJungNode node = DefaultJungNode.getFactory().create();
            graph.addVertex(node);
        }
        List<DefaultJungNode> nlist = new ArrayList<DefaultJungNode>(graph.getVertices());
        for (int i = 0; i < nlist.size(); i++) {
            for (int j = 0; j < degree / 2; j++) {

                DefaultJungEdge e = DefaultJungEdge.getFactory().create();
                graph.addEdge(e, nlist.get(i), nlist.get((i + j + 1) % nlist.size()));
            }


        }
        return graph;
    }

    //degree 4, high clustering, low path
    public static DefaultUndirectedJungGraph generatePinWheel(int nodes, int degree) {
        assert 6 * (nodes / 6) == nodes;

        DefaultUndirectedJungGraph graph = (DefaultUndirectedJungGraph) DefaultUndirectedJungGraph.getFactory().create();
        int count = 0;
        List<DefaultJungNode> outer = new ArrayList<DefaultJungNode>();
        List<DefaultJungNode> inner = new ArrayList<DefaultJungNode>();
        List<DefaultJungNode> lateral = new ArrayList<DefaultJungNode>();
        while (count < nodes) {
            DefaultJungNode[] t = addTri(graph);
            outer.add(t[0]);
            inner.add(t[1]);
            lateral.add(t[2]);
            count += 3;
        }
        for (int i = 0; i < lateral.size(); i++) {
            graph.addEdge(e(), lateral.get((i + 1) % lateral.size()), inner.get(i));
            graph.addEdge(e(), lateral.get((i + 1) % lateral.size()), outer.get(i));
        }
        for (int i = 0; i < inner.size() / 2; i++) {
            graph.addEdge(e(), inner.get(i), inner.get(i + (inner.size() / 2)));
        }

        for (int i = 0; i < outer.size(); i += 2) {
            graph.addEdge(e(), outer.get(i), outer.get((i + 1) % outer.size()));
        }
        if (degree == 5) {
            for (int i = 0; i < lateral.size()/2; i++) {
                graph.addEdge(e(), lateral.get(i), lateral.get(i+lateral.size()/2));
            }
            for (int i = 0; i < inner.size() ; i+=2) {
                graph.addEdge(e(), inner.get(i), inner.get((i + 1) % inner.size()));
            }

            for (int i = 0; i < outer.size()/2; i ++) {
                graph.addEdge(e(), outer.get(i), outer.get(i + outer.size()/2));
            }
        }

        return graph;
    }

    public static double averageCluatering(DefaultUndirectedJungGraph graph) {
        Map<DefaultJungNode, Double> result = Metrics.clusteringCoefficients(graph);
        double total = 0;
        for (Double d : result.values()) {
            total += d;

        }
        return total / result.size();
    }

    public static double averageShortestPath(DefaultUndirectedJungGraph graph) {
        List<DefaultJungNode> nlist = new ArrayList<DefaultJungNode>(graph.getVertices());
        UnweightedShortestPath<DefaultJungNode, DefaultJungEdge> paths = new UnweightedShortestPath<DefaultJungNode, DefaultJungEdge>(graph);
        paths.reset();
        int count = 0;
        int total = 0;
        for (int i = 0; i < nlist.size(); i++) {
            for (int j = i + 1; j < nlist.size(); j++) {
                DefaultJungNode source = nlist.get(i);
                DefaultJungNode target = nlist.get(j);
                total += paths.getDistance(target, source).intValue();
                count++;
            }
        }
        return total / (double) count;
    }

    public static void printGraph(String type, DefaultUndirectedJungGraph graph) {


        printArray(new String[]{type, graph.getVertexCount() + "", avgDegree(graph) + "", String.format("%.2f", averageShortestPath(graph)), String.format("%.2f", averageCluatering(graph))});
    }

    public static DefaultUndirectedJungGraph generateWheel(int nodes, int degree) {
        if (2 * (nodes / 2) != nodes) {
            System.err.println("Wheel requires even number of nodes");
            return null;
        }
        DefaultUndirectedJungGraph ring = generateLatticeGraph(nodes, 2);
        List<DefaultJungNode> nodelist = new ArrayList<DefaultJungNode>(ring.getVertices());
        if (degree == 3) {
            for (int i = 0; i < nodelist.size() / 2; i++) {
                ring.addEdge(e(), nodelist.get(i), nodelist.get(i + nodelist.size() / 2));
            }
        } else if (degree > 3) {

            int delta = 2;
            for (int i = 0; i < nodelist.size(); i++) {
                //System.out.print(i+"-> ");
                int a = (i + nodelist.size() / 2 - delta) % nodelist.size();
                int b = (i + nodelist.size() / 2 + delta) % nodelist.size();
                if (null == ring.findEdge(nodelist.get(i), nodelist.get(a))) {
                    ring.addEdge(e(), nodelist.get(i), nodelist.get(a));
                    //System.out.print("  "+a+"  ");
                }
                if (null == ring.findEdge(nodelist.get(i), nodelist.get(b))) {
                    ring.addEdge(e(), nodelist.get(i), nodelist.get(b));
                    //System.out.print("  "+b+"  ");
                }
                //System.out.println();
            }

            //ring.addEdge(e(),nodelist.get(nodelist.size()/2),nodelist.get(nodelist.size()-2));
            if (degree == 5) {
                for (int i = 0; i < nodelist.size() / 2; i++) {
                    ring.addEdge(e(), nodelist.get(i), nodelist.get(i + nodelist.size() / 2));
                }
            }
        }
        return ring;


    }

    public static float avgDegree(DefaultUndirectedJungGraph graph) {
        float degree = 0.0f;

        for (DefaultJungNode node:graph.getVertices()) {
            degree+=graph.degree(node);
        }
        return degree/graph.getVertexCount();
    }


    public static void main(String[] x) throws IOException {
        String[] headers = new String[]{"TYPE", "SIZE", "DEGREE", "AVG.PATH", "CLUSTERING"};
        printArray(headers);
        DefaultUndirectedJungGraph graph = null;


        // for (int i =3;i<)
        System.out.println("\nDEGREE = 3\n-------------");
        graph = generateWheel(30, 3);
                printGraph("WHEEL (pc)", graph);


        graph = generateBowtie(30);
        printGraph("BOWTIE (PC)", graph);


        graph = generateBowtieCircle(30);
        printGraph("BOWTIE-CIRCLE (pC)", graph);


        System.out.println("\nDEGREE = 4\n-------------");

        graph = generateWheel(30, 4);
        writeGraph(graph, new FileOutputStream("WHEEL.50.4.net"));
        printGraph("WHEEL (pc)", graph);


        graph = generateLatticeGraph(30, 4);
        printGraph("RING-LATTICE (PC)", graph);

        graph = generatePinWheel(30,4);
        printGraph("PINWHEEL (pC)", graph);

//        graph = generateSquareLatticeGraph(50, 4);
//        printGraph("SQUARE_LATTICE (PC)", 4, graph);


        System.out.println("\nDEGREE = 5\n-------------");

//        graph = generateLatticeGraph(50, 5);
//        printGraph("RING-LATTICE", 5, graph);

        graph = generateWheel(30, 5);
        printGraph("WHEEL (pc)", graph);
        writeGraph(graph, new FileOutputStream("WHEEL.50.5.net"));

        graph = generateSquareLatticeGraph(30, 5);
        printGraph("SQUARE_LATTICE (PC)", graph);


        graph = generatePinWheel(30,5);
        printGraph("PINWHEEL (pC)", graph);
        writeGraph(graph, new FileOutputStream("PINWHEEL.50.5.net"));


    }

    public static void writeGraph(Graph<DefaultJungNode, DefaultJungEdge> graph, OutputStream stream) throws IOException {
        PajekNetWriter<DefaultJungNode, DefaultJungEdge> writer = new PajekNetWriter<DefaultJungNode, DefaultJungEdge>();
        OutputStreamWriter os = new OutputStreamWriter(stream);
        MapSettableTransformer<DefaultJungNode, String> s = new MapSettableTransformer<DefaultJungNode, String>(new HashMap<DefaultJungNode, String>());
        MapSettableTransformer<DefaultJungEdge, Number> w = new MapSettableTransformer<DefaultJungEdge, Number>(new HashMap<DefaultJungEdge, Number>()) {
            @Override
            public Number transform(DefaultJungEdge input) {
                return 1.0f;
            }
        };
        writer.save(graph, os, s, w);
    }


    public static void printArray(String[] s) {
        StringBuilder builder = new StringBuilder();
        String sep = "";
        for (String elt : s) {
            builder.append(sep).append(elt);
            sep = ",";
        }
        System.out.println(builder);
    }


}
