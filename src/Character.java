import java.awt.Image;
/**
 * 
 * @author Yeehong
 *
 */
public class Character{
	 private final int SPACE = 10;
	    
	 private int x;	  
	 private int y;	    
	 private Image image;
	    
	 public Character(int x, int y)	    
	 {	        
		 this.x = x;	        
		 this.y = y;    
	 }
	 
	 public Image getImage()    
	 {	        
		 return this.image;    
	 } 
	 public void setImage(Image img)	    
	 {        	
		 image = img;    
	 }    
	 public int x()	   
	 {	        
		 return this.x;    
	 }  
	 public int y()	    
	 {	       
		 return this.y;	    
	 }	    
	 public void setX(int x)    
	 {	       
		 this.x = x;    
	 }	    
	 public void setY(int y)	    
	 {	        
		 this.y = y;    
	 }
	 
	 public boolean isLeftCollision(Character character)	    
	 {	        
		 if(((this.x() - SPACE) == character.x()) && (this.y() == character.y()))	        
		 {
			 return true;        
		 } 	        
		 else	        
		 {	            
			 return false;	        
		 }    
	 }
    
	 public boolean isRightCollision(Character character)
	 {       
		 if (((this.x() + SPACE) == character.x()) && (this.y() == character.y()))        
		 {            	
			 return true;       
		 } 	        
		 else	        
		 {	            
			 return false;        
		 }    
	 }
	    
	 public boolean isTopCollision(Character character)
	 {       
		 if (((this.y() - SPACE) == character.y()) && (this.x() == character.x()))        
		 {            
			 return true;        
		 }         
		 else         
		 {	            
			 return false;	        
		 }    
	 }
	    
	 public boolean isBottomCollision(Character character) 	    
	 {	        
		 if(((this.y() + SPACE) == character.y()) && (this.x() == character.x()))	        
		 {	            
			 return true;	        
		 } 	        
		 else 	        
		 {	           
			 return false;	        
		 }    
	 }

}
