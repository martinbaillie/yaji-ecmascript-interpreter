/* Generated By:JJTree: Do not edit this line. ASTSuperReference.java */

package FESI.AST;

import FESI.Parser.EcmaScript;

public class ASTSuperReference extends SimpleNode {
    private static final long serialVersionUID = 3975932495238636300L;

    public ASTSuperReference(int id) {
        super(id);
    }

    public ASTSuperReference(EcmaScript p, int id) {
        super(p, id);
    }

    public static Node jjtCreate(int id) {
        return new ASTSuperReference(id);
    }

    public static Node jjtCreate(EcmaScript p, int id) {
        return new ASTSuperReference(p, id);
    }

    /** Accept the visitor. **/
    public Object jjtAccept(EcmaScriptVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }
}
