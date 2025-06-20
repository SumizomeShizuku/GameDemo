package org.demo.backpack;

public class DropInfo {

    private final double weight; // 掉落权重（例如：100 表示 100%）
    private final int minQuantity; // 掉落最小数量
    private final int maxQuantity;    // 掉落最大数量

    public DropInfo(double weight, int minQuantity, int maxQuantity) {
        this.weight = weight;
        this.minQuantity = minQuantity;
        this.maxQuantity = maxQuantity;
    }

    public int getMinQuantity() {
        return minQuantity;
    }

    public int getMaxQuantity() {
        return maxQuantity;
    }

    public double getWeight() {
        return weight;
    }

    /**
     * 获取随机掉落数量
     * <p>
     * 如果最大数量小于等于最小数量, 则返回最小数量
     * <p>
     * 否则返回一个介于最小和最大数量之间的随机整数
     *
     * @return 随机掉落数量
     */
    public int getRandomQuantity() {
        if (maxQuantity <= minQuantity) {
            return minQuantity;
        }
        return new java.util.Random().nextInt(maxQuantity - minQuantity + 1) + minQuantity;
    }

    @Override
    public String toString() {
        return "最小数量=" + minQuantity + ", 最大数量=" + maxQuantity + ", 权重=" + weight;
    }
}
