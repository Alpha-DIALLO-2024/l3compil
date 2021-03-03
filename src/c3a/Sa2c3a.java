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
	defaultOut(node);
	return null;
    }
    

    // EXPRESSIONS

    // EXP -> entier 
    public C3aOperand visit(SaExpInt node)
    {
	defaultIn(node);
	defaultOut(node);
	return null;
    }

    // EXP -> VAR 
    public C3aOperand visit(SaExpVar node)
    {
	defaultIn(node);
	defaultOut(node);
	return null; 
    }

    public C3aOperand visit(SaExpAppel node)
    {
	defaultIn(node);
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
	defaultOut(node);
	return null;
    }
    
    // INST -> ret EXP 
    public C3aOperand visit(SaInstRetour node)
    {
	defaultIn(node);
	defaultOut(node);
	return null;
    }	

    
    // INST -> ecr EXP 
    public C3aOperand visit(SaInstEcriture node)
    {
	defaultIn(node);
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

    // INST -> aff VAR EXP 
    public C3aOperand visit(SaInstAffect node)
    {
	defaultIn(node);
	defaultOut(node);
	return null;
    }
    
    // VAR  ->simple id 
    public C3aOperand visit(SaVarSimple node)
    {
	defaultIn(node);
	defaultOut(node);
	return null;
    }
    
    // APP -> id LEXP
    public C3aOperand visit(SaAppel node)
    {
	defaultIn(node);
	defaultOut(node);
	return null;
    }

    // INST -> si EXP LINST LINST 
    public C3aOperand visit(SaInstSi node)
    {
	defaultIn(node);
	defaultOut(node);
	return null;
    }
    
    // LEXP -> EXP LEXP 
    public C3aOperand visit(SaLExp node)
    {
	defaultIn(node);
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
