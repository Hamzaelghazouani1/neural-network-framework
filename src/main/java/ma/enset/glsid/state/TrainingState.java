package ma.enset.glsid.state;

import ma.enset.glsid.neural.NeuralNetwork;

public class TrainingState implements NeuralNetworkState {
    @Override
    public void train(NeuralNetwork network) {
        System.out.println("Already in training...");
    }

    @Override
    public void fit(NeuralNetwork network) {
        System.out.println("Completing training...");
        network.setState(new TrainedState());
    }

    @Override
    public double[] predict(NeuralNetwork network, double[] input) {
        throw new IllegalStateException("Cannot predict while training.");
    }
}
