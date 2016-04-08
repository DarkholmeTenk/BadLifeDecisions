package bld;

import comp34120.ex2.PlayerImpl;
import comp34120.ex2.PlayerType;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * A pseudo leader. The members m_platformStub and m_type are declared
 * in the PlayerImpl, and feel free to use them. You may want to check
 * the implementation of the PlayerImpl. You will use m_platformStub to access
 * the platform by calling the remote method provided by it.
 *
 * @author Xin
 */
final class SaneLeader extends PlayerImpl {

  /**
   * In the constructor, you need to call the constructor
   * of PlayerImpl in the first line, so that you don't need to
   * care about how to connect to the platform. You may want to throw
   * the two exceptions declared in the prototype, or you may handle it
   * by using "try {} catch {}". It's all up to you.
   *
   * @throws RemoteException
   * @throws NotBoundException
   */
  SaneLeader() throws RemoteException, NotBoundException {
    super(PlayerType.LEADER, "Sane Leader");
  }

  /**
   * You may want to delete this method if you don't want modify
   * the original connection checking behavior of the platform.
   * Actually I recommend you to delete this method from your own code
   *
   * @throws RemoteException If implemented, the RemoteException *MUST* be
   *         thrown by this method
   */
  @Override
  public void checkConnection() throws RemoteException {
    super.checkConnection();
  }

  /**
   * You may want to delete this method if you don't want the platform
   * to control the exit behavior of your leader class
   *
   * @throws RemoteException If implemented, the RemoteException *MUST* be
   *         thrown by this method
   */
  @Override
  public void goodbye() throws RemoteException {
    super.goodbye();
  }

  /**
   * You may want to delete this method if you don't want to do any
   * initialization
   *
   * @param p_steps Indicates how many steps will the simulation perform
   * @throws RemoteException If implemented, the RemoteException *MUST* be
   *         thrown by this method
   */
  @Override
  public void startSimulation(int steps) throws RemoteException {
    super.startSimulation(steps);
  }

  /**
   * You may want to delete this method if you don't want to do any
   * finalization
   *
   * @throws RemoteException If implemented, the RemoteException *MUST* be
   *         thrown by this method
   */
  @Override
  public void endSimulation() throws RemoteException {
    super.endSimulation();
  }

  /**
   * To inform this instance to proceed to a new simulation day
   *
   * @param p_date The date of the new day
   * @throws RemoteException This exception *MUST* be thrown by this method
   */
  @Override
  public void proceedNewDay(int day) throws RemoteException {
    // Record record = platform.query(/*type*/, /*day*/);
    // float price = /* compute new price */;
    // platform.publishPrice(/*type*/, price);
  }
}
