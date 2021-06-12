package gamelogic;

/**
 * HitNotifyer.
 * a class that can notify when its being hit
 * @author itamar shachenc tov
 * @since 2021-04-28
 * id: 207497769
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     *
     * @param hl the listener we want to add
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl the listener we want to remove
     */
    void removeHitListener(HitListener hl);
}