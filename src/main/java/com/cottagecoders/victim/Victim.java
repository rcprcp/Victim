package com.cottagecoders.victim;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Victim {

  private static Logger log = LoggerFactory.getLogger(Victim.class);

  private Victim() {
    log.info("Victim ctor got here.");

  }

  public static void main(String[] args) {
    method1();
    Victim victim = new Victim();

    for (int i = 0; i < 5; i++) {
      victim.bobrun1();
      victim.bobrun2();
    }

  }

  private static void method1() {
    log.info("got here.");
    try {
      Thread.sleep(1000);
    } catch (InterruptedException ex) {
      log.error(ex.getMessage());
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
