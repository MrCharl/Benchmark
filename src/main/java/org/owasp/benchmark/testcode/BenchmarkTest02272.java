/**
* OWASP Benchmark Project v1.3alpha
*
* This file is part of the Open Web Application Security Project (OWASP)
* Benchmark Project. For details, please see
* <a href="https://www.owasp.org/index.php/Benchmark">https://www.owasp.org/index.php/Benchmark</a>.
*
* The OWASP Benchmark is free software: you can redistribute it and/or modify it under the terms
* of the GNU General Public License as published by the Free Software Foundation, version 2.
*
* The OWASP Benchmark is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
* even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* @author Nick Sanidas <a href="https://www.aspectsecurity.com">Aspect Security</a>
* @created 2015
*/

package org.owasp.benchmark.testcode;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value="/sqli-04/BenchmarkTest02272")
public class BenchmarkTest02272 extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		java.util.Map<String,String[]> map = request.getParameterMap();
		String param = "";
		if (!map.isEmpty()) {
			String[] values = map.get("BenchmarkTest02272");
			if (values != null) param = values[0];
		}
		

		String bar = doSomething(param);
		
		try {
	        String sql = "SELECT * from USERS where USERNAME='foo' and PASSWORD='" + bar + "'";
	
			org.owasp.benchmark.helpers.DatabaseHelper.JDBCtemplate.batchUpdate(sql);
			response.getWriter().println(
				"No results can be displayed for query: " + org.owasp.esapi.ESAPI.encoder().encodeForHTML(sql) + "<br>"
				+ " because the Spring batchUpdate method doesn't return results."
			);
	//		System.out.println("no results for query: " + sql + " because the Spring batchUpdate method doesn't return results.");
		} catch (org.springframework.dao.DataAccessException e) {
			if (org.owasp.benchmark.helpers.DatabaseHelper.hideSQLErrors) {
        		response.getWriter().println(
					"Error processing request."
				);
        	}
			else throw new ServletException(e);
		}
	}  // end doPost
	
		
	private static String doSomething(String param) throws ServletException, IOException {

		String bar;
		
		// Simple if statement that assigns param to bar on true condition
		int num = 196;
		if ( (500/42) + num > 200 )
		   bar = param;
		else bar = "This should never happen"; 
	
		return bar;	
	}
}
