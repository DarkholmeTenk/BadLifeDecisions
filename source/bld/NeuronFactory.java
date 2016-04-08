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
    if(numberOfInputs < 1)
      throw new IllegalArgumentException("Every neuron needs at least"
                                          + " one input.");
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
    // Not finalised the data should really be crossfolded and then the second
    // partition used for testing.
    for(int day = 1; day < 61; day++)
    {
      try
      {
        Record currentDay = platform.query(PlayerType.LEADER, day);
        double lPrice = currentDay.m_leaderPrice;
        double fPrice = currentDay.m_followerPrice;
        /*
         * Assumption has been made that the followers response function is
         * polynomial in form. Apparently its still linear rgression according
         * to wikipedia :D
         */
         double[] input = new double[numberOfInputs];
         input[0] = lPrice;
         for(int inputNo = 1; inputDay < numberOfInputs; inputDay++)
         {
           input[inputDay] = Math.pow(input[0], inputDay+1);
         }
         returnNeuron.input(input);
         returnNeuron.train(fPrice);
      }
      catch(RemoteException e)
      {
        e.printStackTrace();
      }
    }
    return returnNeuron;
  }
}
