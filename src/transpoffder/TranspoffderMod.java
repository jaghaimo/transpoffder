package transpoffder;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.EveryFrameScript;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.util.Misc;
import lombok.extern.log4j.Log4j;
import org.json.JSONObject;

@Log4j
public class TranspoffderMod extends BaseModPlugin {

    private boolean isAutoScavengeActive;
    private JSONObject settings;

    @Override
    public void afterGameSave() {
        if (hasQol("ScavengeAsYouFly")) {
            restoreAutoScavenge();
        }
    }

    @Override
    public void beforeGameSave() {
        if (hasQol("ScavengeAsYouFly")) {
            removeAutoScavenge();
        }
    }

    @Override
    public void onApplicationLoad() throws Exception {
        isAutoScavengeActive = false;
        settings = Global.getSettings().loadJSON("transpoffder.json");
        if (hasQol("BetterSensorBurst")) {
            Global.getSettings().getAbilitySpec("sensor_burst").getTags().remove("burn-");
            Global.getSettings().getAbilitySpec("sensor_burst").getTags().remove("sensors+");
            log.info("Enabled better sensor burst");
        }
    }

    @Override
    public void onGameLoad(boolean newGame) {
        if (hasQol("PartialSurveyAsYouFly")) {
            addTransientScript(new PartialSurveyScript());
            log.info("Enabled partial survey as you fly");
        }
        if (hasQol("ScavengeAsYouFly")) {
            restoreAutoScavenge();
            notifyAboutState();
            log.info("Enabled scavenge as you fly");
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

    private void notifyAboutState() {
        String state = isAutoScavengeActive ? "enabled" : "disabled";
        Global
            .getSector()
            .getCampaignUI()
            .addMessage(
                "Automatic scavenging is %s.",
                Misc.getTextColor(),
                state,
                state,
                Misc.getHighlightColor(),
                Misc.getHighlightColor()
            );
    }

    private void removeAutoScavenge() {
        isAutoScavengeActive = AutoScavengeAbility.isOn();
        Global.getSector().getCharacterData().removeAbility("auto_scavenge");
    }

    private void restoreAutoScavenge() {
        Global.getSector().getCharacterData().addAbility("auto_scavenge");
        AutoScavengeAbility.setOn(isAutoScavengeActive);
    }
}
