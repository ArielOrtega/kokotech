package com.yoyo.ventas.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Fax") 
public class Fax
{
	@XmlElement(name="CodigoPais")
    private String CodigoPais;

	@XmlElement(name="NumTelefono")
    private String NumTelefono;

    public String getCodigoPais ()
    {
        return CodigoPais;
    }

    public void setCodigoPais (String CodigoPais)
    {
        this.CodigoPais = CodigoPais;
    }

    public String getNumTelefono ()
    {
        return NumTelefono;
    }

    public void setNumTelefono (String NumTelefono)
    {
        this.NumTelefono = NumTelefono;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [CodigoPais = "+CodigoPais+", NumTelefono = "+NumTelefono+"]";
    }
}