package transpoffder.settings;

import com.fs.starfarer.api.Global;
import lombok.extern.log4j.Log4j;
import lunalib.lunaSettings.LunaSettings;
import lunalib.lunaSettings.LunaSettingsListener;
import transpoffder.Utils;

@Log4j
public class SettingsListener implements LunaSettingsListener {

    public static void configure() {
        Settings.BETTER_ACTIVE_BURST.configure();
        Settings.PARTIAL_SURVEY_AS_YOU_FLY.configure();
        Settings.SCAVENGE_AS_YOU_FLY.configure();
        Settings.TRANSPONDER_OFF.configure();
    }

    public static void deactivate() {
        Settings.BETTER_ACTIVE_BURST.disable();
        Settings.PARTIAL_SURVEY_AS_YOU_FLY.disable();
        Settings.SCAVENGE_AS_YOU_FLY.disable();
        Settings.TRANSPONDER_OFF.disable();
    }

    public static void register() {
        LunaSettings.addSettingsListener(new SettingsListener());
        try {
            Global.getSettings().loadTexture("graphics/icons/transpoffder.png");
        } catch (Exception exception) {
            log.error("Failed to load tranpoffder icon!");
        }
    }

    @Override
    public void settingsChanged(String modId) {
        if (!modId.equals(Settings.MOD_ID)) {
            return;
        }
        if (!Utils.isInCampaign()) {
            return;
        }
        configure();
    }
}
