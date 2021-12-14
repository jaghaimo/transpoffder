package transpoffder;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;

public class TranspoffderMod extends BaseModPlugin {

    @Override
    public void onNewGame() {
        onGameLoad(true);
    }

    @Override
    public void onGameLoad(boolean newGame) {
        Global.getSector().getListenerManager().addListener(new LocationChangedListener(), true);
    }
}
