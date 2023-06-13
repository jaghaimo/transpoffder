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

    public static void quickNotify(String text, String highlight1, String highlight2) {
        Global
            .getSector()
            .getCampaignUI()
            .addMessage(
                text,
                Misc.getTextColor(),
                highlight1,
                highlight2,
                Misc.getHighlightColor(),
                Misc.getHighlightColor()
            );
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
}
