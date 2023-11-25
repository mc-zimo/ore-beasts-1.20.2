package net.zimo.crasher.entity.goal;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.item.crafting.Ingredient;

public class CrasherTemptGoal extends TemptGoal {

    private double speedModifier;

    public CrasherTemptGoal(PathfinderMob pMob, double pSpeedModifier, Ingredient pItems) {
        super(pMob, pSpeedModifier, pItems, false);
        speedModifier = pSpeedModifier;
    }

    public void tick() {
        this.mob.getLookControl().setLookAt(this.player, (float)(this.mob.getMaxHeadYRot() + 20), (float)this.mob.getMaxHeadXRot());
        if (!this.canUse()) {
            this.mob.getNavigation().stop();
        } else {
            this.mob.getNavigation().moveTo(this.player, this.speedModifier);
        }

    }

    @Override
    public boolean canContinueToUse() {

        if (this.mob.distanceToSqr(this.player) > 24.0D) {
            return false;
        }

        return this.canUse();
    }

}
