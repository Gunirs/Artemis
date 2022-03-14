package com.github.gunirs.artemis.listeners;

import com.github.gunirs.artemis.Artemis;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

public class ConnectionEvent {
    @SubscribeEvent
    public void onConnectionPlayerEvent(PlayerEvent.PlayerLoggedInEvent event) {
        Artemis.getUserService().createUserIfNotExists(event.player.getCommandSenderName(), event.player.getUniqueID());
    }
}
