package com.vsc.context;

import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class Context implements Serializable {

    private static final Logger logger = Logger.getLogger(Context.class);

    private final Map<String, Serializable> attributes;
    public static final String SESSION_ID = "sessionId";
    public static final String TRANSACTION_ID = "transactionId";
    public static final String PARTNER_ID = "partnerId";
    public static final String LOCALE = "locale";

    public Context(String sessionId, String partnerId) {
        attributes = new ConcurrentHashMap<String, Serializable>();
        if (sessionId != null) {
            setAttribute(SESSION_ID, sessionId);
        }
        if (partnerId != null) {
            setAttribute(PARTNER_ID, partnerId);
        }
        String uuid = UUID.randomUUID().toString();
        if (uuid != null) {
            setAttribute(TRANSACTION_ID, uuid);
        }
    }

    public Context(Context parentContext) {
        String sessionId = null;
        String partnerId = null;
        String transactionId = null;

        if (parentContext != null) {
            sessionId = parentContext.getSessionId();
            partnerId = parentContext.getPartnerId();
            transactionId = parentContext.getTransactionId();
        }


        attributes = (null == parentContext) ?
                new ConcurrentHashMap() :
                new ConcurrentHashMap(parentContext.attributes);

        // set attributes on map for this version only
        // TODO: cease attribute setting on version 7.0
        if (sessionId != null) {
            setAttribute(SESSION_ID, sessionId);
        }
        if (partnerId != null) {
            setAttribute(PARTNER_ID, partnerId);
        }
        if (transactionId != null) {
            setAttribute(TRANSACTION_ID, transactionId);
        }
    }

    public String getSessionId() {
        String s = null;
        try {
            s = (String) getAttribute(SESSION_ID, String.class);
        } catch (Exception e) {
            logger.debug(SESSION_ID + " was not available on attribute map", e);
        }
        return s;
    }

    public String getPartnerId() {
        String s = null;
        try {
            s = (String) getAttribute(PARTNER_ID, String.class);
        } catch (Exception e) {
            logger.debug(PARTNER_ID + " was not available on attribute map", e);
        }
        return s;
    }

    public String getTransactionId() {
        String s = null;
        try {
            s = (String) getAttribute(TRANSACTION_ID, String.class);
        } catch (Exception e) {
            logger.debug(TRANSACTION_ID + " was not available on attribute map", e);
        }
        return s;
    }

    public Serializable setAttribute(String key, Serializable value) {
        if (null == key) {
            throw new IllegalArgumentException("Key must not be null");
        }
        Serializable serializable = null;
        if (value != null) {
            serializable = (Serializable) attributes.put(key, value);
        } else {
            final Object o = attributes.remove(key);
            logger.debug("Value was null, removing value [" + o + "] for key [" + key + "]");

        }
        return serializable;
    }

    public Serializable getAttribute(String key, Class clazz) {
        if (null == clazz) {
            throw new IllegalArgumentException("clazz must not be null.");
        }

        Object value = key != null ? attributes.get(key) : null;

        if (value == null) {
            if (!containsAttribute(key)) {
                if (logger.isDebugEnabled()) {
                    logger.debug("The key [" + key + "] does not exist in the Context.");

                }
                return null;
            }
        }

        if (value != null && !clazz.isAssignableFrom(value.getClass())) {
            throw new ClassCastException("Object referenced by key [" + key + "] is of type ["
                    + value.getClass().getName() + "]. Attempted to cast to [" + clazz.getName()
                    + "].");
        }

        return (Serializable) value;
    }

    public boolean containsAttribute(String key) {
        return attributes.containsKey(key);
    }

}
