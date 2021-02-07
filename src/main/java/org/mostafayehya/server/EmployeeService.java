package org.mostafayehya.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface EmployeeService extends Remote {

    Employee createEmployee(Employee employee) throws RemoteException;

    Employee updateEmployee(Employee employee) throws RemoteException;

    Boolean deleteEmployee() throws RemoteException;

    Employee getFirstEmployee() throws RemoteException;

    Employee getLastEmployee() throws RemoteException;

    Employee getNextEmployee() throws RemoteException;

    Employee getPreviousEmployee() throws RemoteException;

}
