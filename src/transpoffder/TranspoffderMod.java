package transpoffder;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.EveryFrameScript;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.impl.campaign.tutorial.TutorialMissionIntel;
import lombok.extern.log4j.Log4j;
import org.json.JSONObject;

@Log4j
public class TranspoffderMod extends BaseModPlugin {

    private JSONObject settings;

    @Override
    public void onApplicationLoad() throws Exception {
        settings = Global.getSettings().loadJSON("transpoffder.json");
        if (hasQol("BetterSensorBurst")) {
            Global.getSettings().getAbilitySpec("sensor_burst").getTags().remove("burn-");
            log.info("Enabled better sensor burst");
        }
    }

    @Override
    public void onGameLoad(boolean newGame) {
        if (TutorialMissionIntel.isTutorialInProgress()) {
            log.warn("Tutorial detected - skipping mod initialization");
            return;
        }
        if (hasQol("PartialSurveyAsYouFly")) {
            addTransientScript(new PartialSurveyScript());
            log.info("Enabled partial survey as you fly");
        }
        if (hasQol("ScavengeAsYouFly")) {
            addTransientScript(new ScavengeScript());
            log.info("Enabled scavenge ass you fly");
        }
        if (hasQol("Transpoffder")) {
            addTransientListener(new TranspoffderListener());
            log.info("Enabled transpoffder");
        }
    }

    private void addTransientListener(Object listener) {
        Global.getSector().getListenerManager().addListener(listener, true);
    }

    private void addTransientScript(EveryFrameScript script) {
        Global.getSector().addTransientScript(script);
    }

    private boolean hasQol(String key) {
        return settings.optBoolean(key, true);
    }
}
