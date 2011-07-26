package edu.mit.cci.snatools.util.jung;

import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import org.apache.commons.collections15.Factory;

/**
 * User: jintrone
 * Date: 4/27/11
 * Time: 7:29 AM
 */
public class DefaultJungGraph extends DirectedSparseMultigraph<DefaultJungNode,DefaultJungEdge> {


    public static Factory<DefaultJungGraph> getFactory() {
        return new Factory<DefaultJungGraph>() {

            @Override
            public DefaultJungGraph create() {
                return new DefaultJungGraph();
            }
        };
    }
}
