package com.app.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.app.dao.DAO;
import com.app.dao.ReimbursementDao;
import com.app.pojo.Reimbursement;
import com.app.servlet.ManagerServlet;

public class ReimbService {
	static ReimbursementDao reimbDao = new ReimbursementDao();
	static ArrayList<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
	private static Logger logger = Logger.getLogger(ReimbService.class);

	public Reimbursement addReimbursement(double amount, String description, int author, int typeId) {
		Timestamp time = new Timestamp(System.currentTimeMillis());

		Reimbursement obj = new Reimbursement(amount, time, description, author, 1, typeId);
		return reimbDao.save(obj);
	}

	public static List<Reimbursement> checkAllReimbursements() {
		return reimbDao.findAll();
	}

	public static Reimbursement findUser(int id) {
		return reimbDao.findById(id);
	}
	/*
	public static findApprovedRem(int id, int status) {
	
		return reimbDao.findByStatus(id,status);
		
	}
	*/

	public static void updateStatus(int id, int status) {
		logger.trace("updateStatus");
		Reimbursement obj = reimbDao.findById(id);
		obj.setStatusId(status);
		Timestamp time = new Timestamp(System.currentTimeMillis());
		obj.setResolved(time);
		logger.trace(obj);
		reimbDao.update(obj);
	}

}
