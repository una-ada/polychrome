package dev.xn__z7x.polychrome.forge;

import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.balm.api.client.BalmClient;
import net.blay09.mods.balm.forge.ForgeLoadContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import dev.xn__z7x.polychrome.Polychrome;
import dev.xn__z7x.polychrome.client.PolychromeClient;

@Mod(Polychrome.MOD_ID)
public class ForgePolychrome {

    public ForgePolychrome(FMLJavaModLoadingContext context) {
        final var loadContext = new ForgeLoadContext(context.getModEventBus());
        Balm.initializeMod(Polychrome.MOD_ID, loadContext, Polychrome::initialize);
        if (FMLEnvironment.dist.isClient()) {
            BalmClient.initializeMod(Polychrome.MOD_ID, loadContext, PolychromeClient::initialize);
        }
    }

}
