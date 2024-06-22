package org.coworking.dao;

import org.coworking.exceptions.ResourceNotFoundException;
import org.coworking.model.Workspace;

import java.util.List;


/**
 * Интерфейс для доступа к данным о рабочих пространствах (Workspace).
 */
public interface WorkspaceDao {

    /**
     * Добавляет новое рабочее пространство в хранилище.
     *
     * @param workspace объект рабочего пространства для добавления
     */
    void addWorkspace(Workspace workspace);

    /**
     * Обновляет информацию о рабочем пространстве в хранилище.
     *
     * @param workspace обновленный объект рабочего пространства
     * @throws ResourceNotFoundException если рабочее пространство не найдено в хранилище
     */
    void updateWorkspace(Workspace workspace) throws ResourceNotFoundException;

    /**
     * Удаляет рабочее пространство из хранилища по его идентификатору.
     *
     * @param id идентификатор рабочего пространства для удаления
     */
    void deleteWorkspace(int id);

    /**
     * Возвращает рабочее пространство из хранилища по его идентификатору.
     *
     * @param id идентификатор рабочего пространства
     * @return объект рабочего пространства, если найден, или {@code null}, если не найден
     */
    Workspace getWorkspaceById(int id);

    /**
     * Возвращает список всех рабочих пространств из хранилища.
     *
     * @return список всех рабочих пространств
     */
    List<Workspace> getAllWorkspaces();
}
