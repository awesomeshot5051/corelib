package com.awesomeshot5051.corelib.blockentity.render;

import com.mojang.blaze3d.vertex.*;
import com.mojang.math.*;
import net.minecraft.core.*;

public class SaplingRendererUtil {
    public static void renderSapling(PoseStack matrixStack, Direction direction) {
        matrixStack.pushPose();
        switch (direction) {
            case NORTH -> matrixStack.translate(1D, 0D, 1D);
            case WEST -> matrixStack.translate(1D, 0D, 0D);
            case EAST -> matrixStack.translate(0D, 0D, 1.1D);
            case SOUTH -> matrixStack.translate(0D, 0D, 0D);
        }
        matrixStack.mulPose(Axis.YP.rotationDegrees(-direction.toYRot()));
        matrixStack.scale(0.7F, 0.7F, 0.7F);
    }
}