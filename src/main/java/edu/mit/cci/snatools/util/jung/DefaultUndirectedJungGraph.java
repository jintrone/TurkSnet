package edu.mit.cci.snatools.util.jung;


import edu.uci.ics.jung.graph.UndirectedGraph;
import edu.uci.ics.jung.graph.UndirectedSparseMultigraph;
import org.apache.commons.collections15.Factory;

/**
 * User: jintrone
 * Date: 12/13/11
 * Time: 8:52 AM
 */
public class DefaultUndirectedJungGraph extends UndirectedSparseMultigraph<DefaultJungNode,DefaultJungEdge> {


    public static Factory<UndirectedGraph<DefaultJungNode,DefaultJungEdge>> getFactory() {
        return new Factory<UndirectedGraph<DefaultJungNode,DefaultJungEdge>>() {

            public UndirectedGraph<DefaultJungNode,DefaultJungEdge> create() {
                return new DefaultUndirectedJungGraph();
            }
        };
    }
}

