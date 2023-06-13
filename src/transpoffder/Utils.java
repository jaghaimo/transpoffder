package transpoffder;

import com.fs.starfarer.api.EveryFrameScript;
import com.fs.starfarer.api.GameState;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.util.Misc;

public class Utils {

    public static void addTransientListener(Object listener) {
        Global.getSector().getListenerManager().addListener(listener, true);
    }

    public static void addTransientScript(EveryFrameScript script) {
        Global.getSector().addTransientScript(script);
    }

    public static boolean isInCampaign() {
        return Global.getSettings().getCurrentState() == GameState.CAMPAIGN;
    }

    public static void notifyAboutState() {
        String state = AutoScavengeAbility.isOn() ? "enabled" : "disabled";
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

    public static void removeAutoScavenge() {
        boolean isOn = AutoScavengeAbility.isOn();
        Global.getSector().getMemoryWithoutUpdate().set("$transpoffderAutoScavenge", isOn);
        Global.getSector().getCharacterData().removeAbility("auto_scavenge");
    }

    public static void removeTransientListeners(Class<?> clazz) {
        Global.getSector().getListenerManager().removeListenerOfClass(clazz);
    }

    public static void removeTransientScripts(Class<?> clazz) {
        for (EveryFrameScript script : Global.getSector().getTransientScripts()) {
            if (clazz.isInstance(script)) {
                Global.getSector().removeTransientScript(null);
            }
        }
    }

    public static void restoreAutoScavenge() {
        Global.getSector().getCharacterData().addAbility("auto_scavenge");
        boolean isOn = Global.getSector().getMemoryWithoutUpdate().getBoolean("$transpoffderAutoScavenge");
        AutoScavengeAbility.setOn(isOn);
    }
}
