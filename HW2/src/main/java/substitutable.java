/** A candidate product that can be evaluated as a substitute. */
public interface Substitutable {

    /** Determines whether this candidate can replace the original product.
     * @param original product to replace
     * @return whether this candidate is an acceptable replacement
     */
    boolean isAcceptableSubstituteFor(Product original);
}
