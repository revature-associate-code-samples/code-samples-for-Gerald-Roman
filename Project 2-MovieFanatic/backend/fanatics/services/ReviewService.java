/**
 * 
 */
package com.fanatics.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fanatics.beans.RatingCountBean;
import com.fanatics.beans.ReviewBean;
import com.fanatics.models.Review;
import com.fanatics.repository.ReviewRepository;
import com.fanatics.repository.ReviewRepositoryCustom;
import com.fanatics.util.Tool;

/**
 * @author PGerringer
 *
 */
@Service("reviewService")
public class ReviewService {
	
	@Autowired
	private ReviewRepository repo;
	
	@Autowired
	private ReviewRepositoryCustom custom;
	
	/**
	 * 
	 */
	public ReviewService() {
		// TODO Auto-generated constructor stub
	}

	public List<Review> getAll(){
		return repo.findAll();
	}
	
	public Review getById(int id) {
		return repo.findOne(id);
	}
	
	public List<ReviewBean> getView(int id, int movie_id) {
		return custom.findAll(new Integer(id), movie_id);
	}
	
	public RatingCountBean getRatingCount(int id) {
		return(custom.getRatingCount(id));
	}
	
	/**
	 * creates a new review
	 * @param review
	 * @return
	 */
	public Review newReview(Review review) {
		review.setExpire_date(Tool.getTime(Tool.tenYears()));
		review.setReview_date(Tool.getTime(Tool.getCurrentDate()));

		review = repo.save(review);
		
		return review;
	}
}
