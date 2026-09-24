import java.util.HashMap;
import java.util.Map;

public class LoyaltyMember {

    private final String membershipId;
    private final Name name;
    private final String email;
    private final PointsBalance pointsBalance;

    /*
     * Stores existing loyalty members.
     * Membership ID is unique and is therefore used as the key.
     */
    private static final Map<String, LoyaltyMember> MEMBERS =
            new HashMap<>();

    public LoyaltyMember(String membershipId,
                         Name name,
                         String email,
                         PointsBalance pointsBalance) {

        if (membershipId == null || membershipId.length() != 12) {
            throw new IllegalArgumentException(
                    "Membership ID must be exactly 12 characters."
            );
        }

        if (name == null) {
            throw new IllegalArgumentException(
                    "Name cannot be null."
            );
        }

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException(
                    "Email cannot be empty."
            );
        }

        if (pointsBalance == null) {
            throw new IllegalArgumentException(
                    "Points balance cannot be null."
            );
        }

        if (MEMBERS.containsKey(membershipId)) {
            throw new IllegalArgumentException(
                    "Membership ID must be unique."
            );
        }

        this.membershipId = membershipId;
        this.name = name;
        this.email = email;
        this.pointsBalance = pointsBalance;

        MEMBERS.put(membershipId, this);
    }

    public String getMembershipId() {
        return membershipId;
    }

    public Name getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public PointsBalance getPointsBalance() {
        return pointsBalance;
    }

    public void transferPoints(Deposit deposit) {

        if (deposit == null) {
            throw new IllegalArgumentException(
                    "Deposit cannot be null."
            );
        }

        LoyaltyMember recipient =
                MEMBERS.get(deposit.getRecipientId());

        if (recipient == null) {
            throw new IllegalArgumentException(
                    "Recipient does not exist."
            );
        }

        if (!recipient.getName().equals(deposit.getRecipientName())) {
            throw new IllegalArgumentException(
                    "Recipient ID does not match recipient name."
            );
        }

        if (recipient == this) {
            throw new IllegalArgumentException(
                    "A member cannot transfer points to themselves."
            );
        }

        int amount = deposit.getAmount();

        if (pointsBalance.getTotalPoints() < amount) {
            throw new IllegalArgumentException(
                    "Insufficient points."
            );
        }

        // Only modify the balances after all validation succeeds.
        pointsBalance.subtractPoints(amount);
        recipient.getPointsBalance().addTransferredPoints(amount);
    }

    /*
     * Used to isolate unit tests because MEMBERS is static.
     */
    public static void clearMembers() {
        MEMBERS.clear();
    }
}