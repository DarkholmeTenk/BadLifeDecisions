package bld.platform;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ArrayList;

import comp34120.ex2.PlayerType;
import comp34120.ex2.Record;

/**
 * Represents a leader in the game. Every implementation of a leader should
 * extend this class or a subclass.
 */
public abstract class AbstractLeader extends AbstractPlayer
{

  protected List<Record> getDataPoints(int day)
  {
    List<Record> recordList = new ArrayList<Record>();
    for(int i = 1; i < day; i++)
    {
      try
      {
        recordList.add(platform.query(playerType, i));
      }
      catch(RemoteException e)
      {
        break;
      }
    }
    return recordList;
  }


  protected float calculateProfit(float leader, float follower, float cost)
  {
    return (leader-cost)*(2-leader + 0.3f*follower);
  }


  protected float calculateProfit(float leader, float follower)
  {
    return (leader-1.0f)*(2-leader + 0.3f*follower);
  }

  /**
   * Creates a leader with the given display name.
   */
  protected AbstractLeader(String name)
    throws RemoteException, NotBoundException
  {
    super(name, PlayerType.LEADER);
  }
}
