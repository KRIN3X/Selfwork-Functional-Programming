public class AppleLightPredicate implements ApplePredicateInterface {
    public boolean test(Apple apple) {
        return apple.getWeight() <= 150;
    }
}
