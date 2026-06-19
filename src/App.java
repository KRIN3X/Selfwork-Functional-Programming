import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("=== APPROCCIO IMPERATIVO ===");
        imperativeExample();
        
        System.out.println("\n=== APPROCCIO FUNZIONALE ===");
        functionalExample();
        
        System.out.println("\n=== OPERAZIONI DI STREAM ===");
        streamExample();
        
        System.out.println("\n=== FILTRAGGIO MELE - PASSO 1: BASE ===");
        appleFilteringStep1();
        
        System.out.println("\n=== FILTRAGGIO MELE - PASSO 2: PARAMETRIZZATO ===");
        appleFilteringStep2();
        
        System.out.println("\n=== FILTRAGGIO MELE - PASSO 3: CON PREDICATI ===");
        appleFilteringStep3();
        
        System.out.println("\n=== FILTRAGGIO MELE - PASSO 4: CON LAMBDA ===");
        appleFilteringStep4();
        
        System.out.println("\n=== UTILIZZO DELL'INTERFACCIA PREDICATE JAVA ===");
        javaPredicateExample();
    }
    
    // ============ IMPERATIVO vs FUNZIONALE ============
    
    static void imperativeExample() {
        List<Person> people = List.of(
            new Person("Andrea", Gender.MALE),
            new Person("Maria", Gender.FEMALE),
            new Person("Paola", Gender.FEMALE),
            new Person("Roberto", Gender.MALE),
            new Person("Marco", Gender.MALE)
        );
        
        // Imperativo: dire COME filtrare
        List<Person> females = new ArrayList<>();
        for (Person person : people) {
            if (Gender.FEMALE.equals(person.gender)) {
                females.add(person);
            }
        }
        
        for (Person female : females) {
            System.out.println(female.name);
        }
    }
    
    static void functionalExample() {
        List<Person> people = List.of(
            new Person("Andrea", Gender.MALE),
            new Person("Maria", Gender.FEMALE),
            new Person("Paola", Gender.FEMALE),
            new Person("Roberto", Gender.MALE),
            new Person("Marco", Gender.MALE)
        );
        
        // Funzionale: dire COSA vuoi
        List<Person> females = people.stream()
            .filter(person -> person.gender.equals(Gender.FEMALE))
            .toList();
        
        for (Person female : females) {
            System.out.println(female.name);
        }
    }
    
    // ============ OPERAZIONI DI STREAM ============
    
    static void streamExample() {
        List<Integer> numbers = List.of(2, 5, 6, 7, 8, 9, 12);
        
        Integer sum = numbers.stream()
            .filter(number -> number % 2 == 0)
            .map(number -> number * number)
            .reduce(0, Integer::sum);
        
        System.out.println("Somma dei quadrati dei numeri pari: " + sum);
    }
    
    // ============ EVOLUZIONE DEL FILTRAGGIO MELE ============
    
    static void appleFilteringStep1() {
        List<Apple> apples = List.of(
            new Apple(Color.GREEN),
            new Apple(Color.RED),
            new Apple(Color.GREEN),
            new Apple(Color.RED),
            new Apple(Color.GREEN),
            new Apple(Color.RED),
            new Apple(Color.GREEN),
            new Apple(Color.RED)
        );
        
        List<Apple> greenApples = filterGreenApples(apples);
        System.out.println("Mele verdi: " + greenApples.size());
        for (Apple apple : greenApples) {
            System.out.println(apple.getColor());
        }
    }
    
    public static List<Apple> filterGreenApples(List<Apple> apples) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : apples) {
            if (apple.getColor().equals(Color.GREEN)) {
                result.add(apple);
            }
        }
        return result;
    }
    
    static void appleFilteringStep2() {
        List<Apple> apples = List.of(
            new Apple(Color.GREEN, 120),
            new Apple(Color.RED, 170),
            new Apple(Color.GREEN, 150),
            new Apple(Color.RED, 110),
            new Apple(Color.GREEN, 89),
            new Apple(Color.RED, 75),
            new Apple(Color.GREEN, 180),
            new Apple(Color.RED, 115)
        );
        
        List<Apple> greenApples = filterApples(apples, Color.GREEN);
        System.out.println("Mele verdi: " + greenApples.size());
        
        List<Apple> redApples = filterApples(apples, Color.RED);
        System.out.println("Mele rosse: " + redApples.size());
        
        List<Apple> heavyApples = filterApplesByWeight(apples, 150);
        System.out.println("Mele pesanti (> 150g): " + heavyApples.size());
    }
    
    public static List<Apple> filterApples(List<Apple> apples, Color color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : apples) {
            if (color.equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }
    
    public static List<Apple> filterApplesByWeight(List<Apple> apples, int weight) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : apples) {
            if (apple.getWeight() > weight) {
                result.add(apple);
            }
        }
        return result;
    }
    
    static void appleFilteringStep3() {
        List<Apple> apples = List.of(
            new Apple(Color.GREEN, 120),
            new Apple(Color.RED, 170),
            new Apple(Color.GREEN, 150),
            new Apple(Color.RED, 110),
            new Apple(Color.GREEN, 89),
            new Apple(Color.RED, 75),
            new Apple(Color.GREEN, 180),
            new Apple(Color.RED, 115)
        );
        
        List<Apple> redApples = filterApplesWithPredicate(apples, new AppleRedColorPredicate());
        System.out.println("Mele rosse: " + redApples.size());
        
        List<Apple> greenApples = filterApplesWithPredicate(apples, new AppleGreenColorPredicate());
        System.out.println("Mele verdi: " + greenApples.size());
        
        List<Apple> lightApples = filterApplesWithPredicate(apples, new AppleLightPredicate());
        System.out.println("Mele leggere (<= 150g): " + lightApples.size());
        
        List<Apple> heavyApples = filterApplesWithPredicate(apples, new AppleWeightPredicate());
        System.out.println("Mele pesanti (> 150g): " + heavyApples.size());
        
        List<Apple> redHeavyApples = filterApplesWithPredicate(apples, new AppleRedHeavyPredicate());
        System.out.println("Mele rosse e pesanti: " + redHeavyApples.size());
        
        List<Apple> redLightApples = filterApplesWithPredicate(apples, new AppleRedLightPredicate());
        System.out.println("Mele rosse e leggere: " + redLightApples.size());
    }
    
    public static List<Apple> filterApplesWithPredicate(List<Apple> apples, ApplePredicateInterface predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : apples) {
            if (predicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }
    
    static void appleFilteringStep4() {
        List<Apple> apples = List.of(
            new Apple(Color.GREEN, 120),
            new Apple(Color.RED, 170),
            new Apple(Color.GREEN, 150),
            new Apple(Color.RED, 110),
            new Apple(Color.GREEN, 89),
            new Apple(Color.RED, 75),
            new Apple(Color.GREEN, 180),
            new Apple(Color.RED, 115)
        );
        
        // Definisci le lambda come variabili
        ApplePredicateInterface redApple = (Apple a) -> a.getColor().equals(Color.RED);
        ApplePredicateInterface greenApple = (Apple a) -> a.getColor().equals(Color.GREEN);
        ApplePredicateInterface lightApple = (Apple a) -> a.getWeight() < 150;
        ApplePredicateInterface heavyApple = (Apple a) -> a.getWeight() > 150;
        
        List<Apple> redApples = filterApplesWithPredicate(apples, redApple);
        System.out.println("Mele rosse: " + redApples.size());
        
        List<Apple> greenApples = filterApplesWithPredicate(apples, greenApple);
        System.out.println("Mele verdi: " + greenApples.size());
        
        // O lambda inline
        List<Apple> lightApples = filterApplesWithPredicate(apples, (Apple a) -> a.getWeight() < 150);
        System.out.println("Mele leggere: " + lightApples.size());
        
        List<Apple> heavyApples = filterApplesWithPredicate(apples, (Apple a) -> a.getWeight() > 150);
        System.out.println("Mele pesanti: " + heavyApples.size());
    }
    
    // ============ INTERFACCIA PREDICATE DI JAVA ============
    
    static void javaPredicateExample() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        Predicate<Integer> check = (Integer num) -> num % 2 == 0;
        List<Integer> evens = numbers.stream()
            .filter(check)
            .toList();
        
        System.out.println("Numeri pari: " + evens);
        System.out.println("Conteggio: " + evens.size());
    }
}
