package bld;
import bld.Neuron;
import comp34120.ex2.Platform;
import comp34120.ex2.PlayerType;
import comp34120.ex2.Record;
import java.rmi.RemoteException;

/**
 * Class for creating the neuron and training it using the historic data.
 */
public class NeuronFactory
{

  private static final Platform platform = getPlatform();

  private static Platform getPlatform()
  {
    return null;
  }

  /**
   *  Creates a neuron of a given type and with a given number of inputs.
   *
   *  @param The type of neuron to create.
   *  @param The number of inputs to the created neuron.
   *  @throws IllegalArgumentException thrown when invalid type.
   */
  public static Neuron createNeuron( final NeuronType type,
    final int numberOfInputs)
      throws IllegalArgumentException
  {
    switch(type)
    {
      case BASIC_NEURON:
        return createBasicNeuron(numberOfInputs);
      default:
        throw new IllegalArgumentException("Invalid type.");
    }
  }

  private static Neuron createBasicNeuron(final int numberOfInputs)
  {
    Neuron returnNeuron = new Neuron(numberOfInputs);
    for(int i = 0; i < 0 /*SOME NUMBER HERE*/; i++)
    {
      try
      {
      Record currentDay = platform.query(PlayerType.LEADER, i);
      }
      catch(RemoteException e)
      {
        e.printStackTrace();
      }
    }
    return returnNeuron;
  }
}
