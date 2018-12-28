package com.app.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class LoadViewsServlet extends HttpServlet {

	private static Logger log = Logger.getLogger(LoadViewsServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String resourcePath = "partials/" + process(req, resp) + ".html";
		req.getRequestDispatcher(resourcePath).forward(req, resp);
	}

	static String process(HttpServletRequest req, HttpServletResponse resp) {
		log.info("LOAD VIEW REQUEST SENT TO: " + req.getRequestURI());
		switch (req.getRequestURI()) {
		case "/ReimbursementApp/home.view":
			return "home";
		case "/ReimbursementApp/login.view":
			return "login";
		case "/ReimbursementApp/employee.view":
			return "employee";
		case "/ReimbursementApp/status.view":
			return "status";
		case "/ReimbursementApp/manager.view":
			return "manager";
		case "/ReimbursementApp/reimbursement.view":
			return "reimbursement";
		case "/ReimbursementApp/acceptedReq.view":
			return "acceptedReq";
		case "/ReimbursementApp/decline.view":
			return "decline";
		}

		return null;
	}

}