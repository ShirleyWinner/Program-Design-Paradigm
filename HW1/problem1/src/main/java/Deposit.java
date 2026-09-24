public class Deposit {

    private final int amount;
    private final String recipientId;
    private final Name recipientName;

    public Deposit(int amount,
                   String recipientId,
                   Name recipientName) {

        if (amount < 1000 || amount > 10000) {
            throw new IllegalArgumentException(
                    "Deposit amount must be between 1000 and 10000 points."
            );
        }

        if (recipientId == null || recipientId.length() != 12) {
            throw new IllegalArgumentException(
                    "Recipient membership ID must be 12 characters."
            );
        }

        if (recipientName == null) {
            throw new IllegalArgumentException(
                    "Recipient name cannot be null."
            );
        }

        this.amount = amount;
        this.recipientId = recipientId;
        this.recipientName = recipientName;
    }

    public int getAmount() {
        return amount;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public Name getRecipientName() {
        return recipientName;
    }
}