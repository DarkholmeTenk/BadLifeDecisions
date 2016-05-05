package bld;

import java.util.List;
import java.util.ArrayList;

import comp34120.ex2.Record;

public class LinearRegression
{
  private int numberOfDataPoints = 0;
  private float sumOfXValues = 0.0f;
  private float sumofYValues = 0.0f;
  private float sumOfXSquares = 0.0f;
  private float sumofXYValues = 0.0f;
  private float sumOfYSquares = 0.0f;
  private float averageX = 0.0f;
  private float averageY = 0.0f;


  public LinearRegression( List<Record> data )
  {
    float[] x = new float[data.size()];
    float[] y = new float[data.size()];
    for(int day = 0; day < data.size(); day++)
    {
      x[day] = data.get(day).m_leaderPrice;
      y[day] = data.get(day).m_followerPrice;
    }
    numberOfDataPoints = x.length;
    for(int day = 0; day < numberOfDataPoints; day++)
    {
      sumOfXValues += x[day];
      sumOfXSquares += x[day] * x[day];
      sumofYValues += y[day];
      sumOfYSquares += y[day] * y[day];
      sumofXYValues += x[day] * y[day];

    }

    averageX = sumOfXValues / numberOfDataPoints;
    averageY = sumofYValues / numberOfDataPoints;

    float value = (sumofXYValues - sumOfXValues * sumofYValues)
                  / (sumOfXSquares - sumOfXValues * sumOfXValues);
  }

  public float getIntercept()
  {
    return averageY - getSlope()*averageX;
  }

  public float getSlope()
  {
    return (sumofXYValues - (sumOfXValues*sumofYValues)/numberOfDataPoints)
      / (sumOfXSquares - (sumOfXValues)*(sumOfXValues));

  }
}
