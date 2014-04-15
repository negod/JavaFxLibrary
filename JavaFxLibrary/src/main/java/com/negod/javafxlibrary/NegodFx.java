/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.javafxlibrary;

import com.negod.genericlibrary.xml.XmlFileHandler;
import com.negod.javafxlibrary.commandpromt.CmdExecuter;
import com.negod.javafxlibrary.event.EventController;
import com.negod.javafxlibrary.filehandler.FileUnZipper;
import com.negod.javafxlibrary.filehandler.NegodFileChooser;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
public class NegodFx {

    private static NegodFileChooser fileChooser;
    private static XmlFileHandler xmlFileHandler;
    private static CmdExecuter cmdExecuter;
    private static FileUnZipper fileUnZipper;
    private static EventController eventController;

    private static final NegodFx instance = new NegodFx();

    public static NegodFx getInstance() {
        return instance;
    }

    private NegodFx() {
        fileChooser = new NegodFileChooser();
        xmlFileHandler = new XmlFileHandler();
        cmdExecuter = new CmdExecuter();
        fileUnZipper = new FileUnZipper();
        eventController = new EventController();
    }

    public static NegodFileChooser getFileChooser() {
        return fileChooser;
    }

    public static XmlFileHandler getXmlFileHandler() {
        return xmlFileHandler;
    }

    public static CmdExecuter getCmdExecuter() {
        return cmdExecuter;
    }

    public static FileUnZipper getFileUnZipper() {
        return fileUnZipper;
    }

    public static EventController getEvents() {
        return eventController;
    }

}
