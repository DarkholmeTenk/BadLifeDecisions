package bld.platform;

import comp34120.ex2.PlayerType;

public abstract class AbstractLeader extends AbstractPlayer
{
  protected Leader(String name) throws RemoteException, NotBoundException
  {
    super(name, PlayerType.LEADER);
  }
}
