/*
 * Minecraft Keyboard Fix: fix keyboard issues in Minecraft on GNU/Linux
 * Copyright (C) 2019 Leo <liaoyuan@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.leo3418.mckeyboardfix;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

import static org.lwjgl.input.Keyboard.*;

/**
 * The main class of this mod.
 */
@Mod(
        name = "Minecraft Keyboard Fix",
        modid = "mckeyboardfix",
        version = "@version@",
        acceptedMinecraftVersions = "@compatible_versions@",
        clientSideOnly = true,
        updateJSON = "https://leo3418.github.io/mckeyboardfix/promotions.json"
)
public final class MCKeyboardFix {
    /**
     * This constructor is only intended to be called by Minecraft Forge.
     */
    public MCKeyboardFix() {
    }

    /**
     * Emulates a press on a key. If the key is not associated with any key
     * binding in the game, then a key press will not be emulated.
     *
     * @param keyCode the code of the key
     */
    private static void pressKey(int keyCode) {
        KeyBinding.onTick(keyCode);
    }

    /**
     * Fixes the issue where Shift-2 and Shift-6 are not working.
     *
     * @param keyCode the code of the key pressed by the user
     */
    private static void fixShiftIssue(int keyCode) {
        /*
         * In Minecraft on GNU/Linux, Shift-6 is interpreted as a press on the
         * `^` key, and Shift-2 is interpreted as a press on the `@` key, and
         * the numeric key is never detected as pressed in these cases. For
         * most keyboards, this does not make sense since they do not have
         * dedicated `^` or `@` key, but indeed, this is what happens. To fix
         * this issue, we just need to emulate a press on the numeric key.
         */
        switch (keyCode) {
            case KEY_CIRCUMFLEX: { // Shift-6
                pressKey(KEY_6);
            } break;
            case KEY_AT: { // Shift-2
                pressKey(KEY_2);
            } break;
        }
    }

    @Mod.EventHandler
    public void onFMLInitialization(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onInput(InputEvent.KeyInputEvent event) {
        int keyCode = getEventKey();
        fixShiftIssue(keyCode);
    }
}
