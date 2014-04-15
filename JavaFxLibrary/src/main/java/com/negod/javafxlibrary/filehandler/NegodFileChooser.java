/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.javafxlibrary.filehandler;

import com.negod.javafxlibrary.actions.NegodAction;
import dialogs.NegodDialogs;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
public class NegodFileChooser {

    private String rootFolder = "c://";

    public void changeRootFolder(String path) {
        rootFolder = path;
    }

    public File getFile() {
        try {
            FileChooser chooser = new FileChooser();
            chooser.setInitialDirectory(new File(rootFolder));

            File selectedFile = chooser.showOpenDialog(null);

            if (selectedFile.isFile()) {
                return selectedFile;
            } else {
                NegodDialogs.okDialog(new FileChooserDialog("Wrong type", "This is not a file!"));
            }
        } catch (Exception ex) {
            Logger.getLogger(NegodFileChooser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getFilePath() {
        try {
            FileChooser chooser = new FileChooser();
            chooser.setInitialDirectory(new File(rootFolder));

            File selectedFile = chooser.showOpenDialog(null);

            if (selectedFile.isFile()) {
                return selectedFile.getCanonicalPath();
            } else {
                NegodDialogs.okDialog(new FileChooserDialog("Wrong type", "This is not a file!"));
            }
        } catch (IOException ex) {
            Logger.getLogger(NegodFileChooser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public String getFolderPath() {
        try {
            DirectoryChooser chooser = new DirectoryChooser();
            chooser.setInitialDirectory(new File(rootFolder));

            File selectedFolder = chooser.showDialog(null);

            if (selectedFolder.isDirectory()) {
                return selectedFolder.getCanonicalPath();
            } else {
                NegodDialogs.okDialog(new FileChooserDialog("Wrong type", "This is not a folder!"));
            }

        } catch (IOException ex) {
            Logger.getLogger(NegodFileChooser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    private class FileChooserDialog implements NegodAction {

        String title;
        String message;

        public FileChooserDialog(String title, String message) {
            this.title = title;
            this.message = message;
        }

        @Override
        public void yesAction() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void noAction() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void cancelAction() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void okAction() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String getMessage() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String getTitle() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

}
