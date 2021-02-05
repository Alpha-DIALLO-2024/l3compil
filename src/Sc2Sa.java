import sa.SaExp;
import sa.SaExpAdd;
import sa.SaNode;
import sa.SaProg;
import sc.analysis.DepthFirstAdapter;
import sc.node.AAdditionAdsous;
import sc.node.AProgramme;

public class Sc2Sa extends DepthFirstAdapter {
    private SaNode returnValue;
    
    @Override
    public void caseAAdditionAdsous(AAdditionAdsous node)
    {

        SaExp op1 =null;
        SaExp op2 =null;

        inAAdditionAdsous(node);
        if(node.getAdsous() != null)
        {
            node.getAdsous().apply(this);
            op1 = (SaExp)this.returnValue;
        }
        if(node.getPlus() != null)
        {
            node.getPlus().apply(this);
        }
        if(node.getMultdiv() != null)
        {
            node.getMultdiv().apply(this);
            op2 = (SaExp)this.returnValue;
        }
        this.returnValue =new SaExpAdd(op1, op2);
        outAAdditionAdsous(node);
    }

    
}
