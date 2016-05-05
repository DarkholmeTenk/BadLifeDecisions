package bld;

import java.util.List;
import java.util.ArrayList;

import comp34120.ex2.Record;

public class LinearRegression
{
  private int numberOfDataPoints = 0;
  private List<Record> data;

  public float getSumOfYSquared()
  {
    float sumOfYY = 0.0f;
    for(Record datum : data)
      sumOfYY += datum.m_followerPrice * datum.m_followerPrice;
    return sumOfYY;
  }

  public float getSumOfXSquared()
  {
    float sumOfXX = 0.0f;
    for(Record datum : data)
      sumOfXX += datum.m_leaderPrice * datum.m_leaderPrice;
    return sumOfXX;
  }

  public float getSumOfX()
  {
    float sumOfX = 0.0f;
    for(Record datum : data)
      sumOfX += datum.m_leaderPrice;
    return sumOfX;
  }

  public float getSumOfY()
  {
    float sumOfY = 0.0f;
    for(Record datum : data)
      sumOfY += datum.m_followerPrice;
    return sumOfY;
  }

  public float getSumOfXY()
  {
    float sum = 0.0f;
    for(Record datum : data)
      sum += datum.m_followerPrice * datum.m_leaderPrice;
    return sum;
  }

  public float getMeanY()
  {
    return getSumOfY() / (float)numberOfDataPoints;
  }

  public float getMeanX()
  {
    return getSumOfX() / (float)numberOfDataPoints;
  }

  public float getSSR()
  {
    return (getSumOfXY() - getSumOfX() * getSumOfY()/numberOfDataPoints)
              * (getSumOfXY() - getSumOfX() * getSumOfY()/numberOfDataPoints)
                / getSumOfXSquared() - getSumOfX()*getSumOfX()/numberOfDataPoints;

  }

  public float getRSquared()
  {
    System.out.println("" + getSSR() + ", " + getSST());
    return getSSR()/getSST();
  }

  public float getSSE()
  {
    float sum = 0.0f;
    for(Record datum : data)
      sum += (datum.m_leaderPrice - getMeanX())
              *  (datum.m_leaderPrice - getMeanX());
    return sum;
  }

  public float getSST()
  {
    float sst = 0.0f;
    for(Record datum : data)
      sst += (datum.m_followerPrice - getMeanY())
                * (datum.m_followerPrice - getMeanY());
    return sst;
  }

  public LinearRegression( List<Record> data )
  {
    this.data = data;
    this.numberOfDataPoints = data.size();
  }

  public float getIntercept()
  {
    return getMeanY() - getSlope()*getMeanX();
  }

  public float getSlope()
  {
    return (getSumOfXY() - (getSumOfX()*getSumOfY())/numberOfDataPoints)
      / (getSumOfXSquared() - (getSumOfX())*(getSumOfX()));

  }
}
