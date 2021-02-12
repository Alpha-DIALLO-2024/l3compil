import sa.*;

public class Sa2Ts extends SaDepthFirstVisitor <Void> {

    public Void visit(SaDecVar node){

        defaultIn(node);
        defaultOut(node);
        return null;

    }

    public Void visit(SaDecTab node){

        defaultIn(node);
        defaultOut(node);
        return null;

    }

    public Void visit(SaDecFonc node){

        defaultIn(node);
        if(node.getParametres() != null) node.getParametres().accept(this);
        if(node.getVariable() != null) node.getVariable().accept(this);
        if(node.getCorps() != null) node.getCorps().accept(this);
        defaultOut(node);
        return null;

    }

    public Void visit(SaVarSimple node){

        defaultIn(node);
        defaultOut(node);
        return null;

    }

    public Void visit(SaVarIndicee node){

        defaultIn(node);
        node.getIndice().accept(this);
        defaultOut(node);
        return null;

    }

    public Void visit(SaAppel node){

        defaultIn(node);
        if(node.getArguments() != null) node.getArguments().accept(this);
        defaultOut(node);
        return null;

    }


}
