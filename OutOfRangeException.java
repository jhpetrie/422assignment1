class OutOfRangeException extends Exception {
    public OutOfRangeException() {
        super("Age must be between 1 and 20 years.");
    }
}