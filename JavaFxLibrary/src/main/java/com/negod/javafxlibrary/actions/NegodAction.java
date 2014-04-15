/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.javafxlibrary.actions;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
public interface NegodAction {

    void yesAction();

    void noAction();

    void cancelAction();
    
    void okAction();
    
    String getMessage();
    
    String getTitle();
    
}
