package com.yoyo.ventas.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Ubicacion")
public class Ubicacion
{
	@XmlElement(name="OtrasSenas")
    private String OtrasSenas;

	@XmlElement(name="Barrio")
    private String Barrio;

	@XmlElement(name="Distrito")
    private String Distrito;

	@XmlElement(name="Canton")
    private String Canton;

	@XmlElement(name="Provincia")
    private String Provincia;

    public String getOtrasSenas ()
    {
        return OtrasSenas;
    }

    public void setOtrasSenas (String OtrasSenas)
    {
        this.OtrasSenas = OtrasSenas;
    }

    public String getBarrio ()
    {
        return Barrio;
    }

    public void setBarrio (String Barrio)
    {
        this.Barrio = Barrio;
    }

    public String getDistrito ()
    {
        return Distrito;
    }

    public void setDistrito (String Distrito)
    {
        this.Distrito = Distrito;
    }

    public String getCanton ()
    {
        return Canton;
    }

    public void setCanton (String Canton)
    {
        this.Canton = Canton;
    }

    public String getProvincia ()
    {
        return Provincia;
    }

    public void setProvincia (String Provincia)
    {
        this.Provincia = Provincia;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [OtrasSenas = "+OtrasSenas+", Barrio = "+Barrio+", Distrito = "+Distrito+", Canton = "+Canton+", Provincia = "+Provincia+"]";
    }
}
			
			