package com.yoyo.ventas.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="LineaDetalle") 
public class LineaDetalle
{
	@XmlElement(name="UnidadMedida")
    private String UnidadMedida;

	@XmlElement(name="NaturalezaDescuento")
    private String NaturalezaDescuento;

	@XmlElement(name="MontoTotalLinea")
    private String MontoTotalLinea;

	@XmlElement(name="MontoDescuento")
    private String MontoDescuento;

	@XmlElement(name="Cantidad")
    private String Cantidad;

	@XmlElement(name="Detalle")
    private String Detalle;

	@XmlElement(name="NumeroLinea")
    private String NumeroLinea;

	@XmlElement(name="PrecioUnitario")
    private String PrecioUnitario;

	@XmlElement(name="MontoTotal")
    private String MontoTotal;

	@XmlElement(name="SubTotal")
    private String SubTotal;

	public String getUnidadMedida ()
    {
        return UnidadMedida;
    }

    public void setUnidadMedida (String UnidadMedida)
    {
        this.UnidadMedida = UnidadMedida;
    }

    public String getNaturalezaDescuento ()
    {
        return NaturalezaDescuento;
    }

    public void setNaturalezaDescuento (String NaturalezaDescuento)
    {
        this.NaturalezaDescuento = NaturalezaDescuento;
    }

    public String getMontoTotalLinea ()
    {
        return MontoTotalLinea;
    }

    public void setMontoTotalLinea (String MontoTotalLinea)
    {
        this.MontoTotalLinea = MontoTotalLinea;
    }

    public String getMontoDescuento ()
    {
        return MontoDescuento;
    }

    public void setMontoDescuento (String MontoDescuento)
    {
        this.MontoDescuento = MontoDescuento;
    }

    public String getCantidad ()
    {
        return Cantidad;
    }

    public void setCantidad (String Cantidad)
    {
        this.Cantidad = Cantidad;
    }

    public String getDetalle ()
    {
        return Detalle;
    }

    public void setDetalle (String Detalle)
    {
        this.Detalle = Detalle;
    }

    public String getNumeroLinea ()
    {
        return NumeroLinea;
    }

    public void setNumeroLinea (String NumeroLinea)
    {
        this.NumeroLinea = NumeroLinea;
    }

    public String getPrecioUnitario ()
    {
        return PrecioUnitario;
    }

    public void setPrecioUnitario (String PrecioUnitario)
    {
        this.PrecioUnitario = PrecioUnitario;
    }

    public String getMontoTotal ()
    {
        return MontoTotal;
    }

    public void setMontoTotal (String MontoTotal)
    {
        this.MontoTotal = MontoTotal;
    }

    public String getSubTotal ()
    {
        return SubTotal;
    }

    public void setSubTotal (String SubTotal)
    {
        this.SubTotal = SubTotal;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [UnidadMedida = "+UnidadMedida+", NaturalezaDescuento = "+NaturalezaDescuento+", MontoTotalLinea = "+MontoTotalLinea+", MontoDescuento = "+MontoDescuento+", Cantidad = "+Cantidad+", Detalle = "+Detalle+", NumeroLinea = "+NumeroLinea+", PrecioUnitario = "+PrecioUnitario+", MontoTotal = "+MontoTotal+", SubTotal = "+SubTotal+"]";
    }
}
			
			