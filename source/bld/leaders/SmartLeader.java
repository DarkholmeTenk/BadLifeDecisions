package bld.leaders;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import bld.platform.AbstractLeader;
import bld.NeuronFactory;
import bld.NeuronType;
import bld.Neuron;

import comp34120.ex2.Record;

public class SmartLeader extends AbstractLeader 
{
  private Neuron responseFunction;

  public SmartLeader() throws RemoteException, NotBoundException
  {
    super("Simple Leader");
    responseFunction = NeuronFactory.createNeuron(NeuronType.BASIC_NEURON,
    4, platform);
  }

  private float calculateProfit(float leader, float follower, float cost)
  {
    return (leader-cost)*(2-leader + 0.3f*follower);
  }

  private float optimize(int day)
  {
    float currentOptimum = 0; 
    float bestPricingStrategy = 0;    
    for(float i = 1.0f; i < 10.0f; i += 0.01f)
    {
      float[] input = new float[4];
      input[0] = i;
      for(int inputNo = 0; inputNo < 4; inputNo++)
      {
        input[inputNo] = (float)Math.pow((double)input[0], inputNo-1);
      }
      float followerPrice = responseFunction.input(input);
      float profit = calculateProfit(i, followerPrice, 1.0f);
      if(profit > currentOptimum)
      {
        currentOptimum = profit;
        bestPricingStrategy = i;
      }
    }
    return bestPricingStrategy;
  }

  @Override
  public void proceedNewDay(int day) throws RemoteException
  {
    platform.publishPrice(playerType, optimize(day));
  }
}