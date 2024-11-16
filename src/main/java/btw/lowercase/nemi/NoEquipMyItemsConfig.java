package btw.lowercase.nemi;

import eu.midnightdust.lib.config.MidnightConfig;

public class NoEquipMyItemsConfig extends MidnightConfig {
    @Entry(category = "nemi")
    public static EquipAnimationEnum EQUIP_ANIMATION = EquipAnimationEnum.ALL;   // Example for an enum option
}
