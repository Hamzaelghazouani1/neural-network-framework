package ma.enset.glsid.activation;

import org.springframework.stereotype.Component;

@Component("identity")
public class Identity implements ActivationFunction {
    @Override
    public double apply(double value) {
        return value;
    }
}
