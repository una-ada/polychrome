package dev.xn__z7x.polychrome.neoforge.client;

import net.blay09.mods.balm.api.client.BalmClient;
import net.blay09.mods.balm.neoforge.NeoForgeLoadContext;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import dev.xn__z7x.polychrome.Polychrome;
import dev.xn__z7x.polychrome.client.PolychromeClient;

@Mod(value = Polychrome.MOD_ID, dist = Dist.CLIENT)
public class NeoForgePolychromeClient{

  public NeoForgePolychromeClient(IEventBus modEventBus){
    final var context = new NeoForgeLoadContext(modEventBus);
    BalmClient.initializeMod(Polychrome.MOD_ID, context, PolychromeClient::initialize);
  }
}
