/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class AOnlyFuncProgramme extends PProgramme
{
    private PLDecfonc _lDecfonc_;

    public AOnlyFuncProgramme()
    {
        // Constructor
    }

    public AOnlyFuncProgramme(
        @SuppressWarnings("hiding") PLDecfonc _lDecfonc_)
    {
        // Constructor
        setLDecfonc(_lDecfonc_);

    }

    @Override
    public Object clone()
    {
        return new AOnlyFuncProgramme(
            cloneNode(this._lDecfonc_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAOnlyFuncProgramme(this);
    }

    public PLDecfonc getLDecfonc()
    {
        return this._lDecfonc_;
    }

    public void setLDecfonc(PLDecfonc node)
    {
        if(this._lDecfonc_ != null)
        {
            this._lDecfonc_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._lDecfonc_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._lDecfonc_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._lDecfonc_ == child)
        {
            this._lDecfonc_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._lDecfonc_ == oldChild)
        {
            setLDecfonc((PLDecfonc) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}