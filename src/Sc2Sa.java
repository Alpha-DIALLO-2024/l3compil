import sa.SaDecFonc;
import sa.SaDecVar;
import sa.SaExp;
import sa.SaExpAdd;
import sa.SaNode;
import sa.SaProg;
import sc.analysis.DepthFirstAdapter;
import sc.node.AAdditionAdsous;
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

}
