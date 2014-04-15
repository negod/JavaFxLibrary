/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dialogs;

import com.negod.javafxlibrary.actions.NegodAction;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
public class NegodDialogs {

    public static void okDialog(NegodAction action) {

        Action response = Dialogs.create()
                .title(action.getTitle())
                .masthead("Just Checkin'")
                .message(action.getMessage())
                .showConfirm();

        if (response == Dialog.Actions.OK) {
            action.okAction();
        } else if (response == Dialog.Actions.NO) {
            action.noAction();
        } else if (response == Dialog.Actions.YES) {
            action.yesAction();
        } else if (response == Dialog.Actions.CANCEL) {
            action.cancelAction();
        }

    }

}
