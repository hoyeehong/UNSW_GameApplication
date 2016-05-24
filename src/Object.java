import java.awt.Image;
/**
 * 
 * @author Yeehong
 *
 */
public class Object{
	 private final int SPACE = 26;
	    
	 private int x;	  
	 private int y;	    
	 private Image image;
	    
	 public Object(int x, int y)	    
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
	 public int getX()	   
	 {	        
		 return this.x;    
	 }  
	 public int getY()	    
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
	 
	 public boolean isLeftCollision(Object character)	    
	 {	        
		 if(((this.getX() - SPACE) == character.getX()) && (this.getY() == character.getY()))	        
		 {
			 return true;        
		 } 	        
		 else	        
		 {	            
			 return false;	        
		 }    
	 }
    
	 public boolean isRightCollision(Object character)
	 {       
		 if (((this.getX() + SPACE) == character.getX()) && (this.getY() == character.getY()))        
		 {            	
			 return true;       
		 } 	        
		 else	        
		 {	            
			 return false;        
		 }    
	 }
	    
	 public boolean isTopCollision(Object character)
	 {       
		 if (((this.getY() - SPACE) == character.getY()) && (this.getX() == character.getX()))        
		 {            
			 return true;        
		 }         
		 else         
		 {	            
			 return false;	        
		 }    
	 }
	    
	 public boolean isBottomCollision(Object character) 	    
	 {	        
		 if(((this.getY() + SPACE) == character.getY()) && (this.getX() == character.getX()))	        
		 {	            
			 return true;	        
		 } 	        
		 else 	        
		 {	           
			 return false;	        
		 }    
	 }

}
