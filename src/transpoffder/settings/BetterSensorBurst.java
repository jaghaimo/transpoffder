package transpoffder.settings;

import com.fs.starfarer.api.Global;
import lombok.extern.log4j.Log4j;

@Log4j
public class BetterSensorBurst implements Toggler {

    @Override
    public void enable() {
        Global.getSettings().getAbilitySpec("sensor_burst").getTags().remove("burn-");
        Global.getSettings().getAbilitySpec("sensor_burst").getTags().remove("sensors+");
        log.info("Enabled better sensor burst");
    }

    @Override
    public void disable() {
        Global.getSettings().getAbilitySpec("sensor_burst").getTags().add("burn-");
        Global.getSettings().getAbilitySpec("sensor_burst").getTags().add("sensors+");
        log.info("Disabled better sensor burst");
    }
}
