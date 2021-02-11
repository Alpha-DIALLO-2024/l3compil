import sa.*;
import sc.analysis.DepthFirstAdapter;
import sc.node.*;
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
        this.returnValue = new SaDecTab(name, size);
        outADectab(node);
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
        this.returnValue = new SaProg(variable, fonction);
        outAProgramme(node);
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
        this.returnValue = new  SaExpOr(operator1, operator2);
        outAOrExpr(node);
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
        this.returnValue = new SaExpAnd(operator1, operator2);
        outAAndLogAnd(node);
    }


    @Override
    public void caseALessComparison(ALessComparison node)
    {

        SaExp operator1 = null;
        SaExp operator2 = null;

        inALessComparison(node);
        if(node.getComparison() != null)
        {
            node.getComparison().apply(this);
            operator1 = (SaExp) this.returnValue;
        }
        if(node.getLess() != null)
        {
            node.getLess().apply(this);
        }
        if(node.getAdsous() != null)
        {
            node.getAdsous().apply(this);
            operator2 = (SaExp) this.returnValue;
        }
        this.returnValue = new SaExpInf(operator1, operator2);
        outALessComparison(node);
    }



    @Override
    public void caseAEqualityComparison(AEqualityComparison node)
    {
        SaExp operator1 = null;
        SaExp operator2 = null;

        inAEqualityComparison(node);
        if(node.getComparison() != null)
        {
            node.getComparison().apply(this);
            operator1 = (SaExp) this.returnValue;
        }
        if(node.getEquals() != null)
        {
            node.getEquals().apply(this);
        }
        if(node.getAdsous() != null)
        {
            node.getAdsous().apply(this);
            operator2 = (SaExp) this.returnValue;
        }
        this.returnValue = new SaExpEqual(operator1, operator2);
        outAEqualityComparison(node);
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
        this.returnValue = new SaExpAdd(op1, op2);
        outAAdditionAdsous(node);
    }


    @Override
    public void caseASousAdsous(ASousAdsous node)
    {
        SaExp operator1 = null;
        SaExp operator2 = null;

        inASousAdsous(node);
        if(node.getAdsous() != null)
        {
            node.getAdsous().apply(this);
            operator1 = (SaExp) this.returnValue;
        }
        if(node.getMinus() != null)
        {
            node.getMinus().apply(this);
        }
        if(node.getMultdiv() != null)
        {
            node.getMultdiv().apply(this);
            operator1 = (SaExp) this.returnValue;

        }
        this.returnValue = new SaExpSub(operator1, operator2);
        outASousAdsous(node);
    }



    @Override
    public void caseAMultMultdiv(AMultMultdiv node)
    {
        SaExp operator1 = null;
        SaExp operator2 = null;

        inAMultMultdiv(node);
        if(node.getMultdiv() != null)
        {
            node.getMultdiv().apply(this);
            operator1 = (SaExp) this.returnValue;
        }
        if(node.getMult() != null)
        {
            node.getMult().apply(this);
        }
        if(node.getNeg() != null)
        {
            node.getNeg().apply(this);
            operator2 = (SaExp) this.returnValue;
        }
        this.returnValue = new SaExpMult(operator1, operator2);
        outAMultMultdiv(node);
    }



    @Override
    public void caseADivMultdiv(ADivMultdiv node)
    {

        SaExp operator1 = null;
        SaExp operator2 = null;

        inADivMultdiv(node);
        if(node.getMultdiv() != null)
        {
            node.getMultdiv().apply(this);
            operator1 = (SaExp) this.returnValue;
        }
        if(node.getDiv() != null)
        {
            node.getDiv().apply(this);
        }
        if(node.getNeg() != null)
        {
            node.getNeg().apply(this);
            operator2 = (SaExp) this.returnValue;
        }
        this.returnValue = new SaExpDiv(operator1, operator2);
        outADivMultdiv(node);
    }


    @Override
    public void caseANegNeg(ANegNeg node)
    {
        SaExp operator1 = null;
        inANegNeg(node);
        if(node.getNot() != null)
        {
            node.getNot().apply(this);
        }
        if(node.getNeg() != null)
        {
            node.getNeg().apply(this);
            operator1 = (SaExp) this.returnValue;
        }
        this.returnValue = new SaExpNot(operator1);
        outANegNeg(node);
    }

    @Override
    public void caseAParenthesisPar(AParenthesisPar node)
    {
        SaExp expr = null;
        inAParenthesisPar(node);
        if(node.getLPar() != null)
        {
            node.getLPar().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
            expr = (SaExp) this.returnValue;
        }
        if(node.getRPar() != null)
        {
            node.getRPar().apply(this);
        }
        this.returnValue = (SaExp) expr;
        outAParenthesisPar(node);
    }


    @Override
    public void caseAListExprListExpr(AListExprListExpr node)
    {
        inAListExprListExpr(node);
        SaExp tete = null;
        SaLExp queue = null;

        if(node.getListExpr() != null)
        {
            node.getListExpr().apply(this);
            queue = (SaLExp) this.returnValue;
        }
        if(node.getComma() != null)
        {
            node.getComma().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
            tete = (SaExp) this.returnValue;
        }
        this.returnValue = new  SaLExp(tete, queue);
        outAListExprListExpr(node);
    }

    @Override
    public void caseAExprListExpr(AExprListExpr node)
    {
        inAExprListExpr(node);
        SaExp exp = null;
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
            exp = (SaExp) this.returnValue;
        }
        this.returnValue = new SaLExp(exp, null);
        outAExprListExpr(node);
    }

    @Override
    public void caseAEmptyListExpr(AEmptyListExpr node)
    {
        inAEmptyListExpr(node);
        this.returnValue = new SaLExp(null, null);
        outAEmptyListExpr(node);
    }

    @Override
    public void caseAFunctionCall(AFunctionCall node)
    {
        inAFunctionCall(node);
        String nom = null;
        SaLExp lexp = null;
        if(node.getIdentif() != null)
        {
            node.getIdentif().apply(this);
            nom = node.getIdentif().getText();
        }
        if(node.getLPar() != null)
        {
            node.getLPar().apply(this);
        }
        if(node.getListExpr() != null)
        {
            node.getListExpr().apply(this);
            lexp = (SaLExp) this.returnValue;
        }
        if(node.getRPar() != null)
        {
            node.getRPar().apply(this);
        }
        this.returnValue = new SaAppel(nom, lexp);
        outAFunctionCall(node);
    }

    @Override
    public void caseAListInstr(AListInstr node)
    {
        inAListInstr(node);
        SaInst instr = null;
        SaLInst linstr = null;
        if(node.getListInstr() != null)
        {
            node.getListInstr().apply(this);
            linstr = (SaLInst) this.returnValue;
        }
        if(node.getInstr() != null)
        {
            node.getInstr().apply(this);
            instr = (SaInst) this.returnValue;
        }
        this.returnValue = new SaLInst(instr, linstr);
        outAListInstr(node);
    }

    @Override
    public void caseAInstrListInstr(AInstrListInstr node)
    {
        inAInstrListInstr(node);
        SaInst instr = null;
        if(node.getInstr() != null)
        {
            node.getInstr().apply(this);
            instr = (SaInst) this.returnValue;
        }
        this.returnValue = new SaLInst(instr, null);
        outAInstrListInstr(node);
    }

    @Override
    public void caseAIdentifTab(AIdentifTab node)
    {
        inAIdentifTab(node);
        String nom = null;
        SaExp exp = null;
        if(node.getIdentif() != null)
        {
            node.getIdentif().apply(this);
            nom = node.getIdentif().getText();
        }
        if(node.getLSqr() != null)
        {
            node.getLSqr().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
            exp = (SaExp) this.returnValue;
        }
        if(node.getRSqr() != null)
        {
            node.getRSqr().apply(this);
        }
        this.returnValue = new SaVarIndicee(nom, exp);
        outAIdentifTab(node);
    }

    @Override
    public void caseAAffectation(AAffectation node)
    {
        inAAffectation(node);
        String nom = null;
        SaExp exp = null;
        if(node.getIdentif() != null)
        {
            node.getIdentif().apply(this);
            nom = node.getIdentif().getText();
        }
        if(node.getEquals() != null)
        {
            node.getEquals().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
            exp = (SaExp) this.returnValue;
        }
        this.returnValue = new SaInstAffect(new SaVarSimple(nom), exp);
        outAAffectation(node);
    }

    @Override
    public void caseATabAffectation(ATabAffectation node)
    {
        inATabAffectation(node);
        SaVar dec = null;
        SaExp exp = null;
        if(node.getIdentifTab() != null)
        {
            node.getIdentifTab().apply(this);
            dec = (SaVar) this.returnValue;
        }
        if(node.getEquals() != null)
        {
            node.getEquals().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
            exp = (SaExp) this.returnValue;
        }
        this.returnValue = new SaInstAffect(dec, exp);
        outATabAffectation(node);
    }

    @Override
    public void caseAIfThenCondElse(AIfThenCondElse node)
    {
        inAIfThenCondElse(node);
        SaInstSi si = null;
        if(node.getCond() != null)
        {
            node.getCond().apply(this);
            si = (SaInstSi) this.returnValue;
        }
        this.returnValue = si;
        outAIfThenCondElse(node);
    }

    @Override
    public void caseAIfThenElseCondElse(AIfThenElseCondElse node)
    {
        inAIfThenElseCondElse(node);
        SaInstSi si = null;
        SaInst elseBloc = null;
        if(node.getCond() != null)
        {
            node.getCond().apply(this);
            si = (SaInstSi) this.returnValue;
        }
        if(node.getElse() != null)
        {
            node.getElse().apply(this);
        }
        if(node.getBloc() != null)
        {
            node.getBloc().apply(this);
            elseBloc = (SaInst) this.returnValue;
        }
        si.setSinon(elseBloc);
        this.returnValue = si;
        outAIfThenElseCondElse(node);
    }


    @Override
    public void caseAIfThenCond(AIfThenCond node)
    {
        inAIfThenCond(node);
        SaExp exp = null;
        SaInst inst = null;
        if(node.getIf() != null)
        {
            node.getIf().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
            exp = (SaExp) this.returnValue;
        }
        if(node.getThen() != null)
        {
            node.getThen().apply(this);
        }
        if(node.getBloc() != null)
        {
            node.getBloc().apply(this);
            inst = (SaInst) this.returnValue;
        }
        this.returnValue = new SaInstSi(exp, inst, null);
        outAIfThenCond(node);
    }

    @Override
    public void caseABloc(ABloc node)
    {
        inABloc(node);
        SaLInst inst = null;
        if(node.getLAcc() != null)
        {
            node.getLAcc().apply(this);
        }
        if(node.getListInstr() != null)
        {
            node.getListInstr().apply(this);
            inst = (SaLInst) this.returnValue;
        }
        if(node.getRAcc() != null)
        {
            node.getRAcc().apply(this);
        }
        this.returnValue = new SaLInst(null, inst);
        outABloc(node);
    }

    @Override
    public void caseAEmptyBloc(AEmptyBloc node)
    {
        inAEmptyBloc(node);
        if(node.getLAcc() != null)
        {
            node.getLAcc().apply(this);
        }
        if(node.getRAcc() != null)
        {
            node.getRAcc().apply(this);
        }
        this.returnValue = new SaInstBloc(new SaLInst(null, null));
        outAEmptyBloc(node);
    }

    @Override
    public void caseAReturn(AReturn node)
    {
        inAReturn(node);
        SaExp exp = null;
        if(node.getRet() != null)
        {
            node.getRet().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        this.returnValue = new SaInstRetour(exp);
        outAReturn(node);
    }

    @Override
    public void caseALoop(ALoop node)
    {
        inALoop(node);
        SaExp exp = null;
        SaInst bloc = null;
        if(node.getWhile() != null)
        {
            node.getWhile().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
            exp = (SaExp) this.returnValue;
        }
        if(node.getDo() != null)
        {
            node.getDo().apply(this);
        }
        if(node.getBloc() != null)
        {
            node.getBloc().apply(this);
            bloc = (SaInst) this.returnValue;
        }
        this.returnValue = new SaInstTantQue(exp, bloc);
        outALoop(node);
    }

    @Override
    public void caseAAffInstr(AAffInstr node)
    {
        inAAffInstr(node);
        SaInstAffect aff = null;
        if(node.getAffectation() != null)
        {
            node.getAffectation().apply(this);
            aff = (SaInstAffect) this.returnValue;
        }
        if(node.getSemicol() != null)
        {
            node.getSemicol().apply(this);
        }
        this.returnValue = aff;
        outAAffInstr(node);
    }

    @Override
    public void caseAConditionInstr(AConditionInstr node)
    {
        inAConditionInstr(node);
        SaInstSi si = null;
        if(node.getCondElse() != null)
        {
            node.getCondElse().apply(this);
            si = (SaInstSi) this.returnValue;
        }
        this.returnValue = si;
        outAConditionInstr(node);
    }

    @Override
    public void caseARetInstr(ARetInstr node)
    {
        inARetInstr(node);
        SaInstRetour retour = null;
        if(node.getReturn() != null)
        {
            node.getReturn().apply(this);
            retour = (SaInstRetour) this.returnValue;
        }
        if(node.getSemicol() != null)
        {
            node.getSemicol().apply(this);
        }
        this.returnValue = retour;
        outARetInstr(node);
    }

    @Override
    public void caseALoopInstr(ALoopInstr node)
    {
        inALoopInstr(node);
        SaInstTantQue loop = null;
        if(node.getLoop() != null)
        {
            node.getLoop().apply(this);
            loop = (SaInstTantQue) this.returnValue;
        }
        this.returnValue = loop;
        outALoopInstr(node);
    }

    @Override
    public void caseAFcallInstr(AFcallInstr node)
    {
        inAFcallInstr(node);
        SaAppel call = null;
        if(node.getFunctionCall() != null)
        {
            node.getFunctionCall().apply(this);
            call = (SaAppel) this.returnValue;
        }
        if(node.getSemicol() != null)
        {
            node.getSemicol().apply(this);
        }
        this.returnValue = call;
        outAFcallInstr(node);
    }

    @Override
    public void caseALDecfonc(ALDecfonc node)
    {
        inALDecfonc(node);
        SaDecFonc dec = null;
        SaLDec ldec = null;
        if(node.getDecfonc() != null)
        {
            node.getDecfonc().apply(this);
            dec = (SaDecFonc) this.returnValue;
        }
        if(node.getLDecfonc() != null)
        {
            node.getLDecfonc().apply(this);
            ldec = (SaLDec) this.returnValue;
        }
        this.returnValue = new SaLDec(dec, ldec);
        outALDecfonc(node);
    }

    @Override
    public void caseADecfoncLDecfonc(ADecfoncLDecfonc node)
    {
        inADecfoncLDecfonc(node);
        SaDecFonc dec = null;
        if(node.getDecfonc() != null)
        {
            node.getDecfonc().apply(this);
            dec = (SaDecFonc) this.returnValue;
        }
        this.returnValue = new SaLDec(dec, null);
        outADecfoncLDecfonc(node);
    }

    @Override
    public void caseAVarsParameterizedDecfonc(AVarsParameterizedDecfonc node)
    {
        inAVarsParameterizedDecfonc(node);
        String nom = null;
        SaLDec vars = null;
        SaLDec params = null;
        SaInst bloc = null;
        if(node.getIdentif() != null)
        {
            node.getIdentif().apply(this);
            nom = node.getIdentif().getText();
        }
        if(node.getLPar() != null)
        {
            node.getLPar().apply(this);
        }
        if(node.getLParams() != null)
        {
            node.getLParams().apply(this);
            params = (SaLDec) this.returnValue;
        }
        if(node.getRPar() != null)
        {
            node.getRPar().apply(this);
        }
        if(node.getLDecvar() != null)
        {
            node.getLDecvar().apply(this);
            vars = (SaLDec) this.returnValue;
        }
        if(node.getSemicol() != null)
        {
            node.getSemicol().apply(this);
        }
        if(node.getBloc() != null)
        {
            node.getBloc().apply(this);
            bloc = (SaInst) this.returnValue;
        }
        this.returnValue = new SaDecFonc(nom, params, vars, bloc);
        outAVarsParameterizedDecfonc(node);
    }

    @Override
    public void caseANoVarsParameterizedDecfonc(ANoVarsParameterizedDecfonc node)
    {
        inANoVarsParameterizedDecfonc(node);
        String nom = null;
        SaLDec vars = new SaLDec(null, null);
        SaLDec params = null;
        SaInst bloc = null;
        if(node.getIdentif() != null)
        {
            node.getIdentif().apply(this);
            nom = node.getIdentif().getText();
        }
        if(node.getLPar() != null)
        {
            node.getLPar().apply(this);
        }
        if(node.getLParams() != null)
        {
            node.getLParams().apply(this);
            params = (SaLDec) this.returnValue;
        }
        if(node.getRPar() != null)
        {
            node.getRPar().apply(this);
        }
        if(node.getBloc() != null)
        {
            node.getBloc().apply(this);
            bloc = (SaInst) this.returnValue;
        }
        this.returnValue = new SaDecFonc(nom, params, vars, bloc);
        outANoVarsParameterizedDecfonc(node);
    }

    @Override
    public void caseAVarsNoParamsDecfonc(AVarsNoParamsDecfonc node)
    {
        inAVarsNoParamsDecfonc(node);
        String nom = null;
        SaInst bloc = null;
        SaLDec vars = null;
        if(node.getIdentif() != null)
        {
            node.getIdentif().apply(this);
            nom = node.getIdentif().getText();
        }
        if(node.getLPar() != null)
        {
            node.getLPar().apply(this);
        }
        if(node.getRPar() != null)
        {
            node.getRPar().apply(this);
        }
        if(node.getLDecvar() != null)
        {
            node.getLDecvar().apply(this);
            vars = (SaLDec) this.returnValue;
        }
        if(node.getSemicol() != null)
        {
            node.getSemicol().apply(this);
        }
        if(node.getBloc() != null)
        {
            node.getBloc().apply(this);
            bloc = (SaInst) this.returnValue;
        }
        this.returnValue = new SaDecFonc(nom, new SaLDec(null, null), vars, bloc);
        outAVarsNoParamsDecfonc(node);
    }

    @Override
    public void caseANoVarsNoParamsDecfonc(ANoVarsNoParamsDecfonc node)
    {
        inANoVarsNoParamsDecfonc(node);
        String nom = null;
        SaInst bloc = null;
        if(node.getIdentif() != null)
        {
            node.getIdentif().apply(this);
            nom = node.getIdentif().getText();
        }
        if(node.getLPar() != null)
        {
            node.getLPar().apply(this);
        }
        if(node.getRPar() != null)
        {
            node.getRPar().apply(this);
        }
        if(node.getBloc() != null)
        {
            node.getBloc().apply(this);
            bloc = (SaInst) this.returnValue;
        }
        this.returnValue = new SaDecFonc(nom, new SaLDec(null, null), new SaLDec(null, null), bloc);
        outANoVarsNoParamsDecfonc(node);
    }

    @Override
    public void caseALParams(ALParams node)
    {
        inALParams(node);
        String nom = null;
        SaLDec list = null;
        if(node.getType() != null)
        {
            node.getType().apply(this);
        }
        if(node.getIdentif() != null)
        {
            node.getIdentif().apply(this);
            nom = node.getIdentif().getText();
        }
        if(node.getComma() != null)
        {
            node.getComma().apply(this);
        }
        if(node.getLParams() != null)
        {
            node.getLParams().apply(this);
            list = (SaLDec) this.returnValue;
        }
        this.returnValue = new SaLDec(new SaDecVar(nom), list)
        outALParams(node);
    }

    @Override
    public void caseAIdentifLParams(AIdentifLParams node)
    {
        inAIdentifLParams(node);
        String nom = null;
        if(node.getType() != null)
        {
            node.getType().apply(this);
        }
        if(node.getIdentif() != null)
        {
            node.getIdentif().apply(this);
            nom = node.getIdentif().getText();
        }
        this.returnValue = new SaLDec(new SaDecVar(nom), null);
        outAIdentifLParams(node);
    }

    @Override
    public void caseALDecvar(ALDecvar node)
    {
        inALDecvar(node);
        SaDec dec = null;
        SaLDec decL = null;
        if(node.getDecvar() != null)
        {
            node.getDecvar().apply(this);
            dec = (SaDec) this.returnValue;
        }
        if(node.getComma() != null)
        {
            node.getComma().apply(this);
        }
        if(node.getLDecvar() != null)
        {
            node.getLDecvar().apply(this);
            decL = (SaLDec) this.returnValue;
        }
        this.returnValue = new SaLDec(dec, decL);
        outALDecvar(node);
    }

    @Override
    public void caseADecvarLDecvar(ADecvarLDecvar node)
    {
        inADecvarLDecvar(node);
        SaDec decvar = null;
        if(node.getDecvar() != null)
        {
            node.getDecvar().apply(this);
            decvar = (SaDec) this.returnValue;
        }
        this.returnValue = new SaLDec(decvar, null);
        outADecvarLDecvar(node);
    }

    @Override
    public void caseAVoidLDecvar(AVoidLDecvar node)
    {
        inAVoidLDecvar(node);
        outAVoidLDecvar(node);
    }

    @Override
    public void caseADectabDecvar(ADectabDecvar node)
    {
        inADectabDecvar(node);
        if(node.getDectab() != null)
        {
            node.getDectab().apply(this);
        }
        this.returnValue = (SaExp) this.returnValue;
        outADectabDecvar(node);
    }
}
