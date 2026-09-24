public class PointsBalance {

    private int totalPoints;
    private int pointsEarnedThisYear;
    private int pointsExpiringThisYear;

    public PointsBalance(int totalPoints,
                         int pointsEarnedThisYear,
                         int pointsExpiringThisYear) {

        if (totalPoints < 0
                || pointsEarnedThisYear < 0
                || pointsExpiringThisYear < 0) {
            throw new IllegalArgumentException(
                    "Points values cannot be negative."
            );
        }

        this.totalPoints = totalPoints;
        this.pointsEarnedThisYear = pointsEarnedThisYear;
        this.pointsExpiringThisYear = pointsExpiringThisYear;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public int getPointsEarnedThisYear() {
        return pointsEarnedThisYear;
    }

    public int getPointsExpiringThisYear() {
        return pointsExpiringThisYear;
    }

    public void addTransferredPoints(int amount) {
        totalPoints += amount;
        pointsEarnedThisYear += amount;
        pointsExpiringThisYear += amount;
    }

    public void subtractPoints(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException(
                    "Amount cannot be negative."
            );
        }

        if (amount > totalPoints) {
            throw new IllegalArgumentException(
                    "Insufficient points."
            );
        }

        totalPoints -= amount;
    }
}