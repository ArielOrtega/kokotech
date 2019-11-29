package com.yoyo.ventas.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ResumenFactura")
public class ResumenFactura
{
	@XmlElement(name="TotalMercanciasExentas")
    private String TotalMercanciasExentas;

	@XmlElement(name="TotalDescuentos")
    private String TotalDescuentos;

	@XmlElement(name="TotalComprobante")
    private String TotalImpuesto;

	@XmlElement(name="TotalComprobante")
    private String TotalComprobante;

	@XmlElement(name="CodigoMoneda")
    private String CodigoMoneda;

	@XmlElement(name="TotalVenta")
    private String TotalVenta;

	@XmlElement(name="TotalMercanciasGravadas")
    private String TotalMercanciasGravadas;

	@XmlElement(name="TotalVentaNeta")
    private String TotalVentaNeta;

	@XmlElement(name="TipoCambio")
    private String TipoCambio;

	@XmlElement(name="TotalGravado")
    private String TotalGravado;

	@XmlElement(name="TotalServExentos")
    private String TotalServExentos;

	@XmlElement(name="TotalServGravados")
    private String TotalServGravados;

	@XmlElement(name="TotalExento")
    private String TotalExento;

    public String getTotalMercanciasExentas ()
    {
        return TotalMercanciasExentas;
    }

    public void setTotalMercanciasExentas (String TotalMercanciasExentas)
    {
        this.TotalMercanciasExentas = TotalMercanciasExentas;
    }

    public String getTotalDescuentos ()
    {
        return TotalDescuentos;
    }

    public void setTotalDescuentos (String TotalDescuentos)
    {
        this.TotalDescuentos = TotalDescuentos;
    }

    public String getTotalImpuesto ()
    {
        return TotalImpuesto;
    }

    public void setTotalImpuesto (String TotalImpuesto)
    {
        this.TotalImpuesto = TotalImpuesto;
    }

    public String getTotalComprobante ()
    {
        return TotalComprobante;
    }

    public void setTotalComprobante (String TotalComprobante)
    {
        this.TotalComprobante = TotalComprobante;
    }

    public String getCodigoMoneda ()
    {
        return CodigoMoneda;
    }

    public void setCodigoMoneda (String CodigoMoneda)
    {
        this.CodigoMoneda = CodigoMoneda;
    }

    public String getTotalVenta ()
    {
        return TotalVenta;
    }

    public void setTotalVenta (String TotalVenta)
    {
        this.TotalVenta = TotalVenta;
    }

    public String getTotalMercanciasGravadas ()
    {
        return TotalMercanciasGravadas;
    }

    public void setTotalMercanciasGravadas (String TotalMercanciasGravadas)
    {
        this.TotalMercanciasGravadas = TotalMercanciasGravadas;
    }

    public String getTotalVentaNeta ()
    {
        return TotalVentaNeta;
    }

    public void setTotalVentaNeta (String TotalVentaNeta)
    {
        this.TotalVentaNeta = TotalVentaNeta;
    }

    public String getTipoCambio ()
    {
        return TipoCambio;
    }

    public void setTipoCambio (String TipoCambio)
    {
        this.TipoCambio = TipoCambio;
    }

    public String getTotalGravado ()
    {
        return TotalGravado;
    }

    public void setTotalGravado (String TotalGravado)
    {
        this.TotalGravado = TotalGravado;
    }

    public String getTotalServExentos ()
    {
        return TotalServExentos;
    }

    public void setTotalServExentos (String TotalServExentos)
    {
        this.TotalServExentos = TotalServExentos;
    }

    public String getTotalServGravados ()
    {
        return TotalServGravados;
    }

    public void setTotalServGravados (String TotalServGravados)
    {
        this.TotalServGravados = TotalServGravados;
    }

    public String getTotalExento ()
    {
        return TotalExento;
    }

    public void setTotalExento (String TotalExento)
    {
        this.TotalExento = TotalExento;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [TotalMercanciasExentas = "+TotalMercanciasExentas+", TotalDescuentos = "+TotalDescuentos+", TotalImpuesto = "+TotalImpuesto+", TotalComprobante = "+TotalComprobante+", CodigoMoneda = "+CodigoMoneda+", TotalVenta = "+TotalVenta+", TotalMercanciasGravadas = "+TotalMercanciasGravadas+", TotalVentaNeta = "+TotalVentaNeta+", TipoCambio = "+TipoCambio+", TotalGravado = "+TotalGravado+", TotalServExentos = "+TotalServExentos+", TotalServGravados = "+TotalServGravados+", TotalExento = "+TotalExento+"]";
    }
}
			
			