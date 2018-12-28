package com.app.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.app.pojo.Reimbursement;
import com.app.service.ReimbService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/manager")
public class ManagerServlet extends HttpServlet {
	static ReimbService reimbService = new ReimbService();
	private static Logger logger = Logger.getLogger(ManagerServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Reimbursement> reimb = reimbService.checkAllReimbursements();
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(reimb);
		PrintWriter write = resp.getWriter();
		resp.setContentType("application/json");
		write.write(json);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		ObjectMapper mapper = new ObjectMapper();

		Reimbursement reimb = mapper.readValue(req.getInputStream(), Reimbursement.class);
		int id = (int) session.getAttribute("userId");
		reimb.setAuthor(id);
		reimb = reimbService.addReimbursement(reimb.getAmount(), reimb.getDescription(), reimb.getAuthor(),
				reimb.getTypeId());
		logger.trace("ADDED NEW REIMBURSEMENTS: " + reimb);

	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.trace("inside doPut");
		ObjectMapper mapper = new ObjectMapper();
		Reimbursement reimb = mapper.readValue(req.getInputStream(), Reimbursement.class);
		logger.trace("INSIDE STATUS ID " + reimb.getStatusId());
		reimbService.updateStatus(reimb.getId(), reimb.getStatusId());
	}
}
