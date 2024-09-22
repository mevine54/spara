package fr.pompey.dev.afpa.exceptions;

/**
 * Exception levée lors de la saisie incorrecte ou invalide d'une donnée.
 * Par exemple, lorsqu'un utilisateur saisit un champ incorrect (numéro de sécurité sociale, etc.).
 */
public class SaisieException extends Exception {

    /**
     * Constructeur de l'exception SaisieException.
     *
     * @param message Le message décrivant l'erreur de saisie.
     */
    public SaisieException(String message) {
        super(message);
    }

    /**
     * Constructeur de l'exception SaisieException avec cause.
     *
     * @param message Le message décrivant l'erreur de saisie.
     * @param cause   La cause sous-jacente de l'exception.
     */
    public SaisieException(String message, Throwable cause) {
        super(message, cause);
    }
}
