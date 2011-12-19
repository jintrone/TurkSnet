var s=javaTurKit.getCurrentSession();
var n = s.getIteration();
printp("Last iteration count "+n);
s.setIteration(n+1);
s.persist();
printp("Update to "+s.getIteration());
throw "An error";



