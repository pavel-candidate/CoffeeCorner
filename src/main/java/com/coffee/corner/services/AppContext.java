package com.coffee.corner.services;


public enum AppContext {

  INSTANCE;
  private int eachBeverage = 5;
  private boolean useStampCard = true;
  private String currency = "CHF";


  public int getEachBeverage() {return this.eachBeverage;}

  public boolean isUseStampCard() {return this.useStampCard;}

  public String getCurrency() {return this.currency;}
}
