package com.vsc.logging;

import com.vsc.context.Context;
import com.vsc.context.ContextPlaceHolder;
import org.apache.log4j.Layout;
import org.apache.log4j.spi.LocationInfo;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.ThrowableInformation;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class VSCLogLayout extends Layout {
    private static String machineName;
    protected boolean includeLineNumbers = true;
    private static String TRANSCATION_ID = "transcationId";
    protected static final String LINE_SEP = System.getProperty("line.separator");

    static {
        if (machineName == null) {
            try {
                machineName = InetAddress.getLocalHost().getHostName();
            } catch (UnknownHostException e) {
                System.err.println(" failed to get machine name.");
                e.printStackTrace();
            }
        }
    }


    public String format(LoggingEvent loggingEvent) {
        StringBuffer sb = new StringBuffer();
        String dateTime = this.buildTimestampField(loggingEvent);
        String priority = this.buildPriorityField(loggingEvent);
        String threadId = this.buildThreadIdField(loggingEvent);
        String classLineInfo = this.buildClassNameField(loggingEvent);
        String encodedMsg = this.buildMessageField(loggingEvent);
        Context context = ContextPlaceHolder.getContext();
        String transcationId = "";
        String partnerId = "";
        String sessionId = "";
        if (context != null) {
            transcationId = context.getTransactionId();
            partnerId = context.getPartnerId();
            sessionId = context.getSessionId();
        }

        sb.append(dateTime).append('|');
        sb.append(priority).append('|');
        sb.append(machineName).append('|');
        sb.append(partnerId).append('|');
        sb.append(sessionId).append('|');
        sb.append(transcationId).append('|');
        sb.append(threadId).append('|');
        sb.append(classLineInfo).append('|');
        sb.append(encodedMsg).append(LINE_SEP);
        return sb.toString();
    }

    public void activateOptions() {

    }

    public boolean ignoresThrowable() {
        return false;
    }

    protected String buildThreadIdField(LoggingEvent loggingEvent) {
        String name = (loggingEvent == null) ? Thread.currentThread().getName() : loggingEvent.getThreadName();
        // generate threadID
        return Integer.toHexString(name.hashCode());
    }

    protected String buildMessageField(LoggingEvent loggingEvent) {
        if (loggingEvent == null) {
            return "null";
        }

        StringBuffer msg = new StringBuffer();

        // build message field
        String eventMsg = loggingEvent.getRenderedMessage();
        if (eventMsg != null) {
            msg.append(eventMsg);
        }

        ThrowableInformation ti = loggingEvent.getThrowableInformation();
        if (ti != null) {
            String[] throwableStrings = ti.getThrowableStrRep();
            for (int i=0; i < throwableStrings.length; i++) {
                msg.append(LINE_SEP).append(throwableStrings[i]);
            }
        }

        String encodedMsg = null;
        try {
            encodedMsg = URLEncoder.encode(msg.toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
        }

        return encodedMsg;
    }

    protected String buildTimestampField(LoggingEvent loggingEvent) {
        if (loggingEvent == null) {
            return "";
        }

        // date-time stamp
        String dateTime = "?";
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss.SSS");
            dateTime = dateFormat.format(new Date(loggingEvent.timeStamp));
        } catch (Exception e) {
        }
        return dateTime;
    }

    protected String buildPriorityField(LoggingEvent loggingEvent) {
        if (loggingEvent == null) {
            return "";
        }

        // logging priority
        String priority = "?";
        try {
            priority = loggingEvent.level.toString().substring(0, 1);
        } catch (Exception e) {
        }
        return priority;
    }

    protected String buildClassNameField(LoggingEvent loggingEvent) {
        if (loggingEvent == null) {
            return "";
        }

        String loggerName = loggingEvent.getLoggerName();
        String classNameField;

        if (includeLineNumbers) {
                LocationInfo li = loggingEvent.getLocationInformation();
                classNameField = li.getClassName() + ":" + li.getLineNumber();
        } else {
            classNameField = loggerName;
        }

        return classNameField;
    }
}
