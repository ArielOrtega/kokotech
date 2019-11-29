package com.yoyo.ventas.controller;

import java.io.IOException;
import java.io.StringReader;
import java.util.Base64;
import java.util.List;

import javax.validation.Valid;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yoyo.ventas.business.ProductBusiness;
import com.yoyo.ventas.business.ShoppingCartBusiness;
import com.yoyo.ventas.domain.FacturaElectronica;
import com.yoyo.ventas.domain.Resp;
import com.yoyo.ventas.domain.ShoppingCart;
import com.yoyo.ventas.form.ShoppingCartForm;

@Controller
public class ShoppingCartController {
	@Autowired
	private ShoppingCartBusiness shoppingCartBusiness;
	@Autowired
	private ProductBusiness productBusiness;
	
	@RequestMapping(value="/store/shoppingCart/add", method=RequestMethod.GET)
	public String addToCartKeep(Model model, @Valid ShoppingCartForm shoppingCartForm, BindingResult br) {
		ShoppingCart shoppingCart = new ShoppingCart();
		BeanUtils.copyProperties(shoppingCartForm, shoppingCart);
		if(br.hasErrors()) {
			model.addAttribute("product", productBusiness.findProductById(shoppingCartForm.getProductId()));
			model.addAttribute("shoppingCartForm", new ShoppingCartForm());
			return "productDetails";
		}else {
			shoppingCart.getProduct().setProductId(shoppingCartForm.getProductId());
			shoppingCartBusiness.saveShoppingCart(shoppingCart);
			model.addAttribute("product", productBusiness.findProductById(shoppingCartForm.getProductId()));
			return "successProductAdded";
		}
	}
	
	@RequestMapping(value="/store/shoppingCart/check", method=RequestMethod.GET)
	public String checkShoppingCart(Model model) {
		List<ShoppingCart> carts = shoppingCartBusiness.getShoppingCart();
		model.addAttribute("carts", carts);
		float totalPrice = 0;
		for(int i = 0; i < carts.size(); i++) {
			totalPrice += (carts.get(i).getProduct().getPrice() * carts.get(i).getQuantity());
		}
		model.addAttribute("totalPrice", totalPrice);
		return "shoppingCart";
	}
	
	
	@RequestMapping(value="/store/shoppingCart/check", method=RequestMethod.POST)
	public String checkedShoppingCart(Model model) throws JsonParseException, JsonMappingException, IOException, TransformerException, XMLStreamException, JAXBException {
		
		String detalle = "{\"1\": {\"cantidad\":\"1\",\"unidadMedida\":\"Sp\",\"detalle\":\"Impresora\",\"precioUnitario\":\"10000\",\"montoTotal\":\"10000\",\"subtotal\":\"9900\",\"montoTotalLinea\":\"9900\",\"montoDescuento\":\"100\",\"naturalezaDescuento\":\"Pronto pago\"},\"2\":{\"cantidad\":\"1\",\"unidadMedida\":\"Unid\",\"detalle\":\"producto\",\"precioUnitario\":\"10000\",\"montoTotal\":\"10000\",\"subtotal\":\"10000\",\"montoTotalLinea\":\"11170\",\"impuesto\":{\"1\": {\"codigo\":\"01\",\"tarifa\":\"11.7\",\"monto\":\"1170\"}";		
	    String uri = "http://api-demo.crlibre.org/api.php?w=genXML&r=gen_xml_fe&clave=50620111900011767042800100001011522773402107756342&consecutivo=00100001011522773402&fecha_emision=2018-06-17T12:00:00-06:00&emisor_nombre=Walner Borbon&emisor_tipo_indetif=01&emisor_num_identif=702320717&nombre_comercial=Walner Borbon&emisor_provincia=6&emisor_canton=02&emisor_distrito=03&emisor_barrio=01&emisor_otras_senas=Frente a la escuela&emisor_cod_pais_tel=506&emisor_tel=64206205&emisor_cod_pais_fax:=506&emisor_fax=00000000&emisor_email=walner1borbon@gmail.com&receptor_nombre=Walner Borbon&receptor_tipo_identif=01&receptor_num_identif=702320717&receptor_provincia=6&receptor_canton=02&receptor_distrito=03&receptor_barrio=01&receptor_cod_pais_tel=506&receptor_tel=84922891&receptor_cod_pais_fax=506&receptor_fax=00000000&receptor_email=walner.borbon@hotmail.com&condicion_venta=01&plazo_credito=0&medio_pago=01&cod_moneda=CRC&tipo_cambio=564.48&total_serv_gravados=0&total_serv_exentos=10000&total_merc_gravada=10000&total_merc_exenta=0&total_gravados=10000&total_exentos=10000&total_ventas=20000&total_descuentos=100&total_ventas_neta=19900&total_impuestos=1170&total_comprobante=21070&otrosType=OtroTexto&detalles={detalle}}}}";
	    
	    //Extract post result
	    RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, null ,String.class, detalle);
	    String result = response.getBody();

	    //Get xml from response
	    ObjectMapper om = new ObjectMapper();
	    om.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);  
	    Resp resp = om.readValue(result, Resp.class);
	    String xml = resp.getXml();
	    
	    //Decoding xml attached
	    byte[] decodedBytes = Base64.getDecoder().decode(xml);
	    String decodedString = new String(decodedBytes).replace(" xmlns=\"https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica\" xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica FacturaElectronica_V.4.2.xsd\"", "");
	    
	    
	    //Deserialize xml to object
        FacturaElectronica invoice= xmlToInvoice(decodedString);  
        
        model.addAttribute("invoice", invoice);
	    return "invoice";

	}	
	

	public FacturaElectronica xmlToInvoice(String decodedString) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(FacturaElectronica.class);  
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();  
        FacturaElectronica invoice= (FacturaElectronica) jaxbUnmarshaller.unmarshal(new StringReader(decodedString));
        return invoice;
	}
	
	
	@RequestMapping(value="/store/shoppingCart/remove", method=RequestMethod.GET)
	public String removeProduct(Model model, @RequestParam("productId") int productId) {
		shoppingCartBusiness.removeProductCart(productId);
		List<ShoppingCart> carts = shoppingCartBusiness.getShoppingCart();
		model.addAttribute("carts", carts);
		model.addAttribute("totalPrice", shoppingCartBusiness.getTotalPrice(carts));
		return "shoppingCart";
	}
}
