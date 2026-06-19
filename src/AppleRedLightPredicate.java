public class AppleRedLightPredicate implements ApplePredicateInterface {
    @Override
    public boolean test(Apple apple) {
        return Color.RED.equals(apple.getColor()) && apple.getWeight() < 150;
    }
}
