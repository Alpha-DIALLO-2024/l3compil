/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.analysis;

import java.util.*;
import sc.node.*;

public class DepthFirstAdapter extends AnalysisAdapter
{
    public void inStart(Start node)
    {
        defaultIn(node);
    }

    public void outStart(Start node)
    {
        defaultOut(node);
    }

    public void defaultIn(@SuppressWarnings("unused") Node node)
    {
        // Do nothing
    }

    public void defaultOut(@SuppressWarnings("unused") Node node)
    {
        // Do nothing
    }

    @Override
    public void caseStart(Start node)
    {
        inStart(node);
        node.getPProgramme().apply(this);
        node.getEOF().apply(this);
        outStart(node);
    }

    public void inAProgramme(AProgramme node)
    {
        defaultIn(node);
    }

    public void outAProgramme(AProgramme node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAProgramme(AProgramme node)
    {
        inAProgramme(node);
        if(node.getLDecvar() != null)
        {
            node.getLDecvar().apply(this);
        }
        if(node.getSemicol() != null)
        {
            node.getSemicol().apply(this);
        }
        if(node.getLDecfonc() != null)
        {
            node.getLDecfonc().apply(this);
        }
        outAProgramme(node);
    }

    public void inAOnlyFuncProgramme(AOnlyFuncProgramme node)
    {
        defaultIn(node);
    }

    public void outAOnlyFuncProgramme(AOnlyFuncProgramme node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAOnlyFuncProgramme(AOnlyFuncProgramme node)
    {
        inAOnlyFuncProgramme(node);
        if(node.getLDecfonc() != null)
        {
            node.getLDecfonc().apply(this);
        }
        outAOnlyFuncProgramme(node);
    }

    public void inAOrExpr(AOrExpr node)
    {
        defaultIn(node);
    }

    public void outAOrExpr(AOrExpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAOrExpr(AOrExpr node)
    {
        inAOrExpr(node);
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getOr() != null)
        {
            node.getOr().apply(this);
        }
        if(node.getLogAnd() != null)
        {
            node.getLogAnd().apply(this);
        }
        outAOrExpr(node);
    }

    public void inAAndExpr(AAndExpr node)
    {
        defaultIn(node);
    }

    public void outAAndExpr(AAndExpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAndExpr(AAndExpr node)
    {
        inAAndExpr(node);
        if(node.getLogAnd() != null)
        {
            node.getLogAnd().apply(this);
        }
        outAAndExpr(node);
    }

    public void inAAndLogAnd(AAndLogAnd node)
    {
        defaultIn(node);
    }

    public void outAAndLogAnd(AAndLogAnd node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAndLogAnd(AAndLogAnd node)
    {
        inAAndLogAnd(node);
        if(node.getLogAnd() != null)
        {
            node.getLogAnd().apply(this);
        }
        if(node.getAnd() != null)
        {
            node.getAnd().apply(this);
        }
        if(node.getComparison() != null)
        {
            node.getComparison().apply(this);
        }
        outAAndLogAnd(node);
    }

    public void inALessLogAnd(ALessLogAnd node)
    {
        defaultIn(node);
    }

    public void outALessLogAnd(ALessLogAnd node)
    {
        defaultOut(node);
    }

    @Override
    public void caseALessLogAnd(ALessLogAnd node)
    {
        inALessLogAnd(node);
        if(node.getComparison() != null)
        {
            node.getComparison().apply(this);
        }
        outALessLogAnd(node);
    }

    public void inALessComparison(ALessComparison node)
    {
        defaultIn(node);
    }

    public void outALessComparison(ALessComparison node)
    {
        defaultOut(node);
    }

    @Override
    public void caseALessComparison(ALessComparison node)
    {
        inALessComparison(node);
        if(node.getComparison() != null)
        {
            node.getComparison().apply(this);
        }
        if(node.getLess() != null)
        {
            node.getLess().apply(this);
        }
        if(node.getAdsous() != null)
        {
            node.getAdsous().apply(this);
        }
        outALessComparison(node);
    }

    public void inAEqualityComparison(AEqualityComparison node)
    {
        defaultIn(node);
    }

    public void outAEqualityComparison(AEqualityComparison node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAEqualityComparison(AEqualityComparison node)
    {
        inAEqualityComparison(node);
        if(node.getComparison() != null)
        {
            node.getComparison().apply(this);
        }
        if(node.getEquals() != null)
        {
            node.getEquals().apply(this);
        }
        if(node.getAdsous() != null)
        {
            node.getAdsous().apply(this);
        }
        outAEqualityComparison(node);
    }

    public void inAAdsousComparison(AAdsousComparison node)
    {
        defaultIn(node);
    }

    public void outAAdsousComparison(AAdsousComparison node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAdsousComparison(AAdsousComparison node)
    {
        inAAdsousComparison(node);
        if(node.getAdsous() != null)
        {
            node.getAdsous().apply(this);
        }
        outAAdsousComparison(node);
    }

    public void inAAdditionAdsous(AAdditionAdsous node)
    {
        defaultIn(node);
    }

    public void outAAdditionAdsous(AAdditionAdsous node)
    {
        defaultOut(node);
    }

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

    public void inASousAdsous(ASousAdsous node)
    {
        defaultIn(node);
    }

    public void outASousAdsous(ASousAdsous node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASousAdsous(ASousAdsous node)
    {
        inASousAdsous(node);
        if(node.getAdsous() != null)
        {
            node.getAdsous().apply(this);
        }
        if(node.getMinus() != null)
        {
            node.getMinus().apply(this);
        }
        if(node.getMultdiv() != null)
        {
            node.getMultdiv().apply(this);
        }
        outASousAdsous(node);
    }

    public void inAMultdivAdsous(AMultdivAdsous node)
    {
        defaultIn(node);
    }

    public void outAMultdivAdsous(AMultdivAdsous node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAMultdivAdsous(AMultdivAdsous node)
    {
        inAMultdivAdsous(node);
        if(node.getMultdiv() != null)
        {
            node.getMultdiv().apply(this);
        }
        outAMultdivAdsous(node);
    }

    public void inAMultMultdiv(AMultMultdiv node)
    {
        defaultIn(node);
    }

    public void outAMultMultdiv(AMultMultdiv node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAMultMultdiv(AMultMultdiv node)
    {
        inAMultMultdiv(node);
        if(node.getMultdiv() != null)
        {
            node.getMultdiv().apply(this);
        }
        if(node.getMult() != null)
        {
            node.getMult().apply(this);
        }
        if(node.getNeg() != null)
        {
            node.getNeg().apply(this);
        }
        outAMultMultdiv(node);
    }

    public void inADivMultdiv(ADivMultdiv node)
    {
        defaultIn(node);
    }

    public void outADivMultdiv(ADivMultdiv node)
    {
        defaultOut(node);
    }

    @Override
    public void caseADivMultdiv(ADivMultdiv node)
    {
        inADivMultdiv(node);
        if(node.getMultdiv() != null)
        {
            node.getMultdiv().apply(this);
        }
        if(node.getDiv() != null)
        {
            node.getDiv().apply(this);
        }
        if(node.getNeg() != null)
        {
            node.getNeg().apply(this);
        }
        outADivMultdiv(node);
    }

    public void inANegMultdiv(ANegMultdiv node)
    {
        defaultIn(node);
    }

    public void outANegMultdiv(ANegMultdiv node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANegMultdiv(ANegMultdiv node)
    {
        inANegMultdiv(node);
        if(node.getNeg() != null)
        {
            node.getNeg().apply(this);
        }
        outANegMultdiv(node);
    }

    public void inANegNeg(ANegNeg node)
    {
        defaultIn(node);
    }

    public void outANegNeg(ANegNeg node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANegNeg(ANegNeg node)
    {
        inANegNeg(node);
        if(node.getNot() != null)
        {
            node.getNot().apply(this);
        }
        if(node.getNeg() != null)
        {
            node.getNeg().apply(this);
        }
        outANegNeg(node);
    }

    public void inAParNeg(AParNeg node)
    {
        defaultIn(node);
    }

    public void outAParNeg(AParNeg node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAParNeg(AParNeg node)
    {
        inAParNeg(node);
        if(node.getPar() != null)
        {
            node.getPar().apply(this);
        }
        outAParNeg(node);
    }

    public void inAParenthesisPar(AParenthesisPar node)
    {
        defaultIn(node);
    }

    public void outAParenthesisPar(AParenthesisPar node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAParenthesisPar(AParenthesisPar node)
    {
        inAParenthesisPar(node);
        if(node.getLPar() != null)
        {
            node.getLPar().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getRPar() != null)
        {
            node.getRPar().apply(this);
        }
        outAParenthesisPar(node);
    }

    public void inANombrePar(ANombrePar node)
    {
        defaultIn(node);
    }

    public void outANombrePar(ANombrePar node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANombrePar(ANombrePar node)
    {
        inANombrePar(node);
        if(node.getNombre() != null)
        {
            node.getNombre().apply(this);
        }
        outANombrePar(node);
    }

    public void inAIdentifPar(AIdentifPar node)
    {
        defaultIn(node);
    }

    public void outAIdentifPar(AIdentifPar node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAIdentifPar(AIdentifPar node)
    {
        inAIdentifPar(node);
        if(node.getIdentif() != null)
        {
            node.getIdentif().apply(this);
        }
        outAIdentifPar(node);
    }

    public void inAArrPar(AArrPar node)
    {
        defaultIn(node);
    }

    public void outAArrPar(AArrPar node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAArrPar(AArrPar node)
    {
        inAArrPar(node);
        if(node.getIdentif() != null)
        {
            node.getIdentif().apply(this);
        }
        if(node.getLSqr() != null)
        {
            node.getLSqr().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getRSqr() != null)
        {
            node.getRSqr().apply(this);
        }
        outAArrPar(node);
    }

    public void inAFcallPar(AFcallPar node)
    {
        defaultIn(node);
    }

    public void outAFcallPar(AFcallPar node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAFcallPar(AFcallPar node)
    {
        inAFcallPar(node);
        if(node.getFunctionCall() != null)
        {
            node.getFunctionCall().apply(this);
        }
        outAFcallPar(node);
    }

    public void inALcallPar(ALcallPar node)
    {
        defaultIn(node);
    }

    public void outALcallPar(ALcallPar node)
    {
        defaultOut(node);
    }

    @Override
    public void caseALcallPar(ALcallPar node)
    {
        inALcallPar(node);
        if(node.getLireCall() != null)
        {
            node.getLireCall().apply(this);
        }
        outALcallPar(node);
    }

    public void inAEcrireCall(AEcrireCall node)
    {
        defaultIn(node);
    }

    public void outAEcrireCall(AEcrireCall node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAEcrireCall(AEcrireCall node)
    {
        inAEcrireCall(node);
        if(node.getEcrire() != null)
        {
            node.getEcrire().apply(this);
        }
        if(node.getLPar() != null)
        {
            node.getLPar().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getRPar() != null)
        {
            node.getRPar().apply(this);
        }
        outAEcrireCall(node);
    }

    public void inALireCall(ALireCall node)
    {
        defaultIn(node);
    }

    public void outALireCall(ALireCall node)
    {
        defaultOut(node);
    }

    @Override
    public void caseALireCall(ALireCall node)
    {
        inALireCall(node);
        if(node.getLire() != null)
        {
            node.getLire().apply(this);
        }
        if(node.getLPar() != null)
        {
            node.getLPar().apply(this);
        }
        if(node.getRPar() != null)
        {
            node.getRPar().apply(this);
        }
        outALireCall(node);
    }

    public void inAListExprListExpr(AListExprListExpr node)
    {
        defaultIn(node);
    }

    public void outAListExprListExpr(AListExprListExpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAListExprListExpr(AListExprListExpr node)
    {
        inAListExprListExpr(node);
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getComma() != null)
        {
            node.getComma().apply(this);
        }
        if(node.getListExpr() != null)
        {
            node.getListExpr().apply(this);
        }
        outAListExprListExpr(node);
    }

    public void inAExprListExpr(AExprListExpr node)
    {
        defaultIn(node);
    }

    public void outAExprListExpr(AExprListExpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAExprListExpr(AExprListExpr node)
    {
        inAExprListExpr(node);
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        outAExprListExpr(node);
    }

    public void inAEmptyListExpr(AEmptyListExpr node)
    {
        defaultIn(node);
    }

    public void outAEmptyListExpr(AEmptyListExpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAEmptyListExpr(AEmptyListExpr node)
    {
        inAEmptyListExpr(node);
        outAEmptyListExpr(node);
    }

    public void inAFunctionCall(AFunctionCall node)
    {
        defaultIn(node);
    }

    public void outAFunctionCall(AFunctionCall node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAFunctionCall(AFunctionCall node)
    {
        inAFunctionCall(node);
        if(node.getIdentif() != null)
        {
            node.getIdentif().apply(this);
        }
        if(node.getLPar() != null)
        {
            node.getLPar().apply(this);
        }
        if(node.getListExpr() != null)
        {
            node.getListExpr().apply(this);
        }
        if(node.getRPar() != null)
        {
            node.getRPar().apply(this);
        }
        outAFunctionCall(node);
    }

    public void inAListInstr(AListInstr node)
    {
        defaultIn(node);
    }

    public void outAListInstr(AListInstr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAListInstr(AListInstr node)
    {
        inAListInstr(node);
        if(node.getInstr() != null)
        {
            node.getInstr().apply(this);
        }
        if(node.getListInstr() != null)
        {
            node.getListInstr().apply(this);
        }
        outAListInstr(node);
    }

    public void inAInstrListInstr(AInstrListInstr node)
    {
        defaultIn(node);
    }

    public void outAInstrListInstr(AInstrListInstr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAInstrListInstr(AInstrListInstr node)
    {
        inAInstrListInstr(node);
        if(node.getInstr() != null)
        {
            node.getInstr().apply(this);
        }
        outAInstrListInstr(node);
    }

    public void inAIdentifTab(AIdentifTab node)
    {
        defaultIn(node);
    }

    public void outAIdentifTab(AIdentifTab node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAIdentifTab(AIdentifTab node)
    {
        inAIdentifTab(node);
        if(node.getIdentif() != null)
        {
            node.getIdentif().apply(this);
        }
        if(node.getLSqr() != null)
        {
            node.getLSqr().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getRSqr() != null)
        {
            node.getRSqr().apply(this);
        }
        outAIdentifTab(node);
    }

    public void inAAffectation(AAffectation node)
    {
        defaultIn(node);
    }

    public void outAAffectation(AAffectation node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAffectation(AAffectation node)
    {
        inAAffectation(node);
        if(node.getIdentif() != null)
        {
            node.getIdentif().apply(this);
        }
        if(node.getEquals() != null)
        {
            node.getEquals().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        outAAffectation(node);
    }

    public void inATabAffectation(ATabAffectation node)
    {
        defaultIn(node);
    }

    public void outATabAffectation(ATabAffectation node)
    {
        defaultOut(node);
    }

    @Override
    public void caseATabAffectation(ATabAffectation node)
    {
        inATabAffectation(node);
        if(node.getIdentifTab() != null)
        {
            node.getIdentifTab().apply(this);
        }
        if(node.getEquals() != null)
        {
            node.getEquals().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        outATabAffectation(node);
    }

    public void inAIfThenCondElse(AIfThenCondElse node)
    {
        defaultIn(node);
    }

    public void outAIfThenCondElse(AIfThenCondElse node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAIfThenCondElse(AIfThenCondElse node)
    {
        inAIfThenCondElse(node);
        if(node.getCond() != null)
        {
            node.getCond().apply(this);
        }
        outAIfThenCondElse(node);
    }

    public void inAIfThenElseCondElse(AIfThenElseCondElse node)
    {
        defaultIn(node);
    }

    public void outAIfThenElseCondElse(AIfThenElseCondElse node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAIfThenElseCondElse(AIfThenElseCondElse node)
    {
        inAIfThenElseCondElse(node);
        if(node.getCond() != null)
        {
            node.getCond().apply(this);
        }
        if(node.getElse() != null)
        {
            node.getElse().apply(this);
        }
        if(node.getBloc() != null)
        {
            node.getBloc().apply(this);
        }
        outAIfThenElseCondElse(node);
    }

    public void inAIfThenCond(AIfThenCond node)
    {
        defaultIn(node);
    }

    public void outAIfThenCond(AIfThenCond node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAIfThenCond(AIfThenCond node)
    {
        inAIfThenCond(node);
        if(node.getIf() != null)
        {
            node.getIf().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getThen() != null)
        {
            node.getThen().apply(this);
        }
        if(node.getBloc() != null)
        {
            node.getBloc().apply(this);
        }
        outAIfThenCond(node);
    }

    public void inABloc(ABloc node)
    {
        defaultIn(node);
    }

    public void outABloc(ABloc node)
    {
        defaultOut(node);
    }

    @Override
    public void caseABloc(ABloc node)
    {
        inABloc(node);
        if(node.getLAcc() != null)
        {
            node.getLAcc().apply(this);
        }
        if(node.getListInstr() != null)
        {
            node.getListInstr().apply(this);
        }
        if(node.getRAcc() != null)
        {
            node.getRAcc().apply(this);
        }
        outABloc(node);
    }

    public void inAEmptyBloc(AEmptyBloc node)
    {
        defaultIn(node);
    }

    public void outAEmptyBloc(AEmptyBloc node)
    {
        defaultOut(node);
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
        outAEmptyBloc(node);
    }

    public void inAReturn(AReturn node)
    {
        defaultIn(node);
    }

    public void outAReturn(AReturn node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAReturn(AReturn node)
    {
        inAReturn(node);
        if(node.getRet() != null)
        {
            node.getRet().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        outAReturn(node);
    }

    public void inALoop(ALoop node)
    {
        defaultIn(node);
    }

    public void outALoop(ALoop node)
    {
        defaultOut(node);
    }

    @Override
    public void caseALoop(ALoop node)
    {
        inALoop(node);
        if(node.getWhile() != null)
        {
            node.getWhile().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getDo() != null)
        {
            node.getDo().apply(this);
        }
        if(node.getBloc() != null)
        {
            node.getBloc().apply(this);
        }
        outALoop(node);
    }

    public void inAAffInstr(AAffInstr node)
    {
        defaultIn(node);
    }

    public void outAAffInstr(AAffInstr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAffInstr(AAffInstr node)
    {
        inAAffInstr(node);
        if(node.getAffectation() != null)
        {
            node.getAffectation().apply(this);
        }
        if(node.getSemicol() != null)
        {
            node.getSemicol().apply(this);
        }
        outAAffInstr(node);
    }

    public void inAConditionInstr(AConditionInstr node)
    {
        defaultIn(node);
    }

    public void outAConditionInstr(AConditionInstr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAConditionInstr(AConditionInstr node)
    {
        inAConditionInstr(node);
        if(node.getCondElse() != null)
        {
            node.getCondElse().apply(this);
        }
        outAConditionInstr(node);
    }

    public void inARetInstr(ARetInstr node)
    {
        defaultIn(node);
    }

    public void outARetInstr(ARetInstr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseARetInstr(ARetInstr node)
    {
        inARetInstr(node);
        if(node.getReturn() != null)
        {
            node.getReturn().apply(this);
        }
        if(node.getSemicol() != null)
        {
            node.getSemicol().apply(this);
        }
        outARetInstr(node);
    }

    public void inALoopInstr(ALoopInstr node)
    {
        defaultIn(node);
    }

    public void outALoopInstr(ALoopInstr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseALoopInstr(ALoopInstr node)
    {
        inALoopInstr(node);
        if(node.getLoop() != null)
        {
            node.getLoop().apply(this);
        }
        outALoopInstr(node);
    }

    public void inAFcallInstr(AFcallInstr node)
    {
        defaultIn(node);
    }

    public void outAFcallInstr(AFcallInstr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAFcallInstr(AFcallInstr node)
    {
        inAFcallInstr(node);
        if(node.getFunctionCall() != null)
        {
            node.getFunctionCall().apply(this);
        }
        if(node.getSemicol() != null)
        {
            node.getSemicol().apply(this);
        }
        outAFcallInstr(node);
    }

    public void inAEcrireInstr(AEcrireInstr node)
    {
        defaultIn(node);
    }

    public void outAEcrireInstr(AEcrireInstr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAEcrireInstr(AEcrireInstr node)
    {
        inAEcrireInstr(node);
        if(node.getEcrireCall() != null)
        {
            node.getEcrireCall().apply(this);
        }
        if(node.getSemicol() != null)
        {
            node.getSemicol().apply(this);
        }
        outAEcrireInstr(node);
    }

    public void inALDecfonc(ALDecfonc node)
    {
        defaultIn(node);
    }

    public void outALDecfonc(ALDecfonc node)
    {
        defaultOut(node);
    }

    @Override
    public void caseALDecfonc(ALDecfonc node)
    {
        inALDecfonc(node);
        if(node.getDecfonc() != null)
        {
            node.getDecfonc().apply(this);
        }
        if(node.getLDecfonc() != null)
        {
            node.getLDecfonc().apply(this);
        }
        outALDecfonc(node);
    }

    public void inADecfoncLDecfonc(ADecfoncLDecfonc node)
    {
        defaultIn(node);
    }

    public void outADecfoncLDecfonc(ADecfoncLDecfonc node)
    {
        defaultOut(node);
    }

    @Override
    public void caseADecfoncLDecfonc(ADecfoncLDecfonc node)
    {
        inADecfoncLDecfonc(node);
        if(node.getDecfonc() != null)
        {
            node.getDecfonc().apply(this);
        }
        outADecfoncLDecfonc(node);
    }

    public void inAVarsParameterizedDecfonc(AVarsParameterizedDecfonc node)
    {
        defaultIn(node);
    }

    public void outAVarsParameterizedDecfonc(AVarsParameterizedDecfonc node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVarsParameterizedDecfonc(AVarsParameterizedDecfonc node)
    {
        inAVarsParameterizedDecfonc(node);
        if(node.getIdentif() != null)
        {
            node.getIdentif().apply(this);
        }
        if(node.getLPar() != null)
        {
            node.getLPar().apply(this);
        }
        if(node.getLParams() != null)
        {
            node.getLParams().apply(this);
        }
        if(node.getRPar() != null)
        {
            node.getRPar().apply(this);
        }
        if(node.getLDecvar() != null)
        {
            node.getLDecvar().apply(this);
        }
        if(node.getSemicol() != null)
        {
            node.getSemicol().apply(this);
        }
        if(node.getBloc() != null)
        {
            node.getBloc().apply(this);
        }
        outAVarsParameterizedDecfonc(node);
    }

    public void inANoVarsParameterizedDecfonc(ANoVarsParameterizedDecfonc node)
    {
        defaultIn(node);
    }

    public void outANoVarsParameterizedDecfonc(ANoVarsParameterizedDecfonc node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANoVarsParameterizedDecfonc(ANoVarsParameterizedDecfonc node)
    {
        inANoVarsParameterizedDecfonc(node);
        if(node.getIdentif() != null)
        {
            node.getIdentif().apply(this);
        }
        if(node.getLPar() != null)
        {
            node.getLPar().apply(this);
        }
        if(node.getLParams() != null)
        {
            node.getLParams().apply(this);
        }
        if(node.getRPar() != null)
        {
            node.getRPar().apply(this);
        }
        if(node.getBloc() != null)
        {
            node.getBloc().apply(this);
        }
        outANoVarsParameterizedDecfonc(node);
    }

    public void inAVarsNoParamsDecfonc(AVarsNoParamsDecfonc node)
    {
        defaultIn(node);
    }

    public void outAVarsNoParamsDecfonc(AVarsNoParamsDecfonc node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVarsNoParamsDecfonc(AVarsNoParamsDecfonc node)
    {
        inAVarsNoParamsDecfonc(node);
        if(node.getIdentif() != null)
        {
            node.getIdentif().apply(this);
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
        }
        if(node.getSemicol() != null)
        {
            node.getSemicol().apply(this);
        }
        if(node.getBloc() != null)
        {
            node.getBloc().apply(this);
        }
        outAVarsNoParamsDecfonc(node);
    }

    public void inANoVarsNoParamsDecfonc(ANoVarsNoParamsDecfonc node)
    {
        defaultIn(node);
    }

    public void outANoVarsNoParamsDecfonc(ANoVarsNoParamsDecfonc node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANoVarsNoParamsDecfonc(ANoVarsNoParamsDecfonc node)
    {
        inANoVarsNoParamsDecfonc(node);
        if(node.getIdentif() != null)
        {
            node.getIdentif().apply(this);
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
        }
        outANoVarsNoParamsDecfonc(node);
    }

    public void inALParams(ALParams node)
    {
        defaultIn(node);
    }

    public void outALParams(ALParams node)
    {
        defaultOut(node);
    }

    @Override
    public void caseALParams(ALParams node)
    {
        inALParams(node);
        if(node.getType() != null)
        {
            node.getType().apply(this);
        }
        if(node.getIdentif() != null)
        {
            node.getIdentif().apply(this);
        }
        if(node.getComma() != null)
        {
            node.getComma().apply(this);
        }
        if(node.getLParams() != null)
        {
            node.getLParams().apply(this);
        }
        outALParams(node);
    }

    public void inAIdentifLParams(AIdentifLParams node)
    {
        defaultIn(node);
    }

    public void outAIdentifLParams(AIdentifLParams node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAIdentifLParams(AIdentifLParams node)
    {
        inAIdentifLParams(node);
        if(node.getType() != null)
        {
            node.getType().apply(this);
        }
        if(node.getIdentif() != null)
        {
            node.getIdentif().apply(this);
        }
        outAIdentifLParams(node);
    }

    public void inALDecvar(ALDecvar node)
    {
        defaultIn(node);
    }

    public void outALDecvar(ALDecvar node)
    {
        defaultOut(node);
    }

    @Override
    public void caseALDecvar(ALDecvar node)
    {
        inALDecvar(node);
        if(node.getDecvar() != null)
        {
            node.getDecvar().apply(this);
        }
        if(node.getComma() != null)
        {
            node.getComma().apply(this);
        }
        if(node.getLDecvar() != null)
        {
            node.getLDecvar().apply(this);
        }
        outALDecvar(node);
    }

    public void inADecvarLDecvar(ADecvarLDecvar node)
    {
        defaultIn(node);
    }

    public void outADecvarLDecvar(ADecvarLDecvar node)
    {
        defaultOut(node);
    }

    @Override
    public void caseADecvarLDecvar(ADecvarLDecvar node)
    {
        inADecvarLDecvar(node);
        if(node.getDecvar() != null)
        {
            node.getDecvar().apply(this);
        }
        outADecvarLDecvar(node);
    }

    public void inAVoidLDecvar(AVoidLDecvar node)
    {
        defaultIn(node);
    }

    public void outAVoidLDecvar(AVoidLDecvar node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVoidLDecvar(AVoidLDecvar node)
    {
        inAVoidLDecvar(node);
        outAVoidLDecvar(node);
    }

    public void inADecvar(ADecvar node)
    {
        defaultIn(node);
    }

    public void outADecvar(ADecvar node)
    {
        defaultOut(node);
    }

    @Override
    public void caseADecvar(ADecvar node)
    {
        inADecvar(node);
        if(node.getType() != null)
        {
            node.getType().apply(this);
        }
        if(node.getIdentif() != null)
        {
            node.getIdentif().apply(this);
        }
        outADecvar(node);
    }

    public void inADectabDecvar(ADectabDecvar node)
    {
        defaultIn(node);
    }

    public void outADectabDecvar(ADectabDecvar node)
    {
        defaultOut(node);
    }

    @Override
    public void caseADectabDecvar(ADectabDecvar node)
    {
        inADectabDecvar(node);
        if(node.getDectab() != null)
        {
            node.getDectab().apply(this);
        }
        outADectabDecvar(node);
    }

    public void inADectab(ADectab node)
    {
        defaultIn(node);
    }

    public void outADectab(ADectab node)
    {
        defaultOut(node);
    }

    @Override
    public void caseADectab(ADectab node)
    {
        inADectab(node);
        if(node.getType() != null)
        {
            node.getType().apply(this);
        }
        if(node.getIdentif() != null)
        {
            node.getIdentif().apply(this);
        }
        if(node.getLSqr() != null)
        {
            node.getLSqr().apply(this);
        }
        if(node.getNombre() != null)
        {
            node.getNombre().apply(this);
        }
        if(node.getRSqr() != null)
        {
            node.getRSqr().apply(this);
        }
        outADectab(node);
    }
}
