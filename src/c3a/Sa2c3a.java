package c3a;
import java.util.*;
import ts.*;
import sa.*;


public class Sa2c3a extends SaDepthFirstVisitor <C3aOperand> {
    private C3a c3a;
    int indentation;
    public C3a getC3a(){return this.c3a;}
    
    public Sa2c3a(SaNode root, Ts tableGlobale){
        c3a = new C3a();
        C3aTemp result = c3a.newTemp();
        C3aFunction fct = new C3aFunction(tableGlobale.getFct("main"));
        c3a.ajouteInst(new C3aInstCall(fct, result, ""));
        c3a.ajouteInst(new C3aInstStop(result, ""));
        indentation = 0;
        root.accept(this);
    }

    public void defaultIn(SaNode node)
    {
	//for(int i = 0; i < indentation; i++){System.out.print(" ");}
	//indentation++;
	//System.out.println("<" + node.getClass().getSimpleName() + ">");
    }

    public void defaultOut(SaNode node)
    {
	//indentation--;
	//	for(int i = 0; i < indentation; i++){System.out.print(" ");}
	//	System.out.println("</" + node.getClass().getSimpleName() + ">");
    }
    
    // P -> LDEC LDEC 
    public C3aOperand visit(SaProg node)
    {
	defaultIn(node);
        if(node.getVariables() != null)
            node.getVariables().accept(this);
        if(node.getFonctions() != null)
            node.getFonctions().accept(this);
	defaultOut(node);
	return null;
    }

    
    // DECLARATIONS
    
    // DEC -> var id taille 
    public C3aOperand visit(SaDecTab node){
	defaultIn(node);

	defaultOut(node);
	return null;
    }
    
    // DEC -> fct id LDEC LDEC LINST   ----------------------------------------------
    public C3aOperand visit(SaDecFonc node)
    {
	defaultIn(node);

	c3a.ajouteInst(new C3aInstFBegin(node.tsItem, ""));
    if(node.getParametres() != null) node.getParametres().accept(this);
    if(node.getVariable() != null) node.getVariable().accept(this);
    if(node.getCorps() != null) node.getCorps().accept(this);
    c3a.ajouteInst(new C3aInstFEnd(""));

	defaultOut(node);


	return null;
    }
    

    // EXPRESSIONS

    // EXP -> entier
    public C3aOperand visit(SaExpInt node)
    {
	defaultIn(node);
    C3aOperand op1 = new C3aConstant(node.getVal());
    /* Peut être qu'on peut diminuer le nombre de variables temporaires ? */
    C3aOperand result = c3a.newTemp();

    c3a.ajouteInst(new C3aInstAffect(op1, result, ""));
    defaultOut(node);
    return result;
    }

    // EXP -> VAR 
    public C3aOperand visit(SaExpVar node)
    {
	defaultIn(node);
    C3aOperand result = node.getVar().accept(this);
    defaultOut(node);
    return result;
    }

    public C3aOperand visit(SaExpAppel node)
    {
        defaultIn(node);
        node.getVal().accept(this);
        SaLExp queue = node.getVal().getArguments();
        for (int i = 0; i < queue.length(); i++) {
            SaExp arg = queue.getTete();
            C3aOperand param = arg.accept(this);
            c3a.ajouteInst(new c3a.C3aInstParam(param, ""));
            queue = queue.getQueue();
        }
        c3a.ajouteInst(new C3aInstCall(new C3aFunction(node.getVal().tsItem), new C3aTemp(c3a.getTempCounter()), node.getVal().getNom()));
        defaultOut(node);
        return null;
    }

    // EXP -> op2 EXP EXP
    public C3aOperand visit(SaExpAdd node)
    {
	defaultIn(node);
	C3aOperand op1 = node.getOp1().accept(this);
	C3aOperand op2 = node.getOp2().accept(this);
	C3aOperand result = c3a.newTemp();

	c3a.ajouteInst(new C3aInstAdd(op1, op2, result, ""));
	defaultOut(node);
	return result;
    }

    public C3aOperand visit(SaExpSub node)
    {
	defaultIn(node);

    C3aOperand op1 = node.getOp1().accept(this);
    C3aOperand op2 = node.getOp2().accept(this);
    C3aOperand result = c3a.newTemp();

    c3a.ajouteInst(new C3aInstSub(op1, op2, result, ""));

	defaultOut(node);
	return result;
    }
    
    public C3aOperand visit(SaExpMult node)
    {
	defaultIn(node);
    C3aOperand op1 = node.getOp1().accept(this);
    C3aOperand op2 = node.getOp2().accept(this);
    C3aOperand result = c3a.newTemp();

    c3a.ajouteInst(new C3aInstMult(op1, op2, result, ""));

    defaultOut(node);
    return result;
    }
    
    public C3aOperand visit(SaExpDiv node)
    {
	defaultIn(node);

    C3aOperand op1 = node.getOp1().accept(this);
    C3aOperand op2 = node.getOp2().accept(this);
    C3aOperand result = c3a.newTemp();

    c3a.ajouteInst(new C3aInstDiv(op1, op2, result, ""));

    defaultOut(node);
    return result;
    }

    // EXP -> inf EXP EXP
    public C3aOperand visit(SaExpInf node)
    {
	defaultIn(node);

    defaultOut(node);
	return null;
    }

    // EXP -> eq EXP EXP
    public C3aOperand visit(SaExpEqual node)
    {
	defaultIn(node);


	defaultOut(node);
	return null;
    }
    
    // EXP -> and EXP EXP
    public C3aOperand visit(SaExpAnd node)
    {
	defaultIn(node);


	defaultOut(node);
	return null;
    }
    
    // EXP -> or EXP EXP
    public C3aOperand visit(SaExpOr node)
    {
	defaultIn(node);

	defaultOut(node);
	return null;
    }

    // EXP -> not EXP
    public C3aOperand visit(SaExpNot node)
    {
	defaultIn(node);

	defaultOut(node);
	return null;
    }
    
    // EXP -> lire
    public C3aOperand visit(SaExpLire node)
    {
	defaultIn(node);
    C3aOperand resultat = c3a.newTemp();
    c3a.ajouteInst(new C3aInstRead(resultat, ""));
	defaultOut(node);
	return null;
    }
    
    // INST -> ret EXP
    public C3aOperand visit(SaInstRetour node)
    {
	defaultIn(node);
	C3aOperand op1 = node.getVal().accept(this);
	c3a.ajouteInst(new C3aInstReturn(op1, ""));
	defaultOut(node);
	return null;
    }	

    
    // INST -> ecr EXP
    public C3aOperand visit(SaInstEcriture node)
    {
	defaultIn(node);
    C3aOperand op1 = node.getArg().accept(this);
    c3a.ajouteInst(new C3aInstWrite(op1, ""));
	defaultOut(node);
	return null;
    }
    
    // INST -> tq EXP LINST
    public C3aOperand visit(SaInstTantQue node)
    {
        defaultIn(node);

        defaultOut(node);
	return null;
    }

    // INST -> aff VAR EXP -----------------------------------------------------------
    public C3aOperand visit(SaInstAffect node)
    {
	defaultIn(node);
	C3aOperand lhs =  node.getLhs().accept(this);
	C3aOperand rhs = node.getRhs().accept(this);
    c3a.ajouteInst(new C3aInstAffect(rhs, lhs, ""));
	defaultOut(node);
	return lhs;
    }


    
    // VAR  ->simple id 
    public C3aOperand visit(SaVarSimple node)
    {
        defaultIn(node);
        C3aVar var = new C3aVar(node.tsItem, null);
        defaultOut(node);
        return var;
    }
    
    // APP -> id LEXP
    public C3aOperand visit(SaAppel node)
    {
	defaultIn(node);
    if(node.getArguments() != null) node.getArguments().accept(this);
    SaLExp queue = node.getArguments();
    for (int i = 0; i < queue.length(); i++) {
        SaExp arg = queue.getTete();
        C3aOperand param = arg.accept(this);
        c3a.ajouteInst(new c3a.C3aInstParam(param, ""));
        queue = queue.getQueue();
    }
    c3a.ajouteInst(new c3a.C3aInstCall(new C3aFunction(node.tsItem), new C3aTemp(c3a.getTempCounter()), ""));
    System.out.println(node.getNom());
	defaultOut(node);
	return null;
    }

    // INST -> si EXP LINST LINST -------------------------------------------------
    public C3aOperand visit(SaInstSi node)
    {
	defaultIn(node);

	node.getTest().accept(this);

    if (node.getAlors() != null)
       node.getAlors().accept(this);
    if(node.getSinon() != null)
        node.getSinon().accept(this);

	defaultOut(node);
	return null;
    }
    
    // LEXP -> EXP LEXP 
    public C3aOperand visit(SaLExp node)
    {
	defaultIn(node);
    node.getTete().accept(this);
	if(node.getQueue() != null)
	    node.getQueue().accept(this);
	defaultOut(node);
	return null;
    }

    // VAR  ->indicee id EXP
    public C3aOperand visit(SaVarIndicee node)
    {
	defaultIn(node);
	defaultOut(node);
	return null;
    }


    
}
