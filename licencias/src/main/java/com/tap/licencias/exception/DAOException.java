package com.tap.licencias.exception;

import javax.swing.*;

public class DAOException extends Exception {

    private final String H2DB = "H2";

    public DAOException(int errorCode, String source) {

        if (null == source) {
            // si el problema viene de una fuente que no esta aca, entonces:
            JOptionPane.showMessageDialog(null, "Se encontro un problema no definido.");
        } else {
            switch (source) {
                case H2DB: {
//                    try {
//                        // Si se esta usando la base de datos h2, entonces lo redirige a los exceptions
//                        // de h2
//                        throw new H2Exception(errorCode);
//                    } catch (H2Exception ex) {
//                        ex.printStackTrace();
//                    }
                }

                default:
                    // si usa una bd que no esta aca, entonces:
                    JOptionPane.showMessageDialog(null, "Se encontro un problema no definido.");
                    break;
            }
        }

    }

    public DAOException(String message) {
        super(message);
        JOptionPane.showMessageDialog(null, "HUBO UN PROBLEMA: " + message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
        JOptionPane.showMessageDialog(null, "ERROR: " + cause);
    }

    public DAOException(Throwable cause) {
        super(cause);
        JOptionPane.showMessageDialog(null, "ERROR: " + cause);
    }

    public DAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        JOptionPane.showMessageDialog(null, "ERROR: " + cause);
    }
}
