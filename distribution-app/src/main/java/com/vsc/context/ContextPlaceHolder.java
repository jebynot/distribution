package com.vsc.context;

public class ContextPlaceHolder {

    private final static ContextPlaceHolder contextPlaceHolder = new ContextPlaceHolder();
    private static final ThreadLocal contextThreadLocal = new InheritableThreadLocal() {
        protected Object childValue(Object parentValue) {
            return new Context((Context) parentValue);
        }
    };

    private ContextPlaceHolder() {}

    public static void setContext(final Context context) {
        contextThreadLocal.set(context);
    }

    public static Context getContext() {
        return (Context) contextThreadLocal.get();
    }

    public static void removeContext() {
        contextThreadLocal.set(null);
    }

    public static boolean hasContext() {
        return (contextThreadLocal.get() != null);
    }
}
