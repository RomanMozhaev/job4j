package ru.job4j.srpcalc;

import ru.job4j.calculator.Calculator;

import java.util.ArrayList;
import java.util.function.Supplier;

/**
 * InteractCalc is the calculator which is based on the calculator class from the chapter 001.
 * It has the console menu as an user interface.
 */
public class InteractCalc {
    /**
     * close - the field which is used for program closing detecting.
     * calculator - the instance of the calculator class from chapter 001.
     * operations - the array list with accessible operations.
     * first - the first number for operation.
     * second - the second number for operation.
     * result - the result of the operation with first and second.
     * ADD, SUB, DIV, MUL, EXIT - the constants for operation numeration.
     * SIN, COS, TAN, COTAN - the constants for operation numeration.
     * exit - the field for counting Exit option position. Always last.
     */
    private boolean close;
    private Calculator calculator;
    private ArrayList<IOperation> operations;
    private double first;
    private double second;
    private double result;
    private Input input;
    private static final int ADD = 1;
    private static final int SUB = 2;
    private static final int DIV = 3;
    private static final int MUL = 4;
    private static final int SIN = 5;
    private static final int COS = 6;
    private static final int TAN = 7;
    private static final int COTAN = 8;
    private int exit;

    /**
     * the constructor initializes fields.
     */
    public InteractCalc(Input input) {
        this.close = false;
        this.calculator = new Calculator();
        this.operations = new ArrayList<>();
        this.first = 0;
        this.second = 0;
        this.result = 0;
        this.input = input;
    }

    /**
     * setter for close
     *
     * @param close - close field
     */
    public void setClose(boolean close) {
        this.close = close;
    }

    /**
     * setter for first
     *
     * @param first - first field
     */
    public void setFirst(double first) {
        this.first = first;
    }

    /**
     * setter for second
     *
     * @param second - first field
     */
    public void setSecond(double second) {
        this.second = second;
    }

    /**
     * setter for result
     *
     * @param result - first field
     */
    public void setResult(double result) {
        this.result = result;
    }

    /**
     * getter for first
     *
     * @return - first field
     */
    public double getFirst() {
        return first;
    }

    /**
     * getter for second
     *
     * @return - second field
     */
    public double getSecond() {
        return second;
    }

    /**
     * getter for result
     *
     * @return - result field
     */
    public double getResult() {
        return result;
    }

    /**
     * initializes the accessible operations with their numbers.
     */
    private void initOperations() {
        this.operations.add(new Add("Add", ADD));
        this.operations.add(new Subtrack("Sub-Track", SUB));
        this.operations.add(new Division("deviation", DIV));
        this.operations.add(new Multiple("Multiple", MUL));
    }

    /**
     * initializes the exit operation with the last number.
     */
    private void initExit() {
        this.exit = this.operations.size() + 1;
        this.operations.add(new Exit("Close the program", this.exit));
    }

    /**
     * initializes the trigonometric operations.
     */
    private void initWithTrigonometricOperations() {
        initOperations();
        this.operations.add(new Sin("Sines", SIN));
        this.operations.add(new Cos("Cosines", COS));
        this.operations.add(new Tan("Tangents", TAN));
        this.operations.add(new Cotan("Cotangents", COTAN));
    }

    /**
     * shows menu of the operations with their order numbers.
     */
    private int showMenu() {
        for (IOperation op : operations) {
            System.out.println(op.getNum() + ". " + op.getDesc());
        }
        return stringToNumber(this.input.ask("Please, choose one option:"));
    }

    /**
     * executes the chosen operation.
     * It reads the console entering, compares it with accessible numbers.
     * if the number is suitable than it executes prepareCalc method for getting numbers for operation.
     * if the number is not suitable, than ask a user to enter again.
     */
    private void operate(int number, Supplier<Boolean> prepare) {
        if (number >= 1 && number < this.exit) {
            if (prepare.get()) {
                this.operations.get(number - 1).execute();
                showResult();
            }
        } else if (number == this.exit) {
            this.operations.get(this.exit - 1).execute();
        } else {
            System.out.println("The program cannot identify your enter, please try again");
        }
    }

    /**
     * converts String to Integer
     * @param enter - the enter of the user. it should contain the number of the operation.
     * @return - converted integer or -1.
     */
    private int stringToNumber(String enter) {
        char[] chars = enter.toCharArray();
        StringBuilder num = new StringBuilder();
        for (char ch : chars) {
            if (ch >= '0' && ch <= '9') {
                num.append(ch);
            }
        }
        String string = num.toString();
        return string.equals("") ? -1 : Integer.parseInt(num.toString());

    }

    /**
     * writes in the console the result of the operation.
     */
    private void showResult() {
        System.out.println("Operation result is " + getResult());
    }

    /**
     * prepares to basic operations.
     * it checks if result was calculated before. If it was, than it asks do the user want to use it for
     * the next operation.
     * depends on the condition the method gets values for first and second numbers for operations.
     *
     * @return false if the user wants to exit.
     */
    private Boolean prepareCalc() {
        boolean result = true;
        boolean question = true;
        if (getResult() != 0) {
            while (question) {
                String answer = this.input.ask("Do you want to use the result of the previous operation? (Y/N)");
                char firstCh = answer.toLowerCase().charAt(0);
                if (firstCh == 'y') {
                    setFirst(getResult());
                    if (!readDigits("second")) {
                        this.operations.get(this.exit - 1).execute();
                        result = false;
                    }
                    question = false;
                }
                if (firstCh == 'n') {
                    if (!readDigits("first") || !readDigits("second")) {
                        this.operations.get(this.exit - 1).execute();
                        result = false;
                    }
                    question = false;
                } else {
                    System.out.println("The program cannot identify your answer. Please try again.");
                }
            }
        } else {
            if (!readDigits("first") || !readDigits("second")) {
                this.operations.get(this.exit - 1).execute();
                result = false;
            }
        }
        return result;
    }
    /**
     * prepares to trigonometric operations.
     * it checks if result was calculated before. If it was, than it asks do the user want to use it for
     * the next operation.
     * depends on the condition the method gets values for first and second numbers for operations.
     *
     * @return false if the user wants to exit.
     */
    private Boolean prepareTrigonometric() {
        boolean result = true;
        boolean question = true;
        if (getResult() != 0) {
            while (question) {
                String answer = this.input.ask("Do you want to use the result of the previous operation? (Y/N)");
                char firstCh = answer.toLowerCase().charAt(0);
                if (firstCh == 'y') {
                    setFirst(getResult());
                    question = false;
                }
                if (firstCh == 'n') {
                    if (!readDigits("first")) {
                        this.operations.get(this.exit - 1).execute();
                        result = false;
                    }
                    question = false;
                } else {
                    System.out.println("The program cannot identify your answer. Please try again.");
                }
            }
        } else {
            if (!readDigits("first")) {
                this.operations.get(this.exit - 1).execute();
                result = false;
            }
        }
        return result;
    }

    /**
     * the method reads the user entering and applies the values to first and second numbers
     *
     * @param position first or second
     * @return - false if the user wants to exit.
     */
    private boolean readDigits(String position) {
        boolean result = true;
        Double value = null;
        boolean quit = false;
        while (!quit) {
            String number = this.input.ask("Enter 'q' for exit or " + position + " number: ");
            if (number.toLowerCase().charAt(0) == 'q') {
                this.operations.get(this.exit - 1).execute();
                quit = true;
                result = false;
            } else {
                try {
                    value = Double.parseDouble(number);
                    quit = true;
                } catch (Exception e) {
                    System.out.println("You have entered wrong. Please, try again.");
                }
            }
        }
        if (result) {
            if (position.equals("second")) {
                setSecond(value);
            }
            if (position.equals("first")) {
                setFirst(value);
            }
        }
        return result;
    }

    /**
     * the start method for initialization and execution of the program
     */
    public void start() {
        initOperations();
        initExit();
        do {
            operate(showMenu(), this::prepareCalc);
        } while (!this.close);
    }

    /**
     * starts with ADD option only.
     */
    public void startADD() {
        this.operations.add(new Add("Add", ADD));
        this.exit = this.operations.size() + 1;
        this.operations.add(new Exit("Close the program", this.exit));
        do {
            operate(showMenu(), this::prepareCalc);
        } while (!this.close);
    }

    /**
     * starts with basic options and trigonometric options.
     */
    public void startWithTrigonometric() {
        initWithTrigonometricOperations();
        initExit();
        do {
            int answer = showMenu();
            if (answer >= SIN) {
                operate(answer, this::prepareTrigonometric);
            } else {
                operate(answer, this::prepareCalc);
            }
        } while (!this.close);
    }

    public static void main(String[] args) {
        InteractCalc calc = new InteractCalc(new ConsoleInput());
        calc.startWithTrigonometric();

    }

    /**
     * the class Add for add operation
     */
    public class Add extends Operation {

        public Add(String desc, int num) {
            super(desc, num);
        }

        @Override
        public void execute() {
            setResult(calculator.add(getFirst(), getSecond()));
        }
    }

    /**
     * the class Subtrack for sub track operation
     */
    public class Subtrack extends Operation {

        public Subtrack(String desc, int num) {
            super(desc, num);
        }

        @Override
        public void execute() {
            setResult(calculator.subtrack(getFirst(), getSecond()));
        }
    }

    /**
     * the class Division for division operation
     */
    public class Division extends Operation {

        public Division(String desc, int num) {
            super(desc, num);
        }

        @Override
        public void execute() {
            setResult(calculator.div(getFirst(), getSecond()));
        }
    }

    /**
     * the class Multiple is for multiple operation
     */
    public class Multiple extends Operation {

        public Multiple(String desc, int num) {
            super(desc, num);
        }

        @Override
        public void execute() {
            setResult(calculator.multiple(getFirst(), getSecond()));
        }
    }

    /**
     * the class Exit is for exit operation
     */
    public class Exit extends Operation {

        public Exit(String desc, int num) {
            super(desc, num);
        }

        @Override
        public void execute() {
            setClose(true);
        }
    }

    /**
     * the class Sin is for sines calculation
     */
    public class Sin extends Operation {

        public Sin(String desc, int num) {
            super(desc, num);
        }

        @Override
        public void execute() {
            setResult(Math.sin(getFirst()));
        }
    }

    /**
     * the class Cos is for cosines calculation
     */
    public class Cos extends Operation {

        public Cos(String desc, int num) {
            super(desc, num);
        }

        @Override
        public void execute() {
            setResult(Math.cos(getFirst()));
        }
    }

    /**
     * the class Tan is for tangents calculation
     */
    public class Tan extends Operation {

        public Tan(String desc, int num) {
            super(desc, num);
        }

        @Override
        public void execute() {
            setResult(Math.tan(getFirst()));
        }
    }

    /**
     * the class Cotan is for cotangents calculation
     */
    public class Cotan extends Operation {

        public Cotan(String desc, int num) {
            super(desc, num);
        }

        @Override
        public void execute() {
            setResult(1 / Math.tan(getFirst()));
        }
    }

}


