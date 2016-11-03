/*
 * The MIT License (MIT)
 * Copyright (c) 2016 Chris Martin (OmniCypher-), Dylan Curzon (curz46), Connor Hartley (connorhartley)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.github.abilityapi.test;

import com.github.abilityapi.ability.Ability;
import com.github.abilityapi.ability.AbilityManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

public class TestAbility implements Ability {

    private final AbilityManager manager;
    private final Player player;
    private int ticks;

    public TestAbility(AbilityManager manager, Player player) {
        this.manager = manager;
        this.player = player;
    }

    @Override
    public void start() {
        player.sendMessage("Started...");
    }

    @Override
    public void update() {
        if (ticks++ % 5 == 0) {
            player.sendMessage("Update! (every 5)");
        }
    }

    @Override
    public void stop() {
        player.sendMessage("Stopped!");
    }

    @Override
    public boolean isExecuting() {
        return ticks < 20 * 10;
    }

    @EventHandler
    public void onPlayerJump(PlayerMoveEvent evt) {
        Player player = evt.getPlayer();

        if (this.player.equals(player) && !player.isOnGround()) {
            player.sendMessage("You jumped, we stopped!");
            manager.stop(this);
        }
    }

}
