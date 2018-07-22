package com.cottagecoders.victim;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.IntrospectionException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.Map;

public class Victim {

  private static Logger log = LoggerFactory.getLogger(Victim.class);

  private Victim() {
    log.info("Victim ctor got here.");

  }

  public static void main(String[] args) {
    method1();
    Victim victim = new Victim();
    try {
      victim.jmx();
    } catch (ReflectionException | IntrospectionException | InstanceNotFoundException | MBeanException |
        AttributeNotFoundException ex) {
      System.out.println("Exception " + ex.getMessage());
      ex.printStackTrace();
      System.exit(27);
    }
    for (int i = 0; i < 5; i++) {
      victim.bobrun1();
      victim.bobrun2();
    }

    while (true) {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException ex) {
      }
    }
  }

  private static void method1() {
    log.info("method1 - got here.");
    try {
      Thread.sleep(1000);
    } catch (InterruptedException ex) {
      log.error(ex.getMessage());
    }
  }

  // test jmx code:
  private void jmx() throws
      InstanceNotFoundException,
      IntrospectionException,
      ReflectionException,
      MBeanException,
      AttributeNotFoundException {
    MBeanServer server = ManagementFactory.getPlatformMBeanServer();

    for (ObjectName name : server.queryNames(null, null)) {
      MBeanInfo info = server.getMBeanInfo(name);
      if (info.getClassName().equals("sun.management.ClassLoadingImpl")) {
        MBeanAttributeInfo[] arr = info.getAttributes();
        for (MBeanAttributeInfo a : arr) {
          System.out.println("objectName  " + name + " desc " + info.getClassName() + " name " + a.toString() + " type " + a.getType() + " " +
              "readable " + a
              .isReadable() + " writable " + a.isWritable());
          if (a.isReadable()) {
            if(a.getType().equals("long")) {
              System.out.println("long " + a.getName() + " value " + server.getAttribute(name, a.getName()));

            } else if( a.getType().equals("int")) {
              System.out.println("int " + a.getName() + " value " + server.getAttribute(name, a.getName()));

            } else {
              System.out.println("don't know type " + a.getType());
            }
          }
        }
      }
    }
  }

  private void bobrun1() {
    log.info("Victim bobrun1 got here.");
    try {
      Thread.sleep(1000);
    } catch (InterruptedException ex) {
      log.error(ex.getMessage());
    }
  }

  private void bobrun2() {
    log.info("Victim bobrun2 got here.");
    try {
      Thread.sleep(1000);
    } catch (InterruptedException ex) {
      log.error(ex.getMessage());
    }
  }
}
