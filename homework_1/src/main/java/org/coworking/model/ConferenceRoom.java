package org.coworking.model;


/**
 * Представляет конференц-зал в системе управления рабочим пространством.
 */
public class ConferenceRoom {

    private int id;
    private String name;
    private boolean available;

    /**
     * Конструирует новый объект ConferenceRoom с указанными параметрами.
     *
     * @param id        уникальный идентификатор конференц-зала
     * @param name      название конференц-зала
     * @param available {@code true}, если конференц-зал доступен; {@code false} в противном случае
     */
    public ConferenceRoom(int id, String name, boolean available) {
        this.id = id;
        this.name = name;
        this.available = available;
    }

    /**
     * Возвращает уникальный идентификатор конференц-зала.
     *
     * @return уникальный идентификатор
     */
    public int getId() {
        return id;
    }

    /**
     * Устанавливает уникальный идентификатор конференц-зала.
     *
     * @param id уникальный идентификатор для установки
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Возвращает название конференц-зала.
     *
     * @return название конференц-зала
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает название конференц-зала.
     *
     * @param name название для установки
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Проверяет доступность конференц-зала.
     *
     * @return {@code true}, если конференц-зал доступен; {@code false} в противном случае
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Устанавливает статус доступности конференц-зала.
     *
     * @param available {@code true}, если конференц-зал доступен; {@code false} в противном случае
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }
}
