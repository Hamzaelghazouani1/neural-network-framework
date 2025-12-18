package ma.enset.glsid.state;

import ma.enset.glsid.neural.NeuralNetwork;

public class ConstructionState implements NeuralNetworkState {
    @Override
    public void train(NeuralNetwork network) {
        System.out.println("Transitioning from Construction to Training...");
        network.setState(new TrainingState());
    }

    @Override
    public void fit(NeuralNetwork network) {
        System.out.println("Fitting model (shortcut to Trained)...");
        network.setState(new TrainedState());
    }

    @Override
    public double[] predict(NeuralNetwork network, double[] input) {
        throw new IllegalStateException("Cannot predict in Construction state. Please train the model first.");
    }
}
