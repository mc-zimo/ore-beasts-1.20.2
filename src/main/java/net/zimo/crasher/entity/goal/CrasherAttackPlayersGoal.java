package net.zimo.crasher.entity.goal;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.Tags;
import net.zimo.crasher.entity.crasher.CrasherEntity;

import java.util.function.Predicate;

public class CrasherAttackPlayersGoal extends NearestAttackableTargetGoal<Player> {
    public CrasherAttackPlayersGoal(Mob pMob) {
        super(pMob, Player.class, 20, false, true, (Predicate<LivingEntity>)null);
    }

    public boolean canUse() {
        if (this.mob.isBaby()) {
            return false;
        } else {
            if (super.canUse()) {
                for(CrasherEntity crasher : this.mob.level().getEntitiesOfClass(CrasherEntity.class, this.mob.getBoundingBox().inflate(8.0D, 4.0D, 8.0D))) {
                    if (crasher.isBaby()) {
                        return true;
                    }
                }
                if(this.target.getMainHandItem().is(Tags.Items.ORES)){
                    return true;
                }
            }
            return false;
        }
    }

    protected double getFollowDistance() {
        return super.getFollowDistance() * 0.5D;
    }
}