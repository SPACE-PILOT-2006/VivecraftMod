package org.vivecraft.client.gui.settings;

import net.minecraft.client.gui.screens.Screen;
import org.vivecraft.client.gui.framework.VROptionEntry;
import org.vivecraft.client.gui.framework.screens.GuiVROptionsBase;
import org.vivecraft.client_vr.settings.VRSettings;
public class GuiSuperStrengthSettings extends GuiVROptionsBase {
    private final VROptionEntry[] superStrengthSettings = new VROptionEntry[]{
        new VROptionEntry(VRSettings.VrOptions.SUPER_STRENGTH),
        new VROptionEntry(VRSettings.VrOptions.SUPER_STRENGTH_WEAPONS)
    };

    public GuiSuperStrengthSettings(Screen lastScreen) {
        super(lastScreen);
    }

    @Override
    public void init() {
        this.vrTitle = "Super Strength Options";
        super.init(this.superStrengthSettings, true);
        super.addDefaultButtons();
    }
}
