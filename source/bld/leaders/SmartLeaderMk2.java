package bld.leaders;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.*;

import bld.platform.AbstractLeader;
import bld.NeuronFactory;
import bld.NeuronType;
import bld.Neuron;

import comp34120.ex2.Record;

public class SmartLeaderMk2 extends AbstractLeader
{
  private Neuron responseFunction;
  private List<Float> profits;
  private float lastOptimizedPrice = -1.0f;


  public SmartLeaderMk2() throws RemoteException, NotBoundException
  {
    super("Simple Leader Mk2");
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
    if(lastOptimizedPrice != -1.0f)
    {
      Record lastDay = platform.query(playerType, day - 1);
      responseFunction.train(lastDay.m_followerPrice);
      profits.add(calculateProfit(lastDay.m_leaderPrice, lastDay.m_followerPrice));
    }
    lastOptimizedPrice = optimize(day);
    platform.publishPrice(playerType, lastOptimizedPrice);
  }

  @Override
  public void startSimulation(int steps)
  {
    profits = new ArrayList<Float>();
    responseFunction = NeuronFactory.createNeuron(NeuronType.BASIC_NEURON,
    4, platform);
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
