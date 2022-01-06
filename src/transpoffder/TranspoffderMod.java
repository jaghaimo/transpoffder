package transpoffder;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.EveryFrameScript;
import com.fs.starfarer.api.Global;

public class TranspoffderMod extends BaseModPlugin {

    @Override
    public void onApplicationLoad() throws Exception {
        if (hasQol("BetterSensorBurst")) {
            Global.getSettings().getAbilitySpec("sensor_burst").getTags().remove("burn-");
        }
    }

    @Override
    public void onNewGameAfterTimePass() {
        onGameLoad(true);
    }

    @Override
    public void onGameLoad(boolean newGame) {
        if (hasQol("PartialSurveyAsYouFly")) {
            addTransientScript(new PartialSurveyScript());
        }
        if (hasQol("ScavengeAsYouFly")) {
            addTransientScript(new ScavengeScript());
        }
        if (hasQol("Transpoffder")) {
            addTransientListener(new TranspoffderListener());
        }
    }

    private void addTransientListener(Object listener) {
        Global.getSector().getListenerManager().addListener(listener, true);
    }

    private void addTransientScript(EveryFrameScript script) {
        Global.getSector().addTransientScript(script);
    }

    private boolean hasQol(String key) {
        return Global.getSettings().getBoolean(key);
    }
}
