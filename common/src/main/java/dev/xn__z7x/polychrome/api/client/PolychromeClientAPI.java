package dev.xn__z7x.polychrome.api.client;

import java.lang.reflect.InvocationTargetException;

public class PolychromeClientAPI{

  private static final InternalClientMethods __internalMethods;

  static{
    try{
      __internalMethods = (InternalClientMethods) Class.forName("dev.xn__z7x.polychrome.client.InternalClientMethodsImpl").getConstructor().newInstance();
    } catch(InstantiationException | IllegalAccessException |
            InvocationTargetException | NoSuchMethodException |
            ClassNotFoundException e){
      throw new RuntimeException(e);
    }
  }

}
