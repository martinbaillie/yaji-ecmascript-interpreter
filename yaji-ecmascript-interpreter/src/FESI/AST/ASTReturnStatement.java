/* Generated By:JJTree: Do not edit this line. ASTReturnStatement.java */

package FESI.AST;

import FESI.Parser.EcmaScript;

public class ASTReturnStatement extends SimpleNode {
    /**
	 * 
	 */
    private static final long serialVersionUID = 8285181581501888099L;

    public ASTReturnStatement(int id) {
        super(id);
    }

    public ASTReturnStatement(EcmaScript p, int id) {
        super(p, id);
    }

    public static Node jjtCreate(int id) {
        return new ASTReturnStatement(id);
    }

    public static Node jjtCreate(EcmaScript p, int id) {
        return new ASTReturnStatement(p, id);
    }

    /** Accept the visitor. **/
    public Object jjtAccept(EcmaScriptVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }
}