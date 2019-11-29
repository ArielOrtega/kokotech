package com.yoyo.ventas.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Receptor")
public class Receptor
{
	@XmlElement(name="Nombre")
    private String Nombre;

	@XmlElement(name="Identificacion")
    private Identificacion Identificacion;

	@XmlElement(name="Telefono")
    private Telefono Telefono;

	@XmlElement(name="CorreoElectronico")
    private String CorreoElectronico;

	@XmlElement(name="Fax")
    private Fax Fax;

    private String[] content;

    public String getNombre ()
    {
        return Nombre;
    }

    public void setNombre (String Nombre)
    {
        this.Nombre = Nombre;
    }

    public Identificacion getIdentificacion ()
    {
        return Identificacion;
    }

    public void setIdentificacion (Identificacion Identificacion)
    {
        this.Identificacion = Identificacion;
    }

    public Telefono getTelefono ()
    {
        return Telefono;
    }

    public void setTelefono (Telefono Telefono)
    {
        this.Telefono = Telefono;
    }

    public String getCorreoElectronico ()
    {
        return CorreoElectronico;
    }

    public void setCorreoElectronico (String CorreoElectronico)
    {
        this.CorreoElectronico = CorreoElectronico;
    }

    public Fax getFax ()
    {
        return Fax;
    }

    public void setFax (Fax Fax)
    {
        this.Fax = Fax;
    }

    public String[] getContent ()
    {
        return content;
    }

    public void setContent (String[] content)
    {
        this.content = content;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Nombre = "+Nombre+", Identificacion = "+Identificacion+", Telefono = "+Telefono+", CorreoElectronico = "+CorreoElectronico+", Fax = "+Fax+", content = "+content+"]";
    }
}
			
			