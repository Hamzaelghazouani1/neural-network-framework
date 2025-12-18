package ma.enset.glsid;

import ma.enset.glsid.activation.ActivationFunction;
import ma.enset.glsid.neural.Layer;
import ma.enset.glsid.neural.NeuralNetwork;
import ma.enset.glsid.neural.Neuron;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {
    private final ApplicationContext context;

    public AppRunner(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=== Spring Boot Neural Network Framework ===");

        // 1. Get NeuralNetwork prototype from context
        NeuralNetwork nn = context.getBean(NeuralNetwork.class);

        // 2. Configure layers
        Layer inputLayer = new Layer();
        inputLayer.addNeuron(new Neuron());
        inputLayer.addNeuron(new Neuron());

        // Layer hiddenLayer = new Layer(); // Unused, but logically needed for
        // connection?
        // Logic was simplified. I will keep it clean.
        Layer hiddenLayer = new Layer();
        hiddenLayer.addNeuron(new Neuron());
        hiddenLayer.addNeuron(new Neuron());

        ActivationFunction sigmoid = (ActivationFunction) context.getBean("sigmoid");
        hiddenLayer.getNeurons().forEach(n -> n.setActivationStrategy(sigmoid));

        // Adding layers to network
        nn.addLayer(inputLayer);
        nn.addLayer(hiddenLayer);

        System.out.println("Network created via Spring Context.");

        // 3. Test Security Aspect
        try {
            nn.train();
            nn.fit();
            System.out.println("Prediction: " + java.util.Arrays.toString(nn.predict(new double[] { 1.0, 0.5 })));
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
