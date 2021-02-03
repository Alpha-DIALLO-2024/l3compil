/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class ANegNeg extends PNeg
{
    private TNot _not_;
    private PNeg _neg_;

    public ANegNeg()
    {
        // Constructor
    }

    public ANegNeg(
        @SuppressWarnings("hiding") TNot _not_,
        @SuppressWarnings("hiding") PNeg _neg_)
    {
        // Constructor
        setNot(_not_);

        setNeg(_neg_);

    }

    @Override
    public Object clone()
    {
        return new ANegNeg(
            cloneNode(this._not_),
            cloneNode(this._neg_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseANegNeg(this);
    }

    public TNot getNot()
    {
        return this._not_;
    }

    public void setNot(TNot node)
    {
        if(this._not_ != null)
        {
            this._not_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._not_ = node;
    }

    public PNeg getNeg()
    {
        return this._neg_;
    }

    public void setNeg(PNeg node)
    {
        if(this._neg_ != null)
        {
            this._neg_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._neg_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._not_)
            + toString(this._neg_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._not_ == child)
        {
            this._not_ = null;
            return;
        }

        if(this._neg_ == child)
        {
            this._neg_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._not_ == oldChild)
        {
            setNot((TNot) newChild);
            return;
        }

        if(this._neg_ == oldChild)
        {
            setNeg((PNeg) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}