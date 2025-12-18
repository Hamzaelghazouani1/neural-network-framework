package ma.enset.glsid.neural;

import java.util.ArrayList;
import java.util.List;
import ma.enset.glsid.state.*;
import ma.enset.glsid.observer.*;
import ma.enset.glsid.aspect.Log;
import ma.enset.glsid.aspect.SecuredByAspect;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Log
/**
 * Represents a Neural Network composed of multiple layers.
 * Manages the lifecycle state (Construction, Training, Trained) and notifies
 * observers.
 */
public class NeuralNetwork {
    private List<Layer> layers;
    private NeuralNetworkState state;
    private List<NetworkObserver> observers;

    public NeuralNetwork() {
        this.layers = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.state = new ConstructionState();
    }

    public void addObserver(NetworkObserver observer) {
        this.observers.add(observer);
    }

    public void removeObserver(NetworkObserver observer) {
        this.observers.remove(observer);
    }

    private void notifyObservers(String event) {
        for (NetworkObserver observer : observers) {
            observer.update(event);
        }
    }

    public void setState(NeuralNetworkState state) {
        this.state = state;
        notifyObservers("State changed to: " + state.getClass().getSimpleName());
    }

    public void addLayer(Layer layer) {
        this.layers.add(layer);
    }

    public void train() {
        state.train(this);
    }

    public void fit() {
        state.fit(this);
    }

    @SecuredByAspect(roles = { "admin" })
    public double[] predict(double[] inputs) {
        return state.predict(this, inputs);
    }

    public double[] internalPredict(double[] inputs) {
        compute();

        if (layers.isEmpty())
            return new double[0];
        Layer lastLayer = layers.get(layers.size() - 1);
        double[] results = new double[lastLayer.getNeurons().size()];
        for (int i = 0; i < lastLayer.getNeurons().size(); i++) {
            results[i] = lastLayer.getNeurons().get(i).getOutput();
        }
        return results;
    }

    public void compute() {
        for (int i = 1; i < layers.size(); i++) {
            Layer layer = layers.get(i);
            for (Neuron neuron : layer.getNeurons()) {
                neuron.calculateOutput();
            }
        }
    }
}
