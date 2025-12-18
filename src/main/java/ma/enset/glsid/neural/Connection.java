package ma.enset.glsid.neural;

public class Connection {
    private double weight;
    private Neuron sourceNeuron;

    public Connection(Neuron sourceNeuron, double weight) {
        this.sourceNeuron = sourceNeuron;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public double getInput() {
        return sourceNeuron.getOutput();
    }
}
