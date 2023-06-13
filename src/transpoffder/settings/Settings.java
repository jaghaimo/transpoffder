package transpoffder.settings;

import lombok.RequiredArgsConstructor;
import lunalib.lunaSettings.LunaSettings;

@RequiredArgsConstructor
public enum Settings {
    BETTER_ACTIVE_BURST("BetterActiveBurst", new BetterSensorBurst()),
    PARTIAL_SURVEY_AS_YOU_FLY("PartialSurveyAsYouFly", new PartialSurveyToggler()),
    SCAVENGE_AS_YOU_FLY("ScavengeAsYouFly", new ScavengeAsYouFlyToggler()),
    TRANSPONDER_OFF("TransponderOff", new TransponderOffToggler());

    private final String key;
    private final Toggler toggler;
    public static final String MOD_ID = "transpoffder";

    public void configure() {
        if (LunaSettings.getBoolean(MOD_ID, key)) {
            toggler.enable();
        } else {
            toggler.disable();
        }
    }

    public void disable() {
        toggler.disable();
    }
}
