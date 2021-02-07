import sa.*;
import sc.analysis.DepthFirstAdapter;
import sc.node.*;
import sa.SaDecFonc;
import sa.SaDecTab;
import sa.SaDecVar;
import sa.SaExp;
import sa.SaExpAdd;
import sa.SaNode;
import sa.SaProg;
import sc.analysis.DepthFirstAdapter;
import sc.node.AAdditionAdsous;
import sc.node.ADectab;
import sc.node.ADecvar;

public class Sc2Sa extends DepthFirstAdapter {
    private SaNode returnValue;

    @Override
    public void caseADecvar(ADecvar node)
    {
        String text = null;
        inADecvar(node);
        if(node.getType() != null)
        {
            node.getType().apply(this);
        }
        if(node.getIdentif() != null)
        {
            text = node.getIdentif().getText();
            node.getIdentif().apply(this);
        }
        this.returnValue = new SaDecVar(text);
        outADecvar(node);
    }

    @Override
    public void caseAAdditionAdsous(AAdditionAdsous node)
    {
        SaExp op1 = null;
        SaExp op2 = null;
        inAAdditionAdsous(node);
        if(node.getAdsous() != null)
        {
            node.getAdsous().apply(this);
            op1 = (SaExp) this.returnValue;
        }
        if(node.getPlus() != null)
        {
            node.getPlus().apply(this);
        }
        if(node.getMultdiv() != null)
        {
            node.getMultdiv().apply(this);
            op2 = (SaExp) this.returnValue;
        }
        outAAdditionAdsous(node);
        this.returnValue = new SaExpAdd(op1, op2);
    }

    @Override
    public void caseADectab(ADectab node)
    {
        String name = null;
        int size = 0;
        inADectab(node);
        if(node.getType() != null)
        {
            node.getType().apply(this);
        }
        if(node.getIdentif() != null)
        {
            node.getIdentif().apply(this);
            name = node.getIdentif().getText();
        }
        if(node.getLSqr() != null)
        {
            node.getLSqr().apply(this);
        }
        if(node.getNombre() != null)
        {
            node.getNombre().apply(this);
            size = Integer.parseInt(node.getNombre().getText());
        }
        if(node.getRSqr() != null)
        {
            node.getRSqr().apply(this);
        }
        outADectab(node);
        this.returnValue = new SaDecTab(name, size);
    }

    @Override
    public void caseAProgramme(AProgramme node)
    {
        SaLDec variable =null;
        SaLDec fonction  =null;

        inAProgramme(node);
        if(node.getLDecvar() != null)
        {
            node.getLDecvar().apply(this);
            variable = (SaLDec)this.returnValue;
        }
        if(node.getSemicol() != null)
        {
            node.getSemicol().apply(this);
        }
        if(node.getLDecfonc() != null)
        {
            node.getLDecfonc().apply(this);
            fonction  = (SaLDec)this.returnValue;
        }
        outAProgramme(node);

        this.returnValue = new SaProg(variable, fonction );
    }

    @Override
    public void caseAOrExpr(AOrExpr node)
    {
        SaExp operator1 = null;
        SaExp operator2 = null;

        inAOrExpr(node);
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
            operator1 = (SaExp) this.returnValue;
        }
        if(node.getOr() != null)
        {
            node.getOr().apply(this);
        }
        if(node.getLogAnd() != null)
        {
            node.getLogAnd().apply(this);
            operator2 = (SaExp) this.returnValue;

        }
        outAOrExpr(node);

        this.returnValue = new  SaExpOr(operator1, operator2);
    }



    @Override
    public void caseAAndLogAnd(AAndLogAnd node)
    {
        SaExp operator1 = null;
        SaExp operator2 = null;

        inAAndLogAnd(node);
        if(node.getLogAnd() != null)
        {
            node.getLogAnd().apply(this);
            operator1 = (SaExp) this.returnValue;
        }
        if(node.getAnd() != null)
        {
            node.getAnd().apply(this);
        }
        if(node.getComparison() != null)
        {
            node.getComparison().apply(this);
            operator2 = (SaExp) this.returnValue;
        }
        outAAndLogAnd(node);

        this.returnValue = new SaExpAnd(operator1, operator2);
    }



}
