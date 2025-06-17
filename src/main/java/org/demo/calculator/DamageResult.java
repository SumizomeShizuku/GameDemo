package org.demo.calculator;

public class DamageResult {

    private final int damage;
    private final boolean isCritical;

    public DamageResult(int damage, boolean isCritical) {
        this.damage = damage;
        this.isCritical = isCritical;
    }

    public int getDamage() {
        return damage;
    }

    public boolean isCritical() {
        return isCritical;
    }
}
