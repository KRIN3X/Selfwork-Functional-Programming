public class AppleGreenColorPredicate implements ApplePredicateInterface {
    public boolean test(Apple apple) {
        return Color.GREEN.equals(apple.getColor());
    }
}
