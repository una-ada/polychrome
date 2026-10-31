package dev.xn__z7x.polychrome.api;

import java.lang.reflect.InvocationTargetException;

public class PolychromeAPI{

  public static final String MOD_ID = "polychrome";

  private static final InternalMethods __internalMethods;

  static{
    try{
      __internalMethods = (InternalMethods) Class.forName("dev.xn__z7x.polychrome.InternalMethodsImpl").getConstructor().newInstance();
    } catch(InstantiationException | IllegalAccessException |
            InvocationTargetException | NoSuchMethodException |
            ClassNotFoundException e){
      throw new RuntimeException(e);
    }
  }

}
