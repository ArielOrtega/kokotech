package com.yoyo.ventas.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Emisor") 
public class Emisor
{
	@XmlElement(name="Nombre")
    private String Nombre;

	@XmlElement(name="Ubicacion")
    private Ubicacion Ubicacion;

	@XmlElement(name="Identificacion")
    private Identificacion Identificacion;

	@XmlElement(name="NombreComercial")
    private String NombreComercial;

	@XmlElement(name="Telefono")
    private Telefono Telefono;
	
	@XmlElement(name="CorreoElectronico")
    private String CorreoElectronico;

    private String[] content;
	
    public String getNombre ()
    {
        return Nombre;
    }

    public void setNombre (String Nombre)
    {
        this.Nombre = Nombre;
    }

    public Ubicacion getUbicacion ()
    {
        return Ubicacion;
    }

    public void setUbicacion (Ubicacion Ubicacion)
    {
        this.Ubicacion = Ubicacion;
    }

    public Identificacion getIdentificacion ()
    {
        return Identificacion;
    }

    public void setIdentificacion (Identificacion Identificacion)
    {
        this.Identificacion = Identificacion;
    }

    public String getNombreComercial ()
    {
        return NombreComercial;
    }

    public void setNombreComercial (String NombreComercial)
    {
        this.NombreComercial = NombreComercial;
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
        return "ClassPojo [Nombre = "+Nombre+", Ubicacion = "+Ubicacion+", Identificacion = "+Identificacion+", NombreComercial = "+NombreComercial+", Telefono = "+Telefono+", CorreoElectronico = "+CorreoElectronico+", content = "+content+"]";
    }
}
			
			