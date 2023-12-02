package net.zimo.crasher.entity.crasher;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.Tags;
import net.zimo.crasher.entity.EntityManager;
import net.zimo.crasher.entity.goal.CrasherAttackGoal;
import net.zimo.crasher.entity.goal.CrasherTargetAttackablePlayersGoal;
import net.zimo.crasher.entity.goal.CrasherTemptGoal;
import org.jetbrains.annotations.Nullable;

public class CrasherEntity extends Animal implements VariantHolder<CrasherVariant>{

    private static final EntityDataAccessor<Integer> DATA_ID_TYPE_VARIANT = SynchedEntityData.defineId(CrasherEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> ATTACKING = SynchedEntityData.defineId(CrasherEntity.class, EntityDataSerializers.BOOLEAN);
    public final AnimationState idleAnimationState = new AnimationState();
    public int idleAnimationTimeout = 0;
    public final AnimationState walkAnimationState = new AnimationState();
    public final AnimationState gallopAnimationState = new AnimationState();
    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;

    public CrasherEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    public void tick(){
        super.tick();
        if(this.level().isClientSide){
            setupAnimationStates();
        }
    }

    private void setupAnimationStates(){
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }

        if(this.isAttacking() && attackAnimationTimeout <= 0) {
            attackAnimationTimeout = 80; // Length in ticks of your animation
            attackAnimationState.start(this.tickCount);
        } else {
            --this.attackAnimationTimeout;
        }

        if(!this.isAttacking()) {
            attackAnimationState.stop();
        }

        if(this.isPathFinding()){
            if(this.getSpeed()<0.4){
                this.walkAnimationState.start(this.tickCount);
            }
            else{
                this.gallopAnimationState.start(this.tickCount);
            }

        }
        else{
            this.walkAnimationState.stop();
            this.gallopAnimationState.stop();
        }

    }




    public void setAttacking(boolean attacking) {
        this.entityData.set(ATTACKING, attacking);
    }

    public boolean isAttacking() {
        return this.entityData.get(ATTACKING);
    }

    @Override
    protected void updateWalkAnimation(float pPartialTick) {
        float f;
        if (this.getPose() == Pose.STANDING && !this.gallopAnimationState.isStarted()) {
            f = Math.min(pPartialTick * 6.0F, 1.0F);
        } else {
            f = 0.0F;
        }

        this.walkAnimation.update(f, 0.2F);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE_VARIANT, 11);
        this.entityData.define(ATTACKING, false);
    }

    @Override
    protected void registerGoals(){
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new CrasherAttackGoal(this, 1.4f, true));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.2F));
        this.goalSelector.addGoal(3, new CrasherTemptGoal(this, 1.2D, Ingredient.of(Tags.Items.ORES)));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0F));
        this.targetSelector.addGoal(0, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(1, new CrasherTargetAttackablePlayersGoal(this));
        this.targetSelector.addGoal(2, new ResetUniversalAngerTargetGoal(this, false));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.FOLLOW_RANGE, 24.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.3F)
                .add(Attributes.ATTACK_DAMAGE, 4.0D)
                .add(Attributes.ATTACK_KNOCKBACK, 1F);
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

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.HOGLIN_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.HOGLIN_DEATH;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.HOGLIN_HURT;
    }

}
