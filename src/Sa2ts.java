import sa.*;
import ts.Ts;
import ts.TsItemVar;


public class Sa2ts extends SaDepthFirstVisitor <Void> {



    enum Context{
        LOCAL,
        GLOBAL,
        PARAM
    }

    private Ts tableGlobale;
    private Ts tableLocale;
    private Context context;

    public Ts getTableGlobale() { return this.tableGlobale; }

    public Sa2ts(SaNode root) {
        tableGlobale = new Ts();
        tableLocale =  null;
        context = Context.GLOBAL;
        root.accept(this);
        if (tableGlobale.getFct("main") == null) {
            System.err.println("ERREUR : la fonction main n'est pas définie");
            System.exit(1);
        }
    }

    //en contexte local on cherche d'abord dans la table locale couvrante
    //si on ne trouve rien on cherche dans la table globale

    public TsItemVar rechercheExecutable(String identif){
        TsItemVar item = null;
        if (context != Context.GLOBAL){
            item = tableLocale.getVar(identif);
        }

        if (item == null){
            item = tableGlobale.getVar(identif);
        }

        return item;
    }




    //si on est en contexte local on cherche dans la table locale
    //si on est en contexte globale on cherche dans la table globale

    public TsItemVar rechercheDeclarative(String identif){
        TsItemVar itemLocal = null;
        TsItemVar itemGlobal = null;
        if (context != Context.GLOBAL){
            itemLocal = tableLocale.getVar(identif);
        }

        itemGlobal = tableGlobale.getVar(identif);

        if (itemLocal == null && itemGlobal != null){
            System.err.println("ATTENTION : la variable locale ou le paramètre " + identif + " masque une variable globale");
        }

        if (itemLocal != null) return  itemLocal;
        return itemGlobal;
    }



    public Void visit(SaDecVar node){
        defaultIn(node);

        Ts table = (tableLocale != null) ? tableLocale : tableGlobale;
        TsItemVar item = rechercheDeclarative(node.getNom());
        if (item == null || item.portee != table){
            if (context == Context.PARAM){
                node.tsItem = table.addParam(node.getNom());
            } else {
                node.tsItem = table.addVar(node.getNom(), 1);
            }
        }
        else{
            System.err.println("ERREUR : il existe déjà une variable " + node.getNom());
            System.exit(1);
        }


        defaultOut(node);
        return null;

    }


    public Void visit(SaDecTab node){
        defaultIn(node);
        Ts table = tableGlobale;
        TsItemVar item = rechercheDeclarative(node.getNom());

        if (item == null){

            node.tsItem = table.addVar(node.getNom(), node.getTaille());
        }

        else{
            System.err.println("ERREUR : il existe déjà une Table " + node.getNom());
            System.exit(1);
        }


        defaultOut(node);
        return null;

    }



    public Void visit(SaDecFonc node){
        defaultIn(node);

        Ts table = tableGlobale;
        tableLocale = new Ts();

        this.context = Context.PARAM;
        if(node.getParametres() != null) node.getParametres().accept(this);
        this.context = Context.LOCAL;
        if(node.getVariable() != null) node.getVariable().accept(this);

        if (table.getFct(node.getNom()) == null){
            SaLDec parametres = node.getParametres();
            if (parametres == null)
                node.tsItem = table.addFct(node.getNom(), 0, this.tableLocale, node);
            else
                node.tsItem = table.addFct(node.getNom(), node.getParametres().length(), this.tableLocale, node);
        }
        else{
            System.err.println("ERREUR : il existe déjà une fonction " + node.getNom());
            System.exit(1);
            //throw new Exception()
        }

        if(node.getCorps() != null) node.getCorps().accept(this);

        defaultOut(node);
        return null;

    }

    

    public Void visit(SaVarSimple node){
        defaultIn(node);
        TsItemVar item = rechercheExecutable(node.getNom());
        
        if (item == null){
            System.err.println("ERREUR : la variable n'est pas definie");
            System.exit(1);
        }
        else {
            node.tsItem = item;
        }

        defaultOut(node);
        return null;

    }

    public Void visit(SaVarIndicee node){
        defaultIn(node);
        node.getIndice().accept(this);

        TsItemVar item = rechercheExecutable(node.getNom());

        if (item == null){
            System.err.println("ERREUR : la table n'est pas definie");
            System.exit(1);
        }

        if (item.taille <= 1){
            System.err.println("ERREUR : la table n'est pas definie");
            System.exit(1);
        }

        if (item.taille > 1) {

            node.tsItem = item;
        }



        defaultOut(node);
        return null;

    }

    public Void visit(SaAppel node){
        defaultIn(node);

        if(node.getArguments() != null) node.getArguments().accept(this);


        String name = node.getNom();
        SaLExp args = node.getArguments();
        int argNb = (args == null) ? 0 : args.length(); 

        if (tableGlobale.getFct(name) == null){
            System.err.println("ERREUR : la fonction " + node.getNom() + " n'est pas definie");
            System.exit(1);
        }
        node.tsItem = this.tableGlobale.getFct(name);

        if (tableGlobale.getFct(name).getNbArgs() != argNb){

            System.err.println("ERREUR : la nombre d'argument de la fonction " + node.getNom() + " est incorrect");
            System.exit(1);

        }

        if (node.getNom().equals("main") && argNb == 0){

            System.err.println("ERREUR : on fait appel à la fonction main()");
            System.exit(1);

        }

        defaultOut(node);
        return null;

    }

}
