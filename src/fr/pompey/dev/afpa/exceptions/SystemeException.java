package fr.pompey.dev.afpa.exceptions;

/**
 * Exception levée lors d'une erreur système ou technique.
 * Par exemple, lorsqu'une action sur un achat ou une commande échoue pour une raison non liée à la saisie.
 */
public class SystemeException extends Exception {

    /**
     * Constructeur de l'exception SystemeException.
     *
     * @param message Le message décrivant l'erreur système.
     */
    public SystemeException(String message) {
        super(message);
    }

    /**
     * Constructeur de l'exception SystemeException avec cause.
     *
     * @param message Le message décrivant l'erreur système.
     * @param cause   La cause sous-jacente de l'exception.
     */
    public SystemeException(String message, Throwable cause) {
        super(message, cause);
    }
}
