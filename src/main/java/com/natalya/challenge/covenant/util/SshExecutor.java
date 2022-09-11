package com.natalya.challenge.covenant.util;

import static java.util.Arrays.asList;

import com.google.common.io.CharStreams;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
public class SshExecutor {

    private final String hostName;
    private final String userName;
    private final String userPass;

    /**
     * Establish ssh connection by username and password
     *
     * @return Session
     * @throws JSchException
     */
    private Session getJschSession() throws JSchException {
        JSch jsch = new JSch();
        Session session = jsch.getSession(userName, hostName, 22);
        session.setConfig("StrictHostKeyChecking", "no");
        session.setConfig("PreferredAuthentications", "password");
        session.setPassword(userPass);
        session.connect();
        return session;
    }

    private void closeConnection(Channel channel, Session session) {
        if (channel != null && channel.isConnected()) {
            channel.disconnect();
        }

        if (session != null && session.isConnected()) {
            session.disconnect();
        }
    }

    public void runCommandViaSsh(String command) {
        log.info("Execute command: {}", command);
        ChannelExec channel = null;
        Session session = null;
        try {
            session = getJschSession();
            channel = (ChannelExec) session.openChannel("exec");

            channel.setCommand(command);
            channel.setInputStream(null);
            InputStream output = channel.getInputStream();
            channel.connect();

            var results = CharStreams.toString(new InputStreamReader(output)).split("\n");
            log.info("Execution log: {}", asList(results));
        } catch (JSchException | IOException e) {
            closeConnection(channel, session);
            throw new RuntimeException("Exception during running ssh command: ", e);
        }
    }

    /**
     * @param file       File to upload
     * @param targetPath path on remote machine, e.g. ./Documents/file.txt
     */
    public void uploadFileViaSftp(@NonNull File file, @NonNull String targetPath) {
        log.info("Upload file {} to the remote host {}", file.getName(), hostName);
        Session session = null;
        Channel channel = null;

        try {
            session = getJschSession();
            channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp) channel;
            sftpChannel.put(new FileInputStream(file), targetPath);
            log.info("File uploaded");
        } catch (SftpException | JSchException | FileNotFoundException e) {
            closeConnection(channel, session);
            throw new RuntimeException("Failed to transfer FTP file: ", e);
        }
    }
}
