package dev.xn__z7x.polychrome.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.blay09.mods.kuma.api.InputBinding;
import net.blay09.mods.kuma.api.Kuma;
import net.blay09.mods.kuma.api.ManagedKeyMapping;
import dev.xn__z7x.polychrome.Polychrome;

import static dev.xn__z7x.polychrome.Polychrome.id;

public class ModKeyMappings {

    public static ManagedKeyMapping yourKey;

    public static void initialize() {
        yourKey = Kuma.createKeyMapping(id("your_key"))
                .withDefault(InputBinding.key(InputConstants.KEY_B))
                .handleScreenInput(event -> {
                    Polychrome.logger.info("B was pressed - " + Polychrome.MOD_ID);
                    return true;
                })
                .build();
    }
}
