package bld.platform;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import comp34120.ex2.Platform;
import comp34120.ex2.Player;
import comp34120.ex2.PlayerType;

public abstract class AbstractPlayer implements Player
{
  protected Platform platform;
  protected final PlayerType playerType;
  protected final String displayName;

  protected AbstractPlayer(String name, PlayerType type)
    throws RemoteException, NotBoundException
  {
    playerType = type;
    displayName = name;
    registerRMI();
    registerPlatform(name);
  }

  @Override
	public void checkConnection() throws RemoteException {}

  @Override
  public void startSimulation(int steps) throws RemoteException
  {
    platform.log(playerType, "startSimulation(): Not supported yet.");
  }

  @Override
  public void endSimulation() throws RemoteException
  {
    platform.log(playerType, "endSimulation(): Not supported yet.");
  }

  @Override
  public void goodbye() throws RemoteException
  {
    platform.log(playerType, "goodbye(): Not supported yet.");
  }

  private void registerRMI() throws RemoteException
  {
    final Player player = (Player) UnicastRemoteObject.exportObject(this, 0);
    final Registry registry = LocateRegistry.getRegistry();
    registry.rebind(playerType.toString(), player);
  }

  private void registerPlatform(final String name)
    throws RemoteException, NotBoundException
  {
    final Registry registry = LocateRegistry.getRegistry();
    platform = (Platform) registry.lookup("Platform");
    platform.registerPlayer(playerType, name);
  }
}
