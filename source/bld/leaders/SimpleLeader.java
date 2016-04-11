package bld.leaders;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Random;

import bld.platform.AbstractLeader;

/**
 * A simple leader based on comp34120.ex2.leaders.SimpleLeader. This leader
 * tries random prices.
 */
public final class SimpleLeader extends AbstractLeader
{
  private final Random randomizer = new Random(System.currentTimeMillis());

  public SimpleLeader() throws RemoteException, NotBoundException
  {
    super("Simple Leader");
  }

  @Override
	public void proceedNewDay(int day) throws RemoteException
	{
		platform.publishPrice(playerType, randomPrice(1.8f, 0.05f));
	}

  /**
   * Generates a random price based on a Gaussian distribution.
   */
  private float randomPrice(final float mean, final float diversity)
  {
    return (float) (mean + randomizer.nextGaussian() * diversity);
  }
}
