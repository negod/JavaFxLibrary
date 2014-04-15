/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.javafxlibrary.commandpromt;

import com.negod.genericlibrary.dto.Dto;
import com.negod.javafxlibrary.NegodFx;
import com.negod.javafxlibrary.event.global.CmdExecuterEvent;
import com.negod.javafxlibrary.event.global.ProgressBarEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;

/**
 *
 * @author jojoha
 */
public class CmdExecuter {

    private static final String EXECUTING_COMMAND = "Executing command";
    private static final String EXECUTED_COMMAND_SUCCESSFUL = "Command executed successfully!";
    private static final String EXECUTED_COMMAND_FAILURE = "Command execution failed!";

    public enum CmdExecuterData {

        LINE;
    }

    public void ExecuteCommand(ProcessBuilder builder) {
        new CommandLineExecuter(builder).start();
    }

    public void ExecuteCommand(String command) {
        new CommandLineExecuter(getCommand(command)).start();
    }

    public void ExecuteCommand(String command, String path) {
        new CommandLineExecuter(getCommand(command, path)).start();
    }

    private ProcessBuilder getCommand(String command) {
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", command);
        return builder;
    }

    private ProcessBuilder getCommand(String command, String path) {
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", command);
        builder.directory(new File(path));
        return builder;
    }

    private class CommandLineExecuter extends Thread {

        ProcessBuilder builder;
        Dto<CmdExecuterData> cmdData = new Dto(CmdExecuterData.class);

        public CommandLineExecuter(ProcessBuilder builder) {
            this.builder = builder;
        }

        @Override
        public void run() {

            try {
                builder.redirectErrorStream(true);
                Process p = builder.start();
                BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));

                String line;
                while ((line = r.readLine()) != null) {
                    cmdData.set(CmdExecuterData.LINE, line);
                    if (cmdData.getValue(CmdExecuterData.LINE).isNull()) {
                        break;
                    }
                    notifyNewLine(line);
                }

                notifySuccess(EXECUTED_COMMAND_SUCCESSFUL);
            } catch (IOException ex) {
                notifyFailure(EXECUTED_COMMAND_FAILURE);
                Logger.getLogger(CmdExecuter.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    this.join();
                } catch (InterruptedException ex) {
                    Logger.getLogger(CommandLineExecuter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        private void notifySuccess(String message) {
            cmdData.set(CmdExecuterData.LINE, message);
            NegodFx.getInstance().getEvents().notifyObservers(CmdExecuterEvent.CMD_COMMAND_EXECUTED_SUCCESS, cmdData);
        }

        private void notifyFailure(String message) {
            cmdData.set(CmdExecuterData.LINE, message);
            NegodFx.getInstance().getEvents().notifyObservers(CmdExecuterEvent.CMD_COMMAND_EXECUTED_FAILED, cmdData);
        }

        private void notifyNewLine(String message) {
            cmdData.set(CmdExecuterData.LINE, message);
            NegodFx.getInstance().getEvents().notifyObservers(CmdExecuterEvent.NEW_LINE, cmdData);
        }
    }
}
