package transpoffder;

import com.fs.starfarer.api.EveryFrameScript;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.characters.AbilityPlugin;
import com.fs.starfarer.api.impl.campaign.ids.Abilities;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ScavengeScript implements EveryFrameScript {

    private final double cooldown;
    private float advanced = 0;

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
        advanced += amount;
        boolean canExecute = advanced > cooldown && amount != 0;
        if (!canExecute) {
            return;
        }
        CampaignFleetAPI playerFleet = Global.getSector().getPlayerFleet();
        AbilityPlugin scavenge = playerFleet.getAbility(Abilities.SCAVENGE);
        if (scavenge.isUsable()) {
            scavenge.activate();
            advanced = 0;
        }
    }
}
