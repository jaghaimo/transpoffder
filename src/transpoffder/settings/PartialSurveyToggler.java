package transpoffder.settings;

import lombok.extern.log4j.Log4j;
import transpoffder.PartialSurveyScript;
import transpoffder.Utils;

@Log4j
public class PartialSurveyToggler implements Toggler {

    @Override
    public void enable() {
        Utils.addTransientScript(new PartialSurveyScript());
        log.info("Enabled partial survey as you fly");
    }

    @Override
    public void disable() {
        Utils.removeTransientScripts(PartialSurveyScript.class);
        log.info("Disabled partial survey as you fly");
    }
}
