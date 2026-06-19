public class AppleRedColorPredicate implements ApplePredicateInterface {
    @Override
    public boolean test(Apple apple) {
        return Color.RED.equals(apple.getColor());
    }
}
