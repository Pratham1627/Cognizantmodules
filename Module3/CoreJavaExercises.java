import java.io.*;
import java.lang.reflect.*;
import java.net.*;
import java.net.http.*;
import java.sql.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

public class CoreJavaExercises {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        // ex 1
        System.out.println("Hello, World!");

        // ex 2
        System.out.print("Enter first number: ");
        double n1 = Double.parseDouble(sc.nextLine());
        System.out.print("Enter second number: ");
        double n2 = Double.parseDouble(sc.nextLine());
        System.out.print("Enter operator (+, -, *, /): ");
        char op = sc.nextLine().charAt(0);
        if (op == '+') System.out.println("Result: " + (n1 + n2));
        else if (op == '-') System.out.println("Result: " + (n1 - n2));
        else if (op == '*') System.out.println("Result: " + (n1 * n2));
        else if (op == '/') {
            if (n2 == 0) System.out.println("Cannot divide by zero!");
            else System.out.println("Result: " + (n1 / n2));
        }

        // ex 3
        System.out.print("Enter a number: ");
        int num = Integer.parseInt(sc.nextLine());
        if (num % 2 == 0)
            System.out.println(num + " is Even");
        else
            System.out.println(num + " is Odd");

        // ex 4
        System.out.print("Enter year: ");
        int year = Integer.parseInt(sc.nextLine());
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
            System.out.println(year + " is a Leap Year");
        else
            System.out.println(year + " is not a Leap Year");

        // ex 5
        System.out.print("Enter number for multiplication table: ");
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 1; i <= 10; i++) {
            System.out.println(n + " x " + i + " = " + (n * i));
        }

        // ex 6
        int age = 20;
        float marks = 85.5f;
        double salary = 50000.50;
        char grade = 'A';
        boolean isPass = true;
        System.out.println("int: " + age);
        System.out.println("float: " + marks);
        System.out.println("double: " + salary);
        System.out.println("char: " + grade);
        System.out.println("boolean: " + isPass);

        // ex 7
        double d = 9.99;
        int i1 = (int) d;
        System.out.println("double to int: " + i1);
        int x = 5;
        double d2 = (double) x;
        System.out.println("int to double: " + d2);

        // ex 8
        int res1 = 10 + 5 * 2;
        int res2 = (10 + 5) * 2;
        System.out.println("10 + 5 * 2 = " + res1);
        System.out.println("(10 + 5) * 2 = " + res2);

        // ex 9
        System.out.print("Enter marks: ");
        int marks2 = Integer.parseInt(sc.nextLine());
        if (marks2 >= 90) System.out.println("Grade: A");
        else if (marks2 >= 80) System.out.println("Grade: B");
        else if (marks2 >= 70) System.out.println("Grade: C");
        else if (marks2 >= 60) System.out.println("Grade: D");
        else System.out.println("Grade: F");

        // ex 10 - number guessing game
        int secret = new Random().nextInt(100) + 1;
        int guess;
        System.out.println("Guess a number between 1 and 100:");
        do {
            System.out.print("Your guess: ");
            guess = Integer.parseInt(sc.nextLine());
            if (guess < secret) System.out.println("Too low!");
            else if (guess > secret) System.out.println("Too high!");
            else System.out.println("Correct!!");
        } while (guess != secret);

        // ex 11 - factorial
        System.out.print("Enter number for factorial: ");
        int fn = Integer.parseInt(sc.nextLine());
        long fact = 1;
        for (int i = 1; i <= fn; i++) fact *= i;
        System.out.println(fn + "! = " + fact);

        // ex 12 - method overloading
        System.out.println("add(2,3) = " + add(2, 3));
        System.out.println("add(2.5,3.5) = " + add(2.5, 3.5));
        System.out.println("add(1,2,3) = " + add(1, 2, 3));

        // ex 13 - fibonacci
        System.out.print("Enter n for fibonacci: ");
        int fn2 = Integer.parseInt(sc.nextLine());
        System.out.println("Fibonacci(" + fn2 + ") = " + fibonacci(fn2));

        // ex 14 - array sum and average
        System.out.print("Enter size of array: ");
        int size = Integer.parseInt(sc.nextLine());
        int[] arr = new int[size];
        System.out.println("Enter elements in one line (space separated):");
        String[] tokens = sc.nextLine().trim().split(" ");
        for (int i = 0; i < size; i++)
            arr[i] = Integer.parseInt(tokens[i]);
        int sum = 0;
        for (int val : arr) sum += val;
        System.out.println("Sum = " + sum);
        System.out.println("Average = " + (double) sum / size);

        // ex 15 - string reversal
        System.out.print("Enter string to reverse: ");
        String str = sc.nextLine();
        String rev = new StringBuilder(str).reverse().toString();
        System.out.println("Reversed: " + rev);

        // ex 16 - palindrome
        System.out.print("Enter string to check palindrome: ");
        String s = sc.nextLine();
        String clean = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        String revClean = new StringBuilder(clean).reverse().toString();
        if (clean.equals(revClean))
            System.out.println(s + " is a palindrome");
        else
            System.out.println(s + " is not a palindrome");

        // ex 17 - class and object
        Car c1 = new Car("Toyota", "Innova", 2021);
        Car c2 = new Car("Honda", "City", 2023);
        c1.displayDetails();
        c2.displayDetails();

        // ex 18 - inheritance
        Animal a = new Animal();
        Dog dog = new Dog();
        a.makeSound();
        dog.makeSound();

        // ex 19 - interface
        Playable g = new Guitar();
        Playable p = new Piano();
        g.play();
        p.play();

        // ex 20 - try catch
        System.out.print("Enter first number for division: ");
        int num1 = Integer.parseInt(sc.nextLine());
        System.out.print("Enter second number for division: ");
        int num2 = Integer.parseInt(sc.nextLine());
        try {
            System.out.println("Result: " + (num1 / num2));
        } catch (ArithmeticException e) {
            System.out.println("Error: divide by zero");
        }

        // ex 21 - custom exception
        System.out.print("Enter your age: ");
        int userAge = Integer.parseInt(sc.nextLine());
        try {
            if (userAge < 18) throw new InvalidAgeException("Age must be 18 or above!");
            System.out.println("Valid age. Welcome!");
        } catch (InvalidAgeException e) {
            System.out.println("Exception: " + e.getMessage());
        }

        // ex 22 - file writing
        System.out.print("Enter text to write to file: ");
        String txt = sc.nextLine();
        FileWriter fw = new FileWriter("output.txt");
        fw.write(txt);
        fw.close();
        System.out.println("Written to output.txt");

        // ex 23 - file reading
        BufferedReader br = new BufferedReader(new FileReader("output.txt"));
        String line;
        System.out.println("Reading output.txt:");
        while ((line = br.readLine()) != null)
            System.out.println(line);
        br.close();

        // ex 24 - arraylist
        ArrayList<String> names = new ArrayList<>();
        System.out.print("How many names? ");
        int nc = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < nc; i++) {
            System.out.print("Name: ");
            names.add(sc.nextLine());
        }
        System.out.println("Names: " + names);

        // ex 25 - hashmap
        HashMap<Integer, String> map = new HashMap<>();
        System.out.print("How many students? ");
        int mc = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < mc; i++) {
            System.out.print("ID: ");
            int id = Integer.parseInt(sc.nextLine());
            System.out.print("Name: ");
            String nm = sc.nextLine();
            map.put(id, nm);
        }
        System.out.print("Search by ID: ");
        int sid = Integer.parseInt(sc.nextLine());
        System.out.println("Found: " + map.getOrDefault(sid, "Not found"));

        // ex 26 - threads
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 3; i++)
                System.out.println("Thread 1 running - " + i);
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 3; i++)
                System.out.println("Thread 2 running - " + i);
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        // ex 27 - lambda
        List<String> nameList = new ArrayList<>(Arrays.asList("Pratham", "Alice", "Zara", "Bob"));
        Collections.sort(nameList, (a1, b1) -> a1.compareTo(b1));
        System.out.println("Sorted: " + nameList);

        // ex 28 - stream api
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> evens = nums.stream().filter(val -> val % 2 == 0).collect(Collectors.toList());
        System.out.println("Even numbers: " + evens);

        // ex 29 - records
        List<Person> people = List.of(new Person("Alice", 25), new Person("Bob", 16), new Person("Charlie", 30));
        List<Person> adults = people.stream().filter(pr -> pr.age() >= 18).collect(Collectors.toList());
        System.out.println("Adults: " + adults);

        // ex 30 - pattern matching switch
        System.out.println(checkType(42));
        System.out.println(checkType("hello"));
        System.out.println(checkType(3.14));

        // ex 31 to 33 - JDBC (needs MySQL running, update credentials)
        String url = "jdbc:mysql://localhost:3306/event_portal";
        String dbUser = "root";
        String dbPass = "your_password";

        // ex 31 - basic connection
        try {
            Connection conn = DriverManager.getConnection(url, dbUser, dbPass);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Users");
            while (rs.next())
                System.out.println(rs.getInt("user_id") + " " + rs.getString("full_name"));
            conn.close();
        } catch (Exception e) {
            System.out.println("DB Error: " + e.getMessage());
        }

        // ex 32 - insert and update
        try {
            Connection conn = DriverManager.getConnection(url, dbUser, dbPass);
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO Users (full_name, email, city, registration_date) VALUES (?,?,?,?)");
            ps.setString(1, "Test User");
            ps.setString(2, "test@test.com");
            ps.setString(3, "Indore");
            ps.setDate(4, java.sql.Date.valueOf("2025-01-01"));
            ps.executeUpdate();
            System.out.println("Inserted!");

            PreparedStatement ps2 = conn.prepareStatement("UPDATE Users SET city=? WHERE email=?");
            ps2.setString(1, "Bhopal");
            ps2.setString(2, "test@test.com");
            ps2.executeUpdate();
            System.out.println("Updated!");
            conn.close();
        } catch (Exception e) {
            System.out.println("DB Error: " + e.getMessage());
        }

        // ex 33 - transaction
        try {
            Connection conn = DriverManager.getConnection(url, dbUser, dbPass);
            conn.setAutoCommit(false);
            try {
                PreparedStatement debit = conn.prepareStatement("UPDATE accounts SET balance=balance-? WHERE account_id=?");
                debit.setDouble(1, 500);
                debit.setInt(2, 1);
                debit.executeUpdate();

                PreparedStatement credit = conn.prepareStatement("UPDATE accounts SET balance=balance+? WHERE account_id=?");
                credit.setDouble(1, 500);
                credit.setInt(2, 2);
                credit.executeUpdate();

                conn.commit();
                System.out.println("Transfer done!");
            } catch (SQLException e) {
                conn.rollback();
                System.out.println("Transfer failed, rolled back: " + e.getMessage());
            }
            conn.close();
        } catch (Exception e) {
            System.out.println("DB Error: " + e.getMessage());
        }

        // ex 34 - modules note
        System.out.println("Java Modules need separate project structure with module-info.java");
        System.out.println("Compile using: javac --module-source-path src -d mods -m com.greetings");

        // ex 35 - tcp server client
        new Thread(() -> {
            try {
                ServerSocket ss = new ServerSocket(5000);
                Socket cs = ss.accept();
                PrintWriter out2 = new PrintWriter(cs.getOutputStream(), true);
                out2.println("Hello from server!");
                cs.close(); ss.close();
            } catch (IOException e) { System.out.println(e.getMessage()); }
        }).start();

        Thread.sleep(500);
        Socket socket = new Socket("localhost", 5000);
        BufferedReader in2 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println("Server says: " + in2.readLine());
        socket.close();

        // ex 36 - http client
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest req = HttpRequest.newBuilder().uri(URI.create("https://api.github.com")).build();
            HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status: " + resp.statusCode());
        } catch (Exception e) {
            System.out.println("HTTP error: " + e.getMessage());
        }

        // ex 37 - bytecode
        System.out.println("To see bytecode run: javap -c CoreJavaExercises.class");

        // ex 38 - decompile
        System.out.println("To decompile open .class file in JD-GUI tool");

        // ex 39 - reflection
        Class<?> cls = Class.forName("CoreJavaExercises");
        Method[] methods = cls.getDeclaredMethods();
        System.out.println("Methods in this class:");
        for (Method m : methods)
            System.out.println(" - " + m.getName());

        // ex 40 - virtual threads
        for (int i = 0; i < 5; i++) {
            int threadNum = i;
            Thread.ofVirtual().start(() -> System.out.println("Virtual thread: " + threadNum));
        }

        // ex 41 - executor service
        ExecutorService exec = Executors.newFixedThreadPool(3);
        List<Callable<String>> tasks = new ArrayList<>();
        tasks.add(() -> "Task 1 done: " + (10 * 10));
        tasks.add(() -> "Task 2 done: " + (20 * 20));
        tasks.add(() -> "Task 3 done: " + (30 * 30));
        List<Future<String>> results = exec.invokeAll(tasks);
        for (Future<String> f : results)
            System.out.println(f.get());
        exec.shutdown();

        sc.close();
    }

    // ex 12 - overloaded methods
    static int add(int a, int b) { return a + b; }
    static double add(double a, double b) { return a + b; }
    static int add(int a, int b, int c) { return a + b + c; }

    // ex 13 - fibonacci recursive
    static int fibonacci(int n) {
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    // ex 30 - pattern matching
    static String checkType(Object obj) {
        return switch (obj) {
            case Integer i -> "Integer: " + i;
            case String s  -> "String: " + s;
            case Double d  -> "Double: " + d;
            default        -> "Unknown: " + obj;
        };
    }
}

// ex 17 - Car class
class Car {
    String make, model;
    int year;

    Car(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    void displayDetails() {
        System.out.println(year + " " + make + " " + model);
    }
}

// ex 18 - Animal and Dog
class Animal {
    void makeSound() {
        System.out.println("Animal makes a sound");
    }
}

class Dog extends Animal {
    void makeSound() {
        System.out.println("Dog says: Bark!");
    }
}

// ex 19 - Interface
interface Playable {
    void play();
}

class Guitar implements Playable {
    public void play() { System.out.println("Guitar: strum strum"); }
}

class Piano implements Playable {
    public void play() { System.out.println("Piano: tink tink"); }
}

// ex 21 - custom exception
class InvalidAgeException extends Exception {
    InvalidAgeException(String msg) { super(msg); }
}

// ex 29 - record
record Person(String name, int age) {}