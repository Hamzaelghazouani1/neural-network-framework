package ma.enset.glsid.activation;

public class ActivationAdapter implements ActivationFunction {
    private OldActivationImpl oldActivation;

    public ActivationAdapter(OldActivationImpl oldActivation) {
        this.oldActivation = oldActivation;
    }

    @Override
    public double apply(double value) {
        return oldActivation.activate(value, 0.0);
    }
}
