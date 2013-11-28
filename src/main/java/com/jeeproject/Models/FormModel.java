package com.jeeproject.Models;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jeeproject.Entities.User;
import com.jeeproject.Models.UserDAOImpl;

public class FormModel {
    private Map<String, String> errors;
	 
    
    public FormModel(){
    	 
    }
	public User subscribeUser( HttpServletRequest request ) {
		errors= new HashMap<String, String>();
		String email = getValueFromField(request.getParameter("email"));
        String password = getValueFromField(request.getParameter("password"));
        String confirmpassword = getValueFromField(request.getParameter("confirmpassword"));
        String username = getValueFromField(request.getParameter("username"));
        String terms=(request.getParameter("terms")!=null)?request.getParameter("terms") :"";
      
        validateUsername(username);
        validateEmail(email);
        validatePassword(password,confirmpassword);
        agreeterms(terms);
	   if( errors.size()==0 )
	   {  
		  try {
			password= sha1(password);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  User newUser = new User(username,email,password);
	      new UserDAOImpl().AddUser(newUser);
		   return newUser;
	} 
	   else
	   {
		   
		   User UnvalidUser = new User(username,email,"");
		   return UnvalidUser;
		   
	   }
	   
	}
	private void validateEmail( String email )  {
	    if (!email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) 
	        	errors.put("email","Email address is not valid");
	    
	     
	} 
	 
	private void validatePassword( String password, String confirmpassword )  {
	    if ( password != null && confirmpassword != null ) {
	        if ( !password.equals( confirmpassword ) ) {
	        	errors.put("password","Passwords are differents");
	        } else if ( password.length() < 3 ) {
	        	errors.put("password","Passwords must contain at leasts three characters");
	         
	        }
	       
	    } else {
	    	errors.put("password","Empty password field ");
	    	
	    }
	} 
	 
	private void validateUsername( String username ){
	    if(username == null ){
	    	errors.put("username","Username field is empty");
	    	
	    }
	    else if ( username != null && username.length() < 3 ) {
	      	errors.put("username","Username must contain at leasts three characters");
	    
	    }
	    else
	    {
	    	
	    }
	}
	private void agreeterms(String agreeterms){
		System.out.println("agreeterms"+agreeterms);
		if (!agreeterms.equals("on"))
		errors.put("agreeterms", "You must agree the terms and conditions");
		System.out.println(errors.get("agreeterms"));
	}
	public Map <String,String> getErrors (){
		
	return errors;
	}
	private static String getValueFromField( String fieldtext ) {
	    
	    if ( fieldtext == null || fieldtext.trim().length() == 0 ) {
	        return null;
	    } else {
	        return fieldtext.trim();
	    }
	}
	public static String sha1(String input) throws NoSuchAlgorithmException {
		MessageDigest mDigest = MessageDigest.getInstance("SHA1");
		byte[] result = mDigest.digest(input.getBytes());
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < result.length; i++) {
			sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16)
					.substring(1));
		}

		return sb.toString();
	}
	 
	
}
