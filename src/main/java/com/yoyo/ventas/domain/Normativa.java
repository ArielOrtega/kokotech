package com.yoyo.ventas.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Normativa")
public class Normativa
{
	@XmlElement(name="NumeroResolucion")
    private String NumeroResolucion;

	@XmlElement(name="FechaResolucion")
    private String FechaResolucion;

    public String getNumeroResolucion ()
    {
        return NumeroResolucion;
    }

    public void setNumeroResolucion (String NumeroResolucion)
    {
        this.NumeroResolucion = NumeroResolucion;
    }

    public String getFechaResolucion ()
    {
        return FechaResolucion;
    }

    public void setFechaResolucion (String FechaResolucion)
    {
        this.FechaResolucion = FechaResolucion;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [NumeroResolucion = "+NumeroResolucion+", FechaResolucion = "+FechaResolucion+"]";
    }
}
