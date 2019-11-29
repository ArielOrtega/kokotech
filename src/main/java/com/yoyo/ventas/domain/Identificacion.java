package com.yoyo.ventas.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Identificacion") 
public class Identificacion
{
	@XmlElement(name="Numero")
    private String Numero;

	@XmlElement(name="Tipo")
    private String Tipo;

    public String getNumero ()
    {
        return Numero;
    }

    public void setNumero (String Numero)
    {
        this.Numero = Numero;
    }

    public String getTipo ()
    {
        return Tipo;
    }

    public void setTipo (String Tipo)
    {
        this.Tipo = Tipo;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Numero = "+Numero+", Tipo = "+Tipo+"]";
    }
}
