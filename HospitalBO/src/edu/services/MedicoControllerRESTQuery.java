package edu.services;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.LinkedHashSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.dao.MedicoDAO;
import edu.dao.MedicoDAOImpl;
import edu.model.Medico;

@WebServlet("/MedicoControllerRESTQuery")
public class MedicoControllerRESTQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final long TIMEOUT = 2000;

    public MedicoControllerRESTQuery() {
        super();
    }
    
    public String retrieveBody( HttpServletRequest request, long timeout ) throws IOException {
		long startTime = System.currentTimeMillis();
		InputStream in = request.getInputStream();
		StringBuffer texto = new StringBuffer();
		System.out.println("Reading json");
		int a = 0;
		while ((System.currentTimeMillis() < (startTime + timeout)) && a != -1) {
			a = in.read();
			if (a != -1) { 
				texto.append( (char)a );
			}
		}
		System.out.println("Texto lido " + texto);
		in.close();
		return texto.toString();
	}
    
    public void printParameters(HttpServletRequest request) {
    	System.out.println("Parameters");
    	while (request.getParameterNames().hasMoreElements()) {
    		String name = request.getParameterNames().nextElement();
    		String value = request.getParameter( name );
    		System.out.println(name + "  --  " + value);
    	}
    }
    
    public void printHeaders(HttpServletRequest request) {
    	System.out.println("Headers");
    	while (request.getHeaderNames().hasMoreElements()) {
    		String name = request.getHeaderNames().nextElement();
    		String value = request.getHeader( name );
    		System.out.println(name + "  --  " + value);
    	}
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Thread.sleep(333);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String body = retrieveBody(request, 3000);
		
		System.out.println( "Leitura terminada");
		System.out.println( body );
		
		System.out.println("Converting json to Medico Entity");
		ObjectMapper objectMapper = new ObjectMapper();
		
		JsonNode node = objectMapper.readTree( body );
		String nome = node.get("nome").asText();

		
		SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
		objectMapper.setDateFormat( dtf );
		
		MedicoDAO mDao = new MedicoDAOImpl();
		LinkedHashSet<Medico> medicos = mDao.pesquisar(nome);
		
		System.out.printf("Encontrados %d medicos\n", medicos.size());
		String listaJson = objectMapper.writeValueAsString(medicos);
		System.out.println("JSON Gerado : " + listaJson);
		
		response.setStatus(200);
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().println(listaJson);
		response.getWriter().flush();	    
	}

}
