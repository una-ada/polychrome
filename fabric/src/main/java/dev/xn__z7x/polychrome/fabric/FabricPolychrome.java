package dev.xn__z7x.polychrome.fabric;

import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.balm.api.EmptyLoadContext;
import net.fabricmc.api.ModInitializer;
import dev.xn__z7x.polychrome.Polychrome;

public class FabricPolychrome implements ModInitializer {
    @Override
    public void onInitialize() {
        Balm.initializeMod(Polychrome.MOD_ID, EmptyLoadContext.INSTANCE, Polychrome::initialize);
    }
}
