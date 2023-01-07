package neuralnetwork;

import java.util.List;

public class NeuralNetwork {
    Matrix weightsInputHiddenLayer , weightsHiddenLayerOutput , biasHiddenLayer , biasOutput;

    public NeuralNetwork(int i,int h,int o) {
        weightsInputHiddenLayer = new Matrix(h, i);
        weightsHiddenLayerOutput = new Matrix(o, h);

        biasHiddenLayer = new Matrix(h, 1);
        biasOutput = new Matrix(o, 1);
    }

    public List<Double> predict(double[] X) {
        Matrix input = Matrix.fromArray(X);
        Matrix hidden = Matrix.multiply(weightsInputHiddenLayer, input);
        hidden.add(biasHiddenLayer);
        hidden.sigmoid();

        Matrix output = Matrix.multiply(weightsHiddenLayerOutput,hidden);
        output.add(biasOutput);
        output.sigmoid();

        return output.toArray();
    }

    public static void main(String[] args) {
        NeuralNetwork neuralNetwork = new NeuralNetwork(2, 10, 1);
        double [][] input ={{0 , 0} , {0 , 1} , {1 , 0}, {1 , 1}};
        for(double[] d : input) {
            System.out.println(neuralNetwork.predict(d).toString());
        }
    }
}
