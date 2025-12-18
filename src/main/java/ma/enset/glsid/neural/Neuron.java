package ma.enset.glsid.neural;

import java.util.ArrayList;
import java.util.List;
import ma.enset.glsid.activation.ActivationFunction;
import ma.enset.glsid.activation.Sigmoid;

public class Neuron {
    private List<Connection> inputConnections;
    private double output;

    private ActivationFunction activationFunction;

    public Neuron() {
        this.inputConnections = new ArrayList<>();
        this.activationFunction = new Sigmoid();
    }

    public void setActivationStrategy(ActivationFunction activationFunction) {
        this.activationFunction = activationFunction;
    }

    public void addInputConnection(Connection connection) {
        this.inputConnections.add(connection);
    }

    public void calculateOutput() {
        double sum = 0;
        for (Connection connection : inputConnections) {
            sum += connection.getInput() * connection.getWeight();
        }
        this.output = activationFunction.apply(sum);
    }

    public double getOutput() {
        return output;
    }

    public void setOutput(double output) {
        this.output = output;
    }
}
