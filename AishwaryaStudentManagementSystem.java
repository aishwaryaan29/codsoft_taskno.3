import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String name;
    private int rollNumber;
    private String grade;
    private int age;

    public Student(String name, int rollNumber, String grade, int age) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
        this.age = age;
    }

    // Getter methods
    public int getRollNumber() {
        return rollNumber;
    }

    public String getName() {
        return name;
    }

    public String getGrade() {
        return grade;
    }

    public int getAge() {
        return age;
    }

    // Setter methods
    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", rollNumber=" + rollNumber +
                ", grade='" + grade + '\'' +
                ", age=" + age +
                '}';
    }
}

class StudentManagementSystem {
    private ArrayList<Student> studentList = new ArrayList<>();

    public void addStudent(Student student) {
        studentList.add(student);
    }

    public Student searchStudent(int rollNumber) {
        for (Student student : studentList) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }

    public void displayStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No students to display.\n");
            return;
        }

        System.out.println("List of Students:");
        for (Student student : studentList) {
            System.out.println(student);
        }
        System.out.println();
    }
}

public class AishwaryaStudentManagementSystem {
    private static StudentManagementSystem studentManagementSystem = new StudentManagementSystem();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Student Management System");
            System.out.println("1. Add a new student");
            System.out.println("2. Edit student information");
            System.out.println("3. Search for a student");
            System.out.println("4. Display all students");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = getValidInput(1, 5);
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addNewStudent();
                    break;
                case 2:
                    editStudentInformation();
                    break;
                case 3:
                    searchForStudent();
                    break;
                case 4:
                    displayAllStudents();
                    break;
                case 5:
                    exitApplication();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.\n");
            }
        }
    }

    private static void addNewStudent() {
        System.out.print("Enter student name: ");
        String name = getNonEmptyInput();

        System.out.print("Enter student roll number: ");
        int rollNumber = getPositiveIntegerInput();

        System.out.print("Enter student grade: ");
        String grade = getNonEmptyInput();

        System.out.print("Enter student age: ");
        int age = getPositiveIntegerInput();

        studentManagementSystem.addStudent(new Student(name, rollNumber, grade, age));
        System.out.println("Student added successfully!\n");
    }

    private static void editStudentInformation() {
        System.out.print("Enter the roll number of the student to edit: ");
        int rollNumber = getPositiveIntegerInput();
        scanner.nextLine(); // Consume the newline character

        Student student = studentManagementSystem.searchStudent(rollNumber);

        if (student != null) {
            System.out.println("Current information for student with roll number " + rollNumber + ":");
            System.out.println(student);

            System.out.print("Enter new student name (or press Enter to keep the current name): ");
            String newName = scanner.nextLine();

            System.out.print("Enter new student grade (or press Enter to keep the current grade): ");
            String newGrade = scanner.nextLine();

            System.out.print("Enter new student age (or press Enter to keep the current age): ");
            String ageInput = scanner.nextLine();

            // Check if the user entered a new age, otherwise keep the current age
            int newAge = ageInput.isEmpty() ? student.getAge() : getPositiveIntegerInput();

            // Update the student information
            student.setName(newName.isEmpty() ? student.getName() : newName);
            student.setGrade(newGrade.isEmpty() ? student.getGrade() : newGrade);
            student.setAge(newAge);

            System.out.println("Student information updated successfully!\n");
        } else {
            System.out.println("Student with the specified roll number not found.\n");
        }
    }

    private static void searchForStudent() {
        System.out.print("Enter the roll number of the student to search for: ");
        int rollNumber = getPositiveIntegerInput();
        scanner.nextLine(); // Consume the newline character

        Student student = studentManagementSystem.searchStudent(rollNumber);

        if (student != null) {
            System.out.println("Student found:\n" + student + "\n");
        } else {
            System.out.println("Student with the specified roll number not found.\n");
        }
    }

    private static void displayAllStudents() {
        studentManagementSystem.displayStudents();
    }

    private static void exitApplication() {
        System.out.println("Exiting the application. Goodbye!");
        System.exit(0);
    }

    private static String getNonEmptyInput() {
        String input;
        do {
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Please enter a non-empty value.");
            }
        } while (input.isEmpty());
        return input;
    }

    private static int getPositiveIntegerInput() {
        int input;
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid integer.");
                scanner.next(); // Consume the invalid input
            }
            input = scanner.nextInt();
            if (input <= 0) {
                System.out.println("Please enter a positive integer.");
            }
        } while (input <= 0);
        return input;
    }

    private static int getValidInput(int min, int max) {
        int input;
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid integer.");
                scanner.next(); // Consume the invalid input
            }
            input = scanner.nextInt();
            if (input < min || input > max) {
                System.out.println("Please enter a valid option between " + min + " and " + max + ".");
            }
        } while (input < min || input > max);
        return input;
    }
}
