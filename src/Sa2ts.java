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
        System.out.println("Début de la recherche");
        root.accept(this);
        if (tableGlobale.getFct("main") == null) {
            System.err.println("ERREUR : la fonction main n'est pas définie");
            System.exit(1);
        }
        System.out.println("Fin production");
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
            System.exit(1);

        }

        if (itemLocal != null) return  itemLocal;
        return itemGlobal;
    }



    public Void visit(SaDecVar node){
        System.out.println("Visite Decvar");
        defaultIn(node);

        Ts table = (tableLocale != null) ? tableLocale : tableGlobale;
        TsItemVar item = rechercheDeclarative(node.getNom());
        System.out.println(this.context.toString());
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
        System.out.println("Visite Dectab");

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
        System.out.println("Visite Decfonc");

        defaultIn(node);

        Ts table = tableGlobale;
        tableLocale = new Ts();

        this.context = Context.PARAM;
        if(node.getParametres() != null) node.getParametres().accept(this);
        this.context = Context.LOCAL;
        if(node.getVariable() != null) node.getVariable().accept(this);
        if(node.getCorps() != null) node.getCorps().accept(this);

        if (table.getFct(node.getNom()) == null){
            System.out.println("Add function");
            SaLDec parametres = node.getParametres();
            if (parametres == null)
                node.tsItem = table.addFct(node.getNom(), 0, this.tableLocale, node);
            else
                node.tsItem = table.addFct(node.getNom(), node.getParametres().length(), this.tableLocale, node);
            System.out.println("Has added function");
        }

        else{
            System.err.println("ERREUR : il existe déjà une fonction " + node.getNom());
            System.exit(1);
            //throw new Exception()
        }

        defaultOut(node);
        return null;

    }

    

    public Void visit(SaVarSimple node){
        System.out.println("Visite VarSimple");

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
        System.out.println("Visite VarIndicee");

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

        if (item.taille > 1){

            node.tsItem = item;
        }



        defaultOut(node);
        return null;

    }

    public Void visit(SaAppel node){
        System.out.println("Visite Appel");

        defaultIn(node);

        Ts table = tableLocale;

        if(node.getArguments() != null) node.getArguments().accept(this);

        if (tableGlobale.getFct(node.getNom()) == null){
            System.err.println("ERREUR : la fonction " + node.getNom() + " n'est pas definie");
            System.exit(1);
        }

        if (tableGlobale.getFct(node.getNom()).getNbArgs() != node.tsItem.getNbArgs()){

            System.err.println("ERREUR : la nombre d'argument de la fonction " + node.getNom() + " est incorrect");
            System.exit(1);

        }

        if (node.getNom().equals("main") && node.tsItem.getNbArgs() == 0){

            System.err.println("ERREUR : on fait appel à la fonction main()");
            System.exit(1);

        }

        node.tsItem = table.addFct(node.getNom(), node.tsItem.getNbArgs(), node.tsItem.getTable(), node.tsItem.saDecFonc);


        defaultOut(node);
        return null;

    }

}
