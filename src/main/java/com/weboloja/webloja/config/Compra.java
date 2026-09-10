package com.weboloja.webloja.config;


	
	import java.io.IOException;
import java.io.StringReader;
import java.net.URISyntaxException;
	import java.util.ArrayList;
	import java.util.List;


import org.apache.http.Consts;
	import org.apache.http.NameValuePair;
	import org.apache.http.client.entity.UrlEncodedFormEntity;
	import org.apache.http.client.methods.CloseableHttpResponse;
	import org.apache.http.client.methods.HttpPost;
	import org.apache.http.client.utils.URIBuilder;
	import org.apache.http.impl.client.CloseableHttpClient;
	import org.apache.http.impl.client.HttpClients;
	import org.apache.http.message.BasicNameValuePair;
	import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.VisitorSupport;
import org.dom4j.io.SAXReader;

import com.weboloja.webloja.model.Carrinho;
import com.weboloja.webloja.model.Endereco;
import com.weboloja.webloja.model.Produto;
import com.weboloja.webloja.model.User;


public class Compra {
	
	private static final String URL = "https://ws.sandbox.pagseguro.uol.com.br/v2/checkout";
	
	private static String codigo = null;

	public String comprar(Endereco endereco, User user, List <Carrinho> carrinho, List <Produto> produtos) throws DocumentException {
	try {
	      CloseableHttpClient client = HttpClients.createDefault();
	      URIBuilder builder = new URIBuilder(URL);
	      builder.setParameter("email", "warlleymathias1.1@gmail.com");
	      builder.setParameter("token", "9E4270B8925A46ECBDD915CDC0113A25");
	      HttpPost postRequest = new HttpPost(builder.build());
	      postRequest.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
	      List<NameValuePair> formParams = new ArrayList<>();
	      formParams.add(new BasicNameValuePair("email", "warlleymathias1.1@gmail.com"));
	      formParams.add(new BasicNameValuePair("token", "9E4270B8925A46ECBDD915CDC0113A25"));
	      formParams.add(new BasicNameValuePair("currency", "BRL"));
	      
	      for(int i=0; i < produtos.size(); i++) {
	    	  formParams.add(new BasicNameValuePair("itemId"+(i+1), produtos.get(i).getId().toString()));
		      formParams.add(new BasicNameValuePair("itemDescription"+(i+1), produtos.get(i).getNome()));
		      formParams.add(new BasicNameValuePair("itemAmount"+(i+1), produtos.get(i).getValorReal().replace(",", ".")));
		      for(int x=0; x < carrinho.size(); x++) {
		    	  if(carrinho.get(x).getIdProduto().equals(produtos.get(i).getId()))
		    		  formParams.add(new BasicNameValuePair("itemQuantity"+(i+1), ""+carrinho.get(x).getQuantidade()));
		      }
		      formParams.add(new BasicNameValuePair("itemWeight"+(i+1), "1000")); //peso do item 
		      System.out.println(i);
	      }
	      formParams.add(new BasicNameValuePair("reference", "REF1234"));
	      
	      formParams.add(new BasicNameValuePair("senderName", user.getName()));
	      formParams.add(new BasicNameValuePair("senderAreaCode", endereco.getTelefone().substring(1,3)));
	      formParams.add(new BasicNameValuePair("senderPhone", endereco.getTelefone().substring(4).replaceAll("-", "").replaceAll(" ", "")));
	      formParams.add(new BasicNameValuePair("senderCPF", ""));
	      formParams.add(new BasicNameValuePair("senderBornDate", "")); // data de nascimento
	      formParams.add(new BasicNameValuePair("senderEmail", user.getEmail()));
	      
	      formParams.add(new BasicNameValuePair("shippingType", "1")); //tipo de frete entre 1 a 3
	      
	      formParams.add(new BasicNameValuePair("shippingAddressStreet", endereco.getRuaAvenida()));
	      formParams.add(new BasicNameValuePair("shippingAddressNumber", endereco.getNumero()));
	      formParams.add(new BasicNameValuePair("shippingAddressComplement", endereco.getComplemento()));
	      formParams.add(new BasicNameValuePair("shippingAddressDistrict", endereco.getBairro()));
	      formParams.add(new BasicNameValuePair("shippingAddressPostalCode", endereco.getCep()));
	      formParams.add(new BasicNameValuePair("shippingAddressCity", endereco.getCidade()));
	      formParams.add(new BasicNameValuePair("shippingAddressState", endereco.getEstado()));
	      formParams.add(new BasicNameValuePair("shippingAddressCountry", endereco.getContinente()));
	      
	      formParams.add(new BasicNameValuePair("extraAmount", "-0.00")); //desconto ou taxa a mais 
	      formParams.add(new BasicNameValuePair("redirectURL", "http://www.seusite.com.br"));
	      formParams.add(new BasicNameValuePair("notificationURL",
	      "https://yourserver.com/nas_ecommerce/277be731-3b7c-4dac-8c4e-4c3f4a1fdc46/")); // url para envio de notificações
	      formParams.add(new BasicNameValuePair("maxUses", "1")); //quantas vezes é valido o mesmo codigo de compra
	      formParams.add(new BasicNameValuePair("maxAge", "3000")); // Prazo de validade do código de pagamento
	      formParams.add(new BasicNameValuePair("shippingCost", "1.00")); //valor total do frete
	  
	      postRequest.setEntity(new UrlEncodedFormEntity(formParams, Consts.UTF_8));
	      CloseableHttpResponse response = client.execute(postRequest);
	      String result = EntityUtils.toString(response.getEntity());
	  
	      SAXReader reader = new SAXReader();
	      Document document = (Document) reader.read(new StringReader(result));
	      Element root = document.getRootElement();

	      root.accept(new VisitorSupport() {
	        @Override
	        public void visit(Element node) {
	          if (node.getQualifiedName().equals("code")) {
	           codigo = node.getText();
	            ;
	          }
	        }
	      });

	      client.close();
	      return codigo;
	  
	} catch (IOException | URISyntaxException e) {
		e.printStackTrace();
		return null;
	}
	}

}
