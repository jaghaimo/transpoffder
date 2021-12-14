package transpoffder;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.LocationAPI;
import com.fs.starfarer.api.campaign.listeners.CurrentLocationChangedListener;
import com.fs.starfarer.api.impl.campaign.ids.Abilities;

public class LocationChangedListener implements CurrentLocationChangedListener {

    @Override
    public void reportCurrentLocationChanged(LocationAPI prev, LocationAPI curr) {
        if (prev == null || curr == null) {
            return;
        }
        if (curr.isHyperspace()) {
            Global.getSector().getPlayerFleet().getAbility(Abilities.TRANSPONDER).deactivate();
        }
    }
}
