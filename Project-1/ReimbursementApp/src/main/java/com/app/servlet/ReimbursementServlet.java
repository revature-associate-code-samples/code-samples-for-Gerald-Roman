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

import com.app.dao.ReimbursementDao;
import com.app.pojo.Reimbursement;
import com.app.service.ReimbService;
import com.app.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/reimbursement")
public class ReimbursementServlet extends HttpServlet {
	private static Logger logger = Logger.getLogger(ReimbursementServlet.class);
	static ReimbService reimbService = new ReimbService();
	static ReimbursementDao reimbDao = new ReimbursementDao();
	static UserService userService = new UserService();

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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		ObjectMapper mapper = new ObjectMapper();

		int id = (int) session.getAttribute("userId");

		List<Reimbursement> reimb = (List<Reimbursement>) reimbDao.findByUser(id);
		// Reimbursement reimb = reimbService.findUser(id);
		logger.trace("TABLE FOR INDIVIDUAL " + reimb);
		String json = mapper.writeValueAsString(reimb);
		PrintWriter write = resp.getWriter();
		resp.setContentType("application/json");
		logger.trace("JSON " + json);
		write.write(json);
	}

	protected void doAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		ObjectMapper mapper = new ObjectMapper();

		List<Reimbursement> reimb = (List<Reimbursement>) reimbDao.findAll();
		// Reimbursement reimb = reimbService.findUser(id);
		logger.trace("TABLE FOR All " + reimb);
		String json = mapper.writeValueAsString(reimb);
		PrintWriter write = resp.getWriter();
		resp.setContentType("application/json");
		logger.trace("JSON " + json);
		write.write(json);

	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ObjectMapper mapper = new ObjectMapper();
		Reimbursement r = mapper.readValue(req.getInputStream(), Reimbursement.class);
		reimbService.updateStatus(r.getId(), r.getStatusId());
	}
}