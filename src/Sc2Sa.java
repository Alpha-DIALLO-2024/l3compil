import sa.*;
import sc.analysis.DepthFirstAdapter;
import sc.node.AAdditionAdsous;
import sc.node.ADectab;
import sc.node.ADecvar;
import sc.node.AProgramme;

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
        this.returnValue = new SaProg(variable, fonction );
        outAProgramme(node);
    }

}
