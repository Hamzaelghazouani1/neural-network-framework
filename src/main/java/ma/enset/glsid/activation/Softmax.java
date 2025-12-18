package ma.enset.glsid.activation;

import org.springframework.stereotype.Component;

@Component("softmax")
public class Softmax implements ActivationFunction {
    @Override
    public double apply(double value) {
        return Math.exp(value);
    }
}
