/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.javafxlibrary.event;

import com.negod.genericlibrary.dto.Dto;

/**
 *
 * @author Joakim
 */
public interface EventSubscriber {

    public void addObserver(EventObserver observer);

    public void removeObserver(EventObserver observer);

    public void notifyObservers(Enum event, Dto data);
}
