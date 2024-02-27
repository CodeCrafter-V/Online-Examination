import java.util.Scanner;

public class OnlineExam {
    private String userName;
    private String password;
    private boolean isLoggedIn;
    private int timeRemaining;
    private int questionCount;
    private int[] userAnswers;
    private int[] correctAnswers;

    public OnlineExam(String userName, String password) {
        this.userName = userName;
        this.password = password;
        System.out.println("You are registered Successfully... :)");
        this.isLoggedIn = false;
        this.timeRemaining = 10;
        this.questionCount = 10;
        this.userAnswers = new int[questionCount];
        this.correctAnswers = new int[questionCount];

        for (int i = 0; i < questionCount; i++) {
            correctAnswers[i] = (int) Math.round(Math.random());
        }
    }

    public void login() {
        System.out.println(" Login to give exam: ");
        Scanner sc = new Scanner(System.in);
        System.out.print("User Name: ");
        String inputUserName = sc.nextLine();
        System.out.print("Password: ");
        String inputPassword = sc.nextLine();

        if(inputUserName.equals(userName) && inputPassword.equals(password)) {
            isLoggedIn = true;
            System.out.println("Login Successful... Best of Luck :)");
        }
        else {
            System.out.println("Login Failed... Please try again :(");
        }
    }

    public void logout() {
        isLoggedIn = false;
        System.out.println("Logout Successful.");
    }

    public void startExam() {
        if(!isLoggedIn) {
            System.out.println("Please login first.");
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("You have " + timeRemaining + " minutes to complete the exam.");
        for (int i = 0; i<questionCount ; i++) {
            System.out.println("Question " + (i+1) + ": ");
            System.out.println("1. Option 1");
            System.out.println("2. Option 2");
            System.out.println("Your answer (1 or 2): ");
            int answer = sc.nextInt();
            userAnswers[i] = answer;
        }

        System.out.println("\nWant to Submit? \n1: Yes \n2. No");
        int n = sc.nextInt();
        if(n==1){
            submitExam();
        }
        else {
            try {
                Thread.sleep(timeRemaining * 10 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                submitExam();
            }
        }
    }

    public void submitExam() {
        if(!isLoggedIn) {
            System.out.println("First Login!!!");
            return;
        }

        int score = 0;
        for (int i = 0; i < questionCount; i++) {
            if(userAnswers[i] == correctAnswers[i]) {
                score++;
            }
        }
        System.out.println("\nYour score is " + score + " out of " + questionCount + ".");
        System.out.println("\nBest of luck :)\n");
        logout();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your user name and password: ");
        String name = sc.nextLine();
        String password = sc.nextLine();
        OnlineExam exam = new OnlineExam(name, password);
        exam.login();
        exam.startExam();
    }
}
