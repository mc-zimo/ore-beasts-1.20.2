package net.zimo.crasher.entity.crasher;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.Tags;
import net.zimo.crasher.entity.EntityManager;
import net.zimo.crasher.entity.goal.CrasherAttackPlayersGoal;
import net.zimo.crasher.entity.goal.CrasherTemptGoal;
import net.zimo.crasher.entity.goal.TryFindOreGoal;

public class CrasherEntity extends Animal implements VariantHolder<CrasherVariant>{

    private static final EntityDataAccessor<Integer> DATA_ID_TYPE_VARIANT = SynchedEntityData.defineId(CrasherEntity.class, EntityDataSerializers.INT);

    public CrasherEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE_VARIANT, 11);
    }

    @Override
    protected void registerGoals(){
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.5, true));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.2F));
        this.goalSelector.addGoal(3, new CrasherTemptGoal(this, 1.2D, Ingredient.of(Tags.Items.ORES)));
        this.goalSelector.addGoal(4, new TryFindOreGoal(this, 1.2D, 24));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.2F));
        this.targetSelector.addGoal(0, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(1, new CrasherAttackPlayersGoal(this));
        this.targetSelector.addGoal(2, new ResetUniversalAngerTargetGoal(this, false));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0D)
                .add(Attributes.FOLLOW_RANGE, 24.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.2F)
                .add(Attributes.ATTACK_DAMAGE, 2.0D)
                .add(Attributes.ATTACK_KNOCKBACK, 0.5F);
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return EntityManager.Crasher.get().create(pLevel);
    }

    @Override
    public boolean isFood(ItemStack pStack){
        return pStack.is(Tags.Items.ORES);
    }

    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pHand);
        if (itemStack.is(Tags.Items.ORES)) {
            this.setVariant(CrasherVariant.byItem(itemStack));
            return InteractionResult.SUCCESS;
        } else {
            return super.mobInteract(pPlayer, pHand);
        }
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("Variant", this.getTypeVariant());
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.entityData.set(DATA_ID_TYPE_VARIANT, pCompound.getInt("Variant"));
    }

    public int getTypeVariant() {
        return this.entityData.get(DATA_ID_TYPE_VARIANT);
    }

    @Override
    public CrasherVariant getVariant() {
        return CrasherVariant.byId(getTypeVariant());
    }

    @Override
    public void setVariant(CrasherVariant pCrasherVariant){
        this.entityData.set(DATA_ID_TYPE_VARIANT, pCrasherVariant.getId());
    }

    public void setTypeVariant(int pTypeVariant){
        this.entityData.set(DATA_ID_TYPE_VARIANT, pTypeVariant);
    }
}
