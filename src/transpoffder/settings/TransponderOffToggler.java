package transpoffder.settings;

import lombok.extern.log4j.Log4j;
import transpoffder.TransponderOffListener;
import transpoffder.Utils;

@Log4j
public class TransponderOffToggler implements Toggler {

    @Override
    public void enable() {
        Utils.addTransientListener(new TransponderOffListener());
        log.info("Enabled transponder off");
    }

    @Override
    public void disable() {
        Utils.removeTransientListeners(TransponderOffListener.class);
        log.info("Disabled transponder off");
    }
}
