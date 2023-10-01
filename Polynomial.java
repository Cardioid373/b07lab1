import java.lang.Math;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Scanner;


public class Polynomial{
    double[] coeff;
	int[] exp;

    public Polynomial() {
        coeff = new double[1];
        coeff[0] = 0;
		exp = new int[1];
		exp[0] = 0;
	}

    public Polynomial(double[] c, int[] e) {
        coeff = new double[c.length];
        for(int i=0; i<c.length; i++)
            coeff[i] = c[i];
		exp = new int[e.length];
		for(int i=0; i<e.length; i++)
			exp[i] = e[i];
    }

    public Polynomial(File file) throws Exception{
		int count = 0;
		Scanner scnr = new Scanner(file);
		String line = scnr.nextLine();
		scnr.close();
		String[] parts = line.split("\\+");
		String[][] terms = new String[parts.length][];
		for(int i=0; i<parts.length; i++) {
			terms[i] = parts[i].split("-");
			count = count + terms[i].length;
		}
		coeff = new double[count];
		exp = new int[count];
		count = 0;
		for(int i=0; i<parts.length; i++) {
			String[] temp = terms[i][0].split("x");
			coeff[count] = Double.parseDouble(temp[0]);
			if(temp.length == 1)
				exp[count] = 0;
			else
				exp[count] = Integer.parseInt(temp[1]);
			count++;
			for(int j=1; j<terms[i].length; j++) {
				temp = terms[i][j].split("x");
				coeff[count] = 0 - Double.parseDouble(temp[0]);
				if(temp.length == 1)
					exp[count] = 0;
				else
					exp[count] = Integer.parseInt(temp[1]);
				count++;
			}
		}
	}
	
	public Polynomial add(Polynomial p) {
		Polynomial temp = new Polynomial();
		Polynomial sum = new Polynomial();
		int a = this.exp.length;
		int b = p.exp.length;
		int count = 0;
		temp.coeff = new double[a + b];
		temp.exp = new int[a + b];
		for(int i=0; i<a; i++) {
			temp.coeff[i] = this.coeff[i];
			temp.exp[i] = this.exp[i];
		}
		for(int i=0; i<b; i++) {
			int test = 0;
			for(int j=0; j<a; j++) {
				if(p.exp[i] == temp.exp[j]) {
					temp.coeff[j] = temp.coeff[j] + p.coeff[i];
					test = 1;
					break;
				}
			}
			if(test == 0) {
				temp.coeff[a + count] = p.coeff[i];
				temp.exp[a + count] = p.exp[i];
				count++;
			}
		}
		sum.coeff = new double[a + count];
		sum.exp = new int[a + count];
		for(int i=0; i<a + count; i++) {
			sum.coeff[i] = temp.coeff[i];
			sum.exp[i] = temp.exp[i];
		}
		return sum;
    }

    public Polynomial multiply(Polynomial p) {
		Polynomial temp = new Polynomial();
		Polynomial product = new Polynomial();
		int a = this.exp.length;
		int b = p.exp.length;
		int count = 0;
		temp.coeff = new double[a*b];
		temp.exp = new int[a*b];
		for(int i=0; i<a; i++) {
			for(int j=0; j<b; j++) {
				int test = 0;
				double co = this.coeff[i] * p.coeff[j];
				int ex = this.exp[i] + p.exp[j];
				for(int k=0; k<count; k++) {
					if(temp.exp[k] == ex) {
						temp.coeff[k] = temp.coeff[k] + co;
						test = 1;
						break;
					}
				}
				if(test == 0) {
					temp.coeff[count] = co;
					temp.exp[count] = ex;
					count++;
				}
			}
		}
		product.coeff = new double[count];
		product.exp = new int[count];
		for(int i=0; i<count; i++) {
			product.coeff[i] = temp.coeff[i];
			product.exp[i] = temp.exp[i];
		}
		return product;
	}
	
	public double evaluate(double x) {
        double result = 0;
        for(int i=0; i<this.coeff.length; i++) {
            result = result + this.coeff[i] * Math.pow(x, this.exp[i]);
        }
        return result;
    }

    public boolean hasRoot(double x) {
        return this.evaluate(x) == 0;
    }
	
	public void saveToFile(String fileName) throws Exception{
		String s = String.format("%fx%d", this.coeff[0], this.exp[0]);
		for(int i=1; i<this.exp.length; i++) {
			if(this.coeff[i] > 0)
				s = s + String.format("+%fx%d", this.coeff[i], this.exp[i]);
			else
				s = s + String.format("%fx%d", this.coeff[i], this.exp[i]);
		}
		PrintStream ps = new PrintStream(fileName);
		ps.println(s);
		ps.close();
	}
}