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

public class SmartLeader extends AbstractLeader
{
  private  float lastOptimizedPrice = -1.0f;

  public List<Record> getDataPoints(int day)
  {
    List<Record> recordList = new ArrayList<Record>();
    for(int i = 1; i < day; i++)
    {
      System.out.println("" + i);
      try
      {
        recordList.add(platform.query(playerType, i));
      }
      catch(RemoteException e)
      {
        System.out.println("Could only access up to " + i );
        break;
      }
    }
    return recordList;
  }

  public SmartLeader() throws RemoteException, NotBoundException
  {
    super("Smart Leader");
  }

  private float calculateProfit(float leader, float follower, float cost)
  {
    return (leader-cost)*(2-leader + 0.3f*follower);
  }

  private float optimize(int day)
  {
    float currentOptimum = 0;
    float bestPricingStrategy = 0;
    LinearRegression regression = new LinearRegression(getDataPoints(day));
    for(float i = 1.0f; i < 10.0f; i += 0.01f)
    {
      float follower = regression.getSlope()*i + regression.getIntercept();
      float profit = calculateProfit(i, follower, 1.0f);
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
    lastOptimizedPrice = optimize(day);
    platform.publishPrice(playerType, lastOptimizedPrice);
  }
}
