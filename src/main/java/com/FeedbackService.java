package com;

import model.Feedback;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 


@Path("/Feedback")
public class FeedbackService {
	
	Feedback feedbackObj = new Feedback(); 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readFeedbacks() 
	
	 { 
		return feedbackObj.readFeedbacks();  
	 }
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN)
	
	public String insertFeedback(@FormParam("cusID") String cusID, 
	 @FormParam("fbTitle") String fbTitle, 
	 @FormParam("fbRating") String fbRating, 
	 @FormParam("fbDescription") String fbDescription) 
	
	{ 
	 String output = feedbackObj.insertFeedback(cusID, fbTitle, fbRating, fbDescription); 
	return output; 
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String updateFeedback(String feedbackData) {
		
		//Convert the input string to a JSON object 
		 JsonObject itemObject = new JsonParser().parse(feedbackData).getAsJsonObject(); 
		 
		//Read the values from the JSON object
		 String fbID = itemObject.get("fbID").getAsString(); 
		 String cusID = itemObject.get("cusID").getAsString(); 
		 String fbTitle = itemObject.get("fbTitle").getAsString(); 
		 String fbRating = itemObject.get("fbRating").getAsString(); 
		 String fbDescription = itemObject.get("fbDescription").getAsString();
	
	String output = feedbackObj.updateFeedback(fbID, cusID, fbTitle, fbRating, fbDescription);
	return output;
	
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String deleteFeedback(String feedbackData) {
		
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(feedbackData, "", Parser.xmlParser()); 
		 
		//Read the value from the element <feedbackID>
		 String fbID = doc.select("fbID").text();
		
		String output = feedbackObj.deleteFeedback(fbID);
		
		return output;
		
	}

}
