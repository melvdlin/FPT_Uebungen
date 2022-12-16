package sandbox;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        AbstractFactory factory = new DesktopFactory();
        Button button = factory.createButton();

        button.addOnClickListener(() -> System.out.println("I was clicked"));
        button.addOnClickListener(() -> System.out.println(1));

        Button anotherButton = factory.createButton();
        anotherButton.addOnClickListener(() -> System.out.println("I was also clicked"));

        button.onClick();
        anotherButton.onClick();
    }

}

interface AbstractFactory {
    Button createButton();
    Window createWindow(String title);
}

interface Button {
    void onClick();
    void addOnClickListener(Runnable listener);
}

interface Window {
    void draw();
    String getTitle();
}

class DesktopButton implements Button {

    private List<Runnable> listeners = new ArrayList<>();

    public DesktopButton() {

    }

    @Override
    public void onClick() {
        for (Runnable listener : listeners) {
            listener.run();
        }
    }

    @Override
    public void addOnClickListener(Runnable listener) {
        listeners.add(listener);
    }
}

class DesktopWindow implements Window {

    private String title;

    public DesktopWindow(String windowTitle) {
        this.title = windowTitle;
    }

    @Override
    public void draw() {
        System.out.println("Drawing Desktop Window...");
    }

    @Override
    public String getTitle() {
        return title;
    }
}

class DesktopFactory implements AbstractFactory {

    @Override
    public Button createButton() {
        return new DesktopButton();
    }

    @Override
    public Window createWindow(String title) {
        return new DesktopWindow(title);
    }
}