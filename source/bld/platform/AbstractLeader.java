package bld.platform;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import comp34120.ex2.PlayerType;

/**
 * Represents a leader in the game. Every implementation of a leader should
 * extend this class or a subclass.
 */
public abstract class AbstractLeader extends AbstractPlayer
{
  /**
   * Creates a leader with the given display name.
   */
  protected AbstractLeader(String name)
    throws RemoteException, NotBoundException
  {
    super(name, PlayerType.LEADER);
  }
}
