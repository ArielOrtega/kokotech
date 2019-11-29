package com.yoyo.ventas.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Impuesto") 
public class Impuesto
{
	@XmlElement(name="Codigo")
    private String Codigo;

	@XmlElement(name="Monto")
    private String Monto;

	@XmlElement(name="Tarifa")
    private String Tarifa;

    public String getCodigo ()
    {
        return Codigo;
    }

    public void setCodigo (String Codigo)
    {
        this.Codigo = Codigo;
    }

    public String getMonto ()
    {
        return Monto;
    }

    public void setMonto (String Monto)
    {
        this.Monto = Monto;
    }

    public String getTarifa ()
    {
        return Tarifa;
    }

    public void setTarifa (String Tarifa)
    {
        this.Tarifa = Tarifa;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Codigo = "+Codigo+", Monto = "+Monto+", Tarifa = "+Tarifa+"]";
    }
}
			
		
