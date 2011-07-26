package edu.mit.cci.turksnet.plugins;

import edu.mit.cci.turksnet.Node;
import edu.mit.cci.turksnet.Session_;
import edu.mit.cci.turksnet.util.U;

import java.util.*;

/**
 * User: jintrone
 * Date: 2/17/11
 * Time: 9:20 PM
 */
public class StoryHitProvider {

    public static String NODES_PROPERTY = "nodes";
    public static String SHARED_PROPERTY = "shared";
    public static String UNIQUE_PROPERTY = "unique";
    public static String STORY_PROPERTY = "story";
    public static String SOLUTION_PROPERTY = "solution";


    String theStory = "First we assaulted death with pills and targeted radiation, and then with nanites, gene therapy, and anti-ablative cladding woven into human flesh. Next came imprinted lightwaves that held the mind, the record of a human, railed against by the Catholics and the Protestants and the Muslims as a slight against the soul. Shinto ancestor worship became a tangible thing: venerating lacquered cubes of hardwood that contained quantum records of great-grandfathers.\n" +
            "Soon we left Earth behind, a crowded homestead, and made our way outward. We molded worlds to our liking, and then, later, wrote our consciousness into the foamy black of spacetime. After a large but finite number of eons, we left the Galaxy behind, a crowded homestead, and ventured further.\n" +
            "We left identity behind, merged ourselves with the godhead, and wrote poems on the surfaces of stars, sang songs to the iron cores of supernovae.\n" +
            "And now it's all unspooling, the stars all gone dark a trillion years ago, and we think to ourself, we had a good run.";

    public static Session_ createSession(Properties props) throws SessionCreationException {
        if (!props.containsKey(STORY_PROPERTY)) {
            throw new SessionCreationException("Cannot create session; must provide a story");
        }

        if (!props.containsKey(NODES_PROPERTY)) {
            throw new SessionCreationException("Cannot create session; must specify number of nodes");
        }
        Integer nodes = Integer.valueOf(props.getProperty(NODES_PROPERTY));
        Integer shared = props.containsKey(SHARED_PROPERTY) ? Integer.valueOf(props.getProperty(SHARED_PROPERTY)) : null;
        Integer unique = props.containsKey(UNIQUE_PROPERTY) ? Integer.valueOf(props.getProperty(UNIQUE_PROPERTY)) : null;

        if (shared == null && unique == null) {
            throw new SessionCreationException("Cannot create session; must set either unique or shared property");
        }

        String[] words = props.getProperty(STORY_PROPERTY).split(" ");


        String indexedStory = props.getProperty(SOLUTION_PROPERTY);
        if (indexedStory == null) {
            List<Integer> solution = new ArrayList<Integer>();
            String sep = "";
            for (int i = 0; i < words.length; i++) {
                solution.add(i);
            }
            Collections.shuffle(solution);
            for (int i = 0; i < words.length; i++) {
                words[i] = i + "." + words[i];
            }
            indexedStory = U.join(words, " ");
            props.setProperty(SOLUTION_PROPERTY, indexedStory);

        }

        List<String> uniquepool = Arrays.asList(words);
        List<String> sharedpool = new ArrayList<String>();
        Collections.shuffle(uniquepool);

        if (shared != null && unique == null) {
            if (words.length - nodes < shared) {
                throw new SessionCreationException("Cannot create session; not enough words to distribute among nodes");
            } else {
                sharedpool.addAll(uniquepool.subList(0, shared));
                for (String s : sharedpool) {
                    uniquepool.remove(0);
                }
                int uniquecount = uniquepool.size() / nodes;

            }
        } else if (shared == null) {
            if (words.length - nodes * unique < 0) {
                throw new SessionCreationException("Cannot create session; not enough words to distribute among nodes");
            }
        } else if (unique * nodes + shared > words.length) {
            throw new SessionCreationException("Cannot create session; not enough words to distribute among nodes");
        }

        return null;


    }

    private static Session_ initSession(List<String> shared, List<String> unique, int uniquecount, int nodes) {
        Session_ session = new Session_();
        session.setCreated(new Date());
        session.persist();
        session.flush();
        Node node;

        for (int i = 0; i < nodes; i++) {
            List<String> mywords = new ArrayList<String>();
            mywords.addAll(shared);
            node = new Node();
            node.setSession_(session);

            for (int j = 0; j < uniquecount; i++) {
                mywords.add(unique.get(0));
                unique.remove(0);
            }
            if (i == nodes - 1 && !unique.isEmpty()) {
                mywords.addAll(unique);
            }

        }
        return session;
    }

}
