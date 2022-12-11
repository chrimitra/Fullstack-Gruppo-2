package com.gruppo2.fullstack.risorse;

import java.util.ArrayList;
import java.util.List;

import com.gruppo2.fullstack.model.Feedback;

public class Feedbacks {
	
	private List<Feedback> feedbacklist = new ArrayList<>();
	
	public Feedbacks() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public void addFeeds(Feedback feedback){
		
		this.feedbacklist.add(feedback);
		
		
	}
	
	public Feedback getidFeeds(Integer index){
		
		return feedbacklist.get(index);
		
	}
	
	
	

	public List<Feedback> getFeedbacklist() {
		return feedbacklist;
	}
	
	
	

	public void setFeedbacklist(List<Feedback> feedbacklist) {
		this.feedbacklist = feedbacklist;
	}



	@Override
	public String toString() {
		return "Feedbacks [feedbacklist=" + feedbacklist + "]";
	}

	
	

}
