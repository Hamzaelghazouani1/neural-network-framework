package ma.enset.glsid.activation;

public class OldActivationImpl {
    public double activate(double input, double threshold) {
        return input > threshold ? 1.0 : 0.0;
    }
}
