package btw.lowercase.nemi;

import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ClientModInitializer;

public class NoEquipMyItems implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MidnightConfig.init("nemi", NoEquipMyItemsConfig.class);
    }
}