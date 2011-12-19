var s = javaTurKit.getCurrentSession();


if (s.getActive().booleanValue()) {
    printp("In init body");

    var creation = String(s.getHitCreationString("http://cognosis.mit.edu:8084/turksnet"));
    printp("Creation is :"+creation);
    var hitId = mturk.createHIT(eval(creation));
    var hit = mturk.waitForHIT(hitId);
    var qualification = mturk.createQualification("Loom Story Participant " + s.getId() + "(" + (new Date()).valueOf() + ")",
            "This qualification indicates that you are a participant in a specific instance of the Loom game");
    once(function() {
        s.setQualificationRequirements(qualification);

        for (x in hit.assignments) {
            s.processNodeResults(hit.assignments[x].answer.workerId, String(json(hit.assignments[x].answer)));
            mturk.grantQualification(s.getQualificationRequirements(), hit.assignments[x].answer.workerId);
        }
    });

}

var hit;
while (s.getActive().booleanValue())  {

    printp("In active loop - " + s.getIteration()+" "+(typeof s.getActive()));

    var creation = String(s.getHitCreationString("http://cognosis.mit.edu:8084/turksnet"));
    printp("Got creation string");
    var hitId = mturk.createHIT(eval(creation));
    printp("Created a hit");
     hit = mturk.waitForHIT(hitId);
     printp("Done waiting");
    once(function() {
        for (x in hit.assignments) {
            printp("Processing "+x);
            s.processNodeResults(hit.assignments[x].answer.workerId, String(json(hit.assignments[x].answer)));
        }
    });
    printp("Bottom of loop") ;
}

printp("Ready to grant bonus");

for (x in hit.assignments) {
    var m = s.getBonus(hit.assignments[x].answer.workerId);
    printp("Will grant bonus "+m.get("Description")+","+m.get("Bonus")+" to "+hit.assignments[x].answer.workerId);
    if (m.containsKey("Bonus") && parseFloat(m.get("Bonus") > 0)) {
        mturk.grantBonus(hit.assignments[x],m.get("Bonus"),m.get("Description"));
    }
}