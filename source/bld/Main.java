package bld;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import bld.leaders.*;

/**
 * This should create a single concrete instance of bld.platform.AbstractLeader.
 * This can later be altered to allow one of many different leaders to be
 * selected.
 */
public class Main
{
  public static void main(final String[] args)
    throws RemoteException, NotBoundException
  {
    new SmartLeaderMk2();
  }
}
