/* Generated By:JJTree: Do not edit this line. ASTFinally.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package FESI.AST;

import FESI.Parser.*;

public
class ASTFinally extends SimpleNode {
    private static final long serialVersionUID = 2022482779405357796L;
    private Object evaluationSource;

    public ASTFinally(int id) {
        super(id);
    }

    public ASTFinally(EcmaScript p, int id) {
        super(p, id);
    }


    /** Accept the visitor. **/
    public Object jjtAccept(EcmaScriptVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }

    public void setEvaluationSource(Object evaluationSource) {
        this.evaluationSource = evaluationSource;
    }

    public Object getEvaluationSource() {
        return evaluationSource;
    }
}
/* JavaCC - OriginalChecksum=8987c9daa03263f5a4310a3b3eb65ef1 (do not edit this line) */
