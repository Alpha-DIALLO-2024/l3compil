import sa.*;
import ts.Ts;



public class Sa2ts extends SaDepthFirstVisitor <Void> {

    private Ts tableGlobal;
    private Ts tableLocal;
    enum Context{LOCAL, GLOBAL, PARAM} //PARAM => paramettre
    private  Context context;

    public Sa2ts(SaNode root) {
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

        if (node.tsItem.portee.getTableLocale(node.getNom()) != null) {
            if (tableLocal.variables.containsKey(node.getNom())){
                System.out.println("Erreur : "+node+" existe déjà");
            }

            if (!node.tsItem.isParam){
                tableLocal.addVar(node.getNom(), 1);

            }

            if (node.tsItem.isParam){
                tableLocal.addParam(node.getNom());

            }


        }

// A revoir
        /*
        if (node.tsItem.portee.getTableLocale(node.getNom()) == null){
            if (tableGlobal.variables.containsKey(node.getNom())){
                System.out.println("Erreur : "+node+" existe déjà");
            }

            if (!node.tsItem.isParam){
                tableGlobal.addVar(node.getNom(), 1);
            }
        }

         */


        if (node.tsItem.portee.getVar(node.getNom()) != null){
            if (tableGlobal.variables.containsKey(node.getNom())){
                System.out.println("Erreur : "+node+" existe déjà");
            }

            if (!node.tsItem.isParam){
                tableGlobal.addVar(node.getNom(), 1);
            }
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
