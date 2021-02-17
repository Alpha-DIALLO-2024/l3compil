import sa.*;
import ts.Ts;

import java.time.format.TextStyle;

public class Sa2Ts extends SaDepthFirstVisitor <Void> {

    private Ts tableGlobal;
    private Ts tableLocal;
    enum Context{LOCAL, GLOBAL, PARAM} //PARAM => paramettre
    private  Context context;

    public Sa2Ts(SaNode root) {
        tableGlobal = new Ts();
        tableLocal =  null;
        context = Context.GLOBAL;
        root.accept(this);
    }

    public Void visit(SaDecVar node){

        defaultIn(node);

        if(node.tsItem.taille > 1){
            System.out.println("Erreur : "+node+" est un tableau");
        }

        if (!node.tsItem.isParam) {
            System.out.println("Erreur : "+node+" est ni une variable locale , ni une variable globale, ni un paramettre");
        }

        if (node.tsItem.portee.getTableLocale(node.getNom()) != null) {
            if (tableLocal.variables.containsKey(node.getNom())){
                System.out.println("Erreur : "+node+" existe déjà");
            }

            tableLocal.variables.put(node.getNom(), node.tsItem);
        }


        if (node.tsItem.portee.getTableLocale(node.getNom()) == null){
            if (tableGlobal.variables.containsKey(node.getNom())){
                System.out.println("Erreur : "+node+" existe déjà");
            }

            tableGlobal.variables.put(node.getNom(), node.tsItem);
        }

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
