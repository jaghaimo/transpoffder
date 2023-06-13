package transpoffder;

import com.fs.starfarer.api.BaseModPlugin;
import transpoffder.settings.Settings;
import transpoffder.settings.SettingsListener;

public class TranspoffderMod extends BaseModPlugin {

    @Override
    public void afterGameSave() {
        Settings.SCAVENGE_AS_YOU_FLY.configure();
    }

    @Override
    public void beforeGameSave() {
        Settings.SCAVENGE_AS_YOU_FLY.disable();
    }

    @Override
    public void onApplicationLoad() throws Exception {
        SettingsListener.register();
    }

    @Override
    public void onGameLoad(boolean newGame) {
        SettingsListener.configure();
    }
}
