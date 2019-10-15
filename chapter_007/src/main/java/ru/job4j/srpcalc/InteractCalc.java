package ru.job4j.srpcalc;

import ru.job4j.calculator.Calculator;

import java.util.ArrayList;

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
     * EXIT must be always last.
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
    private static final int EXIT = 5;

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
        this.operations.add(new Exit("Close the program", EXIT));
    }

    /**
     * shows menu of the operations with their order numbers.
     */
    private String showMenu() {
        for (IOperation op : operations) {
            System.out.println(op.getNum() + ". " + op.getDesc());
        }
        return this.input.ask("Please, choose one option:");
    }

    /**
     * executes the chosen operation.
     * It reads the console entering, compares it with accessible numbers.
     * if the number is suitable than it executes prepareCalc method for getting numbers for operation.
     * if the number is not suitable, than ask a user to enter again.
     */
    private void operate(String enter) {
        String choose = enter.substring(0, 1);
        char number = choose.charAt(0);
        char quit = Integer.toString(EXIT).charAt(0);
        if (number >= '1' && number < quit) {
            if (prepareCalc()) {
                this.operations.get(Integer.parseInt(choose) - 1).execute();
                showResult();
            }
        } else if (number == quit) {
            this.operations.get(EXIT - 1).execute();
        } else {
            System.out.println("The program cannot identify your enter, please try again");
        }
    }

    /**
     * writes in the console the result of the operation.
     */
    private void showResult() {
        System.out.println("Operation result is " + getResult());
    }

    /**
     * prepares to operations.
     * it checks if result was calculated before. If it was, than it asks do the user want to use it for
     * the next operation.
     * depends on the condition the method gets values for first and second numbers for operations.
     *
     * @return false if the user wants to exit.
     */
    private boolean prepareCalc() {
        boolean result = true;
        boolean question = true;
        if (getResult() != 0) {
            while (question) {
                String answer = this.input.ask("Do you want to use the result of the previous operation? (Y/N)");
                char firstCh = answer.toLowerCase().charAt(0);
                if (firstCh == 'y') {
                    setFirst(getResult());
                    if (!readDigits("second")) {
                        this.operations.get(EXIT - 1).execute();
                        result = false;
                    }
                    question = false;
                }
                if (answer.charAt(0) == 'n') {
                    if (!readDigits("first") || !readDigits("second")) {
                        this.operations.get(EXIT - 1).execute();
                        result = false;
                    }
                    question = false;
                } else {
                    System.out.println("The program cannot identify your answer. Please try again.");
                }
            }
        } else {
            if (!readDigits("first") || !readDigits("second")) {
                this.operations.get(EXIT - 1).execute();
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
                this.operations.get(EXIT - 1).execute();
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
        do {
            operate(showMenu());
        } while (!this.close);
    }

    public static void main(String[] args) {
        InteractCalc calc = new InteractCalc(new ConsoleInput());
        calc.start();

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

}


