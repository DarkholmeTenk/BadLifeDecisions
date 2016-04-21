package bld;
import bld.Neuron;
import comp34120.ex2.Platform;
import comp34120.ex2.PlayerType;
import comp34120.ex2.PlayerImpl;
import comp34120.ex2.Record;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class for creating the neuron and training it using the historic data.
 */
public class NeuronFactory
{

  private static Platform platform;


  private static List<Record> getData()
  {
    List<Record> data = new ArrayList<Record>();
    try
    {
      for(int i = 1; i <= 60; i ++)
      {
        Record currentRecord = platform.query(PlayerType.LEADER, i);
        data.add(currentRecord);
      }
    }
    catch(RemoteException e)
    {
      e.printStackTrace();
    }
    return data;
  }

  private static List<Record>[] crossfold(List<Record> data)
  {
    Random rand = new Random();
    List<Record>[] returnSets = new ArrayList[2];
    returnSets[0] = new ArrayList<Record>();
    returnSets[1] = new ArrayList<Record>();
    while(data.size() > 0 && data.size() % 2 == 0)
    {
      int selected = rand.nextInt(data.size());
      returnSets[0].add(data.get(selected));
      data.remove(selected);
      selected = rand.nextInt(data.size());
      returnSets[1].add(data.get(selected));
      data.remove(selected);
    }
    return returnSets;
  }

  /**
   *  Creates a neuron of a given type and with a given number of inputs.
   *
   *  @param The type of neuron to create.
   *  @param The number of inputs to the created neuron.
   *  @throws IllegalArgumentException thrown when invalid type.
   */
  public static Neuron createNeuron( final NeuronType type,
    final int numberOfInputs, Platform platformInput)
      throws IllegalArgumentException
  {
    if(numberOfInputs < 1)
      throw new IllegalArgumentException("Every neuron needs at least"
                                          + " one input.");
    platform = platformInput;
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
    List<Record> data = getData();
    for(int day = 1; day < 61; day++)
    {
      Record currentDay = data.get(day-1);
      double lPrice = currentDay.m_leaderPrice;
      double fPrice = currentDay.m_followerPrice;
      /*
       * Assumption has been made that the followers response function is
       * polynomial in form. Apparently its still linear rgression according
       * to wikipedia :D
       */
       double[] input = new double[numberOfInputs];
       input[0] = lPrice;
       for(int inputNo = 1; inputNo < numberOfInputs; inputNo++)
       {
         input[inputNo] = Math.pow(input[0], inputNo+1);
       }
       returnNeuron.input(input);
       returnNeuron.train(fPrice);
    }
    return returnNeuron;
  }
}
