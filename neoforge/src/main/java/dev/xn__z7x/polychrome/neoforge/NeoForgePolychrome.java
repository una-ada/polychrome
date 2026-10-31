package dev.xn__z7x.polychrome.neoforge;

import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.balm.neoforge.NeoForgeLoadContext;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import dev.xn__z7x.polychrome.Polychrome;

@Mod(Polychrome.MOD_ID)
public class NeoForgePolychrome {

    public NeoForgePolychrome(IEventBus modEventBus) {
        final var context = new NeoForgeLoadContext(modEventBus);
        Balm.initializeMod(Polychrome.MOD_ID, context, Polychrome::initialize);
    }
}
