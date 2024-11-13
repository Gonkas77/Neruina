package com.bawnorton.neruina.handler.client;

import com.bawnorton.neruina.Neruina;
import com.bawnorton.neruina.version.Texter;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.MessageScreen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.entity.player.PlayerEntity;

public final class ClientTickHandler {
    public static void handleTickingClient(PlayerEntity player, Throwable e) {
        Neruina.LOGGER.warn("Neruina caught an exception, see below for cause", e);
        player.getWorld().disconnect();
        MinecraftClient client = MinecraftClient.getInstance();
        client.disconnect(new MessageScreen(Texter.translatable("menu.savingLevel")));
        client.setScreen(new TitleScreen());
        client.getToastManager().add(SystemToast.create(client,
                SystemToast.Type.WORLD_ACCESS_FAILURE,
                Texter.translatable("neruina.toast.title"),
                Texter.translatable("neruina.toast.desc")
        ));
    }
}