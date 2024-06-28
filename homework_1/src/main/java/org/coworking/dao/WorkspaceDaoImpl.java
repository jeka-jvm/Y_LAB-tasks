package org.coworking.dao;

import org.coworking.exceptions.ResourceNotFoundException;
import org.coworking.model.Workspace;

import java.util.ArrayList;
import java.util.List;


/**
 * Реализация интерфейса WorkspaceDao, предоставляющая доступ к данным о рабочих пространствах.
 */
public class WorkspaceDaoImpl implements WorkspaceDao {

    private final List<Workspace> workspaces = new ArrayList<>();

    /**
     * Добавляет новое рабочее пространство в хранилище.
     *
     * @param workspace объект рабочего пространства для добавления
     */
    @Override
    public void addWorkspace(Workspace workspace) {
        workspaces.add(workspace);
    }

    /**
     * Обновляет информацию о рабочем пространстве в хранилище.
     *
     * @param updatedWorkspace обновленный объект рабочего пространства
     * @throws ResourceNotFoundException если рабочее пространство не найдено в хранилище
     */
    @Override
    public void updateWorkspace(Workspace updatedWorkspace) throws ResourceNotFoundException {
        for (Workspace workspace : workspaces) {
            if (workspace.getId() == updatedWorkspace.getId()) {
                workspace.setName(updatedWorkspace.getName());
                workspace.setAvailable(updatedWorkspace.isAvailable());
                return;
            }
        }
        throw new ResourceNotFoundException("Workspace not found");
    }

    /**
     * Удаляет рабочее пространство из хранилища по его идентификатору.
     *
     * @param id идентификатор рабочего пространства для удаления
     */
    @Override
    public void deleteWorkspace(int id) {
        workspaces.removeIf(workspace -> workspace.getId() == id);
    }

    /**
     * Возвращает рабочее пространство из хранилища по его идентификатору.
     *
     * @param id идентификатор рабочего пространства
     * @return объект рабочего пространства, если найден, или {@code null}, если не найден
     */
    @Override
    public Workspace getWorkspaceById(int id) {
        for (Workspace workspace : workspaces) {
            if (workspace.getId() == id) {
                return workspace;
            }
        }
        return null;
    }

    /**
     * Возвращает список всех рабочих пространств из хранилища.
     *
     * @return список всех рабочих пространств
     */
    @Override
    public List<Workspace> getAllWorkspaces() {
        return new ArrayList<>(workspaces);
    }
}
