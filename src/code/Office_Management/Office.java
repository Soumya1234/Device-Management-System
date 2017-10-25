package code.Office_Management;

public class Office {
     private String Office_Code;
     private Office Controlling_Office;
     private int No_Of_Children;
     private int Level;
     private static int numOfLevels=1;
     
     public Office()
     {
    	 Controlling_Office=null;
    	 No_Of_Children=0;
    	 Level=1;
    	 setOfficeCode();
     }
     
     public Office(Office controlling_office)
     {
    	 Controlling_Office=controlling_office;
    	 Level=Controlling_Office.getOfficeLevel()+1;
    	 setCOChildren();
    	 setOfficeCode();
     }
     
     private void setCOChildren() //increment Controlling Office's noOfChildren once this office is created
     {
    	 Controlling_Office.No_Of_Children++;
     }
     private void setOfficeCode()
     {
    	 if(Level==1)
    	 {
    		 if(numOfLevels==1)
    		 {
    			 Office_Code="1";
    		 }
    		 else
    		 {
    			 Office_Code="1"+paddzero();
    		 }
    	 }
    	 else
    	 {   
    		 int noOfZeroPadding=2;
    		 Office_Code=Integer.toString(Integer.parseInt((Controlling_Office.getOfficeCode()).substring(0, (noOfZeroPadding*Level-1)))+Controlling_Office.No_Of_Children)+
    				 Controlling_Office.getOfficeCode().substring(noOfZeroPadding*Level-1);
    	 }
     }
     
     public String getOfficeCode()
     {
    	 return Office_Code;
     }
     
     private String paddzero()
     {
    	 String result="00";
    	 
    	 for(int i=0;i<numOfLevels-2;i++)
    	 {
    		 result=result+"00";
    	 }
    	 return result;
     }
     
     public static void setNumberOfLevels(int a)
     {
    	 numOfLevels=a;
     }
     
     public int getOfficeLevel()
     {
    	 return Level;
     }
}
