public class AppleWeightPredicate implements ApplePredicateInterface {
    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 150;
    }
}
