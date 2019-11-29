package com.yoyo.ventas.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="DetalleServicio") 
public class DetalleServicio
{
	@XmlElement(name="LineaDetalle")
    private LineaDetalle[] LineaDetalle;

    private String[] content;

	public LineaDetalle[] getLineaDetalle ()
    {
        return LineaDetalle;
    }

    public void setLineaDetalle (LineaDetalle[] LineaDetalle)
    {
        this.LineaDetalle = LineaDetalle;
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
        return "ClassPojo [LineaDetalle = "+LineaDetalle.toString()+", content = "+content+"]";
    }
}
			
			