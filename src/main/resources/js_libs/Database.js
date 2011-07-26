
function Database() {
	this.database = javaTurKit.database
}

/**
 * Each TurKit script file has a JavaScript database associated with it.
 * You may think of a JavaScript Database as a JavaScript environment that is
 * persisted on disk. Any query you make to the
 * database is evaluated in the context of the database, the new state of the database
 * is written to disk, and then the result is returned.
 */
database = new Database()

Database.prototype.database = null

/**
 * Evaluates <i>s</i> in the context of the JavaScript database, and returns a
 * deep clone of the result. Note that this function evaluates the string of
 * JSON returned from the JavaScript database, and returns the result.
 */
Database.prototype.query = function(s) {
	return eval("" + this.database.query(s))
}

/**
 * Evaluates <i>s</i> in the context of the JavaScript database, without change.
 * This version does not wrap your code in a function body.
 * It also does not guarantee that the results will be persisted on disk before the function returns,
 * so you should only use this for calls that read from the database.
 * Note that this version still evaluates the string of
 * JSON returned from the JavaScript database, and returns the result.
 */
Database.prototype.queryRaw = function(s) {
	return eval("" + java_RhinoUtil.json(this.database.queryRaw(s)))
}
