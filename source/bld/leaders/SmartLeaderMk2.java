package bld.leaders;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import bld.platform.AbstractLeader;
import bld.NeuronFactory;
import bld.NeuronType;
import bld.Neuron;
import bld.LinearRegression;

import comp34120.ex2.Record;

public class SmartLeaderMk2 extends AbstractLeader
{
  protected  float lastOptimizedPrice = -1.0f;
  private Neuron responseFunction;
  protected List<Float> profits = new ArrayList<Float>();
  protected float lastProfit;

  public SmartLeaderMk2() throws RemoteException, NotBoundException
  {
    super("Smart Leader Mk2");
  }

  protected float optimize(int day)
  {
    float currentOptimum = 0;
    float bestPricingStrategy = 0;
    for(float i = 1.0f; i < 10.0f; i += 0.01f)
    {
      float[] input = new float[4];
      for(int j = 0; j < 4; j++)
      {
        input[j] = (float)Math.pow(i, j);
      }
      float follower = responseFunction.input(input);
      float profit = calculateProfit(i, follower, 1.0f);
      if(profit > currentOptimum)
      {
        currentOptimum = profit;
        bestPricingStrategy = i;
      }
    }
    lastProfit = currentOptimum;
    return bestPricingStrategy;
  }

  @Override
  public void proceedNewDay(int day) throws RemoteException
  {
    responseFunction = NeuronFactory.createNeuron(NeuronType.BASIC_NEURON, 4, platform);
    lastOptimizedPrice = optimize(day);
    profits.add(lastProfit);
    platform.publishPrice(playerType, lastOptimizedPrice);
  }

  @Override
  public void startSimulation(int steps)
  {
    profits = new ArrayList<Float>();
  }

  @Override
  public void endSimulation()
  {
    float sumOfProfits = 0.0f;
    for(float f : profits)
    {
        sumOfProfits += f;
    }
    System.out.println("Sum of all profits: " + sumOfProfits);
  }
}
