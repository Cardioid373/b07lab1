import java.lang.Math;

public class Polynomial {
    double[] coeff;

    public Polynomial() {
        coeff = new double[1];
        coeff[0] = 0;
	}

    public Polynomial(double[] c) {
        coeff = new double[c.length];
        for(int i=0; i<c.length; i++) {
            coeff[i] = c[i];
        }
    }

    public Polynomial add(Polynomial p) {
		Polynomial sum;
        if(p.coeff.length > this.coeff.length) {
            sum = new Polynomial(p.coeff);
            for(int i=0; i<this.coeff.length; i++) {
                sum.coeff[i] = p.coeff[i] + this.coeff[i];
            }
        }
        else {
            sum = new Polynomial(this.coeff);
            for(int i=0; i<p.coeff.length; i++) {
                sum.coeff[i] = p.coeff[i] + this.coeff[i];
            }
        }
		return sum;
    }

    public double evaluate(double x) {
        double result = 0;
        for(int i=0; i<this.coeff.length; i++) {
            result = result + this.coeff[i] * Math.pow(x, i);
        }
        return result;
    }

    public boolean hasRoot(double x) {
        return this.evaluate(x) == 0;
    }
}