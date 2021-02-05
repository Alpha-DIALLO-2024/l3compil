import sa.SaNode;
import sc.analysis.DepthFirstAdapter;
import sc.node.AAdditionAdsous;

public class Sc2Sa extends DepthFirstAdapter {
    private SaNode returnValue;
    
    @Override
    public void caseAAdditionAdsous(AAdditionAdsous node)
    {
        inAAdditionAdsous(node);
        if(node.getAdsous() != null)
        {
            node.getAdsous().apply(this);
        }
        if(node.getPlus() != null)
        {
            node.getPlus().apply(this);
        }
        if(node.getMultdiv() != null)
        {
            node.getMultdiv().apply(this);
        }
        outAAdditionAdsous(node);
    }
    
}
