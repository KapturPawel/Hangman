package hangman;

public class ErrorsObserver extends Observer {

    public ErrorsObserver(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void update() {
        subject.setState(subject.getState() - 1);
    }
}
