import java.util.*;
public class PolynomialSolver
{
   private Polynomial poly;
   private double rangeMin;
   private double rangeMax;
   private final static double ALMOST_ZERO = 0.0001;
   public PolynomialSolver(Polynomial p)
   {
      poly = p;
      rangeMax = findRange(poly);
      rangeMin = (findRange(poly)) * -1;
      
   }
   
   /*Given a polynomial object it will output a positive double, and the root of the polynomial
   will be between the negative version of the double and the double*/
   private static double findRange(Polynomial poly)
   {
      double[] temp = new double[poly.getCoeff().length];
      for(int x = 0; x < poly.getCoeff().length; x++)
      {
         temp[x] = Math.abs(poly.getCoeff()[x]);
      }
      double lc = temp[0];
      double[] c = new double[poly.getCoeff().length];
      for(int x = 1; x < poly.getCoeff().length; x++)
      {
         c[x] = temp[x] / lc;
      }
      double maxC = 0;
      double sumC = 0;
      for(int x = 0; x < c.length; x++)
      {
         sumC += c[x];
         if(c[x] > maxC)
         {
            maxC = c[x];  
         }
      }
      return Math.min(Math.max(1, sumC), (1 + maxC));
      
       
      
   }
   /*Solves a polynomial and returns roots as an ArrayList of doubles, will return an empty ArrayList
   if no roots are present(Still a work in progress)*/
   public ArrayList<Double> solveQuadratic()
   {
      ArrayList<Double> arr = new ArrayList<Double>();
      double a = poly.getCoeff()[0];
      double b = poly.getCoeff()[1];
      double c = poly.getCoeff()[2];
      if((Math.pow(b, 2)) - (4*a*c) < 0)
      {
         return arr;
         
      }else if((Math.pow(b, 2)) - (4*a*c) == 0)
      {
         arr.add((b*-1)/2*a);
         
      }else
      {
         arr.add(((b*-1) + ((Math.sqrt((Math.pow(b, 2)) - (4*a*c))))/2*a));
         arr.add(((b*-1) - ((Math.sqrt((Math.pow(b, 2)) - (4*a*c))))/2*a));
         
      }
      return arr;
   }
   
   /* Given a polynomial object will return an ArrayList with the roots in double form*/                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
   public ArrayList<Double> findRoots()
   {
      ArrayList<Double> arr = new ArrayList<Double>();
      double increment = 1;
      double stop = 0.03125;
      double solutionsLeft = getMaxSolns();
      
      while(arr.size() < getMaxSolns() && increment > stop)
      {
         for(double x = rangeMin; x <= rangeMax; x += increment)
         {
            double lower = x;
            double upper = x + increment;
            
            if(zero(solve(poly.getCoeff(), lower)))
            {
               if(!(arr.contains(lower)))
               {
                 arr.add(lower);
                 if(doubleRoot(lower))
                 {
                     solutionsLeft -= 2;
                 }else
                 {
                   solutionsLeft -= 1;
                 }  
               }
 
               
            }
            if(sameSign(solve(poly.getCoeff(), lower), solve(poly.getCoeff(), upper)) == false)
            {
               double val = bisect(lower,upper);
               if(!(arr.contains(val)))
               {
                 arr.add(val);
                 solutionsLeft -= 1;
               }               
               
            }
            if(solutionsLeft <= 0)
            {
               return arr;
            }  
         }
         increment = increment/2;
      }
      return arr;
   }
   
   /* Will return true if x has an even multiplicty*/
   private boolean doubleRoot(double x)
   {
       double left = x - ALMOST_ZERO;
       double right = x + ALMOST_ZERO;
       double f_left = solve(poly.getCoeff(), left);
       double f_right = solve(poly.getCoeff(),right);
       if(sameSign(f_left, f_right))
         return true;
       return false;
   }
   /* Helper method which uses a bisection algorithm to help find the roots of a polynomial*/
   private double bisect(double lower, double upper)
   {
      
      if(zero(solve(poly.getCoeff(), lower)))
      {
         return lower;
      }
      if(zero(solve(poly.getCoeff(), upper)))
      {
         return upper;
      }
      
      double mid = average(upper, lower);
      if(zero(solve(poly.getCoeff(), mid)))
      {
        return mid;  
      }
      if(sameSign(solve(poly.getCoeff(), lower), solve(poly.getCoeff(), mid)) == false)
      {
         return bisect(lower, mid);
      }   
      return bisect(mid, upper);
   }
   /* returns the average of 2 doubles*/
   public static double average(double x, double y)
   {
      return (x+y)/2;
   }
   /*returns true if both doubles have the same sign(positive or negative)*/
   public static boolean sameSign (double x, double y)
   {
      if(Math.signum(x) == Math.signum(y))
      {
         return true;
      }else
      {
         return false;
      } 
   }
   
   /*returns the maximum number of possible solutions*/
   public int getMaxSolns()
   {
      return poly.getCoeff().length - 1;
   }
   
   /* returns true if a number is so close to 0 that it can be considered 0*/
   private boolean zero(double x)
   {
      return (Math.abs(x) < ALMOST_ZERO);
   }
   
   /* when given x will return f(x)*/
   public static double solve(double[] coeff, double x)
   {
      double total = 0;
      for (int y = 0; y < coeff.length; y++)
      {
         
         total += coeff[y] * Math.pow(x, coeff.length - y - 1);
      }
      return total;
   }
  
   /* combines 2 ArrayLists*/
   private ArrayList<Double> combineList(ArrayList<Double> a, ArrayList<Double> b)
   {
      for(int x = 0; x < b.size(); x++)
      {
         a.add(b.get(x));  
      }
      return a;
   } 
   
}