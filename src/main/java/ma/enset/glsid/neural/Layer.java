package ma.enset.glsid.neural;

import java.util.ArrayList;
import java.util.List;

public class Layer {
    private List<Neuron> neurons;

    public Layer() {
        this.neurons = new ArrayList<>();
    }

    public void addNeuron(Neuron neuron) {
        this.neurons.add(neuron);
    }

    public List<Neuron> getNeurons() {
        return neurons;
    }
}
