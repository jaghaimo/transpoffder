package transpoffder.settings;

import lombok.extern.log4j.Log4j;
import transpoffder.Utils;

@Log4j
public class ScavengeAsYouFlyToggler implements Toggler {

    @Override
    public void enable() {
        Utils.restoreAutoScavenge();
        Utils.notifyAboutState();
        log.info("Enabled scavenge as you fly");
    }

    @Override
    public void disable() {
        Utils.removeAutoScavenge();
        log.info("Disabled scavenge as you fly");
    }
}
