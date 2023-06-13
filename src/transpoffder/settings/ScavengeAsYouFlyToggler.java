package transpoffder.settings;

import com.fs.starfarer.api.Global;
import lombok.extern.log4j.Log4j;
import transpoffder.AutoScavengeAbility;
import transpoffder.Utils;

@Log4j
public class ScavengeAsYouFlyToggler implements Toggler {

    @Override
    public void enable() {
        boolean isOn = Global.getSector().getMemoryWithoutUpdate().getBoolean("$transpoffderAutoScavenge");
        Global.getSector().getCharacterData().addAbility("auto_scavenge");
        AutoScavengeAbility.setOn(isOn);
        String state = isOn ? "enabled" : "disabled";
        Utils.quickNotify("Automatic scavenging is %s.", state, state);
        log.info("Enabled scavenge as you fly");
    }

    @Override
    public void disable() {
        if (AutoScavengeAbility.has()) {
            boolean isOn = AutoScavengeAbility.isOn();
            Global.getSector().getMemoryWithoutUpdate().set("$transpoffderAutoScavenge", isOn);
            Global.getSector().getCharacterData().removeAbility("auto_scavenge");
        }
        log.info("Disabled scavenge as you fly");
    }
}
