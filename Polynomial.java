public class Polynomial
{
   private double[] coeff;
   private int numTerms;
   
   public Polynomial(double[] c)
   {
      coeff = c;
      numTerms = coeff.length;
   }
   
   public double[] getCoeff()
   {
      return coeff;
   }
   
   public boolean checkLinear()
   {
      if(numTerms == 2)
         return true;
      else
         return false;      
   }
   
   public boolean checkQuadratic()
   {
      if(numTerms == 3)
         return true;
      else
         return false;      
   }
   
   public boolean checkSolution(double x)
   {
      double total = 0;
      for (int y = 0; y < coeff.length; y++)
      {
         total += coeff[y] * x;
      }
      if (total == (double) 0)
      {
         return true;
      }
      return false;
   }
   
   public String toString()
   {
      String answer = "";
      int exponent = coeff.length - 1;
      for(int x = 0; x < coeff.length; x++)
      {  
         if(coeff[x] > 0 && x > 0)
            answer += " + ";
         else if(x > 0)
            answer += " - ";
            
         if(coeff[x] != 0)
         {
            if(coeff[x] == (int)(coeff[x]))
               answer += (int)Math.abs(coeff[x]);
            else
               answer += Math.abs(coeff[x]);
            if(exponent > 0)
            {
               answer += "x";
               if(exponent > 1)
               {
                  answer += "^" + exponent;
               }
            }   
         }      
        exponent--;        
      }
      return answer;
   }
}
