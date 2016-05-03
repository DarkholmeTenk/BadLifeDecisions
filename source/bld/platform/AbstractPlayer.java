package bld.platform;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Timer;
import java.util.TimerTask;

import comp34120.ex2.Platform;
import comp34120.ex2.Player;
import comp34120.ex2.PlayerType;

/**
 * Represents a player in the game.
 */
public abstract class AbstractPlayer implements Player
{
  /**
   * The platform. By calling the methods of platform, you can send messages to
   * the platform.
   */
  protected Platform platform;

  /**
   * The type of this player: Either LEADER or FOLLOWER.
   */
  protected final PlayerType playerType;

  /**
   * The display name for this player. This will be used to identify the player
   * in the platform's GUI.
   */
  protected final String displayName;

  /**
   * Creates a player of the given type with the given display name.
   */
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
    platform.log(playerType, "startSimulation() not supported by this player.");
  }

  @Override
  public void endSimulation() throws RemoteException
  {
    platform.log(playerType, "endSimulation() not supported by this player.");
  }

  @Override
  public void goodbye() throws RemoteException
  {
    ExitTask.exit(500);
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

  /**
   * A task used to automatically exit the leader process when the platform
   * closes.
   */
  private static class ExitTask extends TimerTask {
    static void exit(final long delay) {
      (new Timer()).schedule(new ExitTask(), delay);
    }

    @Override
    public void run() { System.exit(0); }
  }
}
