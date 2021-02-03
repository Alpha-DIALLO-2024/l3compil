/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class ALParams extends PLParams
{
    private TType _type_;
    private TIdentif _identif_;
    private TComma _comma_;
    private PLParams _lParams_;

    public ALParams()
    {
        // Constructor
    }

    public ALParams(
        @SuppressWarnings("hiding") TType _type_,
        @SuppressWarnings("hiding") TIdentif _identif_,
        @SuppressWarnings("hiding") TComma _comma_,
        @SuppressWarnings("hiding") PLParams _lParams_)
    {
        // Constructor
        setType(_type_);

        setIdentif(_identif_);

        setComma(_comma_);

        setLParams(_lParams_);

    }

    @Override
    public Object clone()
    {
        return new ALParams(
            cloneNode(this._type_),
            cloneNode(this._identif_),
            cloneNode(this._comma_),
            cloneNode(this._lParams_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseALParams(this);
    }

    public TType getType()
    {
        return this._type_;
    }

    public void setType(TType node)
    {
        if(this._type_ != null)
        {
            this._type_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._type_ = node;
    }

    public TIdentif getIdentif()
    {
        return this._identif_;
    }

    public void setIdentif(TIdentif node)
    {
        if(this._identif_ != null)
        {
            this._identif_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._identif_ = node;
    }

    public TComma getComma()
    {
        return this._comma_;
    }

    public void setComma(TComma node)
    {
        if(this._comma_ != null)
        {
            this._comma_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._comma_ = node;
    }

    public PLParams getLParams()
    {
        return this._lParams_;
    }

    public void setLParams(PLParams node)
    {
        if(this._lParams_ != null)
        {
            this._lParams_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._lParams_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._type_)
            + toString(this._identif_)
            + toString(this._comma_)
            + toString(this._lParams_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._type_ == child)
        {
            this._type_ = null;
            return;
        }

        if(this._identif_ == child)
        {
            this._identif_ = null;
            return;
        }

        if(this._comma_ == child)
        {
            this._comma_ = null;
            return;
        }

        if(this._lParams_ == child)
        {
            this._lParams_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._type_ == oldChild)
        {
            setType((TType) newChild);
            return;
        }

        if(this._identif_ == oldChild)
        {
            setIdentif((TIdentif) newChild);
            return;
        }

        if(this._comma_ == oldChild)
        {
            setComma((TComma) newChild);
            return;
        }

        if(this._lParams_ == oldChild)
        {
            setLParams((PLParams) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}