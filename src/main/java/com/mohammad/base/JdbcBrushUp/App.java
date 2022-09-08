package com.mohammad.base.JdbcBrushUp;

import com.mohammad.base.JdbcBrushUp.TestingConnection.JdbcOperation;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
      JdbcOperation jo=new JdbcOperation();
      jo.provideConnection();
      jo.createEmployeeTable();
      return; 
    }
}
