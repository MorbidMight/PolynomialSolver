import java.util.*;
public class PolynomialDriver
{
  public static void main(String[] args)
  {
    //double[] coeff = {1, 4, 4};
    Scanner s = new Scanner(System.in);
    System.out.println("Enter the degree: ");
    int numTerms = s.nextInt();
    double[] coeff = new double[numTerms];
    System.out.println("Polynomial Solver");
    System.out.println("First enter the constant then the coefficent of the next degree of x, if there is no term at that degree than type 0 as the " +"\n" + " coefficent, for example, if your polynomial is 3x^2 + 1, type 3, 0, 1.");
    for(int x = 0; x < numTerms; x++)
    {
      System.out.println("Enter Coefficent or Constant: ");
      coeff[x] = s.nextDouble();
    }
    Polynomial poly = new Polynomial(coeff);
    PolynomialSolver p = new PolynomialSolver(poly);
    System.out.println(p.findRoots()); 
  }  
}