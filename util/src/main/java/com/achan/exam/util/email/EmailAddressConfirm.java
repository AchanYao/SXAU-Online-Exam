package com.achan.exam.util.email;

import org.xbill.DNS.*;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Achan
 * @since 2020/1/13 20:26
 */
public final class EmailAddressConfirm {

    static {
        try {
            InetAddress localhost = InetAddress.getLocalHost();
            DOMAIN = localhost.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            DOMAIN = "achanyao.com";
        }
    }

    private static String DOMAIN;

    /**
     *
     * @param to 需要确认的邮件地址
     * @return 不存在为false，存在为true
     * @throws TextParseException
     */
    public static boolean confirm(String to) throws TextParseException {
        if (to == null || to.isEmpty()) {
            return false;
        }
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(to);
        if (!matcher.matches()) {
            return false;
        }

        String host = to.substring(to.indexOf('@') + 1);

        Record[] mxRecords = new Lookup(host, Type.MX).run();
        if (mxRecords == null || mxRecords.length == 0) {
            return false;
        }

        String mxHost = ((MXRecord) mxRecords[0]).getTarget().toString();
        if (mxRecords.length > 1) {
            Arrays.sort(mxRecords, (o1, o2) -> {
                int i1 = ((MXRecord) o1).getPriority();
                int i2 = ((MXRecord) o2).getPriority();
                return i1 > i2 ? -1 : 1;
            });
            mxHost = ((MXRecord) mxRecords[0]).getTarget().toString();
        }

        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(mxHost, 25));
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            long timeout = 6000;
            int sleepSect = 50;
            // 连接(服务器是否就绪)
            if (getResponseCode(timeout, sleepSect, reader) != 220) {
                return false;
            }
            writer.write("HELO " + DOMAIN + "\r\n");
            writer.flush();
            if (getResponseCode(timeout, sleepSect, reader) != 250) {
                return false;
            }
            writer.write("MAIL FROM: <check@" + DOMAIN + ">\r\n");
            writer.flush();
            if (getResponseCode(timeout, sleepSect, reader) != 250) {
                return false;
            }
            // 验证
            writer.write("RCPT TO: <" + to + ">\r\n");
            writer.flush();
            if (getResponseCode(timeout, sleepSect, reader) != 250) {
                return false;
            }
            // 断开
            writer.write("QUIT\r\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    private static int getResponseCode(long timeout, int sleepSect, BufferedReader bufferedReader)
            throws NumberFormatException, IOException {
        int code = 0;
        for (long i = sleepSect; i < timeout; i += sleepSect) {
            try {
                Thread.sleep(sleepSect);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (bufferedReader.ready()) {
                String outline = bufferedReader.readLine();
                while (bufferedReader.ready()) {
                    bufferedReader.readLine();
                }
                code = Integer.parseInt(outline.substring(0, 3));
                break;
            }
        }
        return code;
    }
}
