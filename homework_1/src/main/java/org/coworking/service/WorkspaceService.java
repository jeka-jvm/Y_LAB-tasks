package org.coworking.service;

import org.coworking.exceptions.ResourceNotFoundException;
import org.coworking.model.Workspace;

import java.util.List;


/**
 * Интерфейс сервиса для управления рабочими пространствами.
 */
public interface WorkspaceService {

    /**
     * Добавляет новое рабочее пространство с указанным именем и доступностью.
     *
     * @param name      имя рабочего пространства
     * @param available доступность рабочего пространства (true - доступно, false - не доступно)
     */
    void addWorkspace(String name, boolean available);

    /**
     * Обновляет данные существующего рабочего пространства по его идентификатору.
     *
     * @param id        идентификатор рабочего пространства для обновления
     * @param name      новое имя рабочего пространства
     * @param available новое значение доступности рабочего пространства (true - доступно, false - не доступно)
     * @throws ResourceNotFoundException если рабочее пространство с указанным идентификатором не найдено
     */
    void updateWorkspace(int id, String name, boolean available) throws ResourceNotFoundException;

    /**
     * Удаляет рабочее пространство по его идентификатору.
     *
     * @param id идентификатор рабочего пространства для удаления
     * @throws ResourceNotFoundException если рабочее пространство с указанным идентификатором не найдено
     */
    void deleteWorkspace(int id) throws ResourceNotFoundException;

    /**
     * Получает список всех рабочих пространств.
     *
     * @return список всех рабочих пространств
     */
    List<Workspace> getAllWorkspaces();

}
