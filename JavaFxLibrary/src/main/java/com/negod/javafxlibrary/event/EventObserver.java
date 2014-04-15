/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.javafxlibrary.event;

/**
 *
 * @author Joakim
 */
public interface EventObserver extends EventRegistry {

    public void update(NegodEvent event);
}
