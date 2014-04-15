/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.javafxlibrary.filehandler;

import com.negod.genericlibrary.dto.Dto;
import com.negod.javafxlibrary.NegodFx;
import com.negod.javafxlibrary.event.global.FileCopyEvent;
import com.negod.javafxlibrary.event.global.ProgressBarEvent;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author jojoha
 */
public class FileCopyHandler {

    private static final String COPYING_FILE = "Copying file";

    public enum FileCopyData {

        PATH_FROM, PATH_TO, MESSAGE;
    }

    public FileCopyHandler() {
    }

    private class FileCopy extends Thread {

        String fileName;
        Dto<FileCopyData> fileCopyData;

        public FileCopy(Dto<FileCopyData> fileCopyData) {
            this.fileCopyData = fileCopyData;
        }

        private void notifyFileCopy(String message) {
            fileCopyData.set(FileCopyData.MESSAGE, message);
            NegodFx.getInstance().getEvents().notifyObservers(FileCopyEvent.FILE_COPIED, fileCopyData);
        }

        @Override
        public void run() {

            try {
                File sourceFile = new File(fileCopyData.<String>get(FileCopyData.PATH_FROM));
                File destinationFile = new File(fileCopyData.<String>get(FileCopyData.PATH_TO));

                this.fileName = sourceFile.getName();
                FileUtils.copyFileToDirectory(sourceFile, destinationFile);

                notifyFileCopy(fileName + " successfully copied!");

            } catch (IOException ex) {
                notifyFileCopy(fileName + " could not be copied!");
                Logger.getLogger(FileCopyHandler.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    this.join();
                } catch (InterruptedException ex) {
                    Logger.getLogger(FileCopyHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void copyFile(Dto<FileCopyData> fileCopyData) {
        new FileCopy(fileCopyData).start();
    }
}
