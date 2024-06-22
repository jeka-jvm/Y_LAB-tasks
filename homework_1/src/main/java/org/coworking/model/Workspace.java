package org.coworking.model;

import java.util.Objects;


/**
 * Представляет рабочее пространство с уникальным идентификатором, именем и доступностью.
 */
public class Workspace {

    private int id;
    private String name;
    private boolean available;

    /**
     * Конструирует новое рабочее пространство с заданным идентификатором, именем и доступностью.
     *
     * @param id уникальный идентификатор рабочего пространства
     * @param name имя рабочего пространства
     * @param available доступность рабочего пространства (true - доступно, false - недоступно)
     */
    public Workspace(int id, String name, boolean available) {
        this.id = id;
        this.name = name;
        this.available = available;
    }

    /**
     * Конструирует новое рабочее пространство с заданным идентификатором и именем.
     * Доступность устанавливается по умолчанию в значение true.
     *
     * @param id уникальный идентификатор рабочего пространства
     * @param name имя рабочего пространства
     */
    public Workspace(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Возвращает уникальный идентификатор рабочего пространства.
     *
     * @return уникальный идентификатор рабочего пространства
     */
    public int getId() {
        return id;
    }

    /**
     * Устанавливает уникальный идентификатор рабочего пространства.
     *
     * @param id уникальный идентификатор для установки
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Возвращает имя рабочего пространства.
     *
     * @return имя рабочего пространства
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает имя рабочего пространства.
     *
     * @param name имя для установки
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Проверяет доступность рабочего пространства.
     *
     * @return true, если рабочее пространство доступно, false в противном случае
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Устанавливает доступность рабочего пространства.
     *
     * @param available доступность для установки (true - доступно, false - недоступно)
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * Переопределение метода equals для сравнения рабочих пространств по идентификатору, имени и доступности.
     *
     * @param o объект для сравнения
     * @return true, если объекты равны, false в противном случае
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Workspace workspace = (Workspace) o;
        return id == workspace.id &&
                available == workspace.available &&
                Objects.equals(name, workspace.name);
    }

    /**
     * Переопределение метода hashCode для рабочих пространств.
     *
     * @return хеш-код объекта
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, available);
    }

}

