import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * TimePrinter class: Implements the Runnable interface to define the clock task
 * that will run in a separate, high-priority thread.
 * This design separates the "what to do" (the task) from the "how to do it" (the thread).
 */
class TimePrinter implements Runnable {

    /**
     * The run() method contains the code that the new thread will execute.
     * It's the entry point for the thread's independent execution flow.
     */
    @Override
    public void run() {
        // Define the format for displaying the date and time.
        // "HH:mm:ss" uses a 24-hour format, and "dd-MM-yyyy" specifies the day, month, and year.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");

        // Print a message to the console to indicate that the clock thread has started.
        System.out.println("Clock Thread started. Press Ctrl-C to stop.");
        System.out.println("----------------------------------------------");

        // An infinite loop to continuously update the clock.
        // The loop will run indefinitely until the thread is interrupted.
        while (true) {
            // Get the current date and time from the system clock.
            // This happens on every loop iteration to ensure the time is always up-to-date.
            LocalDateTime now = LocalDateTime.now();

            // Format the LocalDateTime object into a readable string using the formatter.
            String currentTime = now.format(formatter);

            // Print the formatted time to the console.
            // The "\r" (carriage return) character moves the cursor back to the start of the line,
            // overwriting the previous output and creating a continuous update effect.
            System.out.print("\rCurrent Time and Date: " + currentTime);

            try {
                // Pause the current thread for 1000 milliseconds (1 second).
                // This controls the clock's update frequency and prevents the thread from consuming excessive CPU.
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // This block catches the InterruptedException.
                // It is thrown when another thread (e.g., the main thread after a Ctrl-C)
                // calls interrupt() on this thread while it is sleeping.
                System.out.println("\nClock Thread interrupted and stopped.");

                // Re-interrupt the thread. This is a best practice in concurrency
                // to restore the thread's interrupted status, allowing higher-level
                // interrupt handlers to take action if needed.
                Thread.currentThread().interrupt();

                // Break out of the infinite loop to terminate the thread's execution gracefully.
                break;
            }
        }
    }
}

/**
 * DummyTask class: A simple Runnable task to simulate a low-priority background process.
 * It's used here to demonstrate the effect of thread priorities.
 */
class DummyTask implements Runnable {
    @Override
    public void run() {
        // A loop that runs a set number of times.
        for (int i = 0; i < 50; i++) {
            // Simulates a time-consuming background operation.
            // By making the thread sleep, it yields CPU time to other threads.
            try {
                Thread.sleep(200);
            } catch (InterruptedException ignored) {
                // The 'ignored' variable name indicates that this interruption
                // is not critical to the task's logic. We still handle it by
                // setting the flag and exiting the run method.
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}

/**
 * Clock Main Class: The entry point of the program.
 * It's responsible for creating and managing the lifecycle of other threads.
 */
public class Clock {

    public static void main(String[] args) {

        // --- A. Define and Start the High-Priority Display Thread ---

        // Create an instance of the Runnable task for the clock.
        TimePrinter timePrinterTask = new TimePrinter();

        // Create a new Thread object and assign it the clock task.
        // We also give the thread a descriptive name for easy identification.
        Thread clockThread = new Thread(timePrinterTask, "ClockDisplayThread");

        // Set the clock thread's priority to the maximum (10).
        // This is a hint to the operating system's scheduler to give this thread preferential treatment,
        // which helps ensure the clock updates with high precision.
        clockThread.setPriority(Thread.MAX_PRIORITY);

        // Start the thread. This tells the JVM to allocate system resources and
        // begin executing the run() method of the TimePrinter task concurrently.
        clockThread.start();

        // --- B. Define and Start the Low-Priority Background Thread ---

        // Create an instance of the low-priority background task.
        DummyTask dummyTask = new DummyTask();

        // Create a new Thread object for the background task.
        Thread backgroundThread = new Thread(dummyTask, "BackgroundWorkerThread");

        // Set the background thread's priority to the minimum (1).
        // This indicates that the task is not critical and should only run when
        // higher-priority threads are idle.
        backgroundThread.setPriority(Thread.MIN_PRIORITY);

        // Start the background worker thread.
        backgroundThread.start();

        // 5. Main thread output (Priority reporting)
        // These print statements run immediately after the other threads are started.
        // They show that the main thread is not blocked and that all three threads
        // are running concurrently.
        System.out.println("\nMain thread continues its work (Priority: " +
                Thread.currentThread().getPriority() + ")");
        System.out.println("Clock Display Thread Priority: " + clockThread.getPriority());
        System.out.println("Background Worker Thread Priority: " + backgroundThread.getPriority());
    }
}