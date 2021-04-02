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
    
    // DEC -> fct id LDEC LDEC LINST
    public C3aOperand visit(SaDecFonc node)
    {
	defaultIn(node);
	c3a.ajouteInst(new C3aInstFBegin(node.tsItem, "entree fonction"));
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
    C3aOperand result = new C3aConstant(node.getVal());
    /* Peut être qu'on peut diminuer le nombre de variables temporaires ? */
    System.out.println("Sortie ExpInt");
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
        C3aOperand ret = node.getVal().accept(this);
        //if(node.getVal().getArguments() != null) node.getVal().getArguments().accept(this);
        defaultOut(node);
        return ret;
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

    public C3aOperand visit(SaExpModulo node)
    {
	defaultIn(node);

    C3aOperand op1 = node.getOp1().accept(this);
    C3aOperand op2 = node.getOp2().accept(this);
    C3aOperand tmp = c3a.newTemp();
    C3aOperand tmp2 = c3a.newTemp();
    C3aOperand result = c3a.newTemp();

    c3a.ajouteInst(new C3aInstDiv(op1, op2, tmp, ""));
    c3a.ajouteInst(new C3aInstMult(op2, tmp, tmp2, ""));
    c3a.ajouteInst(new C3aInstSub(op1, tmp2, result, ""));

    defaultOut(node);
    return result;
    }

    // EXP -> inf EXP EXP
    public C3aOperand visit(SaExpInf node)
    {
	defaultIn(node);
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);
        C3aLabel e = c3a.newAutoLabel();
        C3aOperand result = c3a.newTemp();
        c3a.ajouteInst(new C3aInstAffect(new C3aConstant(1), result, ""));
        c3a.ajouteInst(new C3aInstJumpIfLess(op1, op2, e, ""));
        c3a.ajouteInst(new C3aInstAffect(new C3aConstant(0), result, ""));

        c3a.addLabelToNextInst(e);


        defaultOut(node);
	return result;
    }

    // EXP -> eq EXP EXP
    public C3aOperand visit(SaExpEqual node)
    {
	defaultIn(node);

        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);

        C3aLabel etiquette0 = c3a.newAutoLabel();

        C3aOperand result = c3a.newTemp();

        c3a.ajouteInst(new C3aInstAffect(new C3aConstant(1), result, ""));
        c3a.ajouteInst(new C3aInstJumpIfEqual(op1, op2, etiquette0, ""));
        c3a.ajouteInst(new C3aInstAffect(new C3aConstant(0), result, ""));

        c3a.addLabelToNextInst(etiquette0);


	defaultOut(node);
	return result;
    }
    
    // EXP -> and EXP EXP
    public C3aOperand visit(SaExpAnd node)
    {
	defaultIn(node);

        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);

        C3aLabel etiquette0 = c3a.newAutoLabel();
        C3aLabel etiquette1 = c3a.newAutoLabel();
        C3aOperand result = c3a.newTemp();

        c3a.ajouteInst(new C3aInstJumpIfEqual(op1, new C3aConstant(0), etiquette1, ""));
        c3a.ajouteInst(new C3aInstJumpIfEqual(op2, new C3aConstant(0), etiquette1, ""));

        c3a.ajouteInst(new C3aInstAffect(new C3aConstant(1), result, ""));
        c3a.ajouteInst(new C3aInstJump(etiquette0, ""));
        c3a.addLabelToNextInst(etiquette1);

        c3a.ajouteInst(new C3aInstAffect(new C3aConstant(0), result, ""));
        c3a.addLabelToNextInst(etiquette0);



	defaultOut(node);
	return result;
    }
    
    // EXP -> or EXP EXP
    public C3aOperand visit(SaExpOr node)
    {
	defaultIn(node);

        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);

        C3aLabel etiquette0 = c3a.newAutoLabel();
        C3aLabel etiquette1 = c3a.newAutoLabel();
        C3aOperand result = c3a.newTemp();

        c3a.ajouteInst(new C3aInstJumpIfNotEqual(op1, new C3aConstant(0), etiquette1, ""));
        c3a.ajouteInst(new C3aInstJumpIfNotEqual(op2, new C3aConstant(0), etiquette1, ""));

        c3a.ajouteInst(new C3aInstAffect(new C3aConstant(0), result, ""));
        c3a.ajouteInst(new C3aInstJump(etiquette0, ""));
        c3a.addLabelToNextInst(etiquette1);

        c3a.ajouteInst(new C3aInstAffect(new C3aConstant(1), result, ""));
        c3a.addLabelToNextInst(etiquette0);

	defaultOut(node);
	return result;
    }

    // EXP -> not EXP
    public C3aOperand visit(SaExpNot node)
    {
	defaultIn(node);

        C3aOperand op1 = node.getOp1().accept(this);

        C3aLabel etiquette0 = c3a.newAutoLabel();

        C3aOperand result = c3a.newTemp();

        c3a.ajouteInst(new C3aInstAffect(new C3aConstant(1), result, ""));
        c3a.ajouteInst(new C3aInstJumpIfEqual(op1, new C3aConstant(0), etiquette0, ""));
        c3a.ajouteInst(new C3aInstAffect(new C3aConstant(0), result, ""));

        c3a.addLabelToNextInst(etiquette0);

	defaultOut(node);
	return result;
    }
    
    // EXP -> lire
    public C3aOperand visit(SaExpLire node)
    {
	defaultIn(node);
    C3aOperand result = c3a.newTemp();
    c3a.ajouteInst(new C3aInstRead(result, ""));
	defaultOut(node);
	return result;
    }
    
    // INST -> ret EXP
    public C3aOperand visit(SaInstRetour node)
    {
	defaultIn(node);
	C3aOperand op1 = node.getVal().accept(this);
	c3a.ajouteInst(new C3aInstReturn(op1, ""));
    c3a.ajouteInst(new c3a.C3aInstFEnd(""));
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
    
    // INST -> tq EXP LINST-----------------------------------------------------------------------------
    public C3aOperand visit(SaInstTantQue node)
    {
        defaultIn(node);


        C3aLabel etiquette0 = c3a.newAutoLabel();
        C3aLabel etiquette1 = c3a.newAutoLabel();

        c3a.addLabelToNextInst(etiquette0);

        C3aOperand test = node.getTest().accept(this);


        c3a.ajouteInst(new C3aInstJumpIfEqual(test, new C3aConstant(0), etiquette1, ""));

        if (node.getFaire() != null){
            node.getFaire().accept(this);

            c3a.ajouteInst(new C3aInstJump(etiquette0, ""));
        }


        c3a.addLabelToNextInst(etiquette1);


        defaultOut(node);
	return null;
    }


    // INST -> aff VAR EXP
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
        int oldCount = c3a.getTempCounter();
        //c3a.setTempCounter(0);
        if(node.getArguments() != null) node.getArguments().accept(this);
        C3aTemp retValue = c3a.newTemp();
        c3a.ajouteInst(new c3a.C3aInstCall(new C3aFunction(node.tsItem), retValue, ""));
        //c3a.setTempCounter(oldCount);
        defaultOut(node);
        return retValue;
    }

    // INST -> si EXP LINST LINST ------------------------------------------------------------------------
    public C3aOperand visit(SaInstSi node)
    {
	defaultIn(node);

        C3aOperand op1 = node.getTest().accept(this);

        C3aLabel faux = c3a.newAutoLabel();
        C3aLabel suite = c3a.newAutoLabel();


        c3a.ajouteInst(new C3aInstJumpIfEqual(op1, new C3aConstant(0), faux, ""));

        if (node.getAlors() != null){
            node.getAlors().accept(this);

        }
        if(node.getSinon() != null){
            c3a.ajouteInst(new C3aInstJump(suite, ""));

        }


        c3a.addLabelToNextInst(faux);

        if(node.getSinon() != null){

            node.getSinon().accept(this);
            c3a.addLabelToNextInst(suite);
        }






        defaultOut(node);
	return null;
    }
    
    // LEXP -> EXP LEXP 
    public C3aOperand visit(SaLExp node)
    {
	defaultIn(node);
    C3aOperand param = node.getTete().accept(this);
    c3a.ajouteInst(new c3a.C3aInstParam(param, ""));
	if(node.getQueue() != null) {
	    node.getQueue().accept(this);
    }
	defaultOut(node);
	return null;
    }

    // VAR  ->indicee id EXP
    public C3aOperand visit(SaVarIndicee node)
    {
	defaultIn(node);
    C3aOperand index = node.getIndice().accept(this);
    C3aOperand val = new C3aVar(node.tsItem, index);
	defaultOut(node);
	return val;
    }


    
}
