import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Scanner;


public class Driver {
    public static void main(String [] args) throws Exception{
        Polynomial p = new Polynomial();
        System.out.println(p.evaluate(3));
		if(p.evaluate(3) == 0)
			System.out.println("test1 passed");
		
        double [] c1 = {6,5};
		int [] e1 = {0,3};
        Polynomial p1 = new Polynomial(c1, e1);
		
        double [] c2 = {-2,-9,1};
		int [] e2 = {1,3,4};
        Polynomial p2 = new Polynomial(c2, e2);
		
		Polynomial p3 = new Polynomial(new File("p3.txt"));
		System.out.println("p3(2) = " + p3.evaluate(2));
		if(p3.evaluate(2) == -47)
			System.out.println("test2 passed");
		
        Polynomial s = p1.add(p2);
        System.out.println("s(2) = " + s.evaluate(2));
		if(s.evaluate(2) == -14)
			System.out.println("test3 passed");
		
		Polynomial product = p1.multiply(p2);
		System.out.println("product(2) = " + product.evaluate(2));
		if(product.evaluate(2) == -2760)
			System.out.println("test4 passed");
		
        if(s.hasRoot(1))
            System.out.println("1 is a root of s");
        else {
            System.out.println("1 is not a root of s");
			System.out.println("test5 passed");
		}
		
		product.saveToFile("p4.txt");
		System.out.println("test6: p4.txt should have -12x1-54x3-4x4-45x6+5x7");
    }
}