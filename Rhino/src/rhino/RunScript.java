/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rhino;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import java.util.ArrayList;

/**
 *
 * @author hpmdu
 */
public class RunScript {
    
    private String script;
    
    public RunScript(String script) {
        // The script, ex: java.lang.System.out.println(3), Math.cos(Math.PI), ....
        this.script = script;
    }
    
    public String require(String file) {
        return file;
    }
    
    public void execute() {
        // Creates and enters a Context to stores information about the execution environment of the script.
        Context cx = Context.enter();
        try {
            // Initialize the standard objects (Object, Function, etc.)
            Scriptable scope = cx.initStandardObjects();
            
            // Now evaluate the string we've colected.
            Object result = cx.evaluateString(scope, script, "<cmd>", 1, null);

            // Convert the result to a string and print it.
            System.err.println(Context.toString(result));

        } finally {
            Context.exit();
        }
    }

    public static void main(String args[]){
        RunScript runScript = new RunScript("Math.PI");
        runScript.execute();
    }
}
