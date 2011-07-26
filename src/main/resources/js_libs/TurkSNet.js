/**
 * User: jintrone
 * Date: Feb 4, 2011
 * Time: 3:42:24 PM
 *
 * Utilities to facilitate a structured workflow between turkers. Actual handling of
 * of hit creation and result processing is offloaded to functions that are passed in.
 *
 * This is really a very simple iterating loop - every node in the network is issued a
 * hit, and then all nodes must complete the current assignment before any of the others
 * can be advanced.
 *
 * Code that maintains node states is for book-keeping purposes (and sanity checking) only.
 *
 *
 */


var NODESTATES = {
    ready:"READY",
    running:"RUNNING",
    waiting:"WAITING"
};

/**
 * Creates a "social" network workflow manager.
 *
 *
 * @param hitHandler Handles the creation of hits. Accepts three parameters
 * @param resultHandler
 * @param maxIts
 */
function TurkSNet(hitHandler, resultHandler, maxIts, sessionIdentifier) {

    this.maxIterations = !maxIts ? 5 : maxIts;
    this.currentIterations = 0;
    this.resultCount = 0;
    this.nodestates = [];
    this.hitFx = hitHandler;
    this.resultFx = resultHandler;
    this.network = {};
    this.sessionIdentifier  = sessionIdentifier?sessionIdentifier:"TurkSnet_Session_"+Date.now()+"_"+Math.floor(Math.random() * 10000000);

}

TurkSNet.prototype.createNetwork = function(object) {

    this.network = new Network(object);
    print(json(this.network));
    this.nodestates = {};
    with (this) {
        for each (var node in network.nodelist) {
           nodestates[node] = {
                iterations:0,
                expects:network.getIncoming(node).length,
                incomingresults:0,
                state:NODESTATES.ready
            };
        }
    }


};


TurkSNet.prototype.createHIT = function(node) {
    with(this) {
        if (nodestates[node].state != NODESTATES.ready) throw "Node is not ready";
        else {
            nodestates[node].state = NODESTATES.running;
            return hitFx(node, {session:sessionIdentifier,sources:network.getIncoming(node).join(";")}, nodestates[node + ""].iterations);
        }

    }
  }


TurkSNet.prototype.stepNode = function(n) {

    if (this.nodestates[n].state == NODESTATES.waiting) throw "Node is waiting";

    //creates hit, or recalls it from the previous iteration
    var hit = this.createHIT(n);
    var result = mturk.waitForHIT(hit);

    //store the result
    this.resultFx(n, {session:sessionIdentifier,iteration:this.nodestates[n].iterations},result);


    //update recipients
    for each (var recipient in this.network.getOutgoing(n)) {
        var nodestatus = this.nodestates[recipient + ""];
        nodestatus.incomingresults++;
        if (nodestatus.incomingresults == nodestatus.expects && nodestatus.state == NODESTATES.waiting) {
            nodestatus.incomingresults = 0;
            nodestatus.state = NODESTATES.ready;
        }

    }

    //update this node state
    nodestatus = this.nodestates[n];
    nodestatus.iterations++;

    //increment global results count
    this.resultCount = (this.resultCount + 1) % this.network.nodelist.length;
    if (this.resultCount == 0) {
        this.currentIterations++;
    }


    if (nodestatus.incomingresults == nodestatus.expects) {
        nodestatus.state = NODESTATES.ready;
        nodestatus.incomingresults = 0;
    } else nodestatus.state = NODESTATES.waiting;

};

TurkSNet.prototype.step = function() {

    with (this) {

        while (currentIterations < maxIterations) {
            var exceptions = false;
            for each (var n in network.nodelist) {
               try {
                this.stepNode(n);
               } catch(exception) {
                   exceptions = true;
               }

            }
            if (exceptions) {
              stop();
            }
        }
    }
};


function UrlHitHandler(baseurl, params) {

    this.baseurl = baseUrl;
    this.defaultparams = params;
}

UrlHitHandler.prototype.createHit = function(node, urlparams, iterations) {
    var url = baseurl + "/" + node;
    var flag = false;
    for (var param in urlparams) {
        url += (flag ? "&" : "?") + param + "=" + urlparams[param];
        flag = true;
    }

    var hitparams = this.defaultparams;
    if (iterations >= 0) {
        //anyone can take this hit
        hitparams.qualificationRequirements = null;

    } else {
        //only the first person who took this can have it
        hitparams.qualificationRequirements = 1;

    }

}


function Network(outgoingConnections) {

    this.nodelist = [];
    this.outgoing = {};
    this.incoming = {};
    for (node in outgoingConnections) {
        this.nodelist.push(node+"");
        for each (target in outgoingConnections[node]) {

            if (!this.outgoing[node]) {
                this.outgoing[node] = [];
            }
            this.outgoing[node].push(target)
            if (!this.incoming[target]) {
                this.incoming[target] = [];
            }
            this.incoming[target].push(node);
        }
    }

}


Network.prototype.getIncoming = function(node) {
    return !this.incoming[node]?[]:this.incoming[node];
}

Network.prototype.getOutgoing = function(node) {
    return !this.outgoing[node]?[]:this.outgoing[node];
}

Network.prototype.hasNode = function(node) {

    return this.incoming[node] || this.outgoing[node];
}






