package dev.xn__z7x.polychrome.fabric.client;

import net.blay09.mods.balm.api.client.BalmClient;
import net.blay09.mods.balm.api.EmptyLoadContext;
import net.fabricmc.api.ClientModInitializer;
import dev.xn__z7x.polychrome.Polychrome;
import dev.xn__z7x.polychrome.client.PolychromeClient;

public class FabricPolychromeClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BalmClient.initializeMod(Polychrome.MOD_ID, EmptyLoadContext.INSTANCE, PolychromeClient::initialize);
    }
}
