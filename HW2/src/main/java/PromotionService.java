/** External service supplying today's product discounts. */
public interface PromotionService {
    /** Returns a discount as a fraction: 0.10 means 10 percent.
     * @param product product being priced
     * @return discount between 0 and 1 inclusive
     */
    double getDiscountPercentage(Product product);
}
