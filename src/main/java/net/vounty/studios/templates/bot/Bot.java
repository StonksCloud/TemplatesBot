package net.vounty.studios.templates.bot;

public abstract class Bot<C> {

    private C service;

    private final Thread shutdownHook = new Thread(this::terminate);

    public abstract void initialize();
    public abstract void terminate();

    public Thread getShutdownHook() {
        return shutdownHook;
    }

    public C getService() {
        return service;
    }

}
