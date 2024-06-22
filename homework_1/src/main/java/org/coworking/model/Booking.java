package org.coworking.model;

import java.time.LocalDateTime;


/**
 * Модель для представления бронирования ресурса.
 */
public class Booking {

    private int id;
    private int resourceId;
    private String resourceType;
    private String username;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Конструктор для создания объекта бронирования.
     *
     * @param id           уникальный идентификатор бронирования
     * @param resourceId   идентификатор ресурса, который забронирован
     * @param resourceType тип ресурса (например, Workspace или ConferenceRoom)
     * @param username     имя пользователя, который забронировал ресурс
     * @param startTime    время начала бронирования
     * @param endTime      время окончания бронирования
     */
    public Booking(int id, int resourceId, String resourceType, String username, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.resourceId = resourceId;
        this.resourceType = resourceType;
        this.username = username;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Пустой конструктор по умолчанию.
     * Используется при необходимости создания объекта без параметров.
     */
    public Booking() {
    }

    /**
     * Получить уникальный идентификатор бронирования.
     *
     * @return уникальный идентификатор бронирования
     */
    public int getId() {
        return id;
    }

    /**
     * Установить уникальный идентификатор бронирования.
     *
     * @param id уникальный идентификатор бронирования
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Получить идентификатор ресурса, который забронирован.
     *
     * @return идентификатор ресурса
     */
    public int getResourceId() {
        return resourceId;
    }

    /**
     * Установить идентификатор ресурса, который забронирован.
     *
     * @param resourceId идентификатор ресурса
     */
    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    /**
     * Получить тип ресурса.
     *
     * @return тип ресурса
     */
    public String getResourceType() {
        return resourceType;
    }

    /**
     * Установить тип ресурса.
     *
     * @param resourceType тип ресурса
     */
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    /**
     * Получить имя пользователя, который забронировал ресурс.
     *
     * @return имя пользователя
     */
    public String getUsername() {
        return username;
    }

    /**
     * Установить имя пользователя, который забронировал ресурс.
     *
     * @param username имя пользователя
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Получить время начала бронирования.
     *
     * @return время начала бронирования
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Установить время начала бронирования.
     *
     * @param startTime время начала бронирования
     */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Получить время окончания бронирования.
     *
     * @return время окончания бронирования
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Установить время окончания бронирования.
     *
     * @param endTime время окончания бронирования
     */
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
