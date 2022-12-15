package constant;

public enum WinningStandard {
    FIFTH_PRIZE("3", "5,000원", false),
    FOURTH_PRIZE("4", "50,000원", false),
    THIRD_PRIZE("5", "1,500,000원", false),
    SECOND_PRIZE("5", "30,000,000원", true),
    FIRST_PRIZE("6", "2,000,000,000원", false);

    private final String sameNumberCount;
    private final String winningAmount;
    private final boolean hasBonus;

    WinningStandard(String sameNumberCount, String winningAmount, boolean hasBonus) {
        this.sameNumberCount = sameNumberCount;
        this.winningAmount = winningAmount;
        this.hasBonus = hasBonus;
    }

    public String getSameNumber() {
        return this.sameNumberCount;
    }

    public String getAmount() {
        return this.winningAmount;
    }
}
