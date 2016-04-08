package bld;
import bld.Neuron;

public class NeuronFactory
{

  public static Neuron createNeuron(NeuronType type, int numberOfInputs)
  {
    switch(type)
    {
      case BASIC_NEURON:
        return createBasicNeuron(numberOfInputs);
      default:
        throw new IllegalArgumentException("Invalid type.");
    }
  }

  private static Neuron createBasicNeuron(int numberOfInputs)
  {
    Neuron returnNeuron = new Neuron(numberOfInputs);
    return returnNeuron;
  }
}
