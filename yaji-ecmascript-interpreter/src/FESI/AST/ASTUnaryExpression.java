/* Generated By:JJTree: Do not edit this line. ASTUnaryExpression.java */

package FESI.AST;

import FESI.Parser.EcmaScript;

public class ASTUnaryExpression extends SimpleNode {
    /**
	 * 
	 */
    private static final long serialVersionUID = -7519324238650314421L;

    public ASTUnaryExpression(int id) {
        super(id);
    }

    public ASTUnaryExpression(EcmaScript p, int id) {
        super(p, id);
    }

    public static Node jjtCreate(int id) {
        return new ASTUnaryExpression(id);
    }

    public static Node jjtCreate(EcmaScript p, int id) {
        return new ASTUnaryExpression(p, id);
    }

    /** Accept the visitor. **/
    public Object jjtAccept(EcmaScriptVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }
}