package transpoffder;

import com.fs.starfarer.api.EveryFrameScript;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI.SurveyLevel;
import com.fs.starfarer.api.util.Misc;
import lombok.extern.log4j.Log4j;

@Log4j
public class PartialSurveyScript implements EveryFrameScript {

    @Override
    public void advance(float amount) {
        CampaignFleetAPI fleet = Global.getSector().getPlayerFleet();
        StarSystemAPI system = fleet.getStarSystem();
        if (system == null) {
            return;
        }
        for (PlanetAPI planet : system.getPlanets()) {
            if (!isInRange(fleet, planet)) {
                log.debug("Skipping " + planet.getName() + " as it is too far away");
                continue;
            }
            MarketAPI market = planet.getMarket();
            if (!canScan(market)) {
                log.debug("Skipping " + planet.getName() + " as it already has info");
                continue;
            }
            log.info("Setting survey level to preliminary for " + planet.getName());
            market.setSurveyLevel(SurveyLevel.PRELIMINARY);
        }
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public boolean runWhilePaused() {
        return false;
    }

    private boolean canScan(MarketAPI market) {
        if (market == null) {
            return false;
        }
        SurveyLevel level = market.getSurveyLevel();
        return level == SurveyLevel.NONE || level == SurveyLevel.SEEN;
    }

    private boolean isInRange(CampaignFleetAPI fleet, SectorEntityToken token) {
        return Misc.getDistance(fleet, token) < fleet.getSensorStrength();
    }
}
