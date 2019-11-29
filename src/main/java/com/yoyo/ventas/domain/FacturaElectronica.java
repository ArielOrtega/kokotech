package com.yoyo.ventas.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="FacturaElectronica") 
public class FacturaElectronica
{
	@XmlElement(name="DetalleServicio")
    private DetalleServicio DetalleServicio;

	@XmlElement(name="MedioPago")
    private String MedioPago;

	@XmlElement(name="Clave")
    private String Clave;

	@XmlElement(name="Receptor")
    private Receptor Receptor;

	@XmlElement(name="Normativa")
    private Normativa Normativa;

	@XmlElement(name="FechaEmision")
    private String FechaEmision;

	@XmlElement(name="PlazoCredito")
    private String PlazoCredito;

	@XmlElement(name="Emisor")
    private Emisor Emisor;

	@XmlElement(name="CondicionVenta")
    private String CondicionVenta;

	@XmlElement(name="NumeroConsecutivo")
    private String NumeroConsecutivo;

	@XmlElement(name="ResumenFactura")
    private ResumenFactura ResumenFactura;

    public DetalleServicio getDetalleServicio ()
    {
        return DetalleServicio;
    }

    public void setDetalleServicio (DetalleServicio DetalleServicio)
    {
        this.DetalleServicio = DetalleServicio;
    }

    public String getMedioPago ()
    {
        return MedioPago;
    }

    public void setMedioPago (String MedioPago)
    {
        this.MedioPago = MedioPago;
    }

    public String getClave ()
    {
        return Clave;
    }

    public void setClave (String Clave)
    {
        this.Clave = Clave;
    }

    public Receptor getReceptor ()
    {
        return Receptor;
    }

    public void setReceptor (Receptor Receptor)
    {
        this.Receptor = Receptor;
    }

    public Normativa getNormativa ()
    {
        return Normativa;
    }

    public void setNormativa (Normativa Normativa)
    {
        this.Normativa = Normativa;
    }

    public String getFechaEmision ()
    {
        return FechaEmision;
    }

    public void setFechaEmision (String FechaEmision)
    {
        this.FechaEmision = FechaEmision;
    }

    public String getPlazoCredito ()
    {
        return PlazoCredito;
    }

    public void setPlazoCredito (String PlazoCredito)
    {
        this.PlazoCredito = PlazoCredito;
    }

    public Emisor getEmisor ()
    {
        return Emisor;
    }

    public void setEmisor (Emisor Emisor)
    {
        this.Emisor = Emisor;
    }

    public String getCondicionVenta ()
    {
        return CondicionVenta;
    }

    public void setCondicionVenta (String CondicionVenta)
    {
        this.CondicionVenta = CondicionVenta;
    }

    public String getNumeroConsecutivo ()
    {
        return NumeroConsecutivo;
    }

    public void setNumeroConsecutivo (String NumeroConsecutivo)
    {
        this.NumeroConsecutivo = NumeroConsecutivo;
    }

    public ResumenFactura getResumenFactura ()
    {
        return ResumenFactura;
    }

    public void setResumenFactura (ResumenFactura ResumenFactura)
    {
        this.ResumenFactura = ResumenFactura;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [DetalleServicio = "+DetalleServicio.toString()+", MedioPago = "+MedioPago+", Clave = "+Clave+", Receptor = "+Receptor.toString()+", Normativa = "+Normativa.toString()+", FechaEmision = "+FechaEmision+", PlazoCredito = "+PlazoCredito+""
        		+ ", Emisor = "+Emisor.toString()+", CondicionVenta = "+CondicionVenta.toString()+", NumeroConsecutivo = "+NumeroConsecutivo+", ResumenFactura = "+
        		ResumenFactura.toString()+"]";
    }
}
			
		