package btw.lowercase.nemi.mixin;

import btw.lowercase.nemi.EquipAnimationEnum;
import btw.lowercase.nemi.NoEquipMyItemsConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ItemInHandRenderer.class)
public class MixinItemInHandRenderer {
    @ModifyArg(method = "renderArmWithItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/ItemInHandRenderer;applyItemArmTransform(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/world/entity/HumanoidArm;F)V"), index = 2)
    private float disableEquipAnimation(float equipProgress) {
        AbstractClientPlayer abstractClientPlayer = Minecraft.getInstance().player;
        if (NoEquipMyItemsConfig.EQUIP_ANIMATION == EquipAnimationEnum.OFF || abstractClientPlayer == null) {
            return equipProgress; // Vanilla Behavior
        }

        boolean isUsingItem = abstractClientPlayer.isUsingItem() && abstractClientPlayer.getUseItemRemainingTicks() > 0;
        if (NoEquipMyItemsConfig.EQUIP_ANIMATION == EquipAnimationEnum.ALL || (NoEquipMyItemsConfig.EQUIP_ANIMATION == EquipAnimationEnum.ITEM_USE && isUsingItem)) {
            return 0.0F;
        }

        return equipProgress; // unreachable
    }
}