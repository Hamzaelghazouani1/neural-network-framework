package ma.enset.glsid.state;

import ma.enset.glsid.neural.NeuralNetwork;

public class TrainedState implements NeuralNetworkState {
    @Override
    public void train(NeuralNetwork network) {
        System.out.println("Re-training model...");
        network.setState(new TrainingState());
    }

    @Override
    public void fit(NeuralNetwork network) {
        System.out.println("Model already trained.");
    }

    @Override
    public double[] predict(NeuralNetwork network, double[] input) {
        System.out.println("Predicting...");
        return network.internalPredict(input);
    }
}
