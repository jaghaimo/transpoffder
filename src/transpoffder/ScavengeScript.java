package transpoffder;

import com.fs.starfarer.api.EveryFrameScript;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.characters.AbilityPlugin;
import com.fs.starfarer.api.impl.campaign.ids.Abilities;

public class ScavengeScript implements EveryFrameScript {

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public boolean runWhilePaused() {
        return false;
    }

    @Override
    public void advance(float amount) {
        if (amount == 0) {
            return;
        }
        CampaignFleetAPI playerFleet = Global.getSector().getPlayerFleet();
        AbilityPlugin scavenge = playerFleet.getAbility(Abilities.SCAVENGE);
        if (scavenge.isUsable()) {
            scavenge.activate();
        }
    }
}
