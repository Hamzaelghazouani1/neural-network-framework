package ma.enset.glsid.state;

import ma.enset.glsid.neural.NeuralNetwork;

public interface NeuralNetworkState {
    void train(NeuralNetwork network);

    void fit(NeuralNetwork network);

    double[] predict(NeuralNetwork network, double[] input);
}
