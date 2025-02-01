package com.awesomeshot5051.corelib.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.*;
import net.minecraft.core.*;
import net.minecraft.world.phys.Vec2;
import org.joml.Vector3f;

public class RenderUtils {

    public static int getArgb(int a, int red, int green, int blue) {
        return a << 24 | red << 16 | green << 8 | blue;
    }

    public static int getAlpha(int argb) {
        return (argb >> 24) & 0xFF;
    }

    public static int getRed(int argb) {
        return (argb >> 16) & 0xFF;
    }

    public static int getGreen(int argb) {
        return (argb >> 8) & 0xFF;
    }

    public static int getBlue(int argb) {
        return argb & 0xFF;
    }

    public static float getAlphaFloat(int argb) {
        return (float) getAlpha(argb) / 255F;
    }

    public static float getRedFloat(int argb) {
        return (float) getRed(argb) / 255F;
    }

    public static float getGreenFloat(int argb) {
        return (float) getGreen(argb) / 255F;
    }

    public static float getBlueFloat(int argb) {
        return (float) getBlue(argb) / 255F;
    }

    public static void vertex(VertexConsumer builder, PoseStack matrixStack, org.joml.Vector3f position, Vec2 texCoord, org.joml.Vector3f normal, int light, int overlay) {
        vertex(builder, matrixStack, position.x(), position.y(), position.z(), texCoord.x, texCoord.y, normal.x(), normal.y(), normal.z(), 255, 255, 255, light, overlay);
    }

    public static void vertex(VertexConsumer builder, PoseStack matrixStack, float posX, float posY, float posZ, float texX, float texY, int red, int green, int blue, int light, int overlay) {
        vertex(builder, matrixStack, posX, posY, posZ, texX, texY, 0F, 0F, -1F, red, green, blue, light, overlay);
    }

    public static void vertex(VertexConsumer builder, PoseStack matrixStack, float posX, float posY, float posZ, float texX, float texY, int light, int overlay) {
        vertex(builder, matrixStack, posX, posY, posZ, texX, texY, 0F, 0F, -1F, 255, 255, 255, light, overlay);
    }

    public static void vertex(VertexConsumer builder, PoseStack matrixStack, float posX, float posY, float posZ, float texX, float texY, float norX, float norY, float norZ, int red, int green, int blue, int light, int overlay) {
        PoseStack.Pose entry = matrixStack.last();
        builder.addVertex(entry.pose(), posX, posY, posZ)
                .setColor(red, green, blue, 255)
                .setUv(texX, texY)
                .setOverlay(overlay)
                .setLight(light)
                .setNormal(entry, norX, norY, norZ);
    }
    public static void renderMob(PoseStack matrixStack, Direction direction) {
            matrixStack.pushPose();
            switch (direction) {
                case NORTH, WEST -> matrixStack.translate(.5D, 0D, .5D);
                case EAST -> matrixStack.translate(.5D, 0D, 0.5D);
                case SOUTH -> matrixStack.translate(0.5D, 0D, 0.5D);
            }
            matrixStack.mulPose(Axis.YP.rotationDegrees(-direction.toYRot()));
            matrixStack.scale(0.4F, 0.4F, 0.4F);
        }

}
