/* Generated By:JJTree: Do not edit this line. ASTOrExpressionSequence.java */

package FESI.AST;

import FESI.Parser.EcmaScript;

public class ASTOrExpressionSequence extends SimpleNode {
    /**
	 * 
	 */
    private static final long serialVersionUID = 6695312727606825960L;

    public ASTOrExpressionSequence(int id) {
        super(id);
    }

    public ASTOrExpressionSequence(EcmaScript p, int id) {
        super(p, id);
    }

    public static Node jjtCreate(int id) {
        return new ASTOrExpressionSequence(id);
    }

    public static Node jjtCreate(EcmaScript p, int id) {
        return new ASTOrExpressionSequence(p, id);
    }

    /** Accept the visitor. **/
    public Object jjtAccept(EcmaScriptVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }
}