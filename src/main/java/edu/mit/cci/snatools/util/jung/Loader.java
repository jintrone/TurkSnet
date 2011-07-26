package edu.mit.cci.snatools.util.jung;

import edu.mit.cci.snatools.util.UnicodeReader;
import edu.uci.ics.jung.algorithms.util.MapSettableTransformer;
import edu.uci.ics.jung.io.PajekNetReader;
import edu.uci.ics.jung.io.PajekNetWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.util.HashMap;

/**
 * User: jintrone
 * Date: 4/21/11
 * Time: 10:53 AM
 */
public class Loader {


    public static DefaultJungGraph loadGraph(InputStream stream) throws IOException {
        Reader fileReader = new UnicodeReader(stream, "UTF-8");
        DefaultJungGraph graph = new DefaultJungGraph();
        PajekNetReader<DefaultJungGraph, DefaultJungNode, DefaultJungEdge> graphreader = new PajekNetReader<DefaultJungGraph, DefaultJungNode, DefaultJungEdge>(DefaultJungNode.getFactory(), DefaultJungEdge.getFactory());
        graphreader.setEdgeWeightTransformer(new MapSettableTransformer<DefaultJungEdge, Number>(new HashMap<DefaultJungEdge, Number>()));
        graphreader.setVertexLabeller(new MapSettableTransformer<DefaultJungNode, String>(new HashMap<DefaultJungNode, String>()));
        graphreader.load(fileReader, graph);
        for (DefaultJungNode node : graph.getVertices()) {
            node.setLabel(graphreader.getVertexLabeller().transform(node));
        }
        for (DefaultJungEdge edge : graph.getEdges()) {
            edge.setWeight(graphreader.getEdgeWeightTransformer().transform(edge).floatValue());
        }
        return graph;

    }

    public static void writeGraph(DefaultJungGraph graph, OutputStream stream) throws IOException {
        PajekNetWriter<DefaultJungNode, DefaultJungEdge> writer = new PajekNetWriter<DefaultJungNode, DefaultJungEdge>();
        OutputStreamWriter os = new OutputStreamWriter(stream);
        writer.save(graph,os,new MapSettableTransformer<DefaultJungNode, String>(new HashMap<DefaultJungNode, String>()),new MapSettableTransformer<DefaultJungEdge, Number>(new HashMap<DefaultJungEdge, Number>()));
    }
}
